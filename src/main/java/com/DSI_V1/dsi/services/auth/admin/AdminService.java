package com.DSI_V1.dsi.services.auth.admin;

import com.DSI_V1.dsi.models.Admin;
import com.DSI_V1.dsi.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private AdminRepository adminRepository;
    public Admin getAdminByEmail(String email){ return adminRepository.findByEmail(email);}
    public Admin createAdmin(Admin admin){
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }
}
