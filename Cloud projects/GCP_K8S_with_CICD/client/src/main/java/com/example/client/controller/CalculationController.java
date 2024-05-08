package com.example.client.controller;

import com.example.client.model.CalculationRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

@RestController
@Validated
public class CalculationController {

    private final String container2Url = "http://localhost:7000";
    private String baseDirectory = "/app/data";

    @PostMapping("/store-file")
    public ResponseEntity<Map<String, Object>> storeFile(@RequestBody @Valid CalculationRequest request) {
        Map<String, Object> response = new HashMap<>();

        // Validate input JSON
        if (request.getFile() == null || request.getFile().isEmpty()) {
            response.put("file", null);
            response.put("error", "Invalid JSON input.");
            return ResponseEntity.badRequest().body(response);
        }

        // Store the file in the persistent storage
        boolean success = storeFileInStorage(request.getFile(), request.getData());

        // Handle the storage result
        if (success) {
            response.put("file", request.getFile());
            response.put("message", "Success.");
            return ResponseEntity.ok(response);
        } else {
            response.put("file", request.getFile());
            response.put("error", "Error while storing the file to the storage.");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/calculate")
    public ResponseEntity<Map<String, Object>> calculate(@RequestBody @Valid CalculationRequest request) {
        Map<String, Object> response = new HashMap<>();

        // Validate input JSON
        if (request.getFile() == null || request.getFile().isEmpty()) {
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

    private boolean storeFileInStorage(String fileName, String data) {
        try {
            // Create the file and write the data in CSV format
            Path filePath = Paths.get(baseDirectory, fileName);
            File file = filePath.toFile();

            try (FileWriter writer = new FileWriter(file);
                 CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("product", "amount"))) {

                // Parse the existing data and write it to the CSV file
                String[] lines = data.split("\n");
                for (int i = 1; i < lines.length; i++) {
                    String[] values = lines[i].split(",");
                    String trimmedProduct = values[0].trim();
                    String trimmedAmount = values[1].trim();
                    csvPrinter.printRecord(trimmedProduct, trimmedAmount);
                }
            }

            return true;
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return false;
        }
    }
}
