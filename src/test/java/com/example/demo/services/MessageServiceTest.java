package com.example.demo.services;

import com.example.demo.dtos.MessageDto;
import com.example.demo.models.Message;
import com.example.demo.repositories.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;
    @Mock
    private Message message;
    @InjectMocks
    private MessageService messageService;
    private MessageDto messageDto;

    @BeforeEach
    public void setUp() {
        message = new Message("This is a test message", Date.valueOf("2024-01-07"));
        messageDto = new MessageDto(message);
    }
    @Test
    void sendMessageTest() {
        doReturn(new Message(messageDto.getBody(), messageDto.getDate())).when(messageRepository).save(any(Message.class));

        Message sentMessage = messageService.sendMessage(messageDto);

        assertNotNull(sentMessage);
        assertEquals(messageDto.getMessageId(), sentMessage.getMessageId());
    }

    @Test
    void deleteMessageTest() {
        doNothing().when(messageRepository).delete(any());

        messageService.deleteMessage(messageDto);

        assertNull(messageRepository.getReferenceById(messageDto.getMessageId()));
    }
}