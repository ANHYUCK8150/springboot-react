package com.study.www.town.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.www.town.entity.Town;

@Repository
public interface TownRepository extends JpaRepository<Town, Long> {

}
