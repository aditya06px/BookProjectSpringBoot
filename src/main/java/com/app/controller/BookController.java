package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.BookDto;
import com.app.service.BookService;



@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService bookservice;
	
	@PostMapping
	ResponseEntity<?> addNewBook(@RequestBody BookDto book) {
		
		try {
			return new ResponseEntity<>(bookservice.addNewBook(book),HttpStatus.OK);
		}catch(RuntimeException e) {
			return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.NOT_FOUND);
		}	
	}

	@DeleteMapping("/{id}")
	ResponseEntity<?> removeBookWithGivenId(@PathVariable Long id)  {
		
	       try {
	    	   System.out.println("inside delete book,  bookId -> " + id);
	    	   return new ResponseEntity<>(bookservice.removeBookWithGivenId(id), HttpStatus.OK);
	       }catch(RuntimeException e) {
	    	   return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.NOT_FOUND);
	       }
	}
	
}
