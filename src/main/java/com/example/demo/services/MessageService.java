package com.example.demo.services;

import com.example.demo.dtos.MessageDto;
import com.example.demo.dtos.WorkspaceDto;
import com.example.demo.models.Message;
import com.example.demo.models.Workspace;
import com.example.demo.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message sendMessage(MessageDto messageDto) {
        Message message = new Message();
        message.setBody(messageDto.getBody());
        message.setDate(messageDto.getDate());

        return messageRepository.save(message);
    }

    public Message getMessageById(Long messageId) throws Exception {
        return messageRepository.findById(messageId)
                .orElseThrow(() -> new Exception("Message doesn't exist"));
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message editMessage(Long messageId, MessageDto messageDto) throws Exception {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new Exception("Message doesn't exist"));
        message.setBody(messageDto.getBody());
        return messageRepository.save(message);
    }

    public void deleteMessage(MessageDto messageDto) {
        Message message = messageRepository.getReferenceById(messageDto.getMessageId());
        messageRepository.delete(message);
    }

}
