package dev.chrisjosue.xatruchbarbershopapi.common.annotations;

import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.ForgotPasswordRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.PasswordUpdateRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, Object> {
    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        if (object instanceof PasswordUpdateRequest passwordUpdateRequest) {
            return passwordUpdateRequest.getConfirmPassword().equals(passwordUpdateRequest.getNewPassword());
        } else if (object instanceof ForgotPasswordRequest forgotPasswordRequest) {
            return forgotPasswordRequest.getConfirmPassword().equals(forgotPasswordRequest.getNewPassword());
        }
        return false;
    }
}
