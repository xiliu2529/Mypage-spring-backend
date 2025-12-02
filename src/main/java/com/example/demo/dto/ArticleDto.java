package com.example.demo.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ArticleDto {
    private Long id;
    private String title;
    private String content;
    private String date;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Data
    public static class CreateArticleRequest {
        private String title;
        private String content;
    }

    @Data
    public static class UpdateArticleRequest {
        private String title;
        private String content;
    }
}