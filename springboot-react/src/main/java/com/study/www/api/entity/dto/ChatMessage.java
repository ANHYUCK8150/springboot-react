package com.study.www.api.entity.dto;

import com.study.www.api.entity.Chatting;

import lombok.Data;

@Data
public class ChatMessage {
	private String message;
	private Long userId;
	private Long roomId;
	private String type = "M";

	public ChatMessage() {}

	public ChatMessage(Chatting chat) {
		this.message = chat.getMessage();
		this.userId = chat.getUserId();
		this.roomId = chat.getRoomId();
	}

	public Chatting toEntity() {
		return Chatting.builder()
			.message(message)
			.userId(userId)
			.roomId(roomId)
			.build();
	}
}
