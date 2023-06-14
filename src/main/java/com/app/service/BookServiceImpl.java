package com.app.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dto.BookDto;
import com.app.entities.Author;
import com.app.entities.Book;
import com.app.repository.AuthorRepository;
import com.app.repository.BookRepository;

@Service
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepo;
	
	@Autowired
	private AuthorRepository authorRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public BookDto addNewBook(BookDto book) {
		
		Optional<Author> auth = authorRepo.findByName(book.getAuthorName());
		
		System.out.println("author name is " + book.getAuthorName());
		Author author = auth.orElseThrow(()-> new ResourceNotFoundException("author with given name "
				+ "doesn't exists"));
		
		Book mppedbook = mapper.map(book, Book.class);
		author.addBook(mppedbook);
		
		return mapper.map(bookRepo.save(mppedbook), BookDto.class);
	}

	@Override
	public BookDto removeBookWithGivenId(Long id) {
		 // book delete first remove book from author
		
		Book book = bookRepo.findById(id).
				orElseThrow(()-> new ResourceNotFoundException("book With given id Doesn;t exist") );
		
		bookRepo.delete(book);
		return mapper.map(book, BookDto.class);
	}

}
