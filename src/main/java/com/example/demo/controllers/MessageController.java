package com.example.demo.controllers;

import com.example.demo.dtos.MessageDto;
import com.example.demo.models.Message;
import com.example.demo.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/sendMessage")
    public ResponseEntity<?> sendMessage(@RequestBody MessageDto messageDto){
        try {
            Message newMessage = messageService.sendMessage(messageDto);
            return new ResponseEntity<>(newMessage, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

     @GetMapping("/getMessageById")
     public ResponseEntity<?> getMessageById(@RequestBody MessageDto messageDto) {
        try {
            Message message = messageService.getMessageById(messageDto.getMessageId());
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/editMessage")
    public ResponseEntity<?> editMessage(@RequestBody MessageDto messageDto){
        try{
            Message message = messageService.editMessage(messageDto);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllMessages")
    public ResponseEntity<?> getAllMessages(@RequestBody MessageDto messageDto) {
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
