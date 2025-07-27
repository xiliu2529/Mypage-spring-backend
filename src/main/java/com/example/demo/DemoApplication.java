package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // 标注这是一个Spring Boot应用的主配置类，自动开启组件扫描和自动配置
public class DemoApplication {

	public static void main(String[] args) {
		// 启动Spring Boot应用，启动内嵌的Tomcat服务器，初始化Spring上下文
		SpringApplication.run(DemoApplication.class, args);
	}

}
