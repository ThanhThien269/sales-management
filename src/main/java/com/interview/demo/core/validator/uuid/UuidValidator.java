package com.interview.demo.core.validator.uuid;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UuidValidator implements ConstraintValidator<UuidValid, Object> {
    private boolean nullable;

    @Override
    public void initialize(UuidValid constraintAnnotation) {
        this.nullable = constraintAnnotation.nullable();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return nullable;
        }

        if (value instanceof UUID) {
            return true;
        }

        if (value instanceof String) {
            try {
                UUID.fromString((String) value);
                return true;
            } catch (IllegalArgumentException e) {
                return false;
            }
        }

        return false;
    }
}
