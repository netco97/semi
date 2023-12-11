package com.example.wk;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.PathVariable;

@Mapper
public interface HumanMapper {

	Human getHuman(String name);
    List<Human> getAllHuman();
    int regHuman(Human h);
}
