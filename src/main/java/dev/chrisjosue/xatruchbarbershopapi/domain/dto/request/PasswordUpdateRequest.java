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
@PasswordMatch
public class PasswordUpdateRequest {
    @NotEmpty(message = "Current Password is required.")
    private String currentPassword;

    @NotEmpty(message = "New Password is required.")
    @Length(min = 8, message = "New Password must have 8 characters min.")
    private String newPassword;

    @NotEmpty(message = "Confirm Password is required.")
    @Length(min = 8, message = "Confirm Password must have 8 characters min.")
    private String confirmPassword;
}
