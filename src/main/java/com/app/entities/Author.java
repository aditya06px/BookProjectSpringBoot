package com.app.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Author extends BaseEntity{


	@Column(length = 30)
	private String name;
	
	@Column(length = 30)
	private String lastName;
	
	@Column(length = 30)
	private String mail;
	
	private int age;
	
	@OneToMany(mappedBy="author",cascade = CascadeType.ALL , orphanRemoval = true)
	private List<Book> booksDB = new ArrayList<>();
	
	
	public Book addBook(Book book) {
		booksDB.add(book);
		book.setAuthor(this);
		return book;
	}
	
	public Book removeBook(Book book) {
		booksDB.remove(book);
		book.setAuthor(null);
		
		return book;
	}
	
}
