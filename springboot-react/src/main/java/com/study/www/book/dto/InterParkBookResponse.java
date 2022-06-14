package com.study.www.book.dto;

import com.study.www.book.entity.Book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InterParkBookResponse {
	private String name;
	private String description;
	private String publishedDate;
	private int price;
	private String image;
	private Long categoryId;
	private String publisher;
	private String author;
	private String isbn;

	public Book toEntity() {
		return Book.builder()
			.name(name)
			.description(description)
			.publishedDate(publishedDate)
			.price(price)
			.image(image)
			.categoryId(categoryId)
			.publisher(publisher)
			.author(author)
			.isbn(isbn)
			.build();
	}
}
