package com.holiday.demo.core.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import java.util.function.BiFunction;

@Component
@Slf4j
public class ErrorManager {

    public final BiFunction<Exception, String, ProviderException> handleHolidayApiError = (ex, requestId) -> {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        String errorMessage = ex.toString();
        log.error("{} Error while calling Holiday API. http status: {} Exception Message is : {}", requestId, httpStatus, errorMessage);
        return new ProviderException(httpStatus, errorMessage);
    };

}
