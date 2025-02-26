package com.DSI_V1.dsi.dto.responses;

import com.DSI_V1.dsi.services.auth.MyCustomerUserDetails;

public class AuthResponse {

    private String token;
    private MyCustomerUserDetails myCustomerUserDetails;

    public AuthResponse(String token, MyCustomerUserDetails myCustomerUserDetails){
        this.myCustomerUserDetails = myCustomerUserDetails;
        this.token = token;
    }

    public String getToken() {
        return token;
    }
    public int getID(){
        return this.myCustomerUserDetails.getID();
    }

    public String getUsername(){
        return this.myCustomerUserDetails.getUsername();
    }

    public String getFirstname(){
        return  this.myCustomerUserDetails.getFirst_name();
    }
    public String getLastname(){
        return  this.myCustomerUserDetails.getLastname();
    }

}
