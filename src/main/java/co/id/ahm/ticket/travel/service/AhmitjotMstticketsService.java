package co.id.ahm.ticket.travel.service;

import co.id.ahm.ticket.travel.dao.AhmitjotMstkotasDao;
import co.id.ahm.ticket.travel.dao.AhmitjotMstticketsDao;
import co.id.ahm.ticket.travel.dto.ResponseDto;
import co.id.ahm.ticket.travel.dto.ResponsePageableDto;
import co.id.ahm.ticket.travel.dto.TicketRequest;
import co.id.ahm.ticket.travel.dto.TicketSearchRequest;
import co.id.ahm.ticket.travel.dto.TicketUpdateRequest;
import co.id.ahm.ticket.travel.exception.ClientException;
import co.id.ahm.ticket.travel.helper.Helpers;
import co.id.ahm.ticket.travel.helper.ResponseDtoHelper;
import co.id.ahm.ticket.travel.helper.ResponsePageableDtoHelper;
import co.id.ahm.ticket.travel.model.AhmitjotMstkotas;
import co.id.ahm.ticket.travel.model.AhmitjotMsttickets;
import co.id.ahm.ticket.travel.model.AhmitjotMstticketsPk;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class AhmitjotMstticketsService {

    private final AhmitjotMstticketsDao ahmitjotMstticketsDao;

    private final AhmitjotMstkotasDao ahmitjotMstkotasDao;

    public ResponsePageableDto getAllTickets(TicketSearchRequest ticketSearchRequest) {

        if (ticketSearchRequest.getDEndBeli().isBefore(ticketSearchRequest.getDStartBeli())) {
            throw new ClientException("End Tanggal Pembelian tidak boleh kurang dari Start Tanggal Pembelian.");
        }

        log.info(ticketSearchRequest.getKeyword().toUpperCase());

        Sort sort = Sort.by(Helpers.setOrderBy(ticketSearchRequest.getOrder()), setSort(ticketSearchRequest.getSort()));
        Pageable pageable = PageRequest.of(ticketSearchRequest.getOffset(), ticketSearchRequest.getLimit(), sort);
        Page<AhmitjotMsttickets> page = ahmitjotMstticketsDao
                .findTicketsByDateAndKeyword(
                        ticketSearchRequest.getDStartBeli(),
                        ticketSearchRequest.getDEndBeli(),
                        ticketSearchRequest.getKeyword(),
                        pageable
                );

        return ResponsePageableDtoHelper.ok(page);
    }

    public ResponseDto getTicket(String noticket) {
        AhmitjotMsttickets ticket = ahmitjotMstticketsDao
                .findByAhmitjotMstticketsPk_Vnoticket(noticket)
                .orElseThrow(() -> new ClientException(String.format(
                        "Nomor Ticket %s tidak valid.", noticket)));

        return ResponseDtoHelper.ok(Collections.singletonList(ticket));
    }

    public ResponseDto putTicket(TicketUpdateRequest updateRequest) {
        AhmitjotMsttickets ticket = ahmitjotMstticketsDao
                .findByAhmitjotMstticketsPk_Vnoticket(updateRequest.getNoTicket())
                .orElseThrow(() -> new ClientException(String.format(
                        "Nomor Ticket %s tidak valid.", updateRequest.getNoTicket())));

        validateDepartureDate(updateRequest.getDberangkat());

        AhmitjotMstkotas kota = getDetailCity(updateRequest.getVcode());

        ticket.setDberangkat(updateRequest.getDberangkat());
        ticket.setNkursi(updateRequest.getNkursi());
        ticket.setVcode(kota.getVcode());
        ticket.setVasal(kota.getVasal());
        ticket.setVtujuan(kota.getVtujuan());

        AhmitjotMsttickets data = ahmitjotMstticketsDao.save(ticket);

        if (data == null) {
            return ResponseDtoHelper.fail();
        }

        return ResponseDtoHelper.ok();
    }

    public ResponseDto deleteTicket(String noticket) {

        validateTicket(noticket);

        if (ahmitjotMstticketsDao.deleteByAhmitjotMstticketsPk_Vnoticket(noticket) == 0) {
            return ResponseDtoHelper.fail();
        }

        return ResponseDtoHelper.ok();
    }

    public ResponseDto postTicket(TicketRequest ticketRequest) {

        validateNik(ticketRequest.getVnik());

        validateDepartureDate(ticketRequest.getDberangkat());

        AhmitjotMstkotas kota = getDetailCity(ticketRequest.getVcode());

        validateLimitTransaction(ticketRequest.getVnik(), ticketRequest.getVcode());

        validateGender(ticketRequest.getVgender());

        AhmitjotMsttickets ticket = AhmitjotMsttickets.builder()
                .vnik(ticketRequest.getVnik())
                .dbeli(LocalDate.now())
                .dberangkat(ticketRequest.getDberangkat())
                .nkursi(ticketRequest.getNkursi())
                .vnama(ticketRequest.getVnama().toUpperCase())
                .vcode(ticketRequest.getVcode())
                .vgender(ticketRequest.getVgender().toUpperCase()).build();

        ticket.setAhmitjotMstticketsPk(generateKeys(ticket));
        ticket.setVcode(kota.getVcode());
        ticket.setVasal(kota.getVasal());
        ticket.setVtujuan(kota.getVtujuan());

        ahmitjotMstticketsDao.save(ticket);

        return ResponseDtoHelper.ok();

    }

    private AhmitjotMstkotas getDetailCity(String destinationCode) {
        AhmitjotMstkotas kota = ahmitjotMstkotasDao.findByVcodeAndVstatus(destinationCode, "T").orElseThrow(() -> new ClientException("Kode Tujuan tidak valid."));
        return kota;
    }

    private void validateLimitTransaction(String nik, String destinationCode) {
        ahmitjotMstticketsDao.findByVcodeAndDbeliAndVnik(destinationCode, LocalDate.now(), nik).ifPresent((ticket) -> {
            throw new ClientException(String.format("User dengan NIK %s Transaksi sudah melebihi batas maksimal.", ticket.getVnik()));
        });
    }

    private void validateTicket(String noticket) {
        if (!ahmitjotMstticketsDao.existsByAhmitjotMstticketsPk_Vnoticket(noticket)) {
            throw new ClientException(String.format("Nomor Ticket %s tidak valid", noticket));
        }
    }

    private static String setSort(String sort) {
        Map<String, String> MAPPED_FIELD = new HashMap<String, String>();
        MAPPED_FIELD.put("id", "AhmitjotMstticketsPk.vkey");
        MAPPED_FIELD.put("noticket", "AhmitjotMstticketsPk.vnoticket");
        MAPPED_FIELD.put("nik", "vnik");
        MAPPED_FIELD.put("nama", "vnama");
        MAPPED_FIELD.put("gender", "vgender");
        MAPPED_FIELD.put("tanggalPembelian", "dbeli");
        MAPPED_FIELD.put("tanggalBerangkat", "dberangkat");
        MAPPED_FIELD.put("kodeTujuan", "vcode");
        MAPPED_FIELD.put("kotaAsal", "vasal");
        MAPPED_FIELD.put("kotaTujuan", "vtujuan");
        MAPPED_FIELD.put("nomorKursi", "nkursi");

        return Helpers.setSort(sort, "id", MAPPED_FIELD);
    }

    private static String generateTicketNumber(String nik, String unique) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddss");
        String date = LocalDateTime.now().format(formatter);
        return String.format("%s-%s-%s", nik, unique, date);
    }

    private static String generateKey() {
        return String.format("AGN%d", generateRandomNumber());
    }

    private static Integer generateRandomNumber() {
        Random random = new Random();
        return 10000 + random.nextInt(99999);
    }

    private static AhmitjotMstticketsPk generateKeys(AhmitjotMsttickets ticket) {
        String key = generateKey();
        String noTicket = generateTicketNumber(ticket.getVnik(), key);
        return AhmitjotMstticketsPk.builder().vkey(key).vnoticket(noTicket).build();
    }

    private static void validateNik(String nik) {
        try {
            Long.valueOf(nik);
        } catch (NumberFormatException ex) {
            throw new ClientException("NIK harus bertipe number");
        }
    }

    private static void validateDepartureDate(LocalDate departureDate) {
        LocalDate today = LocalDate.now();
        if (departureDate.equals(today) || departureDate.isBefore(today)) {
            throw new ClientException("Tanggal Keberangkatan tidak boleh sama dengan atau kurang dari hari ini.");
        }
    }

    private static void validateGender(String gender) {
        if (!Arrays.asList("p", "l").contains(gender.toLowerCase())) {
            throw new ClientException("Genders harus disini L atau P");
        }
    }
}
