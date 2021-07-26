package com.yainnixdev.springleaf.server.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity(name = "users")
@Data
public class User  {
    @Id
    @Column(name = "user_id")
    private String userId;

   @OneToOne(mappedBy = "user" , cascade = CascadeType.ALL)
   @PrimaryKeyJoinColumn
   private Character character;

    private String email;

    @Column(name = "username")
    private String name;

    private String pictureURL;
    private String locale;

    private boolean isActive;




    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name ="user_id"))
    @Enumerated(EnumType.STRING)
    private Set<UserRole> roles;


    public  User() {
    }


}
