package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 允许所有路径
                .allowedOrigins("http://localhost:5173") // 前端开发地址
                .allowedMethods("*")    // 允许所有方法 GET, POST 等
                .allowedHeaders("*")    // 允许所有请求头
                .allowCredentials(true); // 允许携带 cookie
    }
}
