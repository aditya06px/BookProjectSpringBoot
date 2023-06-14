package com.app.service;

import com.app.dto.BookDto;

public interface BookService {

	BookDto addNewBook(BookDto book);

	BookDto removeBookWithGivenId(Long id);

}
