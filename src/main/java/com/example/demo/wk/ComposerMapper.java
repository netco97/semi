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
}
