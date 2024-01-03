package com.example.demo.dy.follow;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FollowMapper {

    void insertFollow(FollowDTO follow);

    void deleteFollow(FollowDTO follow);

    boolean existsFollow(@Param("followerUserId") String followerUserId, 
                         @Param("targetUserId") String targetUserId);
    
    List<FollowDTO> getFollowerList(@Param("userId") String userId);

	List<FollowDTO> getFollowerListWithInfo(@Param("userId") String userId,@Param("start") int start, @Param("end")int end);
    
}