package com.example.demo.controller;

// 引入实体类 User
import com.example.demo.entity.User;
// 引入数据仓库接口 UserRepository，用于数据库操作
import com.example.demo.repository.UserRepository;
// 引入自动注入注解
import org.springframework.beans.factory.annotation.Autowired;
// 引入 HTTP 状态码和响应实体
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// 引入处理请求映射相关注解
import org.springframework.web.bind.annotation.*;

import java.util.Map;      // 用于接收请求体中的键值对
import java.util.Optional; // 用于处理可能为空的查询结果

// 声明这是一个 REST 控制器，返回的都是 JSON 格式数据
@RestController
// 该控制器所有接口路径都以 /api 开头
@RequestMapping("/api")
public class LoginController {

    // 自动注入 UserRepository，用于操作用户数据库
    @Autowired
    private UserRepository userRepository;

    // 处理 POST 请求，路径为 /api/login
    @PostMapping("/login")
    // 接收请求体为 Map<String, String>，返回 ResponseEntity 包含 Map 类型数据（JSON）
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> loginInfo) {
        // 从请求体中取出用户名，注意这里用的是 "username" 作为键
        String username = loginInfo.get("username");
        // 从请求体中取出密码
        String password = loginInfo.get("password");

        // 根据用户名查询用户信息，返回 Optional，防止空指针异常
        Optional<User> optionalUser = userRepository.findByUserid(username);

        // 判断是否找到了对应用户
        if (optionalUser.isPresent()) {
            // 获取用户实体
            User user = optionalUser.get();
            // 判断数据库中用户密码是否和请求中的密码一致
            if (user.getPassword().equals(password)) {
                // 密码匹配，登录成功，返回状态 200 和成功信息
                return ResponseEntity.ok(Map.of(
                        "success", true,
                        "message", "登录成功"
                ));
            }
        }

        // 用户不存在或密码错误，返回 401 未授权状态和失败信息
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                "success", false,
                "message", "账号或密码错误"
        ));
    }
}
