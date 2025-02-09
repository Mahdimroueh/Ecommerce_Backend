package com.mroueh.controller;

import com.mroueh.dto.UserDto;
import com.mroueh.entity.User;
import com.mroueh.response.ApiResponse;
import com.mroueh.response.AuthResponse;
import com.mroueh.service.impl.AuthService;
import com.mroueh.service.impl.JwtService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    @GetMapping("/me")
    public ResponseEntity<AuthResponse> authMe(@CookieValue(value = "jwt", defaultValue = "") String jwt) {
        if (jwt.isEmpty()) {
            return ResponseEntity.status(200).body(new AuthResponse(false, null));
        }
        try {
            // Validate the JWT and extract roles or claims
            boolean isValid = jwtService.validateToken(jwt);
            String roles = jwtService.getRoleFromToken(jwt);
            AuthResponse response = new AuthResponse(isValid, roles);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Handle invalid or expired token
            return ResponseEntity.status(200).body(new AuthResponse(false, null));
        }
    }


    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody UserDto req) {
        System.out.println("Register");
        return  ResponseEntity.ok(authService.registerUser(req));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody UserDto request , HttpServletResponse response) {
        return  ResponseEntity.ok(authService.login(request , response));
    }
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse> logout(HttpServletResponse response) {
        return  ResponseEntity.ok(authService.logout(response));
    }

}
