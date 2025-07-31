package com.example.demo.controller;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> loginInfo) {
        String username = loginInfo.get("username");
        String password = loginInfo.get("password");

        Optional<User> optionalUser = userRepository.findByUserid(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getPassword().equals(password)) {
                String token = JwtUtil.generateToken(username);

                return ResponseEntity.ok(Map.of(
                        "success", true,
                        "message", "登录成功",
                        "token", token
                ));
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                "success", false,
                "message", "账号或密码错误"
        ));
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody Map<String, String> registerInfo) {
        String username = registerInfo.get("username");
        String password = registerInfo.get("password");

        Optional<User> optionalUser = userRepository.findByUserid(username);

        if (optionalUser.isPresent()) {
            // 用户已存在
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                    "success", false,
                    "message", "用户名已存在"
            ));
        }

        // 创建新用户
        User newUser = new User();
        newUser.setUserid(username);
        newUser.setPassword(password);  // 生产环境请使用加密！

        userRepository.save(newUser);

        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "注册成功"
        ));
    }

    // ✅ 加入这个 GET 接口，用于 Render 保活或测试
    @GetMapping("/ping")
    public String ping() {
        return "OK";
    }
}
