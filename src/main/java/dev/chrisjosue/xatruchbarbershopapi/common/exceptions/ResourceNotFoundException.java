package dev.chrisjosue.xatruchbarbershopapi.common.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class ResourceNotFoundException extends BusinessException {
    private HttpStatus httpStatus;

    public ResourceNotFoundException(String message, String field) {
        super(message, field);
        this.httpStatus = HttpStatus.NOT_FOUND;
    }
}
