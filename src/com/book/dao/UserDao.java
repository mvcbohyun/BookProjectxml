package com.book.dao;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.book.beans.UserBean;

@Repository
public class UserDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public String checkUserIdExist(String user_id) {
		
		return sqlSessionTemplate.selectOne("user.checkUserIdExist", user_id);
		
	}
	
	public void addUserInfo(UserBean joinuserBean) {
		
		 sqlSessionTemplate.insert("user.addUserInfo", joinuserBean);
		
	}
	public UserBean getModifyUserInfo(int user_idx) {
		
		
		return sqlSessionTemplate.selectOne("user.getModifyUserInfo",user_idx);
		
	}
	
	public void setModifyUserInfo(UserBean modifyUserBean) {
		System.out.println("33333333333333333333333333333333333333");
		 sqlSessionTemplate.insert("user.setModifyUserInfo", modifyUserBean);
		
	}
	
	public void checkUsermailchk(UserBean joinuserBean) {
		
		 sqlSessionTemplate.update("user.checkUsermailchk", joinuserBean);
		
	}
	public void checkUserRemailchk(String[] mailchklist) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("User_mailchk",mailchklist[0]);
		map.put("User_mail",mailchklist[1]);
		map.put("User_Id",mailchklist[2]);
		
		
		 sqlSessionTemplate.update("user.checkUserRemailchk", map);
		
	}
	
	
	
	public void alter_userKey(String User_mailchk) {
		
		 sqlSessionTemplate.update("user.alter_userKey", User_mailchk);
		
	}
	
	public UserBean getLoginUserInfo(UserBean tempLoginUserBean) {
		
		return sqlSessionTemplate.selectOne("user.getLoginUserInfo", tempLoginUserBean);
		
	}
	
	public String getMailChk(UserBean tempLoginUserBean) {
		
		return sqlSessionTemplate.selectOne("user.getMailChk", tempLoginUserBean);
		
	}
	
	
	
	
	
	
	
}
