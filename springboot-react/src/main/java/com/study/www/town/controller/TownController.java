package com.study.www.town.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.study.www.town.dto.TownDto;
import com.study.www.town.service.TownService;
import com.study.www.util.ReverseGeocoding;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/town")
public class TownController {
	private final TownService townService;

	@GetMapping("/getLocation")
	public ResponseEntity<?> getLocation(@RequestParam("latitude")
	Double latitude, @RequestParam("longitude")
	Double longitude) {
		ReverseGeocoding reverseGeocoding = new ReverseGeocoding();

		TownDto townDto = reverseGeocoding.getAddress(latitude, longitude);

		return ResponseEntity.status(HttpStatus.OK).body(townDto);
	}
}
