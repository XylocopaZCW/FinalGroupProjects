package com.example.demo.models;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

    
    @Entity 
    @Table (name = "Messages")

public class Message {
   


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @Column(name = "body", nullable = false)
    private String body;

    @Column(name = "date", nullable = false)
    private Date date;

    @JsonBackReference
    @ManyToOne
    private User user;

    @JsonBackReference
     @ManyToOne
     private Channel channel;



    public Message() {
        this.date = new Date();
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
    public Channel getChannel() {return channel;}
    public void setChannel(Channel channel) {this.channel = channel;}
    }
