package com.fdobrotv.touristagency.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class RestTemplateBuilderConfiguration {
    @Bean
    public RestTemplateBuilder restTemplateBuilder(RestTemplate restTemplate) {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        restTemplateBuilder.configure(restTemplate);
        return restTemplateBuilder;
    }
}
