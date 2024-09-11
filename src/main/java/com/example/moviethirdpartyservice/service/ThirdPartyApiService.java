package com.example.moviethirdpartyservice.service;

import com.example.moviethirdpartyservice.model.ApiResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public interface ThirdPartyApiService {

    public String getTrendingMoviesUrl();
    public ApiResponse getDataFromApi(Long page);
}
