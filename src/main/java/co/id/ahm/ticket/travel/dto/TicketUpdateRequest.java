package co.id.ahm.ticket.travel.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TicketUpdateRequest {

    private String noTicket;

    @JsonAlias("nik")
    @Size(min = 16, max = 16, message = "NIK tidak boleh kurang atau lebih dari 16 karakter")
    @NotBlank(message = "NIK harus diisi.")
    private String vnik;

    @JsonAlias("tanggalBerangkat")
    @NotNull(message = "Tanggal Keberangkatan harus diisi.")
    private LocalDate dberangkat;

    @JsonAlias("kodeTujuan")
    @NotBlank(message = "Kode Tujuan harus diisi.")
    private String vcode;

    @JsonAlias("nomorKursi")
    @NotNull(message = "Nomor kursi harus diisi.")
    @Min(value = 1, message = "Nomor kursi tidak boleh kurang dari 1")
    @Max(value = 5, message = "Nomor kursi tidak boleh lebih dari 5")
    private Long nkursi;
}
