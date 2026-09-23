package com.commute.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000") // Next.js 클라이언트 허용
public class HelloController {

    @GetMapping("/hello")
    public Map<String, String> getHello() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello from Spring Boot 3.5!");
        response.put("status", "success");
        return response;
    }
}