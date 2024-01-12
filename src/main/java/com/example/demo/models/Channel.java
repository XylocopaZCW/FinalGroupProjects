package com.example.demo.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "channels")
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long channelId;

    @Column(name = "channelname", nullable = false, unique = true)
    private String channelname;
    @Column(name = "accessible", nullable = false)
    private Boolean accessible;
    @Column(name = "visible", nullable = false)
    private Boolean visible;

    @ManyToMany
    private Set<Message> messages = new HashSet<>();

    @ManyToOne
    private Workspace workspace;

    @ManyToMany
    private Set<User> users = new HashSet<>();

    public Channel(){

    }

    public Channel(String channelname, Boolean accessible, Boolean visible) {
        this.channelname = channelname;
        this.accessible = accessible;
        this.visible = visible;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }
    public String getChannelname() {
        return channelname;
    }

    public void setChannelname(String channelname) {
        this.channelname = channelname;
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

}
