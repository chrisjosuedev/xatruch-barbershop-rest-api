package dev.chrisjosue.xatruchbarbershopapi.common.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class BadRequestException extends BusinessException {
    private HttpStatus httpStatus;

    public BadRequestException(String message, String field) {
        super(message, field);
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }
}
