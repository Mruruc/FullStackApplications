package com.bidAndWin.exceptionHandler;

import com.bidAndWin.exceptions.*;
import jakarta.validation.Path;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    public GlobalExceptionHandler(){}

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<ErrorDto> handleIncorrectPasswordException(IncorrectPasswordException incorrectPasswordException){
        return new ResponseEntity<>(
                new ErrorDto(
                        HttpStatus.UNAUTHORIZED,
                        incorrectPasswordException.getMessage(),
                        LocalDateTime.now()),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(DtoNotValidException.class)
    public ResponseEntity<Map<Path,String>> handleDtoNotValidException(DtoNotValidException dtoNotValidException){
        return new ResponseEntity<>(
                dtoNotValidException.getErrorsMap()
              ,HttpStatus.NOT_ACCEPTABLE
        );
    }

    @ExceptionHandler({ClientNotFoundException.class, ItemNotFoundException.class, UserNotFoundException.class})
    public ResponseEntity<ErrorDto> notFoundExceptionHandler(
          RuntimeException exception
    ){
        return new ResponseEntity<>(
                new ErrorDto(
                        HttpStatus.NOT_FOUND,
                        exception.getMessage(),
                        LocalDateTime.now()),
                HttpStatus.NOT_FOUND
        );
    }
}
