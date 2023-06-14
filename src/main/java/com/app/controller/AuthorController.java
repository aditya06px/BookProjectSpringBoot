package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.AuthorDto;
import com.app.service.AuthorService;

@RestController
@RequestMapping("/author")
public class AuthorController {
	
	@Autowired
	private AuthorService authorService;

	@PostMapping
	ResponseEntity<?> addNewAuthor(@RequestBody AuthorDto auth) {
		
		try {
			System.out.println("inside add new Author " + auth.getName());
			return new ResponseEntity<>( authorService.addNewAuthor(auth),HttpStatus.OK);
		}catch(RuntimeException e) {
			return  new ResponseEntity<>(new ApiResponse(e.getMessage()),HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{id}")
	ResponseEntity<?> deleteAuthorWithGivenID(@PathVariable Long id) {
		
		try {
			System.out.println("inside remove author , authorId -> " + id);
			return new ResponseEntity<>(authorService.deleteAuthorWithGivenID(id), HttpStatus.OK);
		}catch(RuntimeException e ) {
			return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}
 	
	@GetMapping("/{id}")
	ResponseEntity<?> getListOfBooksUndersameAuthor(@PathVariable Long id) {
		
		try {
			System.out.println("inside get list of books , author id -> " + id);
			return new ResponseEntity<>(authorService.getListOfBooks(id),HttpStatus.OK);
		}catch(RuntimeException e) {
			return new ResponseEntity<>(new ApiResponse(e.getMessage()),HttpStatus.NOT_FOUND);
		}
	}
}
