package com.yainnixdev.springleaf.server.service;

import com.yainnixdev.springleaf.server.domain.User;
import com.yainnixdev.springleaf.server.domain.UserRole;
import com.yainnixdev.springleaf.server.exception.TokenNotValidException;
import com.yainnixdev.springleaf.server.repository.UserDto;
import com.yainnixdev.springleaf.server.repository.UserRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;


@Service
public class UserService  {
    private UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }



    public User loginUserAccount(@RequestBody UserDto userDto) {
            User user = new User();
            user.setUserId(userDto.getUserId());
            user.setEmail(userDto.getEmail());
            user.setName(userDto.getName());
            user.setPictureURL(userDto.getPictureURL());
            user.setLocale(userDto.getLocale());
            user.setActive(true);
            user.setRoles(Collections.singleton(UserRole.USER));
            return userRepo.save(user);
        }

    }

