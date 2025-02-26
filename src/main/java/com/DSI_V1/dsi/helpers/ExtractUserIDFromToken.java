package com.DSI_V1.dsi.helpers;

import com.DSI_V1.dsi.services.JwtTokenServices;
import com.DSI_V1.dsi.services.auth.MyCustomUserDetailsService;
import com.DSI_V1.dsi.services.auth.MyCustomerUserDetails;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExtractUserIDFromToken {
    @Autowired
    private JwtTokenServices jwtTokenServices;

    @Autowired
    private MyCustomUserDetailsService myCustomUserDetailsService;


    private  MyCustomerUserDetails myCustomerUserDetails;





    public int getUserIDFromToken(HttpServletRequest request){
        Integer user_id = null;

        String authHeader = request.getHeader("Authorization");

        String jwtToken = null;

        String userEmail = null;

        if(authHeader !=null || authHeader.startsWith("Bearer ")){
            jwtToken = authHeader.substring(7);

            userEmail = jwtTokenServices.extractUsername(jwtToken);

        }

        if (userEmail != null){
            myCustomerUserDetails =(MyCustomerUserDetails) myCustomUserDetailsService.loadUserByUsername(userEmail);

            user_id = myCustomerUserDetails.getID();
        }

        return user_id;
    }


}
