package com.example.demo.service;

import com.example.demo.dto.ArticleDto;
import com.example.demo.entity.Article;
import com.example.demo.entity.User;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * 获取用户的所有文章
     */
    @Transactional(readOnly = true)
    public List<ArticleDto> getUserArticles(String username) {
        User user = findUserByUsername(username);
        List<Article> articles = articleRepository.findByUserIdOrderByCreatedAtDesc(user.getUserId());
        return articles.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * 根据ID获取文章
     */
    @Transactional(readOnly = true)
    public Optional<ArticleDto> getArticleById(Long id, String username) {
        User user = findUserByUsername(username);
        Optional<Article> articleOpt = articleRepository.findById(id);
        
        if (articleOpt.isPresent()) {
            Article article = articleOpt.get();
            if (article.getUser().getUserId().equals(user.getUserId())) {
                return Optional.of(convertToDto(article));
            }
        }
        return Optional.empty();
    }

    /**
     * 创建文章
     */
    public ArticleDto createArticle(ArticleDto.CreateArticleRequest request, String username) {
        // 验证输入
        if (request.getTitle() == null || request.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("标题不能为空");
        }
        if (request.getContent() == null || request.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("内容不能为空");
        }

        User user = findUserByUsername(username);
        
        Article article = new Article();
        article.setTitle(request.getTitle().trim());
        article.setContent(request.getContent().trim());
        article.setDate(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
        article.setUser(user);
        article.setUserId(user.getUserId());
        article.setCreatedAt(LocalDateTime.now());
        article.setUpdatedAt(LocalDateTime.now());

        Article savedArticle = articleRepository.save(article);
        return convertToDto(savedArticle);
    }

    /**
     * 更新文章
     */
    public Optional<ArticleDto> updateArticle(Long id, ArticleDto.UpdateArticleRequest request, String username) {
        User user = findUserByUsername(username);
        Optional<Article> articleOpt = articleRepository.findById(id);
        
        if (articleOpt.isEmpty()) {
            return Optional.empty();
        }

        Article article = articleOpt.get();
        if (!article.getUser().getUserId().equals(user.getUserId())) {
            throw new SecurityException("无权修改此文章");
        }

        boolean hasChanges = false;
        if (request.getTitle() != null && !request.getTitle().trim().isEmpty()) {
            article.setTitle(request.getTitle().trim());
            hasChanges = true;
        }
        if (request.getContent() != null && !request.getContent().trim().isEmpty()) {
            article.setContent(request.getContent().trim());
            hasChanges = true;
        }

        if (hasChanges) {
            article.setUpdatedAt(LocalDateTime.now());
            Article updatedArticle = articleRepository.save(article);
            return Optional.of(convertToDto(updatedArticle));
        }

        return Optional.of(convertToDto(article));
    }

    /**
     * 删除文章
     */
    public boolean deleteArticle(Long id, String username) {
        User user = findUserByUsername(username);
        Optional<Article> articleOpt = articleRepository.findById(id);
        
        if (articleOpt.isEmpty()) {
            return false;
        }

        Article article = articleOpt.get();
        if (!article.getUser().getUserId().equals(user.getUserId())) {
            throw new SecurityException("无权删除此文章");
        }

        articleRepository.delete(article);
        return true;
    }

    /**
     * 搜索文章
     */
    @Transactional(readOnly = true)
    public List<ArticleDto> searchArticles(String keyword, String username) {
        User user = findUserByUsername(username);
        List<Article> articles = articleRepository.searchByUserIdAndKeyword(user.getUserId(), keyword);
        return articles.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * 根据日期获取文章
     */
    @Transactional(readOnly = true)
    public List<ArticleDto> getArticlesByDate(String date, String username) {
        User user = findUserByUsername(username);
        List<Article> articles = articleRepository.findByUserIdAndDate(user.getUserId(), date);
        return articles.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * 根据用户名查找用户
     */
    private User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("用户不存在: " + username));
    }

    /**
     * 将Article实体转换为DTO
     */
    private ArticleDto convertToDto(Article article) {
        ArticleDto dto = new ArticleDto();
        dto.setId(article.getArticleId());
        dto.setTitle(article.getTitle());
        dto.setContent(article.getContent());
        dto.setDate(article.getDate());
        dto.setCreatedAt(article.getCreatedAt());
        dto.setUpdatedAt(article.getUpdatedAt());
        return dto;
    }
}