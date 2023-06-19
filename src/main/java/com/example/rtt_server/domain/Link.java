package com.example.rtt_server.domain;

public class Link {
    private int lineId;
    private String linkGuid;
    private int startNodeId;
    private int endNodeId;
    private String startNodeGuid;
    private String endNodeGuid;
    private String categoryName;
    private String familyName;
    private String geometry;

    public int getLineId() {
        return lineId;
    }

    public void setLineId(int lineId) {
        this.lineId = lineId;
    }

    public String getLinkGuid() {
        return linkGuid;
    }

    public void setLinkGuid(String linkGuid) {
        this.linkGuid = linkGuid;
    }

    public int getStartNodeId() {
        return startNodeId;
    }

    public void setStartNodeId(int startNodeId) {
        this.startNodeId = startNodeId;
    }

    public int getEndNodeId() {
        return endNodeId;
    }

    public void setEndNodeId(int endNodeId) {
        this.endNodeId = endNodeId;
    }

    public String getStartNodeGuid() {
        return startNodeGuid;
    }

    public void setStartNodeGuid(String startNodeGuid) {
        this.startNodeGuid = startNodeGuid;
    }

    public String getEndNodeGuid() {
        return endNodeGuid;
    }

    public void setEndNodeGuid(String endNodeGuid) {
        this.endNodeGuid = endNodeGuid;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getGeometry() {
        return geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }

}