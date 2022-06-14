package com.study.www.book.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Book {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String name;

	@Column
	private String description;

	@Column
	private String publishedDate;

	@Column
	private int price;

	@Column
	private String image;

	@Column
	private Long categoryId;

	@Column
	private String publisher;

	@Column
	private String author;

	@Column
	private String isbn;

	@Builder
	public Book(Long id, String name, String description, String publishedDate, int price, String image,
		Long categoryId,
		String publisher, String author, String isbn) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.publishedDate = publishedDate;
		this.price = price;
		this.image = image;
		this.categoryId = categoryId;
		this.publisher = publisher;
		this.author = author;
		this.isbn = isbn;
	}
}
