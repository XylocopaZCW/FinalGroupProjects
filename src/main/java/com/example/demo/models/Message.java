package com.example.demo.models;
import java.sql.Date;
import java.util.Set;
import java.util.HashSet;

import jakarta.persistence.*;

    
    @Entity 
    @Table (name = "Messages")

public class Message {
   

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @Column(name = "body", nullable = false, unique = true)
    private String body;

    @Column(name = "date", nullable = false, unique = true)
    private Date date;

    @ManyToOne
    private User user;

     @ManyToMany
     private Set<Channel> channels = new HashSet<>();



    public Message() {
    }

    public Message(String body, Date date ) {
        this.body = body;
        this.date = date;
        
    }


    public Long getMessageId() {
        return this.messageId;
    }
    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }
    public String getBody() {
        return this.body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public Date getDate() {
        return this.date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public User getUser() {
        return this.user;
    }
    public void setUser(User user) {
        this.user = user;
    }
     public Set<Channel> getChannels() {
         return this.channels;
     }
     public void setChannels(Set<Channel> channels) {
         this.channels = channels;
     }
}
