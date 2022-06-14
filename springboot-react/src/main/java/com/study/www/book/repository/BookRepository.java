package com.study.www.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.www.book.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
