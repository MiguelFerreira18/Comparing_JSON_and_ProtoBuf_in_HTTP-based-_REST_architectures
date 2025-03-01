package com.conveniencestore.conveniencestore.domain.users;

import com.conveniencestore.conveniencestore.protobuf.LocalDateTimeOuterClass;
import com.conveniencestore.conveniencestore.protobuf.UserDto;
import com.conveniencestore.conveniencestore.protobuf.UserRolesOuterClass;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity(name = "Users")
@Table(name="Users")
@Getter
@Setter
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String email;
    private String password;
    private UserRoles role;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;

    public User (UserDto.UserDTO data){
        this.username = data.getUsername();
        this.role = convertRole(data.getRole());
        this.email = data.getEmail();
        this.password = data.getPassword();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRoles.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
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
        return true;
    }

    public UserRoles convertRole(UserRolesOuterClass.UserRoles role){
        if (role.name().equals("ADMIN")) return UserRoles.ADMIN;
        else return UserRoles.EMPLOYEE;
    }

}
