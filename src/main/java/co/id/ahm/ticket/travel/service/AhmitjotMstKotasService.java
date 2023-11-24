package co.id.ahm.ticket.travel.service;

import co.id.ahm.ticket.travel.helper.Helpers;
import co.id.ahm.ticket.travel.helper.ResponseDtoHelper;
import co.id.ahm.ticket.travel.dao.AhmitjotMstkotasDao;
import co.id.ahm.ticket.travel.dto.KotaRequest;
import co.id.ahm.ticket.travel.dto.ResponseDto;
import co.id.ahm.ticket.travel.dto.ResponsePageableDto;
import co.id.ahm.ticket.travel.exception.ClientException;
import co.id.ahm.ticket.travel.exception.NotFoundException;
import co.id.ahm.ticket.travel.helper.ResponsePageableDtoHelper;
import co.id.ahm.ticket.travel.model.AhmitjotMstkotas;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
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
public class AhmitjotMstKotasService {


    private final AhmitjotMstkotasDao ahmitjotMstkotasDao;

    public ResponseDto postKota(AhmitjotMstkotas kota) {

        AhmitjotMstkotas createdKota = ahmitjotMstkotasDao.save(AhmitjotMstkotas.builder()
                .vcode(kota.getVcode())
                .vstatus(kota.getVstatus())
                .vasal(kota.getVasal())
                .vtujuan(kota.getVtujuan())
                .build()
        );

        return ResponseDtoHelper.ok(Collections.singletonList(createdKota));
    }

    public ResponsePageableDto getAllKotas(KotaRequest kotaRequest) {
        Sort sort = Sort.by(Helpers.setOrderBy(kotaRequest.getOrder()), setSort(kotaRequest.getSort()));
        Pageable pageable = PageRequest.of(kotaRequest.getOffset(), kotaRequest.getLimit(), sort);
        Page<AhmitjotMstkotas> page = ahmitjotMstkotasDao.findKotas(kotaRequest.getKeyword(), pageable);

        return ResponsePageableDtoHelper.ok(page);
    }

    public ResponseDto getKota(String destinationCode) {
        destinationCode = Objects.toString(destinationCode, "").toUpperCase();
        Optional<AhmitjotMstkotas> kota = ahmitjotMstkotasDao.findByVcode(destinationCode);
        return ResponseDtoHelper.ok(Collections.singletonList(kota.get()));
    }

    public ResponseDto deleteKota(String destinationCode) {
        AhmitjotMstkotas kota = ahmitjotMstkotasDao.findByVcode(destinationCode)
                .orElseThrow(() -> new ClientException("Kode tujuan tidak valid"));
        ahmitjotMstkotasDao.delete(kota);
        return ResponseDtoHelper.ok();
    }

    public ResponseDto putKota(AhmitjotMstkotas updateRequest) {
        if (!ahmitjotMstkotasDao.existsById(updateRequest.getNid())) {
            throw new NotFoundException("Data kota tidak ditemukan");
        }

        AhmitjotMstkotas kota = ahmitjotMstkotasDao.save(updateRequest);

        if (Objects.isNull(kota)) {
            return ResponseDtoHelper.fail();
        }

        return ResponseDtoHelper.ok(Collections.singletonList(kota));
    }

    private static String setSort(String sort) {
        Map<String, String> MAPPED_FIELDS = new HashMap<>();
        MAPPED_FIELDS.put("id", "nid");
        MAPPED_FIELDS.put("kodeTujuan", "vcode");
        MAPPED_FIELDS.put("kotaAsal", "vasal");
        MAPPED_FIELDS.put("kotaTujuan", "vtujuan");
        MAPPED_FIELDS.put("status", "vstatus");

        return Helpers.setSort(sort, "id", MAPPED_FIELDS);
    }
}
