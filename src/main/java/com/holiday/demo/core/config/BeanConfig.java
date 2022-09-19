package com.holiday.demo.core.config;

import com.holiday.demo.client.api.ApiClient;
import com.holiday.demo.client.api.HolidaysApi;
import com.holiday.demo.core.util.CommonUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpRequest;
import java.time.Duration;
import java.util.function.Consumer;

@Configuration
@AllArgsConstructor
@Slf4j
public class BeanConfig {

    @Bean
    public HolidaysApi holidayApi(){
        Consumer<HttpRequest.Builder> builderConsumer = x -> {
            var httpRequest = x.build();
            log.info("Outgoing: Holiday API call to fetch the holiday list - URI: {} Headers: {}", httpRequest.uri(),
                    CommonUtils.maskApiKeyFromHeaders.apply(httpRequest.headers().map()));
        };
        return new HolidaysApi(new ApiClient().
                setHost("holidayapi.com").
                setBasePath("").
                setRequestInterceptor(builderConsumer).
                setScheme("https").
                setReadTimeout(Duration.ofMillis(20000)));
    }
}
