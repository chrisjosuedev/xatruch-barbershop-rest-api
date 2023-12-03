package dev.chrisjosue.xatruchbarbershopapi.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private Long id;
    private String title;
    private String review;
    private Boolean isApproved;
    private UserDataDto user;
}
