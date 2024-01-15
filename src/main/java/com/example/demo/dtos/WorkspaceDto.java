package com.example.demo.dtos;

public class WorkspaceDto {

    private String workspaceName;
    private Boolean accessible;
    private Boolean visible;

    public WorkspaceDto() {
    }

    public WorkspaceDto(String workspaceName, Boolean accessible, Boolean visible) {
        this.workspaceName = workspaceName;
        this.accessible = accessible;
        this.visible = visible;
    }

    public String getWorkspaceName() {
        return workspaceName;
    }

    public void setWorkspaceName(String workspaceName) {
        this.workspaceName = workspaceName;
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
