package co.id.ahm.ticket.travel.dto;

import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePageableDto<T> {
    private Integer status;
    private String message;
    private List<T> data = Collections.emptyList();
    private Integer totalPages = 0;
    private Long totalItems = 0L;
}
