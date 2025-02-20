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
    private JwtTokenServices jwtTokenServices;
    @Autowired
    private MyCustomUserDetailsService myCustomUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        String jwtToken = null;
        String userEmail = null;


        String requestPath = request.getRequestURI();


        if ("/api/v1/auth/login".equals(requestPath)) {
            filterChain.doFilter(request, response);
            return;
        }

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }


        jwtToken = authHeader.substring(7);
        userEmail = jwtTokenServices.extractUsername(jwtToken);
        try {
            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = myCustomUserDetailsService.loadUserByUsername(userEmail);

                if (jwtTokenServices.validateToken(jwtToken, userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        } catch (ExpiredJwtException e){
            JsonErrorResponse.sendError(response,HttpServletResponse.SC_UNAUTHORIZED,"Token is EXPIRED", request.getRequestURI());
            return;
        } catch (Exception e){
            JsonErrorResponse.sendError(response,HttpServletResponse.SC_UNAUTHORIZED,"Token INVALID!", request.getRequestURI());
            return;
        }
        filterChain.doFilter(request, response);
    }
}



