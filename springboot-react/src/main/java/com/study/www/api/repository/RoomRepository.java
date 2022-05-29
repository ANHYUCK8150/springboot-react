package com.study.www.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.www.api.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

}
