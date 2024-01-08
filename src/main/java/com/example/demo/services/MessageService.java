package com.example.demo.services;

import com.example.demo.dtos.MessageDto;
import com.example.demo.models.Message;
import com.example.demo.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void deleteMessage(MessageDto messageDto) {
        Message message = messageRepository.getReferenceById(messageDto.getMessageId());
        messageRepository.delete(message);
    }

    // getMessageById(Long messageId)

    // editMessage(Long messageId, MessageDto messageDto)

    // listMessagesInChannel(Long channelId)
}
