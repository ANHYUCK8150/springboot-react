package com.study.www.api.entity.dto;

import com.study.www.api.entity.Chatting;
import com.study.www.api.entity.User;

import lombok.Data;

@Data
public class ChatMessage {
	private String message;
	private User user;
	private Long roomId;
	private String type = "M";

	public ChatMessage() {}

	public ChatMessage(Chatting chat) {
		this.message = chat.getMessage();
		this.user = chat.getUser();
		this.roomId = chat.getRoomId();
	}

	public Chatting toEntity() {
		return Chatting.builder()
			.message(message)
			.user(user)
			.roomId(roomId)
			.build();
	}
}
