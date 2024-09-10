package com.example.moviethirdpartyservice.service;

import com.example.moviethirdpartyservice.model.ApiResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ThirdPartyApiServiceImpl implements ThirdPartyApiService {

    private final RestTemplate restTemplate;

    ThirdPartyApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private final String API_KEY = "fb97e27952573c39dd8c56b40023750e";
    private final String API_URL = "https://api.themoviedb.org/3/trending/all/day?language=en-US";

    public ApiResponse getDataFromApi() {
        // Set up headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("api_key", API_KEY);

        HttpEntity<Object> entity = new HttpEntity<>(headers);

        // Make the request
        ResponseEntity<ApiResponse> response = restTemplate.exchange(
                API_URL, HttpMethod.GET, entity, ApiResponse.class);

        // Return the response body
        return response.getBody();
    }
}
