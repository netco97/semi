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
	
	public void regComposer(ComposerDTO composer) {
        composerMapper.regComposer(composer);
        updateIsComposer(ComposerDTO.getUserId()); // ComposerDTO에 getUserId() 메서드가 있다고 가정합니다.
    }
	
    private void updateIsComposer(String userId) {
        composerMapper.updateUserIsComposer(userId);
    }
}
