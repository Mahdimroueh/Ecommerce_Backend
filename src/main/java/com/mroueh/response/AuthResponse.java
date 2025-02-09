package com.mroueh.response;

import com.mroueh.enums.Role;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
public class AuthResponse {

    private boolean isAuthenticated;

    private String roles;

}

