package com.mroueh.controller;

import com.mroueh.response.ApiResponse;
import com.mroueh.response.AuthResponse;
import com.mroueh.service.impl.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class UserController {


    @GetMapping("/user/test")
    public ResponseEntity<ApiResponse> login() {
        return  ResponseEntity.ok(new ApiResponse("success" , true));
    }

}
