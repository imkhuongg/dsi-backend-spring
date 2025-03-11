package com.DSI_V1.dsi.dto.responses;

import com.DSI_V1.dsi.services.auth.admin.AdminUserDetails;
import jakarta.servlet.http.Cookie;

import java.time.LocalDateTime;

public class AdminAuthResponse {
    private String RefreshToken;
    private String token;
    private AdminUserDetails adminUserDetails;

    public AdminAuthResponse(String token, String RefreshToken,AdminUserDetails adminUserDetails) {
        this.token = token;
        this.RefreshToken = RefreshToken;
        this.adminUserDetails = adminUserDetails;
    }


    public String getToken() {
        return token;
    }

    public String getRefreshToken(){
        return RefreshToken;
    }

    public int getID(){
        return this.adminUserDetails.getID();
    }
    public String getUserName(){
        return this.adminUserDetails.getUsername();
    }
    public String getFirstName(){
        return this.adminUserDetails.getFirstName();
    }
    public String getLastName(){
        return this.adminUserDetails.getLastName();
    }
    public String getAvatar(){
        return this.adminUserDetails.getAvatar();
    }
    public LocalDateTime getCreatedAt(){
        return this.adminUserDetails.getCreatedAt();
    }
    public LocalDateTime getUpdatedAt(){
        return this.adminUserDetails.getUpdatedAt();
    }


}
