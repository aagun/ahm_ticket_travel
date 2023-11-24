package co.id.ahm.ticket.travel.dao;

import co.id.ahm.ticket.travel.model.AhmitjotMsttickets;
import co.id.ahm.ticket.travel.model.AhmitjotMstticketsPk;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AhmitjotMstticketsDao
        extends JpaRepository<AhmitjotMsttickets, AhmitjotMstticketsPk> {

    @Query(value = "SELECT a FROM AhmitjotMsttickets a WHERE a.DBELI BETWEEN :dStart AND :dEnd AND (UPPER(a.VNIK) LIKE UPPER(CONCAT('%', :keyword, '%')) OR UPPER(a.VNAMA) LIKE UPPER(CONCAT('%', :keyword, '%')))")
    Page<AhmitjotMsttickets> findTicketsByDateAndKeyword(@Param("dStart") LocalDate dStart, @Param("dEnd") LocalDate dEnd, @Param("keyword") String keyword, Pageable page);

    Page<AhmitjotMsttickets> findAllByDbeliGreaterThanEqualAndDbeliLessThanEqualOrVnikContainingIgnoreCaseOrVnamaContainingIgnoreCase(LocalDate start, LocalDate end, String vnik, String vnama, Pageable page);

    Integer deleteByAhmitjotMstticketsPk_Vnoticket (String noticket);

    boolean existsByAhmitjotMstticketsPk_Vnoticket (String noticket);

    Optional<AhmitjotMsttickets> findByVcodeAndDbeliAndVnik(String vcode, LocalDate now, String vnik);

    Optional<AhmitjotMsttickets> findByAhmitjotMstticketsPk_Vnoticket(String noTicket);
}
