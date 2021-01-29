package com.book.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.book.beans.UserBean;

public class UserValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return UserBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		UserBean userBean = (UserBean)target;
		
		String beanName = errors.getObjectName();
		
		if(beanName.equals("joinUserBean")||beanName.equals("modifyUserBean")) {
			if(userBean.getUser_Pw().equals(userBean.getUser_Pw2()) == false) {
				errors.rejectValue("User_Pw", "NotEquals");
				errors.rejectValue("User_Pw2", "NotEquals");
			}
		}
			
		if(beanName.equals("joinUserBean")) {
			
			if(userBean.isUserIdExist() == false) {
				errors.rejectValue("User_Id", "DontCheckUserIdExist");
			}
			}
		}
	}
	

