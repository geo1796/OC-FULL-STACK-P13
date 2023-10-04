package com.openclassrooms.yourcaryourway.controller;

import com.openclassrooms.yourcaryourway.dto.request.LoginRequest;
import com.openclassrooms.yourcaryourway.dto.response.LoginResponse;
import com.openclassrooms.yourcaryourway.security.service.MyUserDetails;
import com.openclassrooms.yourcaryourway.security.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
@Slf4j
public class LoginController {
    private JwtUtil jwtUtil;
    private DaoAuthenticationProvider authenticationProvider;

    @PostMapping()
    ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
            LoginResponse loginResponse = jwtUtil.generateTokenFromUserDetails(userDetails);
            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        } catch (AuthenticationException e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
