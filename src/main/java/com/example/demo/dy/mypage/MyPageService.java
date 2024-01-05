package com.example.demo.dy.mypage;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyPageService {
	private final MyPageMapper myPageMapper;

	public String deleteUser(String myPK) {
		System.out.println(myPK);
		
		 myPageMapper.deletecomments(myPK);
		 
         myPageMapper.deletesonglike(myPK);
         
         myPageMapper.deletesongcomment(myPK);
         
         myPageMapper.deletesongdouble(myPK);
         
         myPageMapper.deletefolloweruserid(myPK);
         
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
