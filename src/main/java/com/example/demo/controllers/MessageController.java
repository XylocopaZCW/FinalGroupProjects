package com.example.demo.controllers;

import com.example.demo.dtos.MessageDto;
import com.example.demo.models.Channel;
import com.example.demo.models.Message;
import com.example.demo.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/messages")
public class MessageController {

    Logger logger = Logger.getLogger("MessageController");
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/sendMessage/{userId}")
    public ResponseEntity<?> sendMessage(@PathVariable Long userId, @RequestBody MessageDto messageDto) throws Exception {
        Message newMessage = messageService.sendMessage(messageDto, userId);
        return new ResponseEntity<>(newMessage, HttpStatus.OK);
    }

    @PostMapping("/sendMessage/{userId}/{channelId}")
    public ResponseEntity<?> sendMessageToChannel(@PathVariable Long userId, @PathVariable Long channelId, @RequestBody MessageDto messageDto) throws Exception {
        Message newMessage = messageService.sendMessageToChannel(messageDto, userId, channelId);
        return new ResponseEntity<>(newMessage, HttpStatus.OK);
    }

    @GetMapping("channel/{channelId}")
    public ResponseEntity<?> getAllMessagesInChannel(@PathVariable Long channelId) throws Exception {
        return new ResponseEntity<>(messageService.getAllMessagesInChannel(channelId), HttpStatus.OK);
    }

     @GetMapping("/{messageId}")
     public ResponseEntity<?> getMessageById(@PathVariable  Long messageId) throws Exception {
        Message message = messageService.getMessageById(messageId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/{messageId}")
    public ResponseEntity<?> editMessage(@PathVariable Long messageId, @RequestBody MessageDto messageDto) throws Exception{
            Message message = messageService.editMessage(messageId, messageDto);
            return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/getAllMessages")
    public ResponseEntity<?> getAllMessages() {
        try {
            List<Message> messageList =  messageService.getAllMessages();
            return new ResponseEntity<>(messageList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteMessage")
    public ResponseEntity<?> deleteMessage(@RequestBody MessageDto messageDto){
        try {
             messageService.deleteMessage(messageDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
