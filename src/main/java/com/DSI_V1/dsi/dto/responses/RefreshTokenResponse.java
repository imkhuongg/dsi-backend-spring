package com.DSI_V1.dsi.dto.responses;

public class RefreshTokenResponse {
    private boolean isExpired;
    private String token;
    private String username;

    public RefreshTokenResponse(boolean isExpired, String token,String username) {
        this.isExpired = isExpired;
        this.token = token;
        this.username = username;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }
}
