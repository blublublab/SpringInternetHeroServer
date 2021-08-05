package com.yainnixdev.springleaf.server.controller;

import com.yainnixdev.springleaf.server.repository.HeroDto;
import com.yainnixdev.springleaf.server.repository.UserDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


@Controller
public class GameController {

    @MessageMapping("/send_position")
    @SendTo("/topic/get_positions")
    public HeroDto playerPosition(@Payload HeroDto heroDto){
        System.out.println(heroDto.getHeroCoordinates().x);
       return heroDto;
    }
}
