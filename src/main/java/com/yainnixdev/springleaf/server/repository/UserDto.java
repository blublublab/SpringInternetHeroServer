package com.yainnixdev.springleaf.server.repository;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Data
@NotEmpty
@NotNull
public class UserDto {
    public UserDto(){

    }
    private String userId;
    private String email;
    private String name;
    private String locale;
    private String pictureURL;



}
