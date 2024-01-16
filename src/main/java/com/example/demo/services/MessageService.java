package com.example.demo.services;

import com.example.demo.dtos.MessageDto;
import com.example.demo.models.Channel;
import com.example.demo.models.Message;
import com.example.demo.models.User;
import com.example.demo.models.Workspace;
import com.example.demo.repositories.ChannelRepository;
import com.example.demo.repositories.MessageRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final ChannelRepository channelRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, UserRepository userRepository, ChannelRepository channelRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.channelRepository = channelRepository;
    }

    public Message sendMessageToChannel(MessageDto messageDto, Long userID, Long channelID) throws Exception {
        Message message = new Message();
        Channel channel = channelRepository.findById(channelID)
                .orElseThrow(() -> new Exception("Channel doesn't exist"));
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new Exception("User doesn't exist"));
        message.setBody(messageDto.getBody());
        message.setUser(user);
        channel.getMessages().add(message);
        messageRepository.save(message);
        channelRepository.save(channel);
        return message;
    }

    public Message sendMessage(MessageDto messageDto, Long userID) throws Exception {
        Message message = new Message();
        message.setBody(messageDto.getBody());
        message.setUser(userRepository.findById(userID)
                .orElseThrow(() -> new Exception("User doesn't exist")));
        return messageRepository.save(message);
    }

    public Message getMessageById(Long messageId) throws Exception {
        return messageRepository.findById(messageId)
                .orElseThrow(() -> new Exception("Message doesn't exist"));
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message editMessage(Long messageID, MessageDto messageDto) throws Exception {
        Message message = messageRepository.findById(messageID)
                .orElseThrow(() -> new Exception("Message doesn't exist"));
        message.setBody(messageDto.getBody());
        message.setDate(new Date());
        return messageRepository.save(message);
    }

    public void deleteMessage(MessageDto messageDto) throws Exception {
        Message message = messageRepository.findById(messageDto.getMessageId())
                .orElseThrow(() -> new Exception("Message doesn't exist"));
        messageRepository.delete(message);
    }

    public List<Message> getAllMessagesInChannel(Long channelID) throws Exception {
        Channel channel = channelRepository.findById(channelID)
                .orElseThrow(() -> new Exception("Channel doesn't exist"));
        List<Message> channelMessages = channel.getMessages();
//        if (channelMessages.get(0).getMessageId() > channelMessages.get(1).getMessageId()) {
            return channelMessages;
//        } else {
//            return channel.getMessages();
//        }
    }


}
