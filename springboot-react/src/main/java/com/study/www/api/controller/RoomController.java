package com.study.www.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.study.www.api.entity.Chatting;
import com.study.www.api.entity.Room;
import com.study.www.api.service.ChatService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/room")
public class RoomController {
	private final ChatService chatService;
	
	@GetMapping("/chatList")
    public List<Chatting> chatList(@RequestParam("roomId") Long roomId){
    	return chatService.getChatList(roomId);
    }
    
    @GetMapping("/getRoom")
    public Room getRoom(@RequestParam("roomId") Long roomId){
    	return chatService.getRoom(roomId);
    }
}
