package com.book.beans;

import java.util.Date;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserBean {

	
private int	user_idx       ;
@Size(min=4, max=20)
private String  User_Id        ;
@Size(min=4, max=20)
@Pattern(regexp = "[a-zA-Z0-9]*")
private String User_Pw;

@Size(min=4, max=20)
@Pattern(regexp = "[a-zA-Z0-9]*")
private String User_Pw2;
@Size(min=4, max=20)
private String  User_Name      ;
private String  User_BirthDate ;
private String  User_Address   ;
private String  User_CellPhone ;
private String  User_Gender    ;
private String  User_mail      ;
private String  User_mailchk   ;
private String  User_Gubun     ;
private String  User_UseYn     ;
private Date  	AddDate        ;
private String  AddId          ;
private Date  	UpdDate        ;
private String  UpdId ;
private boolean UserIdExist;
private boolean UserLogin;
private String Usermailchk;

public UserBean() {
	this.UserIdExist = false;
	this.UserLogin = false;
	this.Usermailchk ="000";
}

public int getUser_idx() {
	return user_idx;
}
public void setUser_idx(int user_idx) {
	this.user_idx = user_idx;
}
public String getUser_Id() {
	return User_Id;
}
public void setUser_Id(String user_Id) {
	User_Id = user_Id;
}
public String getUser_Pw() {
	return User_Pw;
}
public void setUser_Pw(String user_Pw) {
	User_Pw = user_Pw;
}
public String getUser_Name() {
	return User_Name;
}
public void setUser_Name(String user_Name) {
	User_Name = user_Name;
}
public String getUser_BirthDate() {
	return User_BirthDate;
}
public void setUser_BirthDate(String user_BirthDate) {
	User_BirthDate = user_BirthDate;
}
public String getUser_Address() {
	return User_Address;
}
public void setUser_Address(String user_Address) {
	User_Address = user_Address;
}
public String getUser_CellPhone() {
	return User_CellPhone;
}
public void setUser_CellPhone(String user_CellPhone) {
	User_CellPhone = user_CellPhone;
}
public String getUser_Gender() {
	return User_Gender;
}
public void setUser_Gender(String user_Gender) {
	User_Gender = user_Gender;
}
public String getUser_mail() {
	return User_mail;
}
public void setUser_mail(String user_mail) {
	User_mail = user_mail;
}
public String getUser_mailchk() {
	return User_mailchk;
}
public void setUser_mailchk(String user_mailchk) {
	User_mailchk = user_mailchk;
}
public String getUser_Gubun() {
	return User_Gubun;
}
public void setUser_Gubun(String user_Gubun) {
	User_Gubun = user_Gubun;
}
public String getUser_UseYn() {
	return User_UseYn;
}
public void setUser_UseYn(String user_UseYn) {
	User_UseYn = user_UseYn;
}
public Date getAddDate() {
	return AddDate;
}
public void setAddDate(Date addDate) {
	AddDate = addDate;
}
public String getAddId() {
	return AddId;
}
public void setAddId(String addId) {
	AddId = addId;
}
public Date getUpdDate() {
	return UpdDate;
}
public void setUpdDate(Date updDate) {
	UpdDate = updDate;
}
public String getUpdId() {
	return UpdId;
}
public void setUpdId(String updId) {
	UpdId = updId;
}
public String getUser_Pw2() {
	return User_Pw2;
}
public void setUser_Pw2(String user_Pw2) {
	User_Pw2 = user_Pw2;
}

public boolean isUserIdExist() {
	return UserIdExist;
}

public void setUserIdExist(boolean userIdExist) {
	UserIdExist = userIdExist;
}

public boolean isUserLogin() {
	return UserLogin;
}

public void setUserLogin(boolean userLogin) {
	UserLogin = userLogin;
}

public String getUsermailchk() {
	return Usermailchk;
}

public void setUsermailchk(String usermailchk) {
	Usermailchk = usermailchk;
}



}
