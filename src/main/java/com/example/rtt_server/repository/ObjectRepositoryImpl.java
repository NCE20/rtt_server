package com.example.rtt_server.repository;

import com.example.rtt_server.domain.Object;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ObjectRepositoryImpl implements ObjectRepository {
    private final JdbcTemplate jdbcTemplate;

    public ObjectRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Object findById(int objectId) {
        String query = "SELECT * FROM node WHERE Node_id = ?";
        return jdbcTemplate.queryForObject(query, new ObjectMapper(), objectId);
    }

    private static class ObjectMapper implements RowMapper<Object> {
        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            Object object = new Object();
            object.setNodeId(rs.getInt("Node_id"));
            object.setNodeGuid(rs.getString("Node_guid"));
            object.setCategoryName(rs.getString("Category_name"));
            object.setFamilyName(rs.getString("Family_name"));
            object.setGeometry(rs.getString("Geometry"));
            return object;
        }
    }
}
