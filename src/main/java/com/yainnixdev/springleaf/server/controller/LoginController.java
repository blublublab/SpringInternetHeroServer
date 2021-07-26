package com.yainnixdev.springleaf.server.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.yainnixdev.springleaf.server.domain.User;
import com.yainnixdev.springleaf.server.exception.UserAlreadyExistException;
import com.yainnixdev.springleaf.server.exception.TokenNotValidException;
import com.yainnixdev.springleaf.server.repository.UserDto;
import com.yainnixdev.springleaf.server.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@RestController
public class LoginController {


    private UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String successLogin() {
        return "login success";
    }


    @PostMapping("/token_auth")
    public User loginUserAccount(@RequestBody  String token) throws IOException, GeneralSecurityException {
        System.out.println(token);
        UserDto userDto = new UserDto();
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                // Specify the CLIENT_ID of the app that accesses the backend:
                .setAudience(Collections.singletonList(System.getenv("GOOGLE_OAUTH_CLIENT_ID")))
                .build();
        GoogleIdToken idToken = verifier.verify(token);
        Payload payload = idToken.getPayload();
        System.out.println("User ID: " + payload.getSubject());
        // Get profile information from payload

        userDto.setUserId(payload.getSubject());
        userDto.setEmail(payload.getEmail());
        userDto.setName((String) payload.get("name"));
        userDto.setLocale((String) payload.get("locale"));
        userDto.setPictureURL((String) payload.get("picture"));
        return userService.loginUserAccount(userDto);
    };



    @ExceptionHandler({UserAlreadyExistException.class, GeneralSecurityException.class, IOException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public Throwable handleException(UserAlreadyExistException e) {
        return e;
    }

}
