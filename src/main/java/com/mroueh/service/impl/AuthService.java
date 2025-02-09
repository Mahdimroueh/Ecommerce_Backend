package com.mroueh.service.impl;

import com.mroueh.dto.UserDto;
import com.mroueh.entity.ShoppingCart;
import com.mroueh.entity.User;
import com.mroueh.enums.Role;
import com.mroueh.exception.DuplicateUsernameException;
import com.mroueh.mapper.UserMapper;
import com.mroueh.repository.ShoppingCartRepository;
import com.mroueh.repository.UserRepository;
import com.mroueh.response.ApiResponse;
import com.mroueh.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final ShoppingCartRepository shoppingCartRepository;


    public ApiResponse registerUser(UserDto req) {
        boolean usernameExists = userRepository.existsByUsername(req.getUsername());
        if (usernameExists) {
            throw new DuplicateUsernameException("Username Already Used");
        }
        User user =  userMapper.toEntity(req);
        user.setRole(String.valueOf(Role.ROLE_USER));
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        User savedUser = userRepository.save(user);
        ShoppingCart cart = new ShoppingCart();
        cart.setUser(savedUser);
        shoppingCartRepository.save(cart);
        return new ApiResponse("Successfully Registered" , true);
    }

    public ApiResponse login( UserDto request, HttpServletResponse response) {
        User user = userService.getUserByUsername(request.getUsername());

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String jwt = jwtService.generateToken(user.getUsername(), user.getRole());

        Cookie cookie = new Cookie("jwt", jwt);
        cookie.setHttpOnly(true);
        cookie.setSecure(true); // Use HTTPS in production
        cookie.setPath("/");
        cookie.setMaxAge(60*60*24); // 1 hour
        response.addCookie(cookie);

        return new ApiResponse("Login successful" , true);
    }

    public ApiResponse logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("jwt", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return new ApiResponse("Logout successful" , true);
    }
}

