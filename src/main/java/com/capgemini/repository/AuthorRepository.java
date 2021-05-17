package com.capgemini.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.entities.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

}
