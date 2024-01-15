package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "workspaces")
public class Workspace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workspaceId;

    @Column(name = "name", nullable = false, unique = true)
    private String workspaceName;
    @Column(name = "accessible", nullable = false)
    private Boolean accessible;
    @Column(name = "visible", nullable = false)
    private Boolean visible;

    // Gonna keep it as one admin (the user that created the workspace) for the time being
    @ManyToOne
    private User admin;

    @JsonManagedReference
    @ManyToMany
    private Set<User> users = new HashSet<>();

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "workspace")
    private Set<Channel> channels = new HashSet<>();

    public Workspace() {
    }

    public Workspace(String workspaceName, Boolean accessible, Boolean visible) {
        this.workspaceName = workspaceName;
        this.accessible = accessible;
        this.visible = visible;
    }

    public Long getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(Long workspaceId) {
        this.workspaceId = workspaceId;
    }

    public String getWorkspaceName() {
        return workspaceName;
    }

    public void setWorkspaceName(String name) {
        this.workspaceName = name;
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

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Channel> getChannels() {
        return channels;
    }

    public void setChannels(Set<Channel> channels) {
        this.channels = channels;
    }
}
