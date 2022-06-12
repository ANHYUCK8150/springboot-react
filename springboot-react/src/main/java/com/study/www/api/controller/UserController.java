package com.study.www.api.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.study.www.api.entity.User;
import com.study.www.api.entity.dto.UserDto;
import com.study.www.api.repository.UserRepository;
import com.study.www.auth.entity.CurrentUser;
import com.study.www.auth.entity.UserPrincipal;
import com.study.www.exception.ResourceNotFoundException;
import com.study.www.upload.dto.FileUploadResponse;
import com.study.www.upload.service.S3Uploader;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserController {

	private final UserRepository userRepository;
	private final S3Uploader s3Uploader;

	@GetMapping("/user/me")
	public User getCurrentUser(@CurrentUser
	UserPrincipal userPrincipal) {
		System.out.println(userPrincipal.getAttributes());
		return userRepository.findById(userPrincipal.getId())
			.orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
	}

	@GetMapping("/user/chknickname")
	public ResponseEntity<Boolean> chknickname(@RequestParam
	String nickname) {
		return ResponseEntity.ok(userRepository.existsByName(nickname));
	}

	@PostMapping("/user/updateProfile")
	public ResponseEntity<?> setImage(@CurrentUser
	UserPrincipal userPrincipal, @RequestPart
	String nickname, @RequestPart
	MultipartFile imageFile) {
		Boolean chk = false;
		User user = userRepository.getById(userPrincipal.getId());

		String currentImg = user.getImageUrl();

		try {
			FileUploadResponse profile = s3Uploader.upload(imageFile, "profile");

			user.imageUpdate(profile.getUrl());
			user.nicknameUpdate(nickname);
			userRepository.save(user);

			s3Uploader.removeOldFile(currentImg);

			chk = true;

		} catch (IOException e) {

		}
		return ResponseEntity.ok(chk);
	}

	@GetMapping("/user/list")
	public List<UserDto> userList(@CurrentUser
	UserPrincipal userPrincipal) {
		List<UserDto> list = userRepository.findAll().stream().map(c -> new UserDto(c)).collect(Collectors.toList());

		return list;
	}
}
