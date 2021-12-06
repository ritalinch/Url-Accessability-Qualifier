package com.example.demo.dto.results;

public class BadRequestResult extends ResultDTO {
    public BadRequestResult() {
        super("JSON deserialization failed");
    }
}
