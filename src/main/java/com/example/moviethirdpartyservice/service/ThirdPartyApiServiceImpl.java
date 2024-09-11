package com.example.moviethirdpartyservice.service;

import com.example.moviethirdpartyservice.model.ApiResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class ThirdPartyApiServiceImpl implements ThirdPartyApiService {

    private final RestTemplate restTemplate;

    // API Key should be included in the URL, not in headers
    private final String API_KEY = "fb97e27952573c39dd8c56b40023750e";
    private final String API_URL = "https://api.themoviedb.org/3/trending/movie/day?language=en-U&api_key=fb97e27952573c39dd8c56b40023750e";

    private final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/original";

    public ThirdPartyApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ApiResponse getDataFromApi() {
        try {
            // Make the request
            ResponseEntity<ApiResponse> response = restTemplate.exchange(
                    API_URL, HttpMethod.GET, null, ApiResponse.class);

            Objects.requireNonNull(response.getBody()).getResults().forEach(result -> {
                if (result.getPoster_path() != null){
                    result.setPoster_path(IMAGE_BASE_URL + result.getPoster_path());
                }
                if (result.getBackdrop_path() != null){
                    result.setBackdrop_path(IMAGE_BASE_URL + result.getBackdrop_path());
                }
            });
            // Return the response body
            return response.getBody();
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            // Handle specific HTTP errors
            System.err.println("HTTP error occurred: " + e.getMessage());
        } catch (ResourceAccessException e) {
            // Handle connection issues
            System.err.println("Resource access error: " + e.getMessage());
        } catch (Exception e) {
            // Handle other exceptions
            System.err.println("An error occurred: " + e.getMessage());
        }
        return null;
    }
}
