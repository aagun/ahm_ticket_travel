package co.id.ahm.ticket.travel.controller;

import co.id.ahm.ticket.travel.dto.ResponseDto;
import co.id.ahm.ticket.travel.dto.ResponsePageableDto;
import co.id.ahm.ticket.travel.dto.TicketSearchRequest;
import co.id.ahm.ticket.travel.dto.TicketUpdateRequest;
import co.id.ahm.ticket.travel.dto.TicketRequest;
import co.id.ahm.ticket.travel.service.AhmitjotMstticketsService;
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
@RequestMapping("/api/v1/tickets")
@AllArgsConstructor
@Slf4j
public class AhmitjotMstticketsController {

    private final AhmitjotMstticketsService ahmitjotMstticketsService;

    @PostMapping
    public ResponseDto postTicket(@Valid @RequestBody TicketRequest ticketRequest) {
        return ahmitjotMstticketsService.postTicket(ticketRequest);
    }

    @PostMapping("/search")
    public ResponsePageableDto getAllTickets(@RequestBody TicketSearchRequest ticketSearchRequest) {
        return ahmitjotMstticketsService.getAllTickets(ticketSearchRequest);
    }

    @DeleteMapping
    public ResponseDto deleteTicket(@RequestParam("noticket") String noticket) {
        return ahmitjotMstticketsService.deleteTicket(noticket);
    }

    @GetMapping
    public ResponseDto getTicket(@RequestParam("noticket") String noticket) {
        return ahmitjotMstticketsService.getTicket(noticket);
    }

    @PutMapping
    public ResponseDto putTicket(@Valid @RequestBody TicketUpdateRequest updateRequest) {
        return ahmitjotMstticketsService.putTicket(updateRequest);
    }

}
