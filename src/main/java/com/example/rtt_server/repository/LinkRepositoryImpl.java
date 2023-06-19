package com.example.rtt_server.repository;

import com.example.rtt_server.domain.Link;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class LinkRepositoryImpl implements LinkRepository {
    private final JdbcTemplate jdbcTemplate;

    public LinkRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Link findById(int linkId) {
        String query = "SELECT * FROM link WHERE Line_id = ?";
        return jdbcTemplate.queryForObject(query, new LinkMapper(), linkId);
    }

    private static class LinkMapper implements RowMapper<Link> {
        @Override
        public Link mapRow(ResultSet rs, int rowNum) throws SQLException {
            Link link = new Link();
            link.setLineId(rs.getInt("Line_id"));
            link.setLinkGuid(rs.getString("Link_guid"));
            link.setStartNodeId(rs.getInt("Start_node_id"));
            link.setEndNodeId(rs.getInt("End_node_id"));
            link.setStartNodeGuid(rs.getString("Start_node_guid"));
            link.setEndNodeGuid(rs.getString("End_node_guid"));
            link.setCategoryName(rs.getString("Category_name"));
            link.setFamilyName(rs.getString("Family_name"));
            link.setGeometry(rs.getString("Geometry"));
            return link;
        }

    }
}
