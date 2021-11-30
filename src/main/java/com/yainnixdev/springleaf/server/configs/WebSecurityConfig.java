package com.yainnixdev.springleaf.server.configs;

import com.yainnixdev.springleaf.server.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;
import java.util.Collections;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final DataSource dataSource;
    private final UserService userService;
    private final AuthEntryPoint authEntryPoint;
    private final LoginFilter loginFilter;

    public WebSecurityConfig(UserService userService, DataSource dataSource, AuthEntryPoint authEntryPoint, LoginFilter loginFilter) {
        this.dataSource = dataSource;
        this.userService = userService;
        this.authEntryPoint = authEntryPoint;
        this.loginFilter = loginFilter;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        String password =  System.getenv("ADMIN_PANEL_PASSWORD");
        System.out.println("aut is " + password);

        auth.inMemoryAuthentication()
                .withUser("admin").password(new BCryptPasswordEncoder().encode(password)).roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/admin", true);
          /*          .and()

                .addFilterBefore(loginFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                    .antMatchers( "/oauth2/**", "/login", "/").permitAll()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
                    .and()
                    .exceptionHandling().authenticationEntryPoint(authEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
               // .formLogin().permitAll()
               // .and()
                .oauth2Client();*/
    }




}