package com.DSI_V1.dsi.services.auth.admin;

import com.DSI_V1.dsi.models.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public class AdminUserDetails implements UserDetails {

    private Admin admin;

    public AdminUserDetails(Admin admin){this.admin = admin;}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return admin.getPassword();
    }

    @Override
    public String getUsername() {
        return admin.getEmail();
    }

    public int getID(){
        return admin.getAdmin_id();
    }
    public String getFirstName(){
        return admin.getFirst_name();
    }
    public String getLastName(){
        return admin.getLast_name();
    }
    public String getAddress(){
        return admin.getAddress();
    }
    public int getPhone(){
        return admin.getPhone();
    }
    public LocalDateTime getCreatedAt(){
        return admin.getCreated_at();
    }
    public LocalDateTime getUpdatedAt(){
        return admin.getUpdated_at();
    }
    public String getAvatar(){
        return admin.getAvatar();
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
