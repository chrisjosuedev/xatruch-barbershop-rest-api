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
    @NotBlank(message = "Email is required.")
    @Email(message = "Email address is invalid.")
    private String email;

    @NotEmpty(message = "Password is required.")
    @Length(min = 8, message = "Password must have 8 characters min.")
    private String password;
}
