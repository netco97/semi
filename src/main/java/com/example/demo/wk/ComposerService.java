package com.example.demo.wk;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComposerService {
	
	private final ComposerMapper composerMapper;
	
	public ComposerDTO getComposerById(String userFullPhoneNumber) {
		return composerMapper.findById(userFullPhoneNumber);
	}
	
    public List<ComposerDTO> getAllArtists() {
        return composerMapper.getAllArtists();
    }
    
    public List<ComposerDTO> getArtistsWithPagination(int offset, int limit) {
        System.out.println("offset: " + offset);
    	System.out.println("limit: " + limit);
    	int startRow = offset;
        int endRow = offset + limit - 1;
        System.out.println("startRow: " + startRow);
        System.out.println("endRow: " + endRow);

        Map<String, Object> params = new HashMap<>();
        params.put("startRow", startRow);
        params.put("endRow", endRow);
        

        System.out.println("params: " + params);
        return composerMapper.getArtistsWithPagination(params);
    }
	
    public int getTotalArtistsCount() {
        return composerMapper.getTotalArtistsCount();
    }
    
	public void regComposer(ComposerDTO composer) {
        if(composerMapper.regComposer(composer)==1) {
        	System.out.println("artist 등록 성공!");
        }
    }
	
    public void updateIsComposer(String userFullPhoneNumber) {
        if(composerMapper.updateUserIsComposer(userFullPhoneNumber)==1) {
        	System.out.println("iscomposer update 성공");
        }
    }
}
