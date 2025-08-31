package com.example.demo.entity;

// 引入 JPA 注解，用于实体类和表的映射
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_info")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;  // 对应 user_id 自增主键

    @Column(name = "username", nullable = false, length = 50, unique = true)
    private String username;  // 对应用户名

    @Column(name = "email", length = 100, unique = true)
    private String email;  // 用户邮箱，可空

    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;  // 密码哈希

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    public User() {}

    // getter & setter ...
}
