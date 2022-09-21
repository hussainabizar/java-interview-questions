package com.holiday.demo.core.filter;

import com.holiday.demo.core.exception.HolidayApiException;
import com.holiday.demo.model.SubError;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
@Slf4j
public class XApiKeyAuthenticationFilter extends OncePerRequestFilter {

    @Value("${backbase.holiday_service_key}")
    private String apiKey;

    private static Optional<String> getApiKeyFromRequest(HttpServletRequest request) {
        String apiKey = request.getHeader("x-api-key");
        if (org.springframework.util.StringUtils.hasText(apiKey)) {
            return Optional.of(apiKey);
        }
        return Optional.empty();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain chain)
            throws ServletException, IOException {
        if (request.getRequestURI().startsWith("/backbase/v1")) {
            validateApiKey(request);
            log.info("doFilterInternal(): x-api-key is validated");
        }
        chain.doFilter(request, response);
    }

    private void validateApiKey(HttpServletRequest request) {
        final Optional<String> suppliedKey = getApiKeyFromRequest(request);

        if (suppliedKey.isEmpty() || !suppliedKey.get().equals(apiKey)) {
            throw new HolidayApiException(HttpStatus.BAD_REQUEST,
                    new SubError(4001, "x-api-key is missing or invalid"));
        }
    }
}

