package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dto.AuthorDto;
import com.app.dto.BookDto;
import com.app.entities.Author;
import com.app.entities.Book;
import com.app.repository.AuthorRepository;


@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorRepository  authorRepo;
	
	@Autowired 
	private ModelMapper mapper;
	
	@Override
	public AuthorDto addNewAuthor(AuthorDto auth) {
		
		Author author = mapper.map(auth,Author.class);
		
		authorRepo.save(author);
		
		return mapper.map(author,AuthorDto.class);
	}

	@Override
	public List<BookDto> getListOfBooks(Long id) {
		
		Author author = authorRepo.findById(id).
				orElseThrow(()-> new ResourceNotFoundException("Author with given Id Doesn't exists")) ;
		
		List<Book>books = author.getBooksDB();
		
		List<BookDto>listOfBooksDtos =  books.stream().
										map(b-> mapper.map(b,BookDto.class)).
										collect(Collectors.toList());
		
		return listOfBooksDtos;
	}

	@Override
	public AuthorDto deleteAuthorWithGivenID(Long id) {
		
		Author auth = authorRepo.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("author with given id not found"));
		
		authorRepo.delete(auth);
		return mapper.map(auth, AuthorDto.class);
	}

	
}
