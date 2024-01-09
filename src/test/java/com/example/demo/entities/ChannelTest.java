package com.example.demo.entities;

import com.example.demo.models.Channel;
import com.example.demo.models.Workspace;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ChannelTest {

    @Test
    public void channelSettersAndGettersTest() {
        Channel channel = new Channel();
        channel.setChannelId(1L);
        channel.setChannelname("Test");
        channel.setAccessible(true);
        channel.setVisible(true);

        assertEquals(1L, channel.getChannelId());
        assertEquals("Test", channel.getChannelname());
        assertTrue(channel.getAccessible());
        assertTrue(channel.getVisible());
    }

}