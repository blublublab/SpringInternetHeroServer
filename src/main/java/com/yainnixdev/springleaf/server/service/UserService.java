package com.yainnixdev.springleaf.server.service;

import com.yainnixdev.springleaf.server.domain.User;
import com.yainnixdev.springleaf.server.domain.UserRole;
import com.yainnixdev.springleaf.server.exception.UserAlreadyExistException;
import com.yainnixdev.springleaf.server.exception.UserNotFoundException;
import com.yainnixdev.springleaf.server.repository.UserDto;
import com.yainnixdev.springleaf.server.repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;


@Service
public class UserService implements UserDetailsService {
    private UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }

        return user;
    }

    public User loginUserAccount(@RequestBody UserDto userDto) throws  UserNotFoundException {
        User user = userRepo.findByUsernameAndPassword(userDto.getUsername(), userDto.getPassword());
        if (user == null){
            throw  new UserNotFoundException("User details not found");
        }
        return userRepo.save(user);
    }

    public User registerNewUserAccount(@RequestBody UserDto userDto) {
        if(userRepo.findByUsername(userDto.getUsername()) != null) {
            throw new UserAlreadyExistException("User already exist");
        } else {
            User user = new User();
            user.setUsername(userDto.getUsername());
            user.setPassword(userDto.getPassword());
            user.setActive(true);
            user.setRoles(Collections.singleton(UserRole.USER));
            return userRepo.save(user);
        }
    }
}