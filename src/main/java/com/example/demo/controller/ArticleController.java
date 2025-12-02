package com.example.demo.controller;

import com.example.demo.dto.ArticleDto;
import com.example.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    // 获取当前用户的文章列表
    @GetMapping
    public ResponseEntity<Map<String, Object>> getArticles(Authentication authentication) {
        try {
            String username = authentication.getName();
            List<ArticleDto> articles = articleService.getUserArticles(username);

            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "data", articles
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("success", false, "message", "获取文章失败: " + e.getMessage()));
        }
    }

    // 根据ID获取文章
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getArticleById(@PathVariable Long id, Authentication authentication) {
        try {
            String username = authentication.getName();
            Optional<ArticleDto> article = articleService.getArticleById(id, username);
            
            if (article.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("success", false, "message", "文章不存在"));
            }

            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "data", article.get()
            ));
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of("success", false, "message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("success", false, "message", "获取文章失败: " + e.getMessage()));
        }
    }

    // 创建文章
    @PostMapping
    public ResponseEntity<Map<String, Object>> createArticle(@RequestBody ArticleDto.CreateArticleRequest request, 
                                                           Authentication authentication) {
        try {
            String username = authentication.getName();
            ArticleDto article = articleService.createArticle(request, username);

            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "success", true,
                    "message", "文章创建成功",
                    "data", article
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("success", false, "message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("success", false, "message", "创建文章失败: " + e.getMessage()));
        }
    }

    // 更新文章
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateArticle(@PathVariable Long id, 
                                                           @RequestBody ArticleDto.UpdateArticleRequest request,
                                                           Authentication authentication) {
        try {
            String username = authentication.getName();
            Optional<ArticleDto> article = articleService.updateArticle(id, request, username);
            
            if (article.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("success", false, "message", "文章不存在"));
            }

            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "文章更新成功",
                    "data", article.get()
            ));
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of("success", false, "message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("success", false, "message", "更新文章失败: " + e.getMessage()));
        }
    }

    // 删除文章
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteArticle(@PathVariable Long id, Authentication authentication) {
        try {
            String username = authentication.getName();
            boolean deleted = articleService.deleteArticle(id, username);
            
            if (!deleted) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("success", false, "message", "文章不存在"));
            }

            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "文章删除成功"
            ));
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of("success", false, "message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("success", false, "message", "删除文章失败: " + e.getMessage()));
        }
    }

    // 搜索文章
    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchArticles(@RequestParam String keyword, 
                                                             Authentication authentication) {
        try {
            String username = authentication.getName();
            List<ArticleDto> articles = articleService.searchArticles(keyword, username);

            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "data", articles
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("success", false, "message", "搜索文章失败: " + e.getMessage()));
        }
    }

    // 根据日期获取文章
    @GetMapping("/date/{date}")
    public ResponseEntity<Map<String, Object>> getArticlesByDate(@PathVariable String date, 
                                                                Authentication authentication) {
        try {
            String username = authentication.getName();
            List<ArticleDto> articles = articleService.getArticlesByDate(date, username);

            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "data", articles
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("success", false, "message", "获取文章失败: " + e.getMessage()));
        }
    }
}