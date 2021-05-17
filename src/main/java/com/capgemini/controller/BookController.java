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
@RequestMapping("/api/book/")
public class BookController {

	@Autowired
	private BookRepository br;
	@Autowired
	private AuthorRepository ar;
	
	@PostMapping("/")
	public String create(@RequestBody Book book) {
		br.save(book);
		return "Book data has been  created ..>>>";
	}

	@GetMapping("/{bookid}")
	public Book findById(@PathVariable int bookid) {
		
		return br.findById(bookid).get();
	}
	
	@PutMapping("/{bookid}/author/{authorid}/")
	public String assingAuthor(@PathVariable int bookid, @PathVariable int authorid) {
		
		Book book = br.findById(bookid).get();
		Author author = ar.findById(authorid).get();
		
		book.getAuthors().add(author);
		br.save(book);
		
		return "Author Assinged..>>";
		
	}
}
