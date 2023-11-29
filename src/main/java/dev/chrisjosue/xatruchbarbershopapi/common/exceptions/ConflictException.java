package dev.chrisjosue.xatruchbarbershopapi.common.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class ConflictException extends BusinessException {
    private HttpStatus httpStatus;

    public ConflictException(String message, String field) {
        super(message, field);
        this.httpStatus = HttpStatus.CONFLICT;
    }
}
