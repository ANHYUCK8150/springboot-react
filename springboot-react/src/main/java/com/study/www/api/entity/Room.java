package com.study.www.api.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "room")
@Getter
@NoArgsConstructor
public class Room extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String lastChat;
	
	@OneToMany(targetEntity = Chatting.class,fetch = FetchType.LAZY)
	@JoinColumn(name="room_id")
	@JsonIgnore
	private List<Chatting> chattings = new ArrayList<Chatting>();
	
	@OneToMany(targetEntity = Participant.class,fetch = FetchType.LAZY)
	@JoinColumn(name="room_id")
	@JsonIgnore
	private List<Participant> participants = new ArrayList<Participant>();
	
	@Builder
	public Room(Long id, String lastChat) {
		this.id = id;
		this.lastChat = lastChat;
	}
}
