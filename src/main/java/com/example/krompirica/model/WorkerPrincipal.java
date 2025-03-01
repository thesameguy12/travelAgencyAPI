package com.example.krompirica.model;

import com.example.krompirica.Worker.WorkerEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
@AllArgsConstructor
public class WorkerPrincipal implements UserDetails {
    private final WorkerEntity entity;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){return null;}

    @Override
    public String getPassword(){
        return null;
    }

    @Override
    public String getUsername(){
        return this.entity.getUsername();
    }
    @Override
    public boolean isAccountNonExpired(){
        return false;
    }
    @Override
    public boolean isAccountNonLocked(){
        return false;
    }
    @Override
    public boolean isCredentialsNonExpired(){
        return false;
    }
    @Override
    public boolean isEnabled(){
        return false;
    }
}
