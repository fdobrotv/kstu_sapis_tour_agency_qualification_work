package com.fdobrotv.touristagency.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class RestTemplateConfiguration {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
