package com.yainnixdev.springleaf.server.repository;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import com.yainnixdev.springleaf.server.utils.Point;
import lombok.Data;

@Data
public class HeroDto {
    private Point moveIntention;

    @JsonProperty("point")
    @SerializedName("point")
    private Point heroCoordinates;

    private String heroName;

    public HeroDto(Point heroCoordinates, Point moveIntention, String heroName){
        this.moveIntention = moveIntention;
        this.heroCoordinates = heroCoordinates;
        this.heroName = heroName;
    }
    @JsonProperty("point")
    public Point getHeroCoordinates() {
        return heroCoordinates;
    }

    @JsonProperty("point")
    public void setHeroCoordinates(Point heroCoordinates) {
        this.heroCoordinates = heroCoordinates;
    }
}
