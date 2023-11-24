package co.id.ahm.ticket.travel.controller.advice;

import co.id.ahm.ticket.travel.helper.ResponseDtoHelper;
import co.id.ahm.ticket.travel.dto.ResponseDto;
import co.id.ahm.ticket.travel.exception.ClientException;
import co.id.ahm.ticket.travel.exception.NotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "co.id.ahm.ticket.travel")
@Slf4j
public class ControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseDto methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        return ResponseDtoHelper.fail(getErrors(result.getFieldErrors())
        );
    }

    @ExceptionHandler({ClientException.class, NotFoundException.class})
    public ResponseDto exceptionHandler(ClientException ex) {
        return ResponseDtoHelper.fail(Collections.singletonList(ex.getMessage()));
    }

    private static List<String> getErrors(List<FieldError> errors) {
        List<String> noValuesErrors = Arrays.asList("NotBlank", "NotNull", "NotEmpty");
        Map<String, String> MAPPED_ERRORS = new HashMap<>();

        for (FieldError error : errors) {
            if (noValuesErrors.contains(error.getField()) && MAPPED_ERRORS.get(error.getField()) != null) {
                MAPPED_ERRORS.put(error.getField(), error.getDefaultMessage());
            } else {
                MAPPED_ERRORS.put(error.getField(), error.getDefaultMessage());
            }
        }

        return MAPPED_ERRORS.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }

}
