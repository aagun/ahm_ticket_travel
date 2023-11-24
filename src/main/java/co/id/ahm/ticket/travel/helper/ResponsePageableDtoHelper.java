package co.id.ahm.ticket.travel.helper;

import co.id.ahm.ticket.travel.dto.ResponsePageableDto;
import co.id.ahm.ticket.travel.enums.ResponseStatusCode;
import java.util.List;
import org.springframework.data.domain.Page;

public class ResponsePageableDtoHelper {

    public static ResponsePageableDto ok() {
        return ResponsePageableDto.builder()
                .message("Success")
                .status(ResponseStatusCode.SUCCESS.getValue())
                .build();
    }


    public static ResponsePageableDto ok(Page<?> page) {
        return ok("Success", page);
    }

    public static ResponsePageableDto ok(String message, Page<?> page) {
        return ResponsePageableDto.builder()
                .status(ResponseStatusCode.SUCCESS.getValue())
                .message(message)
                .data((List<Object>) page.getContent())
                .totalItems(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();
    }

    public static ResponsePageableDto fail(String message) {
        return ResponsePageableDto.builder()
                .message("Failed")
                .status(ResponseStatusCode.FAILED.getValue())
                .build();
    }

    public static ResponsePageableDto fail() {
        return fail("Failed");
    }

    public static ResponsePageableDto fail(List errorMessages) {
        return ResponsePageableDto.builder()
                .status(ResponseStatusCode.SUCCESS.getValue())
                .message("Failed")
                .data((List<Object>) errorMessages)
                .build();
    }
}
