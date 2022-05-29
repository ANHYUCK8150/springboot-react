package com.study.www.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.www.api.entity.User;
import com.study.www.api.entity.dto.UserDto;
import com.study.www.api.repository.UserRepository;
import com.study.www.auth.entity.CurrentUser;
import com.study.www.auth.entity.UserPrincipal;
import com.study.www.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/user/me")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
    
    @GetMapping("/user/list")
    public List<UserDto> userList(@CurrentUser UserPrincipal userPrincipal){
    	List<UserDto> list = userRepository.findAll().stream().map(c->new UserDto(c)).collect(Collectors.toList());
    	
    	return list;
    }
}
