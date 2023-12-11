package com.example.wk;

import org.springframework.stereotype.Service;

@Service
public class AccountService {
	
	private final AccountMapper accountMapper;
	
	public AccountService(AccountMapper accountMapper) {
		this.accountMapper = accountMapper;
	}
}
