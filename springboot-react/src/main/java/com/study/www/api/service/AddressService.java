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
		List<Town> townInfo = new ArrayList<Town>();	
		if(searchArr.length == 1) {
			townInfo = addressRepository.getByName(searchArr[0]);
		}else if(searchArr.length == 2) {
			townInfo = addressRepository.getByCityAndName(searchArr[0],searchArr[1]);
			if(townInfo.size() == 0) {
				townInfo = addressRepository.getByDistrictAndName(searchArr[0],searchArr[1]);
			}
		}else if(searchArr.length == 3) {
			townInfo = addressRepository.getByCityAndDistrictAndName(searchArr[0],searchArr[1],searchArr[2]);
		}else if(searchArr.length == 4) {
			townInfo = addressRepository.getByCityAndDistrictAndNameAndEtc(searchArr[0],searchArr[1],searchArr[2],searchArr[3]);
		}
		
		if(townInfo.size() > 0) {
			if(townInfo.size() == 1) {
				townList = addressRepository.getNearAddress(townInfo.get(0).getLatitude(), townInfo.get(0).getLongitude(), range).stream().map(c->new TownDto(c)).collect(Collectors.toList());
			}else {
				for(int i = 0;i<townInfo.size();i++) {
					List<TownDto> list = addressRepository.getNearAddress(townInfo.get(i).getLatitude(), townInfo.get(i).getLongitude(), range).stream().map(c->new TownDto(c)).collect(Collectors.toList());
					for(TownDto townDto : list) {
						townList.add(townDto);
					}
				}
			}
		}
		
		return townList;
	}

	public List<TownIdDto> getTownIdList(String search) {
		return addressRepository.getByName(search).stream().map(c->new TownIdDto(c)).collect(Collectors.toList());
	}

	public List<TownDto> getNearTown(String townId, double range) {
		Town townInfo = addressRepository.getById(Long.parseLong(townId));
		
		List<TownDto> townList = new ArrayList<TownDto>();
		System.out.println(townInfo.getLatitude() + " : " + townInfo.getLongitude());
		if(townInfo != null) {
			townList = addressRepository.getNearAddress(townInfo.getLatitude(),townInfo.getLongitude(),range).stream().map(c->new TownDto(c)).collect(Collectors.toList());
		}
		return townList;
	}

}
