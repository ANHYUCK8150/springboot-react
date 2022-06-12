package com.study.www.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.www.api.entity.Chatting;

@Repository
public interface ChattingRepository extends JpaRepository<Chatting, Long> {

	List<Chatting> getByRoomId(Long roomId);

}
