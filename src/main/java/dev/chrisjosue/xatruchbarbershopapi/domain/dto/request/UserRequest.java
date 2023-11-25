package dev.chrisjosue.xatruchbarbershopapi.domain.dto.request;

import dev.chrisjosue.xatruchbarbershopapi.domain.entity.Person;
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
public class UserRequest extends Person {
    @NotBlank(message = "Email is required.")
    @Email(message = "Email address is invalid.")
    private String email;

    @NotEmpty(message = "Password is required.")
    @Length(min = 8, message = "Password must have 8 characters min.")
    private String password;
}
