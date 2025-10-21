package com.example.clinic.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    
    private String name = "Smart Clinic";
    private String version = "1.0.0";
    
    private JwtProperties jwt = new JwtProperties();
    
    @Data
    public static class JwtProperties {
        private String secret = "your-secret-key-change-this-in-production-must-be-at-least-256-bits";
        private long expirationMs = 86400000; // 24 hours
        private String issuer = "smart-clinic";
    }
}
