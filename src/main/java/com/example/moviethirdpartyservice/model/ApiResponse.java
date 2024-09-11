package com.example.moviethirdpartyservice.model;
import java.util.List;

// Main Response Class
public class ApiResponse {
    private long page;
    private List<ResultResponse> results;
    private long totalPages;
    private long totalResults;

    // Getters and Setters
    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public List<ResultResponse> getResults() {
        return results;
    }

    public void setResults(List<ResultResponse> results) {
        this.results = results;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(long totalResults) {
        this.totalResults = totalResults;
    }
}


