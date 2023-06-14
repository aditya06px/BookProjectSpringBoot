package com.app.dto;



import com.app.entities.Author;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter
@NoArgsConstructor
public class BookDto {

	private String name;

	private String description;

	@JsonProperty(access = Access.READ_WRITE)
	private String authorName;

}
