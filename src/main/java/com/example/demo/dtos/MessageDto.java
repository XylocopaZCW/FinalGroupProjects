package com.example.demo.dtos;


import com.example.demo.models.Message;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;

public class MessageDto {

    private Long messageId;

    @NotBlank(message = "")
    @NotBlank(message = "Message body is required")
    @Size(min = 1, max = 500, message = "Message must be between 1-500 characters")
    private String body;
    @NotBlank(message = "Date is required")
    private Date date;

    public MessageDto() {
    }

    public MessageDto(Message message) {
        this.messageId = message.getMessageId();
        this.body = message.getBody();
        this.date = message.getDate();
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }
}



