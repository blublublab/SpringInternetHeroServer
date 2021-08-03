package com.yainnixdev.springleaf.server.domain;

public class HeroPosition {

    private int xPosition;
    private int yPosition;
    private String heroName;
    public HeroPosition(){
    }

    public HeroPosition(String heroName, int xPosition, int yPosition) {
        this.heroName = heroName;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }
}
