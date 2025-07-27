package com.example.demo.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MariaDBTestConnection {

    public static void main(String[] args) {

        // 这里 URL 不包含账号密码
        String url = "jdbc:postgresql://aws-0-ap-northeast-1.pooler.supabase.com:5432/postgres";

        // 账号密码单独写
        String username = "postgres.odqwkogljynurbdtbxcj";
        String password = "NKkxKROiN8L53pJ2";

        try {
            System.out.println("正在尝试连接数据库...");
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("✅ 数据库连接成功！");
            conn.close();
        } catch (SQLException e) {
            System.out.println("❌ 数据库连接失败！");
            e.printStackTrace();
        }
    }
}
