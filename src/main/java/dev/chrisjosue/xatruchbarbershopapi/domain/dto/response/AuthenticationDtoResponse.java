package dev.chrisjosue.xatruchbarbershopapi.domain.dto.response;

import dev.chrisjosue.xatruchbarbershopapi.common.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationDtoResponse {
    private Long id;
    private String fullName;
    private String email;
    private String profileUrl;
    private Role role;
    private String token;
}
