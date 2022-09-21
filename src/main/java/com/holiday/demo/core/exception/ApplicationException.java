package com.holiday.demo.core.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApplicationException extends Exception {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String errorCode;

    ApplicationException() {
        super();
        timestamp = LocalDateTime.now();
    }

    public ApplicationException(String errorMessage) {
        super(errorMessage);
        timestamp = LocalDateTime.now();
    }

    public ApplicationException(String errorCode, String errorMessage) {
        this(errorMessage);
        this.errorCode = errorCode;
    }

    public ApplicationException(String errorMessage, Throwable errorContext) {
        super(errorMessage, errorContext);
        timestamp = LocalDateTime.now();
    }

    public ApplicationException(String errorCode, String errorMessage, Throwable errorContext) {
        this(errorMessage, errorContext);
        this.errorCode = errorCode;
    }
}



