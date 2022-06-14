package com.study.www.town.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.www.town.dto.CoordinateDto;
import com.study.www.town.dto.TownDto;
import com.study.www.town.service.TownService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/town")
public class TownController {
	private final TownService townService;

	@GetMapping("/getLocation")
	public ResponseEntity<?> getLocation(
		CoordinateDto coordinateDto) {
		//ReverseGeocoding reverseGeocoding = new ReverseGeocoding();
		System.out.println(coordinateDto.getLatitude() + " : " + coordinateDto.getLongitude());
		//TownDto townDto = reverseGeocoding.getAddress(latitude, longitude);
		TownDto townDto = townService.ReverseGeocoding(coordinateDto);

		return ResponseEntity.status(HttpStatus.OK).body(townDto);
	}
}
