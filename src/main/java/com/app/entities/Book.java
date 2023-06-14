package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter 
@Setter 
@NoArgsConstructor
public class Book extends BaseEntity {

	
	@Column(length=30)
	private String name;
	
	@Column(length=30)
	private String description;
	
	
	
	@OneToOne
	@JoinColumn(name="author_id")
	private Author author;
	
}
