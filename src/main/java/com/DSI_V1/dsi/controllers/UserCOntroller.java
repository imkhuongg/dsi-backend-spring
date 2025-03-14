package com.DSI_V1.dsi.controllers;


import com.DSI_V1.dsi.dto.responses.CountResponse;
import com.DSI_V1.dsi.services.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/count")
    public ResponseEntity<?> count(){
        long count = userService.count();

        return ResponseEntity.ok(new CountResponse(count));
    }
}
