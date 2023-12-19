package com.example.demo.th;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TagsDTO {

	private String[] genre;
	private String[] mood;
	private String[] instrument;
	
}
