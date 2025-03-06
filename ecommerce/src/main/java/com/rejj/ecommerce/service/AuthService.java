package com.rejj.ecommerce.service;

import org.springframework.stereotype.Service;

import com.rejj.ecommerce.controller.RegisterRequest;
import com.rejj.ecommerce.controller.TokenResponse;
import com.rejj.ecommerce.model.User;
import com.rejj.ecommerce.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public TokenResponse register(RegisterRequest request){
        
        User user = new User();
    
        

    }


}
