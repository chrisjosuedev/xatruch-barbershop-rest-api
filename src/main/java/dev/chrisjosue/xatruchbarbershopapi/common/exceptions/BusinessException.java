package dev.chrisjosue.xatruchbarbershopapi.common.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException {
    private String field;

    public BusinessException(String message, String field) {
        super(message);
        this.field = field;
    }
}
