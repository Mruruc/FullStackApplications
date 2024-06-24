package com.bidAndWin.validation.customValidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordImpl implements ConstraintValidator<Password,String> {
    @Override
    public void initialize(Password constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        return !this.isEmptyOrNull(password) && passwordLength(password);
    }

    private boolean isEmptyOrNull(String password){
        return password == null || password.isBlank() || password.trim().isEmpty();
    }

    private boolean passwordLength(String password){
        return password.trim().length() >= 8;
    }
}
