package dev.chrisjosue.xatruchbarbershopapi.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingGeneralDto {
    private Long id;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date bookingDate;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime bookingTime;
    private String user;
    private String barber;
}
