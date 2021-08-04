package com.yainnixdev.springleaf.server.controller;

import com.yainnixdev.springleaf.server.repository.UserDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


@Controller
@MessageMapping("/")
public class GameController {


    @MessageMapping("/send_position")
    @SendTo("/topic/get_positions")
    public UserDto playerPosition(@Payload UserDto userDto){
       return userDto;
    }
}
