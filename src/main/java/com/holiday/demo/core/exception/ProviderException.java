package com.holiday.demo.core.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Data
public class ProviderException extends ApplicationException {

    @Serial
    private static final long serialVersionUID = -6397597785768595080L;
    private HttpStatus status;
    private String errorMessage;

    public ProviderException(HttpStatus status, String errorMessage) {
        super(errorMessage);
        this.status = status;
        this.errorMessage = errorMessage;
    }
}