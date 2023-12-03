package dev.chrisjosue.xatruchbarbershopapi.common.annotations;

import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.SettingRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EndDateValidator implements ConstraintValidator<DateEnd, Object> {

    @Override
    public void initialize(DateEnd constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        if (object instanceof SettingRequest settingRequest) {
            return settingRequest.getStartDailyAvailability().isBefore(settingRequest.getEndDailyAvailability());
        }
        return false;
    }
}
