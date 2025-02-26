package com.DSI_V1.dsi.config.filters;

import com.DSI_V1.dsi.helpers.JsonErrorResponse;
import com.DSI_V1.dsi.services.JwtTokenServices;
import com.DSI_V1.dsi.services.auth.MyCustomUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenServices jwtTokenService;
    @Autowired
    private MyCustomUserDetailsService myCustomUserDetailService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        String jwtToken = null;

        String userEmail = null;

        // CHECK / VALIDATE AUTHORIZATION HEADER:
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);

            return;
        }

        jwtToken = authHeader.substring(7);

        userEmail = jwtTokenService.extractUsername(jwtToken);

        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails =  myCustomUserDetailService.loadUserByUsername(userEmail);


            if(jwtTokenService.validateToken(jwtToken, userDetails)){
                UsernamePasswordAuthenticationToken userAuthToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()

                        );

                userAuthToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(userAuthToken);
            }

        }



        filterChain.doFilter(request, response);
    }

}



