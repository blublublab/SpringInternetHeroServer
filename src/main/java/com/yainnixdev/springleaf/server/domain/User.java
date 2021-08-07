package com.yainnixdev.springleaf.server.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity(name = "users")
public class User implements UserDetails {
    @Id
    @Column(name = "user_id")
    @Getter
    @Setter
    private String userId;


    @Getter
    @Setter
    @OneToOne(mappedBy = "user" , cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
   private Hero hero;



    @Getter
    @Setter
    private String email;

    @Setter
    @Column(name = "name")
    private String name;

    @Getter
    @Setter
    @Column(name ="picture_url")
    private String pictureURL;

    @Getter
    @Setter
    private String locale;

    @Setter
    private boolean isActive;



    @Setter
    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name ="user_id"))
    @Enumerated(EnumType.STRING)
    private Set<UserRole> roles;


    public  User() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        name = name.replace(" ", "_");
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }
}

