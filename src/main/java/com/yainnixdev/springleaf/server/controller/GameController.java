package com.yainnixdev.springleaf.server.controller;

import com.yainnixdev.springleaf.server.domain.Hero;
import com.yainnixdev.springleaf.server.repository.HeroDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;


@RestController
public class GameController {

    @MessageMapping("/send_position")
    @SendTo("/topic/get_positions")
    public HeroDto playerPosition(HeroDto heroDto){
       return heroDto;
    }
}
