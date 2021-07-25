package com.yainnixdev.springleaf.server.repository;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Data
public class UserDto {
    public UserDto(){

    }
    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    private String password;

}
