package com.example.demo.models;

import jakarta.persistence.*;

import java.util.HashSet;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    // TODO: un-comment out as the entities become available

//    @ManyToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<Workspace> workspaces = new HashSet<>();
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<Message> messages = new HashSet<>();

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // TODO: un-comment out as the entities become available

//    public Set<Workspace> getUserLanguages() {
//        return workspaces;
//    }
//
//    public void setUserLanguages(Set<Workspace> userLanguages) {
//        this.workspaces = userLanguages;
//    }
//
//    public Set<Message> getUserLanguages() {
//        return messages;
//    }
//
//    public void setUserLanguages(Set<Message> userLanguages) {
//        this.messages = userLanguages;
//    }
}
