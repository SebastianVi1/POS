package org.sebas.pos.controller;

import jakarta.validation.Valid;
import org.sebas.pos.dto.*;
import org.sebas.pos.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> loginUser(@RequestBody @Valid LoginDto loginDto){
        AuthResponseDto response = authService.loginUser(loginDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponseDto> refreshToken(@RequestBody @Valid RefreshTokenDto request){
        AuthResponseDto response = authService.refreshToken(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register")
    public ResponseEntity<UserDto> postAuthResponseDto(@RequestBody UserDto registerDto) {
        UserDto response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
