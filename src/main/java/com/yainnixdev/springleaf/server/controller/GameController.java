package com.yainnixdev.springleaf.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/")
@RestController
public class GameController {

    @GetMapping("/game")
    public String authorizedRequest(){
        return "success";
    }
}
