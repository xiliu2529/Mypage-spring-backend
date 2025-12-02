package com.example.demo.repository;

import com.example.demo.entity.Article;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    // 根据用户查找文章
    List<Article> findByUser(User user);

    // 根据用户ID查找文章
    List<Article> findByUserId(Long userId);

    // 根据用户ID查找文章，按创建时间倒序
    @Query("SELECT a FROM Article a WHERE a.user.userId = :userId ORDER BY a.createdAt DESC")
    List<Article> findByUserIdOrderByCreatedAtDesc(@Param("userId") Long userId);

    // 搜索文章（标题和内容）
    @Query("SELECT a FROM Article a WHERE a.user.userId = :userId AND " +
           "(LOWER(a.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(a.content) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
           "ORDER BY a.createdAt DESC")
    List<Article> searchByUserIdAndKeyword(@Param("userId") Long userId, @Param("keyword") String keyword);

    // 根据日期查找文章
    List<Article> findByUserIdAndDate(Long userId, String date);
}