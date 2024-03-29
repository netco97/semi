package com.example.demo.wk;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ComposerMapper {
	
	ComposerDTO findById(@Param("composer_id") String composer_id);
	
	List<ComposerDTO> getAllArtists();
	
	List<ComposerDTO> getArtistsWithPagination(Map<String, Object> params);
    
    int getTotalArtistsCount();
	
	int regComposer(ComposerDTO composer);
	
	int updateUserIsComposer(@Param("userFullPhoneNumber") String userFullPhoneNumber);

	int updateUserNickName(@Param("composer_name") String composer_name, @Param("userFullPhoneNumber") String userFullPhoneNumber);
	
	void updateComposer(ComposerDTO composerDTO);
	
	List<ComposerDTO> searchArtists(String keyword);

	int updateSongs(@Param("composer_name") String composer_name , @Param("composer_id") String composer_id);
	
	int updateUserTable(@Param("composer_name") String composer_name, @Param("composer_id") String composer_id);

	int updateCommentTable(@Param("composer_name") String composer_name, @Param("composer_id") String composer_id);

	int updateChatTable(@Param("composer_name") String composer_name, @Param("composer_id") String composer_id);

	int updateCreateRoomTable1(@Param("composer_name") String composer_name, @Param("composer_id") String composer_id);

	int updateCreateRoomTable2(@Param("composer_name") String composer_name, @Param("composer_id") String composer_id);

	String selectIsFollow(@Param("followUserId") String followUserId,@Param("targetUserId") String targetUserId);
	
	
}
