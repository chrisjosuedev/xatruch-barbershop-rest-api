package dev.chrisjosue.xatruchbarbershopapi.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {
    @NotNull(message = "Fecha de sesión es requerida.")
    @FutureOrPresent(message = "Fecha no puede ser anterior a la actual.")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate bookingDate;

    @NotNull(message = "Hora de sesión es requerida.")
    @FutureOrPresent(message = "Hora no puede ser anterior a la actual.")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime bookingTime;

    @NotNull(message = "ID de barbero es requerida.")
    private Long barberId;
}
