package dev.chrisjosue.xatruchbarbershopapi.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDetailDto {
    private String serviceName;
    private Double price;
}
