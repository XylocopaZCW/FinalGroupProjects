package com.example.demo.repositories;

import com.example.demo.models.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelRepository extends JpaRepository <Channel, Long> {
}
