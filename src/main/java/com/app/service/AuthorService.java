package com.app.service;

import java.util.List;

import com.app.dto.AuthorDto;
import com.app.dto.BookDto;
import com.app.entities.Book;

public interface AuthorService {

	AuthorDto addNewAuthor(AuthorDto auth);

	List<BookDto> getListOfBooks(Long id);

	AuthorDto deleteAuthorWithGivenID(Long id);

}
