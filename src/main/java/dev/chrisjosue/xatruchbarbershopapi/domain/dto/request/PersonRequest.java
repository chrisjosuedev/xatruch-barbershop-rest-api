package dev.chrisjosue.xatruchbarbershopapi.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonRequest {
    @NotBlank(message = "fullName is required.")
    @Length(min = 2, max = 64, message = "Length must be min 2 and max 64 characters.")
    private String fullName;
}
