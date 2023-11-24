package co.id.ahm.ticket.travel.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseStatusCode {
    FAILED(0),
    SUCCESS(1);

    private final Integer value;
}
