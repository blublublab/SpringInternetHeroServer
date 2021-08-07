package com.yainnixdev.springleaf.server.controller;

import com.google.gson.Gson;
import com.yainnixdev.springleaf.server.repository.HeroDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GameController {

    @MessageMapping("/send_position")
    @SendTo("/topic/get_positions")
    public String playerPosition(HeroDto heroDto){
         String jsonResponse = new Gson().toJson(heroDto);
        System.out.println(jsonResponse);
       return jsonResponse;
    }
}
