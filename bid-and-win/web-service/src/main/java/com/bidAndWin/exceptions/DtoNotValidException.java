package com.bidAndWin.exceptions;

import com.bidAndWin.exceptionHandler.ErrorDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.*;

@Getter
public class DtoNotValidException extends RuntimeException {
    private final Set<? extends ConstraintViolation<?>> violations;

    public <T> DtoNotValidException(Set<ConstraintViolation<T>> constraintViolations) {
        super(buildCustomMessage(constraintViolations));
        this.violations = constraintViolations;
    }

    private static <T> String buildCustomMessage(Set<ConstraintViolation<T>> constraintViolations) {
        StringBuilder message = new StringBuilder("Dto validation failed: ");
        for (ConstraintViolation<T> constraintViolation : constraintViolations) {
            message.append(constraintViolation.getPropertyPath())
                    .append(" ")
                    .append(constraintViolation.getMessage())
                    .append(",");
        }
        return message.toString();
    }

    public Map<Path, String> getErrorsMap() {
        Map<Path, String> map = new HashMap<>();
        for (ConstraintViolation<?> constraintViolation : violations) {
            map.put(constraintViolation.getPropertyPath(), constraintViolation.getMessage());
        }
        return map;
    }

    public List<ErrorDto> getErrors() {
        List<ErrorDto> list = new ArrayList<>();
        for (ConstraintViolation<?> constraintViolation : violations) {
            list.add(
                    new ErrorDto(
                            HttpStatus.NOT_ACCEPTABLE,
                            constraintViolation.getPropertyPath() + " " + constraintViolation.getMessage(),
                            LocalDateTime.now()
                    ));
        }
        return list;
    }

}
