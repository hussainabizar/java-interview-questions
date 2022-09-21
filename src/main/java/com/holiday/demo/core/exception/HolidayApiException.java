package com.holiday.demo.core.exception;

import com.holiday.demo.model.SubError;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class HolidayApiException extends RuntimeException {

    private String type;
    private String title;
    private HttpStatus status;
    private List<SubError> errors;
    private String detail;

    public HolidayApiException(HttpStatus status) {
        super();
        this.status = status;
    }

    public HolidayApiException(HttpStatus status, SubError subError) {
        super(status.getReasonPhrase());
        this.status = status;
        addError(subError);
    }

    public HolidayApiException(HttpStatus status, SubError subError, String detail) {
        super(status.getReasonPhrase());
        this.status = status;
        this.detail = detail;
        addError(subError);
    }

    public HolidayApiException(HttpStatus status, String type, String title, SubError subError, String detail) {
        super(status.getReasonPhrase());
        this.status = status;
        this.type = type;
        this.title = title;
        this.detail = detail;
        addError(subError);
    }

    public HolidayApiException(HttpStatus status, String type, String title, SubError subError) {
        super(status.getReasonPhrase());
        this.status = status;
        this.type = type;
        this.title = title;
        addError(subError);
    }

    public HolidayApiException(HttpStatus status, String type, String title, String detail) {
        super(status.getReasonPhrase());
        this.status = status;
        this.detail = detail;
        this.type = type;
        this.title = title;
    }

    public void addError(SubError subError) {
        if (errors == null) {
            errors = new ArrayList<>();
        }
        errors.add(subError);
    }
}