package com.example.demo.test;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ArticleApiTest implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        // 这个类可以在应用启动时执行一些测试代码
        // 目前留空，后续可以添加API测试逻辑
        System.out.println("文章API后端服务已启动...");
        System.out.println("可用的API端点:");
        System.out.println("- POST /api/login - 用户登录");
        System.out.println("- POST /api/register - 用户注册");
        System.out.println("- GET /api/articles - 获取用户文章列表");
        System.out.println("- POST /api/articles - 创建新文章");
        System.out.println("- GET /api/articles/{id} - 获取指定文章");
        System.out.println("- PUT /api/articles/{id} - 更新文章");
        System.out.println("- DELETE /api/articles/{id} - 删除文章");
        System.out.println("- GET /api/articles/search?keyword=xxx - 搜索文章");
        System.out.println("- GET /api/articles/date/{date} - 按日期获取文章");
    }
}