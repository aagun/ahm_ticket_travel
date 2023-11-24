package co.id.ahm.ticket.travel.controller;

import co.id.ahm.ticket.travel.dto.KotaRequest;
import co.id.ahm.ticket.travel.dto.ResponsePageableDto;
import co.id.ahm.ticket.travel.model.AhmitjotMstkotas;
import co.id.ahm.ticket.travel.service.AhmitjotMstKotasService;
import co.id.ahm.ticket.travel.dto.ResponseDto;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kotas")
@AllArgsConstructor
@Slf4j
public class AhmitjotMstKotasController {

    private final AhmitjotMstKotasService ahmitjotMstKotasService;

    @PostMapping("/search")
    public ResponsePageableDto postFindAllKotas(@RequestBody KotaRequest kotaRequest) {
        return ahmitjotMstKotasService.getAllKotas(kotaRequest);
    }

    @PostMapping
    public ResponseDto postKota(@Valid @RequestBody AhmitjotMstkotas kotaRequest) {
        return ahmitjotMstKotasService.postKota(kotaRequest);
    }

    @GetMapping
    public ResponseDto getKota(@RequestParam("code") String destinationCode) {
        return ahmitjotMstKotasService.getKota(destinationCode);
    }

    @DeleteMapping
    public ResponseDto deleteKota(@RequestParam("code") String destinationCode) {
        return ahmitjotMstKotasService.deleteKota(destinationCode);
    }

    @PutMapping
    public ResponseDto putKota(@Valid @RequestBody AhmitjotMstkotas updateRequest) {
        return ahmitjotMstKotasService.putKota(updateRequest);
    }
}
