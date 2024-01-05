package com.example.demo.models;
import java.sql.Date;
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
        
}
