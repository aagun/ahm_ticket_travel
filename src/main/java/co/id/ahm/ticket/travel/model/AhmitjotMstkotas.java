package co.id.ahm.ticket.travel.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "AHMITJOT_MSTKOTAS")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AhmitjotMstkotas {

    @Id
    @SequenceGenerator(sequenceName = "ahmitjot_mstkotas_seq", name = "ahmitjot_mstkotas_gen", allocationSize = 1)
    @GeneratedValue(generator = "ahmitjot_mstkotas_gen", strategy = GenerationType.SEQUENCE)
    @JsonProperty("id") @JsonAlias("id")
    private Long nid;

    @Column(name = "vcode", length = 64, nullable = false)
    @JsonProperty("kodeTujuan") @JsonAlias("kodeTujuan")
    @NotBlank(message = "Kode Tujuan harus diisi.")
    private String vcode;

    @Column(name = "vasal", length = 64, nullable = false)
    @JsonProperty("kotaAsal") @JsonAlias("kotaAsal")
    @NotBlank(message = "Kota Asal harus diisi.")
    private String vasal;

    @Column(name = "vtujuan", nullable = false)
    @JsonProperty("kotaTujuan") @JsonAlias("kotaTujuan")
    @NotBlank(message = "Kota Tujuan harus diisi.")
    private String vtujuan;

    @Column(name = "vstatus", nullable = false)
    @JsonProperty("status") @JsonAlias("status")
    @NotBlank(message = "Status harus diisi.")
    private String vstatus;
}
