package com.edisonla.evaluacion_desempeno.controllers;

import com.edisonla.evaluacion_desempeno.dtos.AuthRequest;
import com.edisonla.evaluacion_desempeno.entities.UserInfo;
import com.edisonla.evaluacion_desempeno.services.JwtService;
import com.edisonla.evaluacion_desempeno.services.UserInfoService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private UserInfoService service;

    private JwtService jwtService;

    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        return service.addUser(userInfo);
    }

    // Removed the role checks here as they are already managed in SecurityConfig

    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.email(), authRequest.password())
        );
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.email());
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }
}