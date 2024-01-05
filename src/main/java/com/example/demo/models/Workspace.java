package com.example.demo.models;

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
    private String name;
    @Column(name = "accessible", nullable = false)
    private Boolean accessible;
    @Column(name = "visible", nullable = false)
    private Boolean visible;

    // TODO: Add User admin field

//    @Column(name = "admin", nullable = false, unique = true)
//    private Set<User> admin = new HashSet<>();

//    @ManyToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @ManyToMany
    private Set<User> users = new HashSet<>();

//    @OneToMany
//    private Set<Channel> channels = new HashSet<>();


    public Workspace() {
    }

    public Workspace(String name, Boolean accessible, Boolean visible) {
        this.name = name;
        this.accessible = accessible;
        this.visible = visible;
    }

    public Long getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(Long workspaceId) {
        this.workspaceId = workspaceId;
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
