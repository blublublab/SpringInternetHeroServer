package com.yainnixdev.springleaf.server.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.yainnixdev.springleaf.server.domain.User;
import com.yainnixdev.springleaf.server.exception.TokenNotValidException;
import com.yainnixdev.springleaf.server.exception.UserAlreadyExistException;
import com.yainnixdev.springleaf.server.repository.UserDto;
import com.yainnixdev.springleaf.server.service.UserService;
import lombok.SneakyThrows;
import okhttp3.*;
import org.apache.commons.codec.binary.Base64;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


@RequestMapping("/")
@RestController
public class LoginController  {


    private UserService userService;
    private String userToken = "";
    public LoginController(UserService userService) {
        this.userService = userService;
    }

  /*  @SneakyThrows
    @PostMapping("/login")
    public String getUserToken(@RequestBody String authCode){
        OkHttpClient client = new OkHttpClient();
        okhttp3.RequestBody requestBody =  new FormBody.Builder()
                .add("grant_type", "authorization_code")
                .add("client_id", System.getenv("GOOGLE_OAUTH_CLIENT_ID"))
                .add("client_secret", System.getenv("GOOGLE_OAUTH_CLIENT_SECRET"))
                .add("code", authCode)
                .build();
        final Request request = new Request.Builder()
                .url("https://www.googleapis.com/oauth2/v4/token")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .post(requestBody)
                .build();
        ;
        CountDownLatch awaitResponse = new CountDownLatch(1);
        client.newCall(request).enqueue(new Callback() {
             @Override
             public void onFailure(@NotNull Call call, @NotNull IOException e) {
                 userToken  = "Failed to get User Token from auth code " + authCode ;
                 awaitResponse.countDown();
             }
             @Override
             public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                 try {
                     JSONObject tokenInfo = new JSONObject(response.body().string());
                     userToken =  tokenInfo.getString("access_token");
                 } catch (JSONException e) {
                     awaitResponse.countDown();
                     e.printStackTrace();
                 }
                 awaitResponse.countDown();
             }

         });
        awaitResponse.await(20, TimeUnit.SECONDS);
        // registerUserAccount(userToken);
        System.out.println(userToken);
        return userToken;
    }
*/

    public String loginUser(String userToken) throws IOException, GeneralSecurityException , UserAlreadyExistException {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                // Specify the CLIENT_ID of the app that accesses the backend:
                .setAudience(Collections.singletonList(System.getenv("GOOGLE_OAUTH_CLIENT_ID")))
                .build();
        GoogleIdToken idToken = null;
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
        userDto.setName((String) payload.get("name"));
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
