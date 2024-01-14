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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
        message.setMessageId(1L);
        messageDto = new MessageDto(message);
    }

//    @Test
//    void sendMessageTest() {
//        doReturn(message).when(messageRepository).save(any(Message.class));
//
//        Message sentMessage = messageService.sendMessage(messageDto);
//
//        assertNotNull(sentMessage);
//        assertEquals(messageDto.getMessageId(), sentMessage.getMessageId());
//    }

    @Test
    void deleteMessageTest() throws Exception {
        doReturn(Optional.of(message)).when(messageRepository).findById(messageDto.getMessageId());

        messageService.deleteMessage(messageDto);

        verify(messageRepository).delete(message);
    }

    @Test
    void deleteMessageTest_Exception() {
        doReturn(Optional.of(message)).when(messageRepository).findById(messageDto.getMessageId());
        doThrow(new IllegalArgumentException()).when(messageRepository).delete(any(Message.class));

        assertThrows(Exception.class, () -> messageService.deleteMessage(messageDto));
    }

    @Test
    void getMessageByIdTest() throws Exception {
        doReturn(Optional.of(message)).when(messageRepository).findById(messageDto.getMessageId());

        Message returnedMessage = messageService.getMessageById(messageDto.getMessageId());

        assertNotNull(returnedMessage);
        assertEquals(messageDto.getMessageId(), returnedMessage.getMessageId());
    }

    @Test
    void getMessageByIdTest_Exception() {
        doThrow(new IllegalArgumentException()).when(messageRepository).findById(any(Long.class));

        assertThrows(Exception.class, () -> messageService.getMessageById(messageDto.getMessageId()));
    }

    @Test
    void getAllMessagesTest() {
        List<Message> messageList =  new ArrayList<>();
        messageList.add(new Message("This is message one", Date.valueOf("2024-01-08")));
        messageList.add(new Message("This is message two", Date.valueOf("2024-01-08")));
        messageList.add(new Message("This is message three", Date.valueOf("2024-01-08")));

        doReturn(messageList).when(messageRepository).findAll();

        List<Message> allMessages = messageService.getAllMessages();

        assertNotNull(allMessages);
        assertEquals(3, allMessages.size());
    }

//    @Test
//    void editMessageTest() throws Exception {
//        doReturn(Optional.of(message)).when(messageRepository).findById(messageDto.getMessageId());
//        doReturn(message).when(messageRepository).save(any(Message.class));
//
//        messageDto.setBody("This is an edited body");
//        Message editedMessage = messageService.editMessage(messageDto);
//
//        assertNotNull(editedMessage);
//        assertEquals(messageDto.getMessageId(), editedMessage.getMessageId());
//        assertEquals("This is an edited body", editedMessage.getBody());
//    }

//    @Test
//    void editMessageTest_Exception() {
//        doReturn(Optional.of(message)).when(messageRepository).findById(messageDto.getMessageId());
//        doThrow(new IllegalArgumentException()).when(messageRepository).save(any(Message.class));
//
//        assertThrows(Exception.class, () -> messageService.editMessage(messageDto));
//    }

}