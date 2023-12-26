package com.example.demo.wk;


import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComposerService {
	
	private final ComposerMapper composerMapper;
	
	public ComposerDTO getComposerById(Long id) {
		return composerMapper.findById(id);
	}
	
	public void insertComposer(ComposerDTO composer) {
        composerMapper.insertComposer(composer);
        
//        // 유저를 아티스트로 업데이트
//        updateUserToArtist(composer.getUserFullPhoneNumber());
        
        System.out.println("ComposerService" + composer);
    }
	
//	private void updateUserToArtist(String userFullPhoneNumber) {
//        composerMapper.updateUserToArtist(userFullPhoneNumber);
//    }

}
