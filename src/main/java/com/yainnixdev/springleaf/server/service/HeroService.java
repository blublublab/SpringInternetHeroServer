package com.yainnixdev.springleaf.server.service;

import com.yainnixdev.springleaf.server.domain.Hero;
import com.yainnixdev.springleaf.server.exception.HeroAlreadyExistException;
import com.yainnixdev.springleaf.server.exception.HeroNotFoundException;
import com.yainnixdev.springleaf.server.repository.HeroRepo;
import com.yainnixdev.springleaf.server.repository.UserRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.awt.*;

@Service
public class HeroService {
    private HeroRepo heroRepo;
    private UserService userService;
    public HeroService(HeroRepo heroRepo, UserService userService) {
        this.heroRepo = heroRepo;
        this.userService = userService;
    }

    public Hero getHeroByUserId(@RequestBody  String user_id){
        Hero hero =   heroRepo.findByUser_UserId(user_id);
        if(hero == null){
            throw new HeroNotFoundException("Hero not found");
        }
        return hero;
    }

    public Hero createNewHero(@RequestBody Hero inputHero, @RequestHeader("user_id") String user_id){
        // TODO: Add variable sprites to hero creation
        Hero hero = heroRepo.findByUser_UserId(user_id);
        if(hero != null) {
            throw new HeroAlreadyExistException("Hero " + hero.getHeroName() + " already exist");
        }
        hero = new Hero();
        hero.setHeroName(inputHero.getHeroName());
        hero.setUser(userService.getUserByUserId(user_id));
        return heroRepo.save(hero);
    }
}
