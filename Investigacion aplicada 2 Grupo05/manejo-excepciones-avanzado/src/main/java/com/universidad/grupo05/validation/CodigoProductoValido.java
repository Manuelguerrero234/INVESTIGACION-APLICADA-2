package com.universidad.grupo05.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CodigoProductoValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CodigoProductoValido {
    String message() default "El código contiene caracteres no permitidos o la palabra 'TEST'";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}