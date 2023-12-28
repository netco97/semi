package com.example.demo.th.MusicPaging;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.th.music_comment.MusicCommentDTOwithNickName;
import com.example.demo.th.music_comment.MusicCommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MusicPagingC {
	
	private final MusicCommentService musicCommentService;

	
	 @GetMapping("/MusicCommentPaging")
	    public SongCommentResponseDTO getAllCommentsBySongIdWithPaging(
	            @RequestParam("song_id") int song_id,
	            @RequestParam("page") int page,
	            @RequestParam("size") int size) {
		 
		 System.out.println("페이징 뭣같에ㅛㅇ ㅎㅎ" +song_id);

	    	List<MusicCommentDTOwithNickName> comments = musicCommentService.getCommentsBySongIdWithPaging(song_id, page, size);
	        int totalComments = musicCommentService.countCommentsBySongId(song_id);
	        
	        SongCommentResponseDTO responseDTO = new SongCommentResponseDTO();
	        responseDTO.setComments(comments);
	        responseDTO.setTotalComments(totalComments);
	        responseDTO.setPage(page);
	        responseDTO.setTotalPages((int) Math.ceil((double) totalComments / size));
	        
	        System.out.println(comments);
	        System.out.println(totalComments);
	        
	        return responseDTO;
	    }
}

