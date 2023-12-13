package com.example.wk;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
public class CommentC {
	
	@Autowired
	private CommentService commentService;
	
	@GetMapping("/{composerId}")
	public List<CommentDTO> getCommentsByComposerId(@PathVariable Long composerId) {
		System.out.println(composerId);
		return commentService.getCommentsByComposerId(composerId);
	}
	
	@PostMapping
	public void insertComment(@RequestBody CommentDTO comment) {
		System.out.println(comment);
		commentService.insertComment(comment);
	}

}
