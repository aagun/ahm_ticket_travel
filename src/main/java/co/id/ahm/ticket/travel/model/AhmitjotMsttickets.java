package co.id.ahm.ticket.travel.model;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonMerge;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonView;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "AHMITJOT_MSTTICKETS")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AhmitjotMsttickets implements Serializable {

    @EmbeddedId
    @JsonUnwrapped
    private AhmitjotMstticketsPk ahmitjotMstticketsPk;

    @Column(name = "vnik", length = 16, nullable = false)
    @JsonAlias("nik") @JsonProperty("nik")
    private String vnik;

    @Column(name = "vnama", length = 100, nullable = false)
    @JsonAlias("nama") @JsonProperty("nama")
    private String vnama;

    @Column(name = "vgender", length = 12, nullable = false)
    @JsonAlias("gender") @JsonProperty("gender")
    private String vgender;

    @Column(name = "dbeli")
    @JsonAlias("tanggalPembelian") @JsonProperty("tanggalPembelian")
    private LocalDate dbeli;

    @Column(name = "dberangkat")
    @JsonAlias("tanggalBerangkat") @JsonProperty("tanggalBerangkat")
    private LocalDate dberangkat;

    @Column(name = "vcode", length = 64, nullable = false)
    @JsonAlias("kodeTujuan") @JsonProperty("kodeTujuan")
    private String vcode;

    @Column(name = "vasal", length = 64, nullable = false)
    @JsonAlias("kotaAsal") @JsonProperty("kotaAsal")
    private String vasal;

    @Column(name = "vtujuan", nullable = false)
    @JsonAlias("kotaTujuan") @JsonProperty("kotaTujuan")
    private String vtujuan;

    @Column(name = "nkursi")
    @JsonAlias("nomorKursi") @JsonProperty("nomorKursi")
    private Long nkursi;

}
