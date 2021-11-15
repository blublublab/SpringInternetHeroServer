package com.yainnixdev.springleaf.server.service;

import com.yainnixdev.springleaf.server.domain.Hero;
import com.yainnixdev.springleaf.server.exception.HeroAlreadyExistException;
import com.yainnixdev.springleaf.server.exception.HeroNotFoundException;
import com.yainnixdev.springleaf.server.repository.HeroDto;
import com.yainnixdev.springleaf.server.repository.HeroRepo;
import com.yainnixdev.springleaf.server.utils.Point;
import org.springframework.stereotype.Service;
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

    public Point getPointByHeroName(@RequestBody String heroName){
       Hero hero =  heroRepo.findByHeroName(heroName);
       Float x, y;
        if (hero == null) {
           throw  new HeroNotFoundException("Coordinates not found , setting 0, 0 by default ");
       }
        x =  hero.getCoordinate_X();
        y =  hero.getCoordinate_Y();
        return new com.yainnixdev.springleaf.server.utils.Point(x, y);
    }

    public Hero updateHero(@RequestBody HeroDto heroToUpdate){
        Hero oldHero = heroRepo.findByHeroName(heroToUpdate.getHeroName());
        oldHero.setCoordinate_X(heroToUpdate.getHeroCoordinates().getX());
        oldHero.setCoordinate_Y(heroToUpdate.getHeroCoordinates().getY());
        return  heroRepo.save(oldHero);
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
        hero.setMoney(0);
        hero.setLevel(0);
        hero.setCoordinate_X(1500F);
        hero.setCoordinate_Y(1500F);
        return heroRepo.save(hero);
    }
}
