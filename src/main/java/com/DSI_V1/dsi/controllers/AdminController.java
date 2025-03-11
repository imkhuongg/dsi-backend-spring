package com.DSI_V1.dsi.controllers;

import com.DSI_V1.dsi.dto.requests.RefershTokenDTO.AdminRefreshDTO;
import com.DSI_V1.dsi.dto.responses.RefreshTokenResponse;
import com.DSI_V1.dsi.services.JwtTokenServices;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("api/v1/admin")
public class AdminController {
    @Autowired
    private JwtTokenServices jwtTokenServices;

    @PostMapping("/refresh")
    public ResponseEntity<?> getRefresh(@RequestBody AdminRefreshDTO adminRefreshDTO){
        String refreshToken = adminRefreshDTO.getRefreshToken();
        boolean isExpired = jwtTokenServices.isTokenExpired(refreshToken);
        String userName = jwtTokenServices.extractUsername(refreshToken);

        if(isExpired){
            return ResponseEntity.ok(new RefreshTokenResponse(true, "null",userName));
        }



        String token = jwtTokenServices.generateToken(userName);
        return ResponseEntity.ok(new RefreshTokenResponse(false, token,userName));
    }

}
