package dev.chrisjosue.xatruchbarbershopapi.common.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class ResourceNotFound extends RuntimeException {
    private HttpStatus httpStatus;

    public ResourceNotFound(String message) {
        super(message);
        this.httpStatus = HttpStatus.NOT_FOUND;
    }
}
