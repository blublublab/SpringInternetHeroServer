
package com.yainnixdev.springleaf.server.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.gson.annotations.SerializedName;
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
    private Point point;

    @Column(name = "coordinate_x")
    @SerializedName("x")
    private Float coordinateX;

    @Column(name = "coordinate_y")
    @SerializedName("y")
    private Float coordinateY;

    public void setPoint(Point heroPoint) {
        coordinateX = heroPoint.getX();
        coordinateY = heroPoint.getY();
        this.point = heroPoint;
    }


    private String heroName;


    private Integer damage;
    private Integer level;
    private Integer money;

}