package com.yainnixdev.springleaf.server.controller;

import com.yainnixdev.springleaf.server.repository.HeroDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.config.annotation.EnableWebSocket;


@Controller
public class GameController {

    @MessageMapping("/send_position")
    @SendTo("/topic/get_positions")
    public HeroDto playerPosition(@Payload HeroDto heroDto){
        System.out.println("connection executed");
       return heroDto;
    }
}
