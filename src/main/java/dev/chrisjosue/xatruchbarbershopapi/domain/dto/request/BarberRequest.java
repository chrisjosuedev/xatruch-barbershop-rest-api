package dev.chrisjosue.xatruchbarbershopapi.domain.dto.request;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BarberRequest extends UserRequest {
    @NotNull(message = "Hire date is required.")
    @Temporal(TemporalType.DATE)
    private Date hired;
}
