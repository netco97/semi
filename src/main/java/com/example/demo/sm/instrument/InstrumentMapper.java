package com.example.demo.sm.instrument;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InstrumentMapper {
	String getInstrument(String instrument);
}
