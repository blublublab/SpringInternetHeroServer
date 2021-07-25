package com.yainnixdev.springleaf.server.controller;

import com.yainnixdev.springleaf.server.domain.User;
import com.yainnixdev.springleaf.server.exception.UserAlreadyExistException;
import com.yainnixdev.springleaf.server.exception.UserNotFoundException;
import com.yainnixdev.springleaf.server.repository.UserDto;
import com.yainnixdev.springleaf.server.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {


    private UserService userService;
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public  String successLogin(){
        return "login success";
    }

    @PostMapping("/login")
    public User loginUserAccount(@RequestBody UserDto userDto){
        try {
            return userService.loginUserAccount(userDto);
        } catch(UserNotFoundException e) {
            throw new UserNotFoundException("Can't login with this data");
        }
    }

    @PostMapping("/register")
    public User registerUserAccount(@RequestBody UserDto userDto) {

        try {
        return userService.registerNewUserAccount(userDto);
        } catch(UserAlreadyExistException e ) {
            throw new UserAlreadyExistException("User already exist");
        }

    }

    @ExceptionHandler({UserAlreadyExistException.class, UserNotFoundException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public Throwable handleException(UserAlreadyExistException e) {
        return e;
    }

}
