package com.DSI_V1.dsi.services.auth;



import com.DSI_V1.dsi.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MyCustomUserDetailsService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(MyCustomUserDetailsService.class);
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getUserByEmail(email);

        if(user == null) throw new UsernameNotFoundException("unable to load User");

        return new MyCustomerUserDetails(user);
    }
}
