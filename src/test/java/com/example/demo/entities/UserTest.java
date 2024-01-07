package com.example.demo.entities;

import com.example.demo.models.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void userSettersAndGettersTest() {
        User user = new User();
        user.setUserId(1L);
        user.setUsername("Test");
        user.setEmail("Test@test.com");
        user.setPassword("Test");

        assertEquals(1L, user.getUserId());
        assertEquals("Test", user.getUsername());
        assertEquals("Test@test.com", user.getEmail());
        assertEquals("Test", user.getPassword());
    }

}
