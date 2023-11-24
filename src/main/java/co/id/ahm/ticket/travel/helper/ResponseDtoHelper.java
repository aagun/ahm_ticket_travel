package co.id.ahm.ticket.travel.helper;

import co.id.ahm.ticket.travel.dto.ResponseDto;
import co.id.ahm.ticket.travel.enums.ResponseStatusCode;
import java.util.Collections;
import java.util.List;

public class ResponseDtoHelper {

    public static ResponseDto ok() {
        return ok(Collections.emptyList());
    }


    public static ResponseDto ok(List list) {
        return ok("Success", list);
    }

    public static ResponseDto ok(String message, List list) {
        return new ResponseDto<>(ResponseStatusCode.SUCCESS.getValue(), message, list);
    }

    public static ResponseDto fail(String message) {
        return new ResponseDto<>(ResponseStatusCode.FAILED.getValue(), message, Collections.emptyList());
    }

    public static ResponseDto fail() {
        return fail("Failed");
    }

    public static ResponseDto fail(List errorMessages) {
        return new ResponseDto<>(
                ResponseStatusCode.FAILED.getValue(),
                "Failed",
                errorMessages
        );
    }
}
