package com.study.www.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

	@Builder
	public Room(Long id, String lastChat) {
		this.id = id;
		this.lastChat = lastChat;
	}
}
