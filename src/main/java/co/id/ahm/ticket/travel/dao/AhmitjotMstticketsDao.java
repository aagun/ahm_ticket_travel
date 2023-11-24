package co.id.ahm.ticket.travel.dao;

import co.id.ahm.ticket.travel.model.AhmitjotMsttickets;
import co.id.ahm.ticket.travel.model.AhmitjotMstticketsPk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface AhmitjotMstticketsDao
        extends JpaRepository<AhmitjotMsttickets, AhmitjotMstticketsPk> {

    Page<AhmitjotMsttickets> findByDbeliIsGreaterThanEqualAndDbeliIsLessThanEqualAndVnikContainingIgnoreCaseOrVnamaContainingIgnoreCase(LocalDate startDate, LocalDate endDate, String vnik, String vnama, Pageable page);

    Integer deleteByAhmitjotMstticketsPk_Vnoticket(String noticket);

    boolean existsByAhmitjotMstticketsPk_Vnoticket(String noticket);

    Optional<AhmitjotMsttickets> findByVcodeAndDbeliAndVnik(String vcode, LocalDate now, String vnik);

    Optional<AhmitjotMsttickets> findByAhmitjotMstticketsPk_Vnoticket(String noTicket);
}
