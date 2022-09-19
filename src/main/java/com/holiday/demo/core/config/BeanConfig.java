package com.holiday.demo.core.config;

import com.holiday.demo.client.api.ApiClient;
import com.holiday.demo.client.api.HolidaysApi;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpRequest;
import java.time.Duration;
import java.util.function.Consumer;

@Configuration
@AllArgsConstructor
public class BeanConfig {

    @Bean
    public HolidaysApi holidayApi(){
        Consumer<HttpRequest.Builder> builderConsumer = x -> {
            var httpRequest = x.build();
        };
        return new HolidaysApi(new ApiClient().
                setHost("holidayapi.com").
                setBasePath("").
                setRequestInterceptor(builderConsumer).
                setScheme("https").
                setReadTimeout(Duration.ofMillis(20000)));
    }
}
