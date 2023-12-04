package dev.chrisjosue.xatruchbarbershopapi.common.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.Set;

public class MaxSizeValidator implements ConstraintValidator<MaxSize, Set<Long>> {
    private int max;
    private int min;

    @Override
    public void initialize(MaxSize constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.max = constraintAnnotation.max();
        this.min = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(Set<Long> ids, ConstraintValidatorContext constraintValidatorContext) {
        return ids.size() >= min && ids.size() <= max;
    }
}
