package com.example.demo.dtos;

public class WorkspaceDto {

    private String name;
    private Boolean accessible;
    private Boolean visible;

    public WorkspaceDto() {
    }

    public WorkspaceDto(String name, Boolean accessible, Boolean visible) {
        this.name = name;
        this.accessible = accessible;
        this.visible = visible;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAccessible() {
        return accessible;
    }

    public void setAccessible(Boolean accessible) {
        this.accessible = accessible;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
}
