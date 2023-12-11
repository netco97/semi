package com.example.demo;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HumanMapper {

	Human getHuman(String name);
    List<Human> getAllHuman();
    int regHuman(Human h);
}
