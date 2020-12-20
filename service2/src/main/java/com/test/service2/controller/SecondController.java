package com.test.service2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/second")
class SecondController {
    private Map<String, Object> users = new HashMap<>();

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Second Test successfull");
    }

    @GetMapping("/error")
    public ResponseEntity<String> error() {
        return ResponseEntity.ok("You did something wrong");
    }
}