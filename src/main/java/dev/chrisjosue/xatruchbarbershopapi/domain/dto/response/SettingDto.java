package dev.chrisjosue.xatruchbarbershopapi.domain.dto.response;

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
public class SettingDto {
    private byte id;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime startDailyAvailability;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime endDailyAvailability;

    private Boolean isActive;
}
