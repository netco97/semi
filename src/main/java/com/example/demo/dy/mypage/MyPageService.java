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
		
		if (myPageMapper.deletetargetuserid(myPK) >=1) {
			System.out.println("deletetargetuserid 성공");
			
		}  
         
		if (myPageMapper.deleteratingid(myPK) >=1) {
			System.out.println("deleteratingid 성공");
			
		}  
         
		
		if (myPageMapper.deleteratingid1(myPK) >=1) {
			System.out.println("deleteratingid1 성공");
			
		}  
     	
		if (myPageMapper.deletechatid(myPK) >=1) {
			System.out.println("deletechatid 성공");
			
		}  
     	
		if (myPageMapper.deleteroom1(myPK) >=1) {
			System.out.println("deleteroom1 성공");
			
		}  
		
		if (myPageMapper.deleteroom2(myPK) >=1) {
			System.out.println("deleteroom2 성공");
			
		}  
     	
		if (myPageMapper.deletecomposerid(myPK) >=1) {
			System.out.println("deletecomposerid 성공");
			
		}  

		 int deleteResult = myPageMapper.deleteUser(myPK);
		
		 if(deleteResult >= 1) {
			 
			return "sucesss";
		 }
		 else{
			 return "fail";
		 }

	}

	public String getUserImageByPhoneNumber(String phoneNumber) {
		
		 String imageUrl = myPageMapper.getUserImageByPhoneNumber(phoneNumber);
		
		
		return imageUrl;
	};
	
	

}
