package com.example.moviethirdpartyservice.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfiguration {

    // The rest template is used to make HTTP requests to other services
   // @LoadBalanced - don't use this for external service calls
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
