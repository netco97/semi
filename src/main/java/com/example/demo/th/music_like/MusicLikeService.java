package com.example.demo.th.music_like;


import javax.print.attribute.SetOfIntegerSyntax;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MusicLikeService {
	
	private final HttpSession httpSession;

    private final MusicLikeMapper musicLikeMapper;
    String userFullPhoneNumber = "";  

//    public MusicLikeService(MusicLikeMapper musicLikeMapper) {
//        this.musicLikeMapper = musicLikeMapper;
//    }
    
   public int loadingCheckLike(int song_id) {
	   userFullPhoneNumber = httpSession.getAttribute("userFullPhoneNumber").toString();
	   
	   if (userFullPhoneNumber == null) {

		   return 0;
	    }
	   
	   MusicLikeDTO isLike = musicLikeMapper.checkLike(song_id, userFullPhoneNumber);
	   if(isLike == null) {
		   
		   
		   return 0;
	   }
	   int checkLike = isLike.getIsLike();
	   
		   
	   
	   System.out.println("쳇라이크 : "+ isLike.getIsLike());
	   
	   
	   
	   
	   
	   System.out.println("첵라이크 확인 : "+  checkLike);
	   
	   
	   
	   System.out.println(song_id+ "::::" + isLike.getIsLike());
	   
	   return checkLike;
	   
	   
	   
	   
   }


    public void checkLike(int song_id) {

        // 좋아요를 눌렀는지 안눌렀는지 확인하기 위해 데이터를 불러옴
    	userFullPhoneNumber = httpSession.getAttribute("userFullPhoneNumber").toString();
        MusicLikeDTO isLike = musicLikeMapper.checkLike(song_id, userFullPhoneNumber);
        
        
        if (isLike != null) {
        	if(updateLike(isLike)>=1) {
        		System.out.println("LikeTable update 성공");
        	}
        } else {
            if(insertLike(song_id, userFullPhoneNumber)==1) {
            	System.out.println("LikeTable insert 성공");
            }
        }

    }

    private int updateLike(MusicLikeDTO isLike) {
        // 좋아요를 누른적이 있을 때
        if (isLike.getIsLike() == 1) {
            // 좋아요를 누른적이 있고 눌러져 있을 때 // 1일때
            // 좋아요를 취소해줘야함
            isLike.setIsLike(0);
            return musicLikeMapper.updateLike(isLike.getSong_like_id(), 0);
        } else {
            // 좋아요를 누른적이 있고 지금은 안눌러져 있을 때 // 0 일때
            // 좋아요를 추가해줘야함
            isLike.setIsLike(0);
            return musicLikeMapper.updateLike(isLike.getSong_like_id(), 1);
        }
        
    }

    private int insertLike(int song_id, String userFullPhoneNumber) {
        return musicLikeMapper.insertLike(song_id, userFullPhoneNumber);
    }

	public int selLike(int song_id) {
		userFullPhoneNumber = httpSession.getAttribute("userFullPhoneNumber").toString();
		MusicLikeDTO isLike = musicLikeMapper.checkLike(song_id, userFullPhoneNumber);
		
		return isLike.getIsLike();
	}
    
    
}
