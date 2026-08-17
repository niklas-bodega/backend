package com.lasias.hostelbookingbackend.controllers;

import com.lasias.hostelbookingbackend.dtos.AuthRequestDTO;
import com.lasias.hostelbookingbackend.models.AppUser;
import com.lasias.hostelbookingbackend.services.AppUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AppUserService appUserService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody AuthRequestDTO request) {
        ResponseCookie jwtCookie = appUserService.loginUser(request);
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,jwtCookie.toString()).build();
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(@AuthenticationPrincipal AppUser user) {
        return appUserService.logout(user);
    }

    @GetMapping("/logout-all-devices")
    public ResponseEntity<String> logoutAllDevices(@AuthenticationPrincipal AppUser user){
        return appUserService.logoutAllDevices(user);
    }

}
