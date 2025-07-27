package com.example.demo.entity;

// 引入 JPA 注解，用于实体类和表的映射
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

// 声明这是一个 JPA 实体类，和数据库表映射
@Entity
// 指定该实体对应的数据库表名为 "user"，请根据实际数据库表名修改
@Table(name = "user_info")
public class User {

    // 声明 id 字段为主键
    @Id
    // 主键生成策略，使用数据库自增（IDENTITY）
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 映射数据库列，不能为空且唯一，对应用户的唯一标识字段
    @Column(nullable = false, unique = true)
    private String userid;

    // 映射数据库列，不能为空，对应用户密码
    @Column(nullable = false)
    private String password;

    // 无参构造函数，JPA 需要
    public User() {}

    // id 的 getter 方法
    public Long getId() {
        return id;
    }

    // id 的 setter 方法
    public void setId(Long id) {
        this.id = id;
    }

    // userid 的 getter 方法
    public String getUserid() {
        return userid;
    }

    // userid 的 setter 方法
    public void setUserid(String userid) {
        this.userid = userid;
    }

    // password 的 getter 方法
    public String getPassword() {
        return password;
    }

    // password 的 setter 方法
    public void setPassword(String password) {
        this.password = password;
    }
}
