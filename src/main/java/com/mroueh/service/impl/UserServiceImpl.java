package com.mroueh.service.impl;

import com.mroueh.entity.User;
import com.mroueh.exception.EntityNotFoundException;
import com.mroueh.repository.UserRepository;
import com.mroueh.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(()-> new EntityNotFoundException("Invalid Username"));
    }
}
