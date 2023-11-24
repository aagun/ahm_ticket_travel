package co.id.ahm.ticket.travel.dao;

import co.id.ahm.ticket.travel.model.AhmitjotMstkotas;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AhmitjotMstkotasDao extends JpaRepository<AhmitjotMstkotas, Long> {

    @Query(nativeQuery = true, value = "SELECT *\n" +
            "FROM AHMITJOT_MSTKOTAS\n" +
            "WHERE\n" +
            "    VSTATUS = 'T'\n" +
            "    AND (\n" +
            "            UPPER(VCODE) LIKE UPPER(CONCAT('%', :KEYWORD, '%'))\n" +
            "            OR UPPER(VASAL) LIKE UPPER(CONCAT('%', :KEYWORD, '%'))\n" +
            "            OR UPPER(VTUJUAN) LIKE UPPER(CONCAT('%', :KEYWORD, '%'))\n" +
            "        )")
    Page<AhmitjotMstkotas> findKotas(@Param("KEYWORD") String keyword, Pageable page);

    Optional<AhmitjotMstkotas> findByVcodeAndVstatus(String vcode, String vstatus);

    Optional<AhmitjotMstkotas> findByVcode(String destinationCode);

}
