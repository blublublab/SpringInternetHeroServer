package com.yainnixdev.springleaf.server.service;

import com.yainnixdev.springleaf.server.domain.User;
import com.yainnixdev.springleaf.server.domain.UserRole;
import com.yainnixdev.springleaf.server.repository.UserDto;
import com.yainnixdev.springleaf.server.repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
import java.util.Optional;


@Service
public class UserService  implements UserDetailsService {
    private UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User getUserByUserId(@RequestBody String userId){
        Optional<User> optionalEntity =  userRepo.findById(userId);
        User user;
        if(optionalEntity.isPresent()){
            user = optionalEntity.get();
        } else {
            throw  new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public User registerUserAccount(@RequestBody UserDto userDto) {
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByName(username);
        if(user == null){
            throw new UsernameNotFoundException(username +  " not found");
        } else {
            return user;
        }
    }
}


