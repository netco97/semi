package com.example.wk;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {
	
	private final AccountMapper accountMapper;
	
	public AccountService(AccountMapper accountMapper) {
		this.accountMapper = accountMapper;
	}
	
	public List<AccountDTO> getAccountAll(){
		return accountMapper.getAccountAll();
		
	}
	
	public AccountDTO login(String a_id, String a_pw) {
		return accountMapper.login(a_id, a_pw);
	}
	
}	

