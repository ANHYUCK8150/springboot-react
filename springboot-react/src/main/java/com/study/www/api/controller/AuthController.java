package com.study.www.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.study.www.api.entity.User;
import com.study.www.api.entity.dto.ApiResponse;
import com.study.www.api.entity.dto.AuthResponse;
import com.study.www.api.entity.dto.LoginRequest;
import com.study.www.api.entity.dto.SignUpRequest;
import com.study.www.api.entity.dto.UserDto;
import com.study.www.api.repository.UserRepository;
import com.study.www.auth.entity.AuthProvider;
import com.study.www.auth.token.TokenProvider;
import com.study.www.exception.BadRequestException;

import java.net.URI;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final TokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("이미 해당 이메일을 사용하고 있습니다.");
        }

        // 계정 생성
        User result = userRepository.save(User.builder()
                .name(signUpRequest.getName())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .provider(AuthProvider.local)
                .build()
        );

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/me")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "성공적으로 계정 생성이 되었습니다."));
    }
    
    @PostMapping("/check")
    public UserDto check(@RequestBody AuthResponse authResponse) {
    	UserDto user = new UserDto();
    	if(tokenProvider.validateToken(authResponse.getAccessToken())) {
    		Long userId = tokenProvider.getUserIdFromToken(authResponse.getAccessToken());
    		
    		if(userId != null) {
    			User use = userRepository.getById(userId);
    			
    			user.setId(use.getId());
    			user.setEmail(use.getEmail());
    			user.setName(use.getName());
    		}
    	}
    	return user;
    	//return ResponseEntity.ok(user);
    }

}
