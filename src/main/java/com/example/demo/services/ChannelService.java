package com.example.demo.services;

import com.example.demo.dtos.ChannelDto;
import com.example.demo.models.Channel;
import com.example.demo.models.User;
import com.example.demo.repositories.ChannelRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ChannelService {
    private final ChannelRepository channelRepository;
    private final UserRepository userRepository;
    @Autowired
    public ChannelService(ChannelRepository channelRepository, UserRepository userRepository) {
        this.channelRepository = channelRepository;
        this.userRepository = userRepository;
    }
    public Channel createChannel(ChannelDto channelDto){
        Channel newChannel = new Channel();
        newChannel.setChannelname(channelDto.getChannelName());
        newChannel.setAccessible(channelDto.getAccessibility());
        newChannel.setVisible(channelDto.getVisible());
        return channelRepository.save(newChannel);
    }
    public Channel getChannelById(Long channelId) throws Exception {
        return channelRepository.findById(channelId)
                .orElseThrow(() -> new Exception("Channel doesn't exist"));


    }
    public List<Channel> getAllChannel(){
        return channelRepository.findAll();


    }
    public Channel updateChannel(Long channelId, ChannelDto channelDto) throws Exception {
        Channel channel = channelRepository.findById(channelId)
                .orElseThrow(() -> new Exception("Channel doesn't exist"));
        return channelRepository.save(channel);

    }
    public void deleteChannel(Long channelId) throws Exception{
        Channel  channel = channelRepository.findById(channelId)
                .orElseThrow(() -> new Exception("Channel doesn't exist"));
        channelRepository.delete(channel);
    }

    public User addUserToChannel(Long channelId, Long userId) throws Exception {
        Channel channel = channelRepository.findById(channelId)
                .orElseThrow(() -> new Exception("Channel doesn't exist"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("User Doesn't exist"));
        channel.getUsers().add(user);
        user.getChannels().add(channel);
        channelRepository.save(channel);
        return userRepository.save(user);
    }


    public void removeUserFromChannel(Long channelId, Long userId) throws Exception {
        Channel channel = channelRepository.findById(channelId)
                .orElseThrow(() -> new Exception("Channel doesn't exist"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("User doesn't exist"));
        channel.getUsers().remove(user);
        user.getChannels().remove(channel);
        channelRepository.save(channel);
        userRepository.save(user);
    }

    public Set<User> getAllUsersInChannel(Long channelId) throws Exception{
        Channel channel = channelRepository.findById(channelId)
        .orElseThrow(() -> new Exception("Channel doesn't exist"));
        return channel.getUsers();
    }
}
