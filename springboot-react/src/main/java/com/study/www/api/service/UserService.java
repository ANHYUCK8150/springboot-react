package com.study.www.api.service;

import org.springframework.stereotype.Service;

import com.study.www.api.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
}
