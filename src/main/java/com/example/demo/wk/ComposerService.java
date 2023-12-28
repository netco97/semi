package com.example.demo.wk;


import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComposerService {
	
	private final ComposerMapper composerMapper;
	
	public ComposerDTO getComposerById(String userFullPhoneNumber) {
		return composerMapper.findById(userFullPhoneNumber);
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
