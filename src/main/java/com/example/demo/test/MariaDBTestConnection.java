package com.example.demo.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MariaDBTestConnection {

    public static void main(String[] args) {
        String url = "jdbc:mysql://mysql-xiliubat.alwaysdata.net:3306/xiliubat_myapp_db?useSSL=true&serverTimezone=UTC&allowPublicKeyRetrieval=true&verifyServerCertificate=false";
        String username = "xiliubat";
        String password = "Hlt3507222";

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
