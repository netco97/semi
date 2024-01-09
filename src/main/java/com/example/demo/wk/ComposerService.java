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
    
    public List<ComposerDTO> getArtistsWithPagination(int page, int pageSize) {
    	int offset = (page - 1) * pageSize;
    	int limit = offset + pageSize;
    	
        Map<String, Object> params = new HashMap<>();
        params.put("offset", offset);
        params.put("limit", limit);
        
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

	public void updateUserNickName(String composer_name, String userFullPhoneNumber) {
		if(composerMapper.updateUserNickName(composer_name, userFullPhoneNumber)==1) {
			System.out.println("userNickname update 성공");
		}
	}
	
    public void updateComposer(ComposerDTO composerDTO) {
        composerMapper.updateComposer(composerDTO);
    }
    
    public List<ComposerDTO> searchArtists(String keyword) {
        return composerMapper.searchArtists(keyword);
    }

	public int updateSongs(String composer_name, String composer_id) {
		return composerMapper.updateSongs(composer_name, composer_id);
		
	}

	public int updateUserTable(String composer_name, String composer_id) {
		
		return composerMapper.updateUserTable(composer_name, composer_id);
	}

	public int updateCommentTable(String composer_name, String composer_id) {
		return composerMapper.updateCommentTable(composer_name,composer_id);
	}

	public int updateChatTable(String composer_name, String composer_id) {
		return composerMapper.updateChatTable(composer_name, composer_id);
	}

	public int updateCreateRoomTable1(String composer_name, String composer_id) {
		return composerMapper.updateCreateRoomTable1(composer_name, composer_id);
	}

	public int updateCreateRoomTable2(String composer_name, String composer_id) {
		return composerMapper.updateCreateRoomTable2(composer_name, composer_id);
	}
	
	public String selectIsFollow(String followUserId, String targetUserId) {
		return composerMapper.selectIsFollow(followUserId , targetUserId);
		
	} 
	
	
}
