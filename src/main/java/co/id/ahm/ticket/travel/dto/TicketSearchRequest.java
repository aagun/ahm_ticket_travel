package co.id.ahm.ticket.travel.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketSearchRequest {

    @NotBlank(message = "Kata Kunci harus diisi.")
    private String keyword;

    @JsonAlias("startTanggalPembelian")
    @NotNull(message = "Start Tanggal Pembelian harus diisi.")
    private LocalDate dStartBeli;

    @JsonAlias("endTanggalPembelian")
    @NotNull(message = "End Tanggal Pembelian harus diisi.")
    private LocalDate dEndBeli;

    private Integer limit;

    private Integer offset;

    private String order;

    private String sort;
}
