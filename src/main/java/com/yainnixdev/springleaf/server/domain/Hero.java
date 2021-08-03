
package com.yainnixdev.springleaf.server.domain;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;

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
    private User user;

    @Column(name ="hero_name")
    @NotNull
    private String heroName;

    @NotNull
    private Integer level;
    @NotNull
    private Integer money;

}