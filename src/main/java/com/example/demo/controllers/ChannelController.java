package com.example.demo.controllers;

import com.example.demo.dtos.ChannelDto;
import com.example.demo.models.Channel;
import com.example.demo.services.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/channels")
public class ChannelController {
    private final ChannelService channelService;
    @Autowired
    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }

    @PostMapping("/createChannel")
    public ResponseEntity<?> createChannel(@RequestBody ChannelDto channelDto){
       try {
            Channel newchannel = channelService.createChannel(channelDto);
            return new ResponseEntity<>(newchannel, HttpStatus.OK);
       } catch (Exception e) {
           return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

}
