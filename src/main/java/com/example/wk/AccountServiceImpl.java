package com.example.wk;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
	
    private final AccountMapper accountMapper;

    @Autowired
    public AccountServiceImpl(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

//    @Override
//    public void registerAccount(AccountDTO account) {
//        accountMapper.insertAccount(account);
//    }
//
//    @Override
//    public AccountDTO getAccountById(String aId) {
//        return accountMapper.getAccountById(aId);
//    }
    
    @Override
    public List<AccountDTO> getAccountAll() {
        return accountMapper.getAccountAll();
    }

	
}
