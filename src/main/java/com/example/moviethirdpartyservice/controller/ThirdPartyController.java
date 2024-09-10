package com.example.moviethirdpartyservice.controller;

import com.example.moviethirdpartyservice.model.ApiResponse;
import com.example.moviethirdpartyservice.service.ThirdPartyApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ThirdPartyController {

    private final ThirdPartyApiService thirdPartyService;

    ThirdPartyController(ThirdPartyApiService thirdPartyService) {
        this.thirdPartyService = thirdPartyService;
    }

    @GetMapping("/movie-info/list")
    public ApiResponse getMoviesFromApi() {
       return thirdPartyService.getDataFromApi();
    }
}
