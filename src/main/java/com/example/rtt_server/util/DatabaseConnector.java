package com.example.rtt_server.util;

import java.sql.*;

public class DatabaseConnector {
    private static final String DB_URL = "localhost:8889/DSC01";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void testConnection() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            // 연결 테스트용 SQL 쿼리 실행
            ResultSet resultSet = statement.executeQuery("SELECT 1");
            if (resultSet.next()) {
                System.out.println("MySQL 연결 성공");
            }
        } catch (SQLException e) {
            System.out.println("MySQL 연결 실패: " + e.getMessage());
        }
    }
}
