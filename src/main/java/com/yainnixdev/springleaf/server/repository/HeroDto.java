package com.yainnixdev.springleaf.server.repository;


import com.yainnixdev.springleaf.server.utils.Point;
import lombok.Data;

@Data
public class HeroDto {
    private Point moveIntention;
    private Point heroCoordinates;
    private String heroName;

    public HeroDto(Point heroCoordinates, Point moveIntention, String heroName){
        this.moveIntention = moveIntention;
        this.heroCoordinates = heroCoordinates;
        this.heroName = heroName;
    }
}
