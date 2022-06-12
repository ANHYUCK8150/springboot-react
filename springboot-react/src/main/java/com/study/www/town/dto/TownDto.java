package com.study.www.town.dto;

import com.study.www.town.entity.Town;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TownDto {
	private Long id;
	private String city;
	private String district;
	private String name;
	private String etc;
	private String adress;

	public TownDto() {}

	public TownDto(Town town) {
		this.id = town.getId();
		this.city = town.getCity();
		this.district = town.getDistrict();
		this.name = town.getName();
		this.etc = town.getEtc();
		this.adress = (town.getCity() + " " + town.getDistrict() + " " + town.getName() + " " + town.getEtc()).trim();
	}

	public void setAdress(String city, String district, String name, String etc) {
		this.adress = (city + " " + district + " " + name + " " + etc).trim();
	}

}
