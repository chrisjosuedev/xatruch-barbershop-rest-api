package dev.chrisjosue.xatruchbarbershopapi.common.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MaxSizeValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE_PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface MaxSize {
    String message() default "Maximum list capacity reached.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    int max() default 5;
    int min() default 1;
}
