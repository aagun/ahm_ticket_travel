package co.id.ahm.ticket.travel.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class KotaRequest {

    @JsonAlias("keyword")
    private String keyword;

    private Integer limit;

    private Integer offset;

    private String order;

    private String sort;
}
