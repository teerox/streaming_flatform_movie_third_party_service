package com.example.moviethirdpartyservice.service;

import com.example.moviethirdpartyservice.model.ApiResponse;
import org.springframework.beans.factory.annotation.Value;
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
    // Use environment variables or secure storage for API key
    @Value("${tmdb.api.key}")
    private String API_KEY; // = System.getenv("TMDB_API_KEY"); // Retrieve API key from environment variable

    private final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/original";

    public ThirdPartyApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public String getTrendingMoviesUrl() {
        if (API_KEY == null || API_KEY.isEmpty()) {
            throw new IllegalStateException("API Key is missing. Please set the environment variable 'TMDB_API_KEY'.");
        }
        return "https://api.themoviedb.org/3/trending/movie/day?language=en-US&api_key=" + API_KEY;
    }

    public ApiResponse getDataFromApi(Long page) {
        System.out.println("url: " + getTrendingMoviesUrl() + "&page=" + page);
        try {
            // Make the request
            ResponseEntity<ApiResponse> response = restTemplate.exchange(
                    getTrendingMoviesUrl() + "&page=" + page, HttpMethod.GET, null, ApiResponse.class);

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
