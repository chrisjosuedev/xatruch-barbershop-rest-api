package dev.chrisjosue.xatruchbarbershopapi.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopServiceRequest {
    private String serviceName;
    private Double price;
}
