package com.study.www.api.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.study.www.api.entity.Town;

@Repository
public interface AddressRepository extends JpaRepository<Town, Long> {
	String nearQuery = "(6371*acos(cos(radians(:latitude))*cos(radians(m.latitude))"
			+ "*cos(radians(m.longitude) - radians(:longitude)) + sin(radians(:latitude))"
			+ "*sin(radians(m.latitude))))";
	String getNearsql = "select m from town m where " + nearQuery + " < :range";

	List<Town> getByName(String string);

	Town getByCityAndName(String string, String string2);

	Town getByCityAndDistrictAndName(String string, String string2, String string3);

	Town getByCityAndDistrictAndNameAndEtc(String string, String string2, String string3, String string4);
	
	@Query(getNearsql)
	List<Town> getNearAddress(@Param("latitude") String latitude,@Param("longitude") String longitude,@Param("range") double range);


}
