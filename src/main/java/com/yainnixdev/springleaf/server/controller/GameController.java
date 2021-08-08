package com.yainnixdev.springleaf.server.controller;

import com.google.gson.Gson;
import com.yainnixdev.springleaf.server.repository.HeroDto;
import com.yainnixdev.springleaf.server.repository.MessageDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLOutput;


@RestController
public class GameController {

    @MessageMapping("/send_position")
    @SendTo("/topic/get_positions")
    public String playerPosition(HeroDto heroDto){
         String jsonResponse = new Gson().toJson(heroDto);
        System.out.println(jsonResponse);
       return jsonResponse;
    }

    @MessageMapping("/ping_server")
    public void pingServer(){

    }

    @MessageMapping("/send_message")
    @SendTo("topic/get_messages")
    public String sendMessage(MessageDto messageDto){
        System.out.println(messageDto.getMessage());
        return new Gson().toJson(messageDto);
    }
}
