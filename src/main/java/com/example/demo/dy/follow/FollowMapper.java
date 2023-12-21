package com.example.demo.dy.follow;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FollowMapper {

    void insertFollow(FollowDTO follow);

    void deleteFollow(FollowDTO follow);

    boolean existsFollow(@Param("followerComposerId") Long followerComposerId, 
                         @Param("targetComposerId") Long targetComposerId);
}