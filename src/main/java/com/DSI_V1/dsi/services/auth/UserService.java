package com.DSI_V1.dsi.services.auth;

import com.DSI_V1.dsi.models.User;
import com.DSI_V1.dsi.repository.UserRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepositoty userRepositoty;
    public User getUserByEmail(String email){
        return userRepositoty.getUserByEmail(email);
    }
    public int registerUser(String first_name,String last_name,String email, String password){
        return userRepositoty.registerUser(first_name, last_name, email,password);
    }
}
