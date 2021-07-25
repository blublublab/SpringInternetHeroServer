package com.yainnixdev.springleaf.server.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity(name = "users")
@Data
public class User implements UserDetails {
    @Id
    @Column(name = "user_id")
    private Integer userId;

    @OneToOne(mappedBy = "user" , cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Character character;

    private String username;
    private String password;

    private boolean isActive;




    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name ="user_id"))
    @Enumerated(EnumType.STRING)
    private Set<UserRole> roles;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }


    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public  User() {
    }


}
