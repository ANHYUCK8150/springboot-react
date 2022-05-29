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

@Entity(name = "participant")
@Getter
@NoArgsConstructor
public class Participant extends BaseTimeEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private int notReadChat;
	
	@ManyToOne(targetEntity = Room.class,fetch=FetchType.LAZY)
	@JoinColumn
	@JsonIgnore
	private Room room;
	
	@ManyToOne(targetEntity = User.class,fetch = FetchType.LAZY)
	@JoinColumn
	@JsonIgnore
	private User user;
	
	@Builder
	public Participant(Long id, int notReadChat) {
		this.id = id;
		this.notReadChat = notReadChat;
	}
}
