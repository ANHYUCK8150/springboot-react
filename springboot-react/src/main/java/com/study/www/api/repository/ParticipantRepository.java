package com.study.www.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.www.api.entity.Participant;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {

}
