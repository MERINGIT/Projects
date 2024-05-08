package com.example.client.controller;

import com.example.client.model.CalculationRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CalculationController {

    private final String container2Url = "http://app2:7000";

     // Assuming you have a property in your application.properties for the mounted directory path
    private String baseDirectory="/data/";

    @PostMapping("/calculate")
    public ResponseEntity<Map<String, Object>> calculate(@RequestBody CalculationRequest request) {
        Map<String, Object> response = new HashMap<>();

        // Validate input JSON
        if (request.getFile() == null) {
            response.put("file", null);
            response.put("error", "Invalid JSON input.");
            return ResponseEntity.badRequest().body(response);
        }

        // Check if file exists in the mounted directory
        Path filePath = Paths.get(baseDirectory, request.getFile());
        File file = filePath.toFile();

        if (!file.exists() || !file.isFile()) {
            response.put("file", request.getFile());
            response.put("error", "File not found.");
            return ResponseEntity.badRequest().body(response);
        }

        // Forward request to Container 2 using RestTemplate
        ResponseEntity<Map> responseFromContainer2 = callContainer2(request);

        // Handle Container 2 response
        if (responseFromContainer2.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.ok(responseFromContainer2.getBody());
        } else {
            return ResponseEntity.badRequest().body(responseFromContainer2.getBody());
        }
    }

    private ResponseEntity<Map> callContainer2(CalculationRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CalculationRequest> requestEntity = new HttpEntity<>(request, headers);

        RestTemplate restTemplate = new RestTemplate();
        String url = container2Url + "/process";
        return restTemplate.postForEntity(url, requestEntity, Map.class);
    }
}