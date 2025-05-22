package com.crud.code.hospital.utility;

//These are Spring Security classes used to authenticate and store user details in the security context.

import com.crud.code.hospital.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired

    private JwtUtility jwtUtil;
    @Autowired
    private CustomUserDetailsService  userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //Reads the Authorization header from the request
        String authorizationHeader = request.getHeader("Authorization");
        String token = null;
        String userName = null;
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer "))
        {
            token = authorizationHeader.substring(7);
            userName = jwtUtil.extractUsername(token); // extract the username from the token payload of jwtutility
        }
        if(userName!= null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
            if(jwtUtil.validateToken(token , userDetails.getUsername())){
                UsernamePasswordAuthenticationToken authToken =new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
            }

