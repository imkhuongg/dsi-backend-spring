package com.DSI_V1.dsi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/")
    public String getHome(){
        return  "HOME";
    }

    @GetMapping("/test")
    public String getTest(){

        return  "HELLO <br/>WELCOME TO SPRING BOOT WIDTH INDEX API";
    }

    @GetMapping("/app/admin")
    public String getAdmin(){
        return "admin";
    }

    @GetMapping("/app/dashboard")
    public String getDashboard(){
        return "Dash board";
    }
}
