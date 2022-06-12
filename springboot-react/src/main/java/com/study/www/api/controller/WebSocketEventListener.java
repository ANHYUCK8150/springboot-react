package com.study.www.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.study.www.api.entity.dto.ChatMessage;
import com.study.www.api.entity.dto.UserDto;

@Component
public class WebSocketEventListener {

	private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

	@Autowired
	private SimpMessageSendingOperations messagingTemplate;

	@EventListener
	public void handleWebSocketConnectListener(SessionConnectedEvent event) {
		logger.info("Received a new web socket connection");
	}

	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

		ChatMessage chatMessage = (ChatMessage)headerAccessor.getSessionAttributes().get("ChatMessage");
		List<UserDto> userList = (List<UserDto>)headerAccessor.getSessionAttributes().get("UserList");

		if (chatMessage != null) {
			int idx = 0;
			for (UserDto user : userList) {

				if (user.getId().equals(chatMessage.getUser().getId())) {
					userList.remove(idx);
					break;
				}
				idx++;
			}

			messagingTemplate.convertAndSend("/topic/addChatToClient/" + chatMessage.getRoomId(), userList);
		}
	}
}
