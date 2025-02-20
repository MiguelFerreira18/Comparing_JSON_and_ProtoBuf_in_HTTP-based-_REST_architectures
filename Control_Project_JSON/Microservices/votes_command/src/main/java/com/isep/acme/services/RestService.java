package com.isep.acme.services;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.isep.acme.dto.ReviewDTO;

import java.time.LocalDate;
import java.util.List;

/* Code based on following tutorial https://attacomsian.com/blog/http-requests-resttemplate-spring-boot */

@Service
public class RestService {

    private final RestTemplate restTemplate;

    public RestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String getFunFact(LocalDate date) {
        String url = "http://numbersapi.com/{month}/{day}/date";
        return this.restTemplate.getForObject(url, String.class, date.getMonthValue(),date.getDayOfMonth());
    }

    public List<ReviewDTO> fetchDataFromMicroservice() {
        String microserviceUrl = "http://localhost:8080/reviews";
        ResponseEntity<List<ReviewDTO>> response = restTemplate.exchange(
            microserviceUrl,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<ReviewDTO>>(){});
        return response.getBody();
    }
}
