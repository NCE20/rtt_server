package com.example.rtt_server.domain;

public class Object {
    private int nodeId;
    private String nodeGuid;
    private String categoryName;
    private String familyName;
    private String geometry;

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeGuid() {
        return nodeGuid;
    }

    public void setNodeGuid(String nodeGuid) {
        this.nodeGuid = nodeGuid;
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
