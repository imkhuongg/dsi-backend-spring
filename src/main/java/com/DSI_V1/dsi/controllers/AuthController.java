package com.DSI_V1.dsi.controllers;

import com.DSI_V1.dsi.dto.requests.AdminLogin;
import com.DSI_V1.dsi.dto.requests.LoginRequest;
import com.DSI_V1.dsi.dto.responses.AdminAuthResponse;
import com.DSI_V1.dsi.dto.responses.AuthResponse;
import com.DSI_V1.dsi.models.Admin;
import com.DSI_V1.dsi.services.JwtTokenServices;
import com.DSI_V1.dsi.services.auth.UserService;
import com.DSI_V1.dsi.services.auth.MyCustomUserDetailsService;
import com.DSI_V1.dsi.services.auth.MyCustomerUserDetails;
import com.DSI_V1.dsi.services.auth.admin.AdminService;
import com.DSI_V1.dsi.services.auth.admin.AdminUserDetails;
import com.DSI_V1.dsi.services.auth.admin.AdminUserDetailsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.Cookie;

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

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminUserDetailsServices adminUserDetailsServices;



    @PostMapping("/login/admin")
    public ResponseEntity<?> adminLogin(@RequestBody AdminLogin adminLogin){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(adminLogin.getEmail(), adminLogin.getPassword())
            );

            AdminUserDetails adminUserDetails = (AdminUserDetails) authentication.getPrincipal();

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtTokenServices.generateToken(adminUserDetails);

            String refreshToken  = jwtTokenServices.generateRefreshToken(adminUserDetails);

            AdminAuthResponse authResponse = new AdminAuthResponse(token,refreshToken, adminUserDetails);

            return ResponseEntity.ok(authResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email hoặc mật khẩu không đúng!");
        }

    }

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

        return  new ResponseEntity<>("Registration successfully" , HttpStatus.CREATED);
    }
    @PostMapping("/register/admin")
    public ResponseEntity<?> AdminRegister(@RequestBody Admin admin){
        try {
            Admin savedAdmin = adminService.createAdmin(admin);
            return new ResponseEntity<>("admin create successfully ", HttpStatus.CREATED);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something wentWrong: " + e.getMessage());
        }
    }
}
