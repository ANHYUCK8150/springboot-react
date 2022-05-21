package com.study.www.api.entity.dto;

import com.study.www.api.entity.Town;

import lombok.Getter;

@Getter
public class TownIdDto {
	private Long id;
	private String city;
	
	public TownIdDto() {
		
	}

	public TownIdDto(Town town) {
		this.id = town.getId();
		this.city = town.getCity() + " " + town.getDistrict() + " " + town.getName() + " " + town.getEtc();
	}
}
