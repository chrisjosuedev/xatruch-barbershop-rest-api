package dev.chrisjosue.xatruchbarbershopapi.common.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class InternalServerException extends BusinessException {
    private HttpStatus httpStatus;

    public InternalServerException(String message, String field) {
        super(message, field);
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
