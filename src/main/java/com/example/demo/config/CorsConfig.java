package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

// 声明这是一个配置类，Spring容器启动时会扫描并加载它
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    // 重写 WebMvcConfigurer 接口中的 addCorsMappings 方法，用来配置跨域请求的规则
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        // 添加跨域映射路径，/** 表示允许所有路径的跨域请求
                // 允许的请求源（域名）模式，允许来自 "https://xiliu.netlify.app" 的请求，注意结尾的斜杠
        registry.addMapping("/**")
                .allowedOriginPatterns(
                        "http://localhost:5173",
                        "https://xiliu.netlify.app"
                )
                // 允许所有 HTTP 请求方法（GET, POST, PUT, DELETE等）
                .allowedMethods("*")
                // 允许所有请求头
                .allowedHeaders("*")
                // 允许携带凭证（如 Cookies、HTTP认证等）
                .allowCredentials(true);
    }
}
