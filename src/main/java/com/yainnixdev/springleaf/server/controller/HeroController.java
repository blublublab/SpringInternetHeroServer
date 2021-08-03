package com.yainnixdev.springleaf.server.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.yainnixdev.springleaf.server.domain.Hero;
import com.yainnixdev.springleaf.server.exception.HeroAlreadyExistException;
import com.yainnixdev.springleaf.server.exception.HeroNotFoundException;
import com.yainnixdev.springleaf.server.repository.HeroRepo;
import com.yainnixdev.springleaf.server.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller("/hero")
public class HeroController {

    private HeroRepo heroRepo;
    private UserService userService;
    public HeroController(HeroRepo heroRepo, UserService userService) {
        this.heroRepo = heroRepo;
        this.userService = userService;
    }

    @PostMapping("/get")
    public Hero getHero(String user_id){
      Hero hero =   heroRepo.findByUser_Id(user_id);
      if(hero == null){
          throw new HeroNotFoundException("Hero not found");
      }
      return hero;
    }

    @PostMapping("/create")
    public Hero createNewHero(Hero inputHero, String user_id){

        // TODO: Add variable sprites to hero creation
        Hero hero = heroRepo.findByUser_Id(user_id);
        if(hero != null) {
            throw new HeroAlreadyExistException("Hero already exist");
        }
        hero = new Hero();
        hero.setHeroName(inputHero.getHeroName());
        hero.setUser(userService.getUserByUserId(user_id));
        return hero;
    }
}
