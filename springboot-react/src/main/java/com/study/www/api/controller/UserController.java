package com.study.www.api.controller;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.study.www.api.entity.User;
import com.study.www.api.entity.dto.AuthResponse;
import com.study.www.api.entity.dto.UserDto;
import com.study.www.api.repository.UserRepository;
import com.study.www.api.service.UserService;
import com.study.www.auth.entity.CurrentUser;
import com.study.www.auth.entity.UserPrincipal;
import com.study.www.auth.token.TokenProvider;
import com.study.www.exception.ResourceNotFoundException;


@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserRepository userRepository;
    
    private final UserService userService;
    
    private final TokenProvider tokenProvider;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
    
    @PostMapping("/user/list")
    public List<UserDto> getList(@RequestBody AuthResponse authResponse){
    	List<UserDto> list = new ArrayList<UserDto>();
    	
    	Long userId = tokenProvider.getUserIdFromToken(authResponse.getAccessToken());
    	
    	if(userId != null) {
    		
    	}
    	
    	return list;
    }
}
