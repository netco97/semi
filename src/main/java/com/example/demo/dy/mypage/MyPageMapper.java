package com.example.demo.dy.mypage;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface MyPageMapper {
	int deletecomments(String myPK);

	int deletesonglike(String myPK);

	int deletesongcomment(String myPK);

	int deletesongdouble(String myPK);

	int deletefolloweruserid(String myPK);

	int deletetargetuserid(String myPK);
	
	int deleteratingid(String myPK);
	
	int	deleteratingid1(String myPK);
	
	int deletechatid(String myPK);
	
	int deleteroom1(String myPK);
	
	int deleteroom2(String myPK);
	
	int deletecomposerid(String myPK);

	int deleteUser(String myPK);
	

}
