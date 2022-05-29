package com.study.www.api.entity.dto;

import com.study.www.api.entity.Chatting;
import com.study.www.api.entity.Room;
import com.study.www.api.entity.User;

import lombok.Data;

@Data
public class ChatMessage {
	private String message;
    private User user;
    private Room room;
    
    public ChatMessage() {}
    
    public ChatMessage(Chatting chat) {
    	this.message = chat.getMessage();
    	this.user = chat.getUser();
    	this.room = chat.getRoom();
    }
    
    public Chatting toEntity() {
    	return Chatting.builder()
    			.message(message)
    			.user(user)
    			.room(room)
    			.build();
    }
}
