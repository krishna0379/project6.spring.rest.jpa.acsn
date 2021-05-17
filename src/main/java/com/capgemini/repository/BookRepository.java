package com.capgemini.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.entities.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
