package dev.chrisjosue.xatruchbarbershopapi.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetailResponseDto {
    private String field;
    private String message;
}
