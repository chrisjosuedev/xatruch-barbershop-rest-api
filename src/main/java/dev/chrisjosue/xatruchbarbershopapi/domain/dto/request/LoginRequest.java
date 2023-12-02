package dev.chrisjosue.xatruchbarbershopapi.domain.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotBlank(message = "Email es requerido.")
    @Email(message = "Email es inválido.")
    private String email;

    @NotEmpty(message = "Contraseña es requerida.")
    private String password;
}
