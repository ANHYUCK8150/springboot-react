package com.study.www.api.entity.dto;

import com.study.www.api.entity.Town;

import lombok.Getter;

@Getter
public class TownDto {
	private String city;
	private String district;
	private String name;
	private String etc;
	
	public TownDto() {
		
	}

	public TownDto(Town town) {
		this.city = town.getCity();
		this.district = town.getDistrict();
		this.name = town.getName();
		this.etc = town.getEtc();
	}
	
}