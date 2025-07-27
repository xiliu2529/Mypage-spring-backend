package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 继承 JpaRepository，提供对 User 实体的 CRUD 操作，主键类型是 Long
public interface UserRepository extends JpaRepository<User, Long> {

    // 根据 userid 查询用户，返回 Optional 包装，避免空指针
    Optional<User> findByUserid(String userid);

}
