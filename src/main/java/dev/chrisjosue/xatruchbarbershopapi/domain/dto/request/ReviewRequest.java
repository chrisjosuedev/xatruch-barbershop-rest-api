package dev.chrisjosue.xatruchbarbershopapi.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequest {
    @NotBlank(message = "Título es requerido.")
    @Length(min = 2, max = 20, message = "Título debe tener entre 2 y 20 caracteres.")
    private String title;

    @NotBlank(message = "Review es requerido.")
    @Length(min = 2, max = 100, message = "Review debe tener entre 2 y 100 caracteres.")
    private String review;
}
