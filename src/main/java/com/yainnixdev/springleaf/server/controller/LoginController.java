package com.yainnixdev.springleaf.server.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.yainnixdev.springleaf.server.exception.TokenNotValidException;
import com.yainnixdev.springleaf.server.exception.UserAlreadyExistException;
import com.yainnixdev.springleaf.server.repository.UserDto;
import com.yainnixdev.springleaf.server.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.MessageFormat;
import java.util.Collections;


@RequestMapping("/")
@Controller
public class LoginController  {


    private UserService userService;
    private String userToken = "";
    public LoginController(UserService userService) {
        this.userService = userService;
    }


    public String loginUser(String userToken) throws IOException, GeneralSecurityException , UserAlreadyExistException {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                // Specify the CLIENT_ID of the app that accesses the backend:
                .setAudience(Collections.singletonList(System.getenv("GOOGLE_OAUTH_CLIENT_ID")))
                .build();
        GoogleIdToken idToken;
        Payload payload;
        try {
        idToken = verifier.verify(userToken);
        payload = idToken.getPayload();
        } catch (TokenNotValidException e){
            throw new TokenNotValidException("Token from user not valid");
        };

        // Get profile information from payload
        UserDto userDto = new UserDto();
        userDto.setUserId(payload.getSubject());
        userDto.setEmail(payload.getEmail());
       String name = String.valueOf(payload.get("name"));
        name = name.replace(" ", "_");
        userDto.setName(name);
        userDto.setLocale((String) payload.get("locale"));
        userDto.setPictureURL((String) payload.get("picture"));
        System.out.println(MessageFormat.format("User ID: {0} Authorized", payload.getSubject()));
        return  userService.registerUserAccount(userDto).getUsername();
    };


    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public Throwable handleException(UserAlreadyExistException e) {
        return e;
    }

}
