package com.mruruc.exceptionHandler;


import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@Slf4j
@ControllerAdvice()
public class GlobalExceptionalHandler {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception exception, Model model){
        log.error("Exception occurred: ", exception);
        model.addAttribute("exception", exception);
        return "errorPage";
    }
}
