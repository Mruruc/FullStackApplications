package com.bidAndWin.payment;

import com.bidAndWin.exceptionHandler.ErrorDto;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice(basePackages = "com.bidAndWin.payment")
public class PaymentExceptionHandler {

    @ExceptionHandler(PayPalRESTException.class)
    public ResponseEntity<ErrorDto> handlePaypalPaymentException(PayPalRESTException payPalRESTException){
        return new ResponseEntity<>(
                new ErrorDto(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "While Processing payment error occurred try again",
                        LocalDateTime.now()
                ),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
