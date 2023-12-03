package dev.chrisjosue.xatruchbarbershopapi.common.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class MethodNotAllowedException extends BusinessException {
    private HttpStatus httpStatus;

    public MethodNotAllowedException(String message, String field) {
        super(message, field);
        this.httpStatus = HttpStatus.METHOD_NOT_ALLOWED;
    }
}
