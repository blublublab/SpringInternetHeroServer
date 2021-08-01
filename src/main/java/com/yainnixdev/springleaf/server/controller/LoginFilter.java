package com.yainnixdev.springleaf.server.controller;

import com.yainnixdev.springleaf.server.controller.LoginController;
import com.yainnixdev.springleaf.server.exception.TokenNotValidException;
import com.yainnixdev.springleaf.server.service.UserService;
import lombok.SneakyThrows;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginFilter extends OncePerRequestFilter {
    UserService userService;
    LoginController loginController;

    public LoginFilter(UserService userService, LoginController loginController) {
        this.userService = userService;
        this.loginController = loginController;
    }

    @Override
    @SneakyThrows
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
        final String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken;
        // JWT Token is in the form "Bearer token". Remove Bearer word and get
        // only the Token
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = loginController.loginUser(jwtToken);
            } catch (TokenNotValidException e) {
                System.out.println("Token has expired");
            }
        } else {
            System.out.println("JWT Token does not begin with Bearer String");
        }
        // Once we get the token validate it.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userService.loadUserByUsername(username);
            // if token is valid configure Spring Security to manually set
            // authentication

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            usernamePasswordAuthenticationToken
                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            // After setting the Authentication in the context, we specify
            // that the current user is authenticated. So it passes the
            // Spring Security Configurations successfully.
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        }
        chain.doFilter(request, response);
    }

}
