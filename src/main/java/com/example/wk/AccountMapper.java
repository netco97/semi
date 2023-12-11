package com.example.wk;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {
	
    List<AccountDTO> getAccountAll(); // account 전부 가져오기
//    void insertAccount(AccountDTO account);
//    AccountDTO getAccountById(String aId);
    
	

}
