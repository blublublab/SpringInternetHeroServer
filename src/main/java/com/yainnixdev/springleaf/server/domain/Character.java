package com.yainnixdev.springleaf.server.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity(name = "character")
public class Character {

    public Character(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer userId;

    @OneToOne()
    @MapsId
    @PrimaryKeyJoinColumn (name = "user_id")
    private User user;


    private Integer level;
    private Integer money;

}
