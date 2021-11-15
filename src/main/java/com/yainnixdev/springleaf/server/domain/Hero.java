
package com.yainnixdev.springleaf.server.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yainnixdev.springleaf.server.utils.Point;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "hero")
public class Hero {

    public Hero(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "hero_id")
    private String hero_id;

    @OneToOne()
    @MapsId
    @PrimaryKeyJoinColumn (name = "user_id")
    @JsonBackReference
    private User user;

    @Transient
    @JsonProperty
    private Point heroPoint;

    private Float coordinateX;
    private Float coordinateY;

    public void setHeroPoint(Point heroPoint) {
        coordinateX = heroPoint.getX();
        coordinateY = heroPoint.getY();
        this.heroPoint = heroPoint;
    }
    public Point getHeroPoint(){
        return heroPoint;
    }


    private String heroName;

    private Integer level;
    private Integer money;

}