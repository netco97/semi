package com.example.demo.wk;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AccountMapper {
	
	@Select("SELECT * FROM account_test")
	List<AccountDTO> getAccountAll();
	
//	@Insert("INSERT INTO account_test (a_name, a_id, a_pw, a_gender, a_addr, a_interest, a_introduce, a_img) " +
//	        "VALUES (#{a_name}, #{a_id}, #{a_pw}, #{a_gender}, #{a_addr}, #{a_interest}, #{a_introduce}, #{a_img})")
//	void insertAccount(AccountDTO accountDTO);
//	
//	@Update("UPDATE account_test " +
//	        "SET a_name = #{a_name}, a_id = #{a_id}, a_pw = #{a_pw}, a_gender = #{a_gender}, " +
//	        "a_addr = #{a_addr}, a_interest = #{a_interest}, a_introduce = #{a_introduce}, a_img = #{a_img} " +
//	        "WHERE a_id = #{a_id}")
//	void updateAccount(AccountDTO account);
//	
//	
//	@Delete("DELETE FROM account_test WHERE a_id = #{a_id}")
//	void deleteAccount(String a_id);
	
	@Select("SELECT * FROM account_test WHERE a_id = #{arg0} AND a_pw = #{arg1}")
	AccountDTO login(String a_id, String a_pw);
	
}
