package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TestService_Model {

	private final HumanMapper humanMapper;

    public TestService_Model(HumanMapper humanMapper) {
        this.humanMapper = humanMapper;
    }
    public void regHuman(Human h) {
//    	Human h = new Human();
//    	h.setName(name);
//    	h.setAge(age);
    	humanMapper.regHuman(h);
    }
    
	public Human getHuman(String name) {
		return humanMapper.getHuman(name);
	}
	public List<Human> getAllHuman() {
		return humanMapper.getAllHuman();
	}
}
