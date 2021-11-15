package com.yainnixdev.springleaf.server.controller;

import com.google.gson.Gson;
import com.yainnixdev.springleaf.server.repository.HeroDto;
import com.yainnixdev.springleaf.server.repository.MessageDto;
import com.yainnixdev.springleaf.server.service.HeroService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GameController {
    private HeroService heroService;
    public GameController(HeroService heroService) {
        this.heroService = heroService;
    }



    @MessageMapping("/send_position")
    @SendTo("/topic/get_position")
    public String playerPosition(HeroDto heroDto){
         String jsonResponse = new Gson().toJson(heroDto);
         heroService.updateHero(heroDto);
         System.out.println(jsonResponse);
       return jsonResponse;
    }

    @MessageMapping("/ping_server")
    public void pingServer(){

    }

    @MessageMapping("/send_character_operation")
    @SendTo("/topic/character")
    public String characterOperations(HeroDto heroDto){
        return new Gson().toJson(heroDto);
    }

    @MessageMapping("/send_message")
    @SendTo("/topic/get_message")
    public String sendMessage(MessageDto messageDto){
        String jsonResponse  = new Gson().toJson(messageDto);
        System.out.println(jsonResponse);
        return jsonResponse;
    }
}
