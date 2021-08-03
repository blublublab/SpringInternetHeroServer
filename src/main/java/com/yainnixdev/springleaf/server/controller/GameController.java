package com.yainnixdev.springleaf.server.controller;

import com.yainnixdev.springleaf.server.domain.HeroPosition;
import com.yainnixdev.springleaf.server.repository.HeroRepo;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.awt.*;

@Controller
public class GameController {
    private HeroRepo heroRepo;



    public GameController(HeroRepo heroRepo) {
        this.heroRepo = heroRepo;
    }

    @MessageMapping("/set_positions")
    @SendTo("/topic/get_positions")
    public HeroPosition playerPosition(String characterName , Point point){
        return new HeroPosition(characterName, point.x, point.y);
    }
}
