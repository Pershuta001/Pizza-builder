package com.example.pizzabuilder.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

public class Utils {
    public static final ObjectMapper objectMapper = Jackson2ObjectMapperBuilder
            .json()
            .modules(new JavaTimeModule())
            .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .build()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

    public static boolean notEmpty(String str) {
        if (str == null || "".equals(str))
            return false;
        return true;
    }
}
