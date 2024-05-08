package com.example.rest.controller;

import com.example.rest.model.CalculationRequest;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ProcessController {

    private String baseDirectory = "/data/";

    @PostMapping("/process")
    public ResponseEntity<Map<String, Object>> process(@RequestBody CalculationRequest request) {
        Map<String, Object> response = new HashMap<>();

        File file = new File(baseDirectory, request.getFile());

        // Load the file into memory and calculate the sum
        Map<String, Object> result = calculateFromFile(file, request.getProduct());

        // Populate the response map based on the result
        response.put("file", request.getFile());
        response.putAll(result);

        return ResponseEntity.ok(response);
    }

    private Map<String, Object> calculateFromFile(File file, String targetProduct) {
        Map<String, Object> result = new HashMap<>();

        try (CSVParser csvParser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(file))) {
            int sum = 0;
            for (CSVRecord record : csvParser) {
                String product = record.get("product");
                if (targetProduct.equalsIgnoreCase(product)) {
                    int amount = Integer.parseInt(record.get("amount"));
                    sum += amount;
                }
            }

            if (sum == 0) {
                throw new IOException("Sum is zero.");
            }

            result.put("sum", sum);
        } catch (Exception e) {
            result.put("error", "Input file not in CSV format.");
        }

        return result;
    }
}
