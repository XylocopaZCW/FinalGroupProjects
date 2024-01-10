package com.example.demo.controllers;

import com.example.demo.dtos.ChannelDto;
import com.example.demo.models.Channel;
import com.example.demo.services.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/channels")
public class ChannelController {
    private final ChannelService channelService;

    @Autowired
    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;

    }

    @PostMapping()
    public ResponseEntity<?> createChannel(@RequestBody ChannelDto channelDto) {
        try {
            Channel newchannel = channelService.createChannel(channelDto);
            return new ResponseEntity<>(newchannel, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{channelId}")
    public ResponseEntity<?> getChannelById(@PathVariable Long channelId) throws Exception {
        return new ResponseEntity<>(channelService.getChannelById(channelId), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getAllChannels() {
        return new ResponseEntity<>(channelService.getAllChannel(), HttpStatus.OK);
    }

    @PutMapping("/{channelId}")
    public ResponseEntity<?> updateChannel(@PathVariable Long channelId, @RequestBody ChannelDto channelDto) throws Exception {
        return new ResponseEntity<>(channelService.updateChannel(channelId, channelDto), HttpStatus.OK);
    }
    @DeleteMapping("/{channelId}")
    public ResponseEntity<?> deleteChannel(@PathVariable Long channelId) throws Exception {
        channelService.deleteChannel(channelId);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{channelId}/users")
    public ResponseEntity<?> addUserToChannel(@PathVariable Long channelId, @RequestBody Long userId) throws Exception{
        return new ResponseEntity<>(channelService.addUserToChannel(channelId, userId), HttpStatus.OK);
    }
    @DeleteMapping("/{channelId}/users/{userId}")
    public ResponseEntity<?> removeUserFromChannel(@PathVariable Long channelId, @PathVariable Long userId) throws Exception {
        channelService.removeUserFromChannel(channelId, userId);
        return  ResponseEntity.noContent().build();
    }
    @GetMapping("{channelId}/users")
    public ResponseEntity<?> getAllUsersInChannel(@PathVariable Long channelId) throws Exception {
        return new ResponseEntity<>(channelService.getAllUsersInChannel(channelId), HttpStatus.OK);
    }


}