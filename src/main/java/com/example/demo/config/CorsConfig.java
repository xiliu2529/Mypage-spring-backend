package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("https://cool-baklava-1b8557.netlify.app") // 用这个替代 allowedOrigins
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
