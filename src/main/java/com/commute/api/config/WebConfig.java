package com.commute.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // CORS를 적용할 API 경로 패턴
                .allowedOrigins("http://localhost:3000") // Next.js 주소
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true) // 쿠키/인증 헤더 포함 허용 시 필요
                .maxAge(3600); // Preflight 요청 결과 캐싱 시간 (초)
    }
}