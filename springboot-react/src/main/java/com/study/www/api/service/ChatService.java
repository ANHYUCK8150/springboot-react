package com.study.www.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.www.api.entity.Chatting;
import com.study.www.api.entity.Room;
import com.study.www.api.entity.dto.ChatMessage;
import com.study.www.api.repository.ChattingRepository;
import com.study.www.api.repository.RoomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatService {
	private final ChattingRepository chattingRepository;
	private final RoomRepository roomRepository;

	public List<Chatting> getChatList(Long roomId) {
		return chattingRepository.getByRoomId(roomId);
	}

	public void insertChatting(ChatMessage messageDTO) {
		Chatting chatting = messageDTO.toEntity();
		chattingRepository.save(chatting);
		
	}
	
	@Transactional
	public void checkRoom(Long room) {
		Room roomInfo = roomRepository.getById(room);
		
		if(roomInfo == null) {
			roomRepository.save(
					Room.builder()
					.id(room)
					.lastChat("")
					.build()
				);
		}
		
	}

	public Room getRoom(Long roomId) {
		return roomRepository.getById(roomId);
	}
	
	
}
