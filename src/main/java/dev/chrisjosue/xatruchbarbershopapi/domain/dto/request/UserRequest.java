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
public class UserRequest {
    @NotBlank(message = "Name is required.")
    @Length(min = 2, max = 64, message = "Length must be min 2 and max 64 characters.")
    private String fullName;

    @NotBlank(message = "Email is required.")
    @Email(message = "Email address is invalid.")
    private String email;

    @NotEmpty(message = "Password is required.")
    /**
     * TODO: Passay Validator
     */
    private String password;

    private String profileUrl;
}
