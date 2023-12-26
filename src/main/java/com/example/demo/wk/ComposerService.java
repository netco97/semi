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
        System.out.println(composer);
    }

}
