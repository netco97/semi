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
    }
	
    public void updateIsComposer(String userFullPhoneNumber) {
        composerMapper.updateUserIsComposer(userFullPhoneNumber);
    }
}
