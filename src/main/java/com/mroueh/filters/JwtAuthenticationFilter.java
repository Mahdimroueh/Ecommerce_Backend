package com.mroueh.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mroueh.entity.User;
import com.mroueh.exception.EntityNotFoundException;
import com.mroueh.exception.SessionExpiredException;
import com.mroueh.exception.UserNameNotFoundException;
import com.mroueh.repository.UserRepository;
import com.mroueh.response.ApiResponse;
import com.mroueh.response.AuthResponse;
import com.mroueh.service.UserService;
import com.mroueh.service.impl.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.ErrorResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String token = null;
            if (request.getCookies() != null) {
                for (Cookie cookie : request.getCookies()) {
                    if ("jwt".equals(cookie.getName())) {
                        token = cookie.getValue();
                    }
                }
            }

            if (token != null) {
                String username = jwtService.extractClaims(token).getSubject();
                User user = userService.getUserByUsername(username);

                if (jwtService.isTokenValid(token, username)) {
                    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            user, null, List.of(authority));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    throw new SessionExpiredException("Token is not valid or expired");
                }
            }
            filterChain.doFilter(request, response);
        } catch (SessionExpiredException ex ) {
            sendErrorResponse(response, "Session expired, please log in again");
        } catch (EntityNotFoundException ex ) {
            sendErrorResponse(response, "please Register again");
        }
        catch (Exception ex) {
            throw ex;
        }
    }

    private void sendErrorResponse(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        response.setContentType("application/json");

        Cookie cookie = new Cookie("jwt", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        ApiResponse errorResponse = new ApiResponse(message ,false);

        objectMapper.writeValue(response.getOutputStream(), errorResponse);


    }
}
