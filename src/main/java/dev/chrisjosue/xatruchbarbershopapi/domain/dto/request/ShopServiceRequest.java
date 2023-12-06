package dev.chrisjosue.xatruchbarbershopapi.domain.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopServiceRequest {
    @NotBlank(message = "Nombre del servicio es requerido.")
    @Length(min = 2, max = 30, message = "Nombre del servicio debe tener entre 2 y 30 caracteres.")
    private String serviceName;

    @NotNull(message = "Precio es requerido.")
    @PositiveOrZero(message = "Precio debe ser un valor positivo o cero en caso de servicio gratuito.")
    private Double price;
}
