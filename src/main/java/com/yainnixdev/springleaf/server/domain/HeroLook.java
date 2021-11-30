package com.yainnixdev.springleaf.server.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

/*

@Data
@Entity(name = "hero_look")
public class HeroLook {

    public HeroLook(){

    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "look_Id")
    private String heroLookId;

    @OneToOne()
    @MapsId
    @PrimaryKeyJoinColumn (name = "hero_hero_name")
    @JsonBackReference
    private Hero hero;


    private int heroModel;

    // Set enums
    private int heroAction;

}

*/
