package com.mruruc.exception;

import jakarta.validation.ConstraintViolation;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Getter
public class DtoNotValidException extends RuntimeException {
    private final Set<? extends  ConstraintViolation<?>> violations;

    public <T> DtoNotValidException(Set<ConstraintViolation<T>> violations) {
        super(buildCustomMessage(violations));
        this.violations = violations;
    }

    private static <T> String buildCustomMessage(Set<ConstraintViolation<T>> constraintViolations) {
        StringBuilder message = new StringBuilder();
        for (ConstraintViolation<T> constraintViolation : constraintViolations) {
            message.append(constraintViolation.getPropertyPath())
                    .append(" ")
                    .append(constraintViolation.getMessage())
                    .append(",");
        }
        return message.toString();
    }

    public Map<String , String > getErrorsMap(){
        Map<String , String > map = new HashMap<>();
        for (ConstraintViolation<?> constraintViolation : violations) {
            map.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
        }
        return map;
    }
}
