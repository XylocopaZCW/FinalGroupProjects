package com.example.demo.services;

import com.example.demo.dtos.ChannelDto;
import com.example.demo.models.Channel;
import com.example.demo.models.User;
import com.example.demo.repositories.ChannelRepository;
import com.example.demo.repositories.UserRepository;
import org.checkerframework.checker.units.qual.C;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class ChannelServiceTest {
    @Mock
    private ChannelRepository channelRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private Channel channels;

    @Mock
    private User users;

    @InjectMocks
    private ChannelService channelService;

    @Test
    public void createChannelTest(){
        given(channelRepository.save(any(Channel.class))).willReturn(new Channel());
        Channel created = channelService.createChannel(new ChannelDto());
        assertNotNull(created);
    }

    @Test
    public void getChannelByIdTest() throws Exception {
        given(channelRepository.findById(1L)).willReturn(Optional.of(channels));
        Channel found = channelService.getChannelById(1L);
        assertNotNull(found);
    }

    @Test
    public void getChannelByIdNotFoundTest(){
        given(channelRepository.findById(0L)).willReturn(Optional.empty());
        assertThrows(Exception.class, () -> channelService.getChannelById(0L));
    }

    @Test
    public void updateChannelTest() throws Exception {
        Channel original = new Channel();
        original.setChannelname("Orginal");
        original.setAccessible(false);
        original.setVisible(false);
        original.setChannelId(1L);
        given(channelRepository.findById(1L)).willReturn(Optional.of(original));
        given(channelRepository.save(any(Channel.class))).willAnswer(invocation -> invocation.getArgument(0));

        ChannelDto updated = new ChannelDto();
        updated.setChannelName("New");
        updated.setAccessibility(true);
        updated.setVisible(true);
        Channel actual = channelService.updateChannel(1L, updated);

        assertNotNull((actual));
        assertEquals("New", actual.getChannelname());
        assertTrue(actual.getAccessible());
        assertTrue(actual.getVisible());
    }

    @Test
    public void updateChannelNotFoundTest() {
        Channel original = new Channel();
        original.setChannelId(1l);
        given(channelRepository.findById(1l)).willReturn(Optional.of(original));
        given(channelRepository.save(any(Channel.class))).willAnswer(invocation -> invocation.getArgument(0));
        ChannelDto update = new ChannelDto();
        assertThrows(Exception.class, () -> channelService.updateChannel(2L, update));

    }

    @Test
    public void deleteChannelTest() throws Exception{
        given(channelRepository.findById(1L)).willReturn(Optional.of(channels));
        channelService.deleteChannel(1L);
        verify(channelRepository).delete(channels);
    }

    @Test
    public void deleteChannelNotFoundTest() {
        Channel original = new Channel();
        original.setChannelId(1L);
        given(channelRepository.findById(1L)).willReturn(Optional.of(original));
        given(channelRepository.save(any(Channel.class))).willAnswer(invocation -> invocation.getArgument(0));
        assertThrows(Exception.class, () -> channelService.deleteChannel(2L));

    }

    @Test
    public void addUserToChannelTest() {
        User user = new User();
        user.setUserId(1L);
        user.setUsername("TestUser");
        Channel channel1 = new Channel();
        channel1.setChannelId(1L);
        given(channelRepository.findById(1L)).willReturn(Optional.of(channel1));
        given(userRepository.findById(1L)).willReturn(Optional.of(user));
        given(channelRepository.save(any(Channel.class))).willAnswer(invocation -> invocation.getArgument(0));
        given(userRepository.save(any(User.class))).willAnswer(invocation -> invocation.getArgument(0));
    }

    @Test
    public void addUserToChannelNotFoundTest(){
        User user = new User();
        user.setUserId(1L);
        Channel channel1 = new Channel();
        channel1.setChannelId(1L);
        given(channelRepository.findById(1L)).willReturn(Optional.of(channel1));
        given(userRepository.findById(1L)).willReturn(Optional.of(user));
        given(channelRepository.save(any(Channel.class))).willAnswer(invocation -> invocation.getArgument(0));
        assertThrows(Exception.class, () -> channelService.addUserToChannel(2L, 1L));

    }

    @Test
    public void removeUserFromChannelTest() throws Exception {
        given(channelRepository.findById(1L)).willReturn(Optional.of(channels));
        given(userRepository.findById(1L)).willReturn(Optional.of(users));
        channelService.removeUserFromChannel(1L, 1L);
        assertFalse(channels.getUsers().contains(users));

    }

    @Test
    public void removeUserFromChannelUserNotFoundTest(){
        User user = new User();
        user.setUserId(1L);
        Channel channel1 = new Channel();
        channel1.setChannelId(1L);
        given(channelRepository.findById(1L)).willReturn(Optional.of(channel1));
        given(userRepository.findById(1L)).willReturn(Optional.of(user));
        given(channelRepository.save(any(Channel.class))).willAnswer(invocation -> invocation.getArgument(0));
        assertThrows(Exception.class, () -> channelService.removeUserFromChannel(2L, 1L));

    }

    @Test
    public void getAllUsersInChannelTest() throws Exception {
        Channel channel1 = new Channel();
        channel1.setChannelId(1L);
        User user1 = new User();
        User user2 = new User();
        given(channelRepository.findById(1L)).willReturn(Optional.of(channel1));
        Set<User> expected = new HashSet<>(List.of(user1, user2));
        channel1.setUsers(expected);
        Set<User> actual = channelService.getAllUsersInChannel(1L);
        assertEquals(expected, actual);
    }
}

