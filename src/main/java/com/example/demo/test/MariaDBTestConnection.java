package com.example.demo.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MariaDBTestConnection {

    public static void main(String[] args) {

        // 数据库连接的 URL，不包含账号和密码，指定了数据库类型、地址、端口和数据库名
        String url = "jdbc:postgresql://aws-0-ap-northeast-1.pooler.supabase.com:5432/postgres";

        // 数据库账号
        String username = "postgres.odqwkogljynurbdtbxcj";
        // 数据库密码
        String password = "NKkxKROiN8L53pJ2";

        try {
            System.out.println("正在尝试连接数据库...");  // 打印连接开始提示
            // 使用 DriverManager 获取数据库连接
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("✅ 数据库连接成功！");    // 连接成功提示
            conn.close();  // 关闭连接，释放资源
        } catch (SQLException e) {
            System.out.println("❌ 数据库连接失败！");  // 连接失败提示
            e.printStackTrace();  // 打印异常堆栈信息，便于调试
        }
    }
}
