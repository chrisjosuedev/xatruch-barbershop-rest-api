package dev.chrisjosue.xatruchbarbershopapi.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDataDto {
    private String fullName;
    private String profileUrl;
}
