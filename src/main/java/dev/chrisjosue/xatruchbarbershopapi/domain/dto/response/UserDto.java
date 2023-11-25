package dev.chrisjosue.xatruchbarbershopapi.domain.dto.response;

import dev.chrisjosue.xatruchbarbershopapi.common.enums.Role;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserDto extends PersonDto {
    private String email;
    private String profileUrl;
    private Role role;
}
