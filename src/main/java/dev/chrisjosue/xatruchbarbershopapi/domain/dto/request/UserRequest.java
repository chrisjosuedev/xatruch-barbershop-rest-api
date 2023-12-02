package dev.chrisjosue.xatruchbarbershopapi.domain.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserRequest extends PersonRequest {
    @NotBlank(message = "Email es requerido.")
    @Email(message = "Email es inválido.")
    private String email;

    @NotEmpty(message = "Contraseña es requerida.")
    @Length(min = 8, message = "Contraseña debe tener 8 caracteres mínimo.")
    private String password;
}
