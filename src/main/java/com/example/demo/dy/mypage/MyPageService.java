package com.example.demo.dy.mypage;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyPageService {
	private final MyPageMapper myPageMapper;

	public String deleteUser(String myPK) {
		System.out.println(myPK);
		
		if (myPageMapper.deletecomments(myPK) >=1) {
			System.out.println("deletecomments 성공");
			
		}  
		 
		if (myPageMapper.deletesonglike(myPK) >=1) {
			System.out.println("deletesonglike 성공");
			
		}  
		
	
		if (myPageMapper.deletesongcomment(myPK) >=1) {
			System.out.println("deletesongcomment 성공");
			
		}  
         
         
		if (myPageMapper.deletesongdouble(myPK) >=1) {
			System.out.println("deletesongdouble 성공");
			
		}  
        
		
		if (myPageMapper.deletefolloweruserid(myPK) >=1) {
			System.out.println("deletefolloweruserid 성공");
			
		}  
         
		if (myPageMapper.deletefolloweruserid(myPK) >=1) {
			System.out.println("deletefolloweruserid 성공");
			
		}  
         
         myPageMapper.deletetargetuserid(myPK);
         
         myPageMapper.deleteratingid(myPK);
     	
         myPageMapper.deleteratingid1(myPK);
     	
         myPageMapper.deletechatid(myPK);
     	
         myPageMapper.deleteroom1(myPK);
     	
         myPageMapper.deleteroom2(myPK);
         
         myPageMapper.deletecomposerid(myPK);

		 int deleteResult = myPageMapper.deleteUser(myPK);
		
		 if(deleteResult > 0) {
			 
			return "sucesss";
			 
			 
		 }
		 return "fail";

	};
	
	

}
