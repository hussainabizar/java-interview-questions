package com.holiday.demo.core.exception;

import com.holiday.demo.model.ErrorResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(basePackages = "com.holiday.demo.rest")
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(HolidayApiException.class)
    protected ResponseEntity<Object> handleHolidayApiException(HolidayApiException ex){
        ErrorResponse errorResponse = ErrorResponse.builder()
        .type(ex.getType())
        .title(ex.getTitle())
        .status(ex.getStatus().value())
        .errors(ex.getErrors())
        .detail(ex.getDetail()).build();
        return new ResponseEntity<>(errorResponse, ex.getStatus());
    }
}
