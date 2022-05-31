package com.study.www.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import com.study.www.api.entity.User;
import com.study.www.api.entity.dto.ChatMessage;
import com.study.www.api.entity.dto.UserDto;
import com.study.www.api.service.ChatService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ChatController {

	private static List<UserDto> userList = new ArrayList<UserDto>();

    private final SimpMessagingTemplate simpMessagingTemplate;
    
    private final ChatService chatService;

    @MessageMapping("/chat")
    public void sendMessage(@Payload ChatMessage messageDTO){
    	
    	chatService.checkRoom(messageDTO.getRoom().getId());
    	
    	chatService.insertChatting(messageDTO);
    	
    	System.out.println(messageDTO.getUser().getId() + " : " + messageDTO.getMessage() + " : roomid = " + messageDTO.getRoom().getId());
    	
        this.simpMessagingTemplate.convertAndSend("/topic/addChatToClient/"+messageDTO.getRoom().getId(),messageDTO);
    }

    @MessageMapping("/join")
    public void joinUser(@Payload ChatMessage messageDTO, SimpMessageHeaderAccessor headerAccessor){    	
    	UserDto userDto = new UserDto(messageDTO.getUser());
    	userList.add(userDto);
    	
    	List<UserDto> dicList = deduplication(userList, UserDto::getId);
    	
    	headerAccessor.getSessionAttributes().put("ChatMessage", messageDTO);
    	headerAccessor.getSessionAttributes().put("UserList", userList);
    	
    	this.simpMessagingTemplate.convertAndSend("/topic/addChatToClient/"+messageDTO.getRoom().getId(),dicList);
    }
    
    @MessageMapping("/leave")
    public void leaveUser(@Payload ChatMessage messageDTO, SimpMessageHeaderAccessor headerAccessor){    	

    	int idx = 0;
        for(UserDto user : userList) {
        	
        	if(user.getId().equals(messageDTO.getUser().getId())) {
        		userList.remove(idx);
        	}
        	idx++;
        }
        
    	this.simpMessagingTemplate.convertAndSend("/topic/addChatToClient/"+messageDTO.getRoom().getId(),userList);
    }
    
    public static <T> List<T> deduplication(final List<T> list, Function<? super T, ?> key) {
        return list.stream()
            .filter(deduplication(key))
            .collect(Collectors.toList());
    }

    private static <T> Predicate<T> deduplication(Function<? super T, ?> key) {
        final Set<Object> set = ConcurrentHashMap.newKeySet();
        return predicate -> set.add(key.apply(predicate));
    }
	
  
}
