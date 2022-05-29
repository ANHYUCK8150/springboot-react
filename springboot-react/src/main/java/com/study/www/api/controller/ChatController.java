package com.study.www.api.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import com.study.www.api.entity.dto.ChatMessage;
import com.study.www.api.service.ChatService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ChatController {

	private static Set<String> userList = new HashSet<>();

    private final SimpMessagingTemplate simpMessagingTemplate;
    
    private final ChatService chatService;
    
    @MessageMapping("/chat")
    public void sendMessage(@Payload ChatMessage messageDTO){
    	
    	chatService.checkRoom(messageDTO.getRoom().getId());
    	
    	chatService.insertChatting(messageDTO);
    	
    	System.out.println(messageDTO.getUser().getId() + " : " + messageDTO.getMessage() + " : roomid = " + messageDTO.getRoom().getId());
    	
        this.simpMessagingTemplate.convertAndSend("/queue/addChatToClient/"+messageDTO.getRoom().getId(),messageDTO);
    }

    @MessageMapping("/join")
    public void joinUser(@Payload String userId){
        userList.add(userId);
    }
  
}
