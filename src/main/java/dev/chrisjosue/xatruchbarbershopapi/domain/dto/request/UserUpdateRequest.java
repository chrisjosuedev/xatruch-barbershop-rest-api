package dev.chrisjosue.xatruchbarbershopapi.domain.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserUpdateRequest extends PersonRequest {
    @NotBlank(message = "Email es requerido.")
    @Email(message = "Email es inválido.")
    private String email;
}
