package com.study.www.api.controller;

import java.util.List;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.study.www.api.entity.dto.TownDto;
import com.study.www.api.entity.dto.TownIdDto;
import com.study.www.api.service.AddressService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@EnableJpaAuditing
@RequestMapping("/api/address")
public class AddressController {
	private final AddressService addressService;
	
	@GetMapping("/location")
	public ResponseEntity<?> location(@RequestParam("search") String search
			,@RequestParam("range") double range){
		if(range == 0) {
			range = 1;
		}
		List<TownDto> townList = addressService.townList(search,range);
		
		return ResponseEntity.ok(townList);
	}
	
	@GetMapping("/getTownId")
	public ResponseEntity<?> getTownId(@RequestParam("search") String search){
		
		if(search.isEmpty()) {
			return ResponseEntity.ok("행정동을 입력하세요");
		}
		
		List<TownIdDto> townIdList = addressService.getTownIdList(search);
		
		return ResponseEntity.ok(townIdList);
	}
	
	@GetMapping("/nearTown")
	public ResponseEntity<?> nearTown(@RequestParam("townId") String townId
			,@RequestParam("range") double range){
		if(range == 0) {
			range = 1;
		}
		List<TownDto> townList = addressService.getNearTown(townId,range);
		
		if(townList.size() == 0) {
			return ResponseEntity.ok("townId를 확인해주세요.");
		}
		return ResponseEntity.ok(townList);
	}
}