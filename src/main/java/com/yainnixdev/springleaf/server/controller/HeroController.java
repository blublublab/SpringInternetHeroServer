package com.yainnixdev.springleaf.server.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.yainnixdev.springleaf.server.domain.Hero;
import com.yainnixdev.springleaf.server.exception.HeroAlreadyExistException;
import com.yainnixdev.springleaf.server.exception.HeroNotFoundException;
import com.yainnixdev.springleaf.server.repository.HeroRepo;
import com.yainnixdev.springleaf.server.service.HeroService;
import com.yainnixdev.springleaf.server.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequestMapping("/hero")
public class HeroController {

    private HeroService heroService;
    private UserService userService;
    public HeroController(HeroService heroService, UserService userService) {
        this.heroService = heroService;
        this.userService = userService;
    }


    @PostMapping("/get_point")
    public Point getPoint(@RequestBody String heroName){
       Point point =  heroService.getPointByHeroName(heroName);
       if(point == null){
           throw new HeroNotFoundException("Point of hero not found");
       }
       return point;
    }

    @PostMapping("/get_by/user_id")
    public Hero getHero(@RequestBody  String userId){
      Hero hero =  heroService.getHeroByUserId(userId);
      if(hero == null){
          throw new HeroNotFoundException("Hero not found");
      }
      return hero;
    }

    @PostMapping("/create")
    public Hero createNewHero(@RequestBody  Hero inputHero,@RequestHeader("user_id") String user_id){
        return heroService.createNewHero(inputHero , user_id);
    }
}
