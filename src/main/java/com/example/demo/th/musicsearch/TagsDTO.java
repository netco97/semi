package com.example.demo.th.musicsearch;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TagsDTO {

	private String[] genre;
	private String[] mood;
	private String[] instrument;
	
}
