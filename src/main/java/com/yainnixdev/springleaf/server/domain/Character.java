
package com.yainnixdev.springleaf.server.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;

@Data
@Entity(name = "character")
public class Character {

    public Character(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "character_id")
    private String characterId;

    @OneToOne()
    @MapsId
    @PrimaryKeyJoinColumn (name = "user_id")
    private User user;


    private Integer level;
    private Integer money;

}