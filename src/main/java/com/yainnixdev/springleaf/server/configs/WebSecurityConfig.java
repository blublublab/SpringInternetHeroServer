package com.yainnixdev.springleaf.server.configs;

import com.yainnixdev.springleaf.server.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private DataSource dataSource;
    private UserService userService;
    private AuthEntryPoint authEntryPoint;
    private LoginFilter loginFilter;

    public WebSecurityConfig(UserService userService, DataSource dataSource, AuthEntryPoint authEntryPoint, LoginFilter loginFilter) {
        this.dataSource = dataSource;
        this.userService = userService;
        this.authEntryPoint = authEntryPoint;
        this.loginFilter = loginFilter;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
         web.ignoring();
    }




    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .addFilterBefore(loginFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                    .antMatchers( "/oauth2/**", "/login").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .exceptionHandling().authenticationEntryPoint(authEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
               // .formLogin().permitAll()
               // .and()
                .oauth2Client();
    }




}