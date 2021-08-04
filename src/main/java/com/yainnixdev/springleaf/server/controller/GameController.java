package com.yainnixdev.springleaf.server.controller;

import com.yainnixdev.springleaf.server.domain.Hero;
import com.yainnixdev.springleaf.server.domain.HeroDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
@MessageMapping("/game")
public class GameController {
    @SendTo("/topic")
    @MessageMapping("/topic")
    public HeroDto playerPosition(@Payload @RequestBody HeroDto heroDto){
        System.out.println(heroDto.getMoveIntention().x);
        System.out.println(heroDto.getHero().getHeroName());
        return new HeroDto(heroDto.getMoveIntention(), heroDto.getHero());
    }
}
