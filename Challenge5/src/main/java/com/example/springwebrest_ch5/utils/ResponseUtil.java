package com.example.springwebrest_ch5.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {
    public static ResponseEntity<Map<String, Object>> successResponse(String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", message);
        return ResponseEntity.ok(response);
    }
    public static <T> ResponseEntity<Map<String, Object>> successResponseWithData(String message, T data) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", message);
        response.put("data", data);
        return ResponseEntity.ok(response);
    }

    public static ResponseEntity<Map<String, Object>> errorResponse(String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", message);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

    }

    public static ResponseEntity<Map<String, Object>> notFoundResponse(String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "not found");
        response.put("message", message);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
