package com.DSI_V1.dsi.controllers;

import com.DSI_V1.dsi.dto.requests.LoginRequest;
import com.DSI_V1.dsi.dto.responses.AuthResponse;
import com.DSI_V1.dsi.services.JwtTokenServices;
import com.DSI_V1.dsi.services.auth.UserService;
import com.DSI_V1.dsi.services.auth.MyCustomUserDetailsService;
import com.DSI_V1.dsi.services.auth.MyCustomerUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private MyCustomUserDetailsService myCustomUserDetailsService;

    @Autowired
    private JwtTokenServices jwtTokenServices;



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){

        Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword())
        );

        MyCustomerUserDetails userDetails =
                (MyCustomerUserDetails) myCustomUserDetailsService.loadUserByUsername(loginRequest.getEmail());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenServices.generateToken(userDetails);

        AuthResponse response = new AuthResponse(token ,userDetails);
        return new ResponseEntity<>(response , HttpStatus.ACCEPTED);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestParam("first_name") String firstName,
                                   @RequestParam("last_name") String lastName,
                                   @RequestParam("email") String email,
                                   @RequestParam("password") String password){
        String hashed_password = passwordEncoder.encode(password);

        int result = userService.registerUser(firstName,lastName,email,hashed_password);
        if(result != 1) return new ResponseEntity<>("Something went wrong" , HttpStatus.BAD_REQUEST);

        return  new ResponseEntity<>("Registration Successfull" , HttpStatus.CREATED);
    }
}
