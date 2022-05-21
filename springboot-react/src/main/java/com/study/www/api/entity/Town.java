package com.study.www.api.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity(name = "town")
public class Town {
	@Id
	@Column(name="town_id")
	private Long id;
	
	@Column
	private String city;
	
	@Column
	private String district;
	
	@Column(name="town_name")
	private String name;
	
	@Column
	private String etc;
	
	@Column(precision = 18, scale = 10)
	private String latitude;
	
	@Column(precision = 18, scale = 10)
	private String longitude;
	
	@Builder
	public Town(Long id, String city, String district, String name, String etc, String latitude, String longitude) {
		this.id = id;
		this.city = city;
		this.district = district;
		this.name = name;
		this.etc = etc;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	

}
