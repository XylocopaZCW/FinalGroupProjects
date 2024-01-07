package com.example.demo.services;

import com.example.demo.dtos.ChannelDto;
import com.example.demo.models.Channel;
import com.example.demo.repositories.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelService {
    private final ChannelRepository channelRepository;

    @Autowired
    public ChannelService(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
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
    public Channel updateChannel(Long channelId) throws Exception {
        Channel channel = channelRepository.findById(channelId)
                .orElseThrow(() -> new Exception("Channel doesn't exist"));
        return channelRepository.save(channel);

    }
    public void deleteChannel(Long channelId) throws Exception{
        Channel  channel = channelRepository.findById(channelId)
                .orElseThrow(() -> new Exception("Channel doesn't exist"));
        channelRepository.delete(channel);
    }
}
