package com.study.www.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "chatting")
@Getter
@NoArgsConstructor
public class Chatting extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String message;

	@Column
	private int notRead;

	@Column
	private Long userId;

	@Column
	private Long roomId;

	@Builder
	public Chatting(Long id, String message, int notRead, Long userId, Long roomId) {
		this.id = id;
		this.message = message;
		this.notRead = notRead;
		this.userId = userId;
		this.roomId = roomId;
	}
}
