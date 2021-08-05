
package com.yainnixdev.springleaf.server.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.awt.*;

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



    private int coordinate_X;
    private int coordinate_Y;


    private String heroName;

    private Integer level;
    private Integer money;

}