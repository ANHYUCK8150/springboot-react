package com.study.www.book.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.study.www.book.dto.InterParkBookResponse;
import com.study.www.book.service.InterParkAPIService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookController {
	private final InterParkAPIService interParkAPIService;

	@GetMapping("/book/search")
	public ResponseEntity<?> search(@RequestParam
	String query) {
		List<InterParkBookResponse> bookList = interParkAPIService.search(query);

		return ResponseEntity.ok(bookList);
	}
}
