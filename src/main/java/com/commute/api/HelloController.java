package com.commute.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * API疎通確認用のハローコントローラー。
 * Hello controller for API connectivity checks.
 *
 * @author Kim Gwangjin
 * @since 2026/09/27
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000") // Next.jsクライアントを許可 / Allow Next.js client
public class HelloController {

    /**
     * 疎通確認用のハローメッセージを返却する。
     * Returns a hello message for connectivity verification.
     *
     * @return メッセージとステータスを含むMap / Map containing message and status
     */
    @GetMapping("/hello")
    public Map<String, String> getHello() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello from Spring Boot 3.5!");
        response.put("status", "success");
        return response;
    }
}
