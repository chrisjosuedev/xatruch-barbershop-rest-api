package dev.chrisjosue.xatruchbarbershopapi.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.chrisjosue.xatruchbarbershopapi.common.annotations.DateEnd;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DateEnd
public class SettingRequest {
    @JsonFormat(pattern = "HH:mm:ss")
    @NotNull(message = "Hora de inicio es requerida.")
    @Temporal(TemporalType.TIME)
    private LocalTime startDailyAvailability;

    @JsonFormat(pattern = "HH:mm:ss")
    @Temporal(TemporalType.TIME)
    @NotNull(message = "Hora de fin es requerida.")
    private LocalTime endDailyAvailability;
}
