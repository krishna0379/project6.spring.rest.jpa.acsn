package com.capgemini.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.entities.Author;
import com.capgemini.entities.Book;
import com.capgemini.repository.AuthorRepository;
import com.capgemini.repository.BookRepository;

@RestController
@RequestMapping("/api/author/")
public class AuthorController {
	
	@Autowired
	private AuthorRepository ar;
	
	@Autowired
	private BookRepository br;
	
	
	@PostMapping("/")
	public String create(@RequestBody Author author) {
		ar.save(author);
		return "Author Created..>>>";
	}
	
	@GetMapping("/{authorid}")
	public Author findById(@PathVariable int authorid) {
		return ar.findById(authorid).get();
	}
	
	@PutMapping("/{authorid}/book/{bookid}")
	public String assingBookToAuthor(@PathVariable int authorid, @PathVariable int bookid) {
		
		Book book = br.findById(bookid).get();
		Author author = ar.findById(authorid).get();
		
		author.getBooks().add(book);
		
		ar.save(author);
		
		return "Book Assinged";
		
	}

}
