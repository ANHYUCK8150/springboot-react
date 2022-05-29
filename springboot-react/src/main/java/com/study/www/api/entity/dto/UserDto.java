package com.study.www.api.entity.dto;

import com.study.www.api.entity.User;

import lombok.Getter;

@Getter
public class UserDto {
	    private Long id;
	    private String name;
	    private String email;
	    private String imageUrl;
	    
	    public UserDto(User user) {
	    	this.id = user.getId();
	    	this.name = user.getName();
	    	this.email = user.getEmail();
	    	this.imageUrl = user.getImageUrl();
	    }
}
