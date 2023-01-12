package com.example.transactionapi.config;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Configuration
public class ObjectMapperConfig {

    @Bean
    @Primary
    public ObjectMapper objectMapper(){
        return new ObjectMapper().registerModule(new JavaTimeModule()).enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
                .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
    }
}
