package com.DSI_V1.dsi.services.auth.admin;

import com.DSI_V1.dsi.models.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminUserDetailsServices implements UserDetailsService {

    @Autowired
    private AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminService.getAdminByEmail(username);

        if(admin == null) throw new UsernameNotFoundException("unable to load Admin");

        return new AdminUserDetails(admin);
    }
}
