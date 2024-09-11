package com.example.moviethirdpartyservice.controller;

import com.example.moviethirdpartyservice.model.ApiResponse;
import com.example.moviethirdpartyservice.service.ThirdPartyApiService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ThirdPartyController {

    private final ThirdPartyApiService thirdPartyService;

    ThirdPartyController(ThirdPartyApiService thirdPartyService) {
        this.thirdPartyService = thirdPartyService;
    }

    @GetMapping("/movies-external/list")
    public ApiResponse getMoviesFromApi(@RequestParam(defaultValue = "1") long page) {
       return thirdPartyService.getDataFromApi(page);
    }
}
