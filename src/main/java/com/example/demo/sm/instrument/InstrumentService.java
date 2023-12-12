package com.example.demo.sm.instrument;

import org.springframework.stereotype.Service;

@Service
public class InstrumentService {
	private final InstrumentMapper instrumentMapper;
	
	public InstrumentService(InstrumentMapper instrumentMapper) {
		this.instrumentMapper = instrumentMapper;
	}
	
	public String getInstrumentId(String instrument) {
		return instrumentMapper.getInstrument(instrument);
	}
}
