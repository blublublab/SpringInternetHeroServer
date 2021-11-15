
package com.yainnixdev.springleaf.server.domain;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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


    @Column(name = "coordinate_x")
    @JsonIgnore
    private Float coordinateX;

    @Column(name = "coordinate_y")
    @JsonIgnore
    private Float coordinateY;


    @Transient
    @JsonSerialize
    private Point point;

    public void setPoint(Point heroPoint) {
        coordinateX = heroPoint.getX();
        coordinateY = heroPoint.getY();
        this.point = heroPoint;
    }

    public Point getPoint(){
        point = new Point(coordinateX, coordinateY);
        return point;
    }


    private String heroName;


    private Integer damage;
    private Integer level;
    private Integer money;

}