package com.yainnixdev.springleaf.server.controller;

import com.yainnixdev.springleaf.server.domain.Hero;
import com.yainnixdev.springleaf.server.domain.HeroDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/game")
public class GameController {
    @MessageMapping("/set_positions")
    @SendTo("/topic/get_positions")
    public HeroDto playerPosition(@RequestBody HeroDto heroDto){
        return new HeroDto(heroDto.getMoveIntention(), heroDto.getHero());
    }
}
