package com.holiday.demo.core.util;

import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

@NoArgsConstructor
public class CommonUtils {

    public static final UnaryOperator<Map<String, List<String>>> maskApiKeyFromHeaders = httpHeadersMap -> {
        Map<String, List<String>> maskedHeaders = new HashMap<>();
        httpHeadersMap.forEach((key, value) -> {
            if (key.contains("key"))
                maskedHeaders.put(key, value.stream().map(v -> StringUtils.repeat("*", v.length())).collect(Collectors.toList()));
            else maskedHeaders.put(key, value);
        });
        return maskedHeaders;
    };

}
