package com.example.client.model;

public class CalculationRequest {
    private String file;
    private String data; // Added 'data' field
    private String product;

    // getters and setters

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
