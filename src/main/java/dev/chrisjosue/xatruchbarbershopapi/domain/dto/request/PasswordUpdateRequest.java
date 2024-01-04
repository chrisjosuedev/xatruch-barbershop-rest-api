package dev.chrisjosue.xatruchbarbershopapi.domain.dto.request;


import dev.chrisjosue.xatruchbarbershopapi.common.annotations.PasswordMatch;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
@PasswordMatch(message = "Confirmar Contraseña no coincide.")
public class PasswordUpdateRequest {
    @NotEmpty(message = "Contraseña actual es requerida.")
    private String currentPassword;

    @NotEmpty(message = "Nueva contraseña es requerida.")
    @Length(min = 8, message = "Nueva contraseña debe tener 8 caracteres mínimo.")
    private String newPassword;

    @NotEmpty(message = "Confirmar contraseña es requrido.")
    @Length(min = 8, message = "Confirmar contraseña debe tener 8 caracteres mínimo.")
    private String confirmPassword;
}
