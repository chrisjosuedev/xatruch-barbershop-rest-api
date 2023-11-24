package dev.chrisjosue.xatruchbarbershopapi.domain.dto;

import dev.chrisjosue.xatruchbarbershopapi.common.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String fullName;
    private String email;
    private String profileUrl;
    private Role role;
}
