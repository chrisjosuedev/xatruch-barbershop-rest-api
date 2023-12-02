package dev.chrisjosue.xatruchbarbershopapi.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonRequest {
    @NotBlank(message = "Nombre completo es requerido.")
    @Length(min = 2, max = 64, message = "Nombre debe tener entre 2 y 64 caracteres.")
    private String fullName;
}
