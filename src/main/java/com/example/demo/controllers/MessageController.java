package com.example.demo.controllers;

import com.example.demo.dtos.MessageDto;
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

    @PostMapping("/sendMessage/{userID}")
    public ResponseEntity<?> sendMessage(@PathVariable Long userID, @RequestBody MessageDto messageDto) throws Exception {
        Message newMessage = messageService.sendMessage(messageDto, userID);
        return new ResponseEntity<>(newMessage, HttpStatus.OK);
    }

    @PostMapping("/sendMessage/{userID}/{channelID}")
    public ResponseEntity<?> sendMessageToChannel(@PathVariable Long userID, @PathVariable Long channelID, @RequestBody MessageDto messageDto) throws Exception {
        Message newMessage = messageService.sendMessage(messageDto, userID);
        return new ResponseEntity<>(newMessage, HttpStatus.OK);
    }

     @GetMapping("/{messageID}")
     public ResponseEntity<?> getMessageById(@PathVariable  Long messageID) throws Exception {
        Message message = messageService.getMessageById(messageID);
        return new ResponseEntity<>(message, HttpStatus.OK);

    }

    @PutMapping("/{messageID}")
    public ResponseEntity<?> editMessage(@PathVariable Long messageID, @RequestBody MessageDto messageDto) throws Exception{
            Message message = messageService.editMessage(messageID, messageDto);
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
