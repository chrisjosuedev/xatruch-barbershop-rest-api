package dev.chrisjosue.xatruchbarbershopapi.common.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EndDateValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateEnd {
    String message() default "End Time must be greater than Star time.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
