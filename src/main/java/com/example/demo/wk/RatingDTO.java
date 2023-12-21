package com.example.demo.wk;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RatingDTO {
	private Long id;
	private Long composerId;
	private Long userId;
	private int rating;

}
