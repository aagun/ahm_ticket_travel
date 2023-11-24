package co.id.ahm.ticket.travel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Embeddable
public class AhmitjotMstticketsPk implements Serializable {
    @Column(name = "vkey", length = 32, nullable = false)
    @JsonProperty("id")
    private String vkey;

    @Column(name = "vnoticket", length = 64, nullable = false)
    @JsonProperty("nomorTicket")
    private String vnoticket;
}
