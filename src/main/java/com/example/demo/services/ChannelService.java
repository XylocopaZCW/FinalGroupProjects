package com.example.demo.services;

import com.example.demo.dtos.ChannelDto;
import com.example.demo.models.Channel;
import com.example.demo.models.Message;
import com.example.demo.models.User;
import com.example.demo.models.Workspace;
import com.example.demo.repositories.ChannelRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.WorkspaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ChannelService {
    private final ChannelRepository channelRepository;
    private final UserRepository userRepository;
    private final WorkspaceRepository workspaceRepository;
    @Autowired
    public ChannelService(ChannelRepository channelRepository, UserRepository userRepository, WorkspaceRepository workspaceRepository) {
        this.channelRepository = channelRepository;
        this.userRepository = userRepository;
        this.workspaceRepository = workspaceRepository;
    }
    public Channel createChannel(ChannelDto channelDto){
        Channel newChannel = new Channel();
        newChannel.setChannelName(channelDto.getChannelName());
        newChannel.setAccessible(channelDto.getAccessibility());
        newChannel.setVisible(channelDto.getVisible());
        return channelRepository.save(newChannel);
    }
    public Channel getChannelById(Long channelId) throws Exception {
        return channelRepository.findById(channelId)
                .orElseThrow(() -> new Exception("Channel doesn't exist"));


    }
    public List<Channel> getAllChannels(){
        return channelRepository.findAll();


    }
    public Channel updateChannel(Long channelId, ChannelDto channelDto) throws Exception {
        Channel channel = channelRepository.findById(channelId)
                .orElseThrow(() -> new Exception("Channel doesn't exist"));
        channel.setChannelName(channelDto.getChannelName());
        channel.setAccessible(channelDto.getAccessibility());
        channel.setVisible(channelDto.getVisible());
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

    public Channel createChannelInWorkspace(Long workspaceId, ChannelDto channelDto) throws Exception {
        Workspace workspace = workspaceRepository.findById(workspaceId)
                .orElseThrow(() -> new Exception("Workspace not found"));
        Channel newChannel = new Channel();
        newChannel.setChannelName(channelDto.getChannelName());
        newChannel.setAccessible(channelDto.getAccessibility());
        newChannel.setVisible(channelDto.getVisible());
        newChannel.setWorkspace(workspace);
        workspace.getChannels().add(newChannel);
        channelRepository.save(newChannel);
        workspaceRepository.save(workspace);
        return newChannel;
    }

}
