package com.yainnixdev.springleaf.server.controller;

import com.yainnixdev.springleaf.server.repository.CharacterRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/")
@RestController
public class GameController {
    private CharacterRepo characterRepo;

    public GameController(CharacterRepo characterRepo) {
        this.characterRepo = characterRepo;
    }

    @GetMapping("/game")
    public String generateMonster(){

        return "blablabla";
    }
}
