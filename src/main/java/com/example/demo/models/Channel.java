package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "channels")
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long channelId;

    @Column(name = "channelname", nullable = false, unique = true)
    private String channelName;
    @Column(name = "accessible", nullable = false)
    private Boolean accessible;
    @Column(name = "visible", nullable = false)
    private Boolean visible;

    @ManyToMany
    private Set<Message> messages = new HashSet<>();

    @JsonIgnore
    @ManyToOne
    private Workspace workspace;

    @ManyToMany
    private Set<User> users = new LinkedHashSet<>();

    public Channel(){

    }

    public Channel(String channelName, Boolean accessible, Boolean visible) {
        this.channelName = channelName;
        this.accessible = accessible;
        this.visible = visible;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }
    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
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

    public  Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Workspace getWorkspace() {
        return workspace;
    }

    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }
}
