package com.example.demo.wk;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComposerService {
	
	@Autowired
	private ComposerMapper composerMapper;
	
	public ComposerDTO getComposerById(Long id) {
		return composerMapper.findById(id);
	}
	

}
