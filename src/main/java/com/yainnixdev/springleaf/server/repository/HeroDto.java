package com.yainnixdev.springleaf.server.repository;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import com.yainnixdev.springleaf.server.utils.CreatureDirection;
import com.yainnixdev.springleaf.server.utils.Point;
import lombok.Data;

@Data
public class HeroDto {
    private Point moveIntention;

    @SerializedName("point")
    @JsonProperty("point")
    private Point heroCoordinates;

    private String heroName;

    @SerializedName("direction")
    @JsonProperty("direction")
    private CreatureDirection creatureDirection;

    public HeroDto(Point heroCoordinates, Point moveIntention, String heroName, CreatureDirection creatureDirection){
        this.moveIntention = moveIntention;
        this.heroCoordinates = heroCoordinates;
        this.heroName = heroName;
        this.creatureDirection = creatureDirection;
    }
}
