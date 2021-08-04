package com.yainnixdev.springleaf.server.domain;

import java.awt.*;

public class HeroDto {
    Point moveIntention;
    Hero hero;

    public HeroDto(Point moveIntention, Hero hero) {
        this.moveIntention = moveIntention;
        this.hero = hero;
    }

    public Point getMoveIntention() {
        return moveIntention;
    }

    public void setMoveIntention(Point moveIntention) {
        this.moveIntention = moveIntention;
    }

    public Hero getHero(){
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }
}
