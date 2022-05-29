package com.study.www.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "chatting")
@Getter
@NoArgsConstructor
public class Chatting extends BaseTimeEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String message;
	
	@Column
	private int notRead;
	
	@ManyToOne(optional = false, targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn
	@JsonIgnore
	private User user;
	
	@ManyToOne(targetEntity = Room.class, fetch = FetchType.EAGER)
	@JoinColumn
	@JsonIgnore
	private Room room;
	
	@Builder
	public Chatting(Long id, String message, int notRead, User user, Room room) {
		this.id = id;
		this.message = message;
		this.notRead = notRead;
		this.user = user;
		this.room = room;
	}
}
