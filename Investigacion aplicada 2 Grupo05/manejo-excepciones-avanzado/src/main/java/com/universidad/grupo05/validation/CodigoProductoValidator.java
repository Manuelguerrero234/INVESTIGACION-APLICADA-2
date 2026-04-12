package com.universidad.grupo05.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CodigoProductoValidator implements ConstraintValidator<CodigoProductoValido, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return !value.toUpperCase().contains("TEST");
    }
}