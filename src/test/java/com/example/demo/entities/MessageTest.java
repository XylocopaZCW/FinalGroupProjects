package com.example.demo.entities;

import com.example.demo.models.Message;
import com.example.demo.models.User;
import org.junit.jupiter.api.Test;


import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    public void messageGettersAndSettersTest() {
        Message message = new Message();
        User user = new User();

        message.setMessageId(1L);
        message.setBody("This is a test message");
        message.setDate(Date.valueOf("2024-01-07"));
        message.setUser(user);

        assertEquals(1L, message.getMessageId());
        assertEquals("This is a test message", message.getBody());
        assertEquals(Date.valueOf("2024-01-07"), message.getDate());
        assertEquals(user, message.getUser());
    }

}
