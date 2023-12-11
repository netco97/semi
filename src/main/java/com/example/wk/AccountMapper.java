package com.example.wk;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {
	AccountDTO getAccount(String name);
	List<AccountDTO> getAllAccount();
	int regAccount(AccountDTO a);
	

}