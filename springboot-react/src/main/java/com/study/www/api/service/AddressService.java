package com.study.www.api.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.www.api.entity.Town;
import com.study.www.api.entity.dto.TownDto;
import com.study.www.api.entity.dto.TownIdDto;
import com.study.www.api.repository.AddressRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AddressService {
	private final AddressRepository addressRepository;

	public List<TownDto> townList(String search, double range) {
		String[] searchArr = search.split(" ");
		List<TownDto> townList = new ArrayList<TownDto>();
		Town townInfo = new Town();
		
		if(searchArr.length == 1) {
			//townInfo = addressRepository.getByName(searchArr[0]);
		}else if(searchArr.length == 2) {
			townInfo = addressRepository.getByCityAndName(searchArr[0],searchArr[1]);
		}else if(searchArr.length == 3) {
			townInfo = addressRepository.getByCityAndDistrictAndName(searchArr[0],searchArr[1],searchArr[2]);
		}else if(searchArr.length == 4) {
			townInfo = addressRepository.getByCityAndDistrictAndNameAndEtc(searchArr[0],searchArr[1],searchArr[2],searchArr[3]);
		}
		
		if(townInfo.getId() != null) {
			townList = addressRepository.getNearAddress(townInfo.getLatitude(),townInfo.getLongitude(),range).stream().map(c->new TownDto(c)).collect(Collectors.toList());
			//townList = addressRepository.getNearAddress("37.5234220000","126.8586800000","1").stream().map(c->new TownDto(c)).collect(Collectors.toList());
		}
		
		return townList;
	}

	public List<TownIdDto> getTownIdList(String search) {
		return addressRepository.getByName(search).stream().map(c->new TownIdDto(c)).collect(Collectors.toList());
	}

	public List<TownDto> getNearTown(String townId, double range) {
		Town townInfo = addressRepository.getById(Long.parseLong(townId));
		
		List<TownDto> townList = new ArrayList<TownDto>();
		
		if(townInfo != null) {
			townList = addressRepository.getNearTown(townInfo.getLatitude(),townInfo.getLongitude(),range).stream().map(c->new TownDto(c)).collect(Collectors.toList());
		}
		return townList;
	}

}
