package com.example.rtt_server.util;

import java.sql.*;

public class DatabaseConnector {
    private static final String DB_URL = "";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://" + DB_URL, DB_USER, DB_PASSWORD);
    }

    public static void testConnection() {
        Connection conn = null;
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            // 연결 테스트용 SQL 쿼리 실행
            ResultSet resultSet = statement.executeQuery("SELECT * FROM link");
            if (resultSet.next()) {
                int lineId = resultSet.getInt("Link_id");
                String linkGuid = resultSet.getString("Link_guid");
                int startNodeId = resultSet.getInt("Start_node_id");
                int endNodeId = resultSet.getInt("End_node_id");
                String startNodeGuid = resultSet.getString("Start_node_guid");
                String endNodeGuid = resultSet.getString("End_node_guid");
                String categoryName = resultSet.getString("Category_name");
                String familyName = resultSet.getString("Family_name");
                System.out.println("MySQL 연결 성공");
                System.out.println("임의로 값을 읽어오자!");

                System.out.println("Line ID: " + lineId + ", Link GUID: " + linkGuid + ", Start Node ID: " + startNodeId +
                        ", End Node ID: " + endNodeId + ", Start Node GUID: " + startNodeGuid +
                        ", End Node GUID: " + endNodeGuid + ", Category Name: " + categoryName +
                        ", Family Name: " + familyName);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("MySQL 연결 실패: " + e.getMessage());
        }
    }
}