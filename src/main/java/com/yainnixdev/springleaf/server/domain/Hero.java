
package com.yainnixdev.springleaf.server.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.context.annotation.Primary;
import org.springframework.lang.NonNull;

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
    private String characterId;

    @OneToOne()
    @MapsId
    @PrimaryKeyJoinColumn (name = "user_id")
    @JsonBackReference
    private User user;


    private String heroName;

    @ColumnDefault("0")
    private Integer level;

    @NonNull
    private Integer money;

    @ColumnDefault("0")
    private int xCoordinate;
    @ColumnDefault("0")
    private int yCoordinate;

}