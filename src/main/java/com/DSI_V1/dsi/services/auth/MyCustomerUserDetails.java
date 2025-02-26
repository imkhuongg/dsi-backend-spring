package com.DSI_V1.dsi.services.auth;

import com.DSI_V1.dsi.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.List;


public class MyCustomerUserDetails implements UserDetails {

    private User user;

    public MyCustomerUserDetails(User user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
    public int getID(){
        return this.user.getUser_id();
    }
    @Override
    public String getPassword() {
        return user.getPassword();
    }


    public String getFirst_name(){
        return this.user.getFirst_name();
    }

    public String getLastname(){
        return this.user.getLast_name();
    }
    
    @Override
    public String getUsername() {
        return user.getEmail();
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
}
