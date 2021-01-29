package com.book.service;

import java.util.HashMap;
import java.util.Random;

import javax.annotation.Resource;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.book.beans.UserBean;
import com.book.dao.UserDao;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private JavaMailSender mailSender;

	@Resource(name = "loginUserBean")
	@Lazy
	private UserBean loginUserBean;

	
	public boolean checkUserIdExist(String user_id){
		
		String chkUserName  = userDao.checkUserIdExist(user_id);
		if(chkUserName == null) {
		return true; 
		}else {
		return false;
		}
	}
	
	public void addUserInfo(UserBean joinuserBean) {
		
		userDao.addUserInfo(joinuserBean);
		
	}
	
	public void getModifyUserInfo(UserBean modifyUserBean) {
		UserBean tempmodifyUserBean =userDao.getModifyUserInfo(loginUserBean.getUser_idx());
		
		modifyUserBean.setUser_Id(tempmodifyUserBean.getUser_Id());
		modifyUserBean.setUser_Name(tempmodifyUserBean.getUser_Name());
		modifyUserBean.setUser_mail(tempmodifyUserBean.getUser_mail());
		modifyUserBean.setUser_CellPhone(tempmodifyUserBean.getUser_CellPhone());
		modifyUserBean.setUser_Gender(tempmodifyUserBean.getUser_Gender());
		modifyUserBean.setUser_idx(loginUserBean.getUser_idx());
	
		
	}
	
	public void setModifyUserInfo(UserBean modifyUserBean) {
		System.out.println("2222222222222222222222222222222");
		modifyUserBean.setUser_idx(loginUserBean.getUser_idx());
		userDao.setModifyUserInfo(modifyUserBean);
	}
	
	// 이메일 난수 만드는 메서드
		private String init() {
			Random ran = new Random();
			StringBuffer sb = new StringBuffer();
			int num = 0;

			do {
				num = ran.nextInt(75) + 48;
				if ((num >= 48 && num <= 57) || (num >= 65 && num <= 90) || (num >= 97 && num <= 122)) {
					sb.append((char) num);
				} else {
					continue;
				}

			} while (sb.length() < size);
			if (lowerCheck) {
				return sb.toString().toLowerCase();
			}
			return sb.toString();
		}

		// 난수를 이용한 키 생성
		private boolean lowerCheck;
		private int size;

		public String getKey(boolean lowerCheck, int size) {
			this.lowerCheck = lowerCheck;
			this.size = size;
			return init();
		}



	
	public void  checkUsermailchk(UserBean joinUserBean,HttpServletRequest  request) {
		String key = getKey(false, 20);
		joinUserBean.setUser_mailchk(key);
		
		userDao.checkUsermailchk(joinUserBean);
		MimeMessage mail = mailSender.createMimeMessage();
		String htmlStr = "<h2>안녕하세요 </h2><br><br>" 
				+ "<h3>메일 인증 메세지 입니다 </h3>" + "<p>인증하기 버튼을 눌러 주세요 " 
				+ "<a href='http://localhost:8080" + "/BookProjectxml"+ "/user/key_alter?user_id="+joinUserBean.getUser_Id()+"&User_mailchk="+key+"'>인증하기</a></p>"
				+ "(혹시 잘못 전달된 메일이라면 이 이메일을 무시하셔도 됩니다)";
		try {
			mail.setSubject("[본인인증] 인증메일입니다", "utf-8");
			mail.setText(htmlStr, "utf-8", "html");
			mail.addRecipient(RecipientType.TO, new InternetAddress(joinUserBean.getUser_mail()));
			mailSender.send(mail);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		
	}
	
	public boolean  checkUserRemailchk(String Usermail,String User_Id,HttpServletRequest  request) {
		String key = getKey(false, 20);
		System.out.println("2222222222222222222222222222222222222222");
		String[] mailchklist = new String[3];
		mailchklist[0]=key;
		mailchklist[1]=Usermail;
		mailchklist[2]=User_Id;
		
		userDao.checkUserRemailchk(mailchklist);
		MimeMessage mail = mailSender.createMimeMessage();
		String htmlStr = "<h2>안녕하세요 </h2><br><br>" 
				+ "<h3>메일 인증 메세지 입니다 </h3>" + "<p>인증하기 버튼을 눌러 주세요 " 
				+ "<a href='http://localhost:8080" + "/BookProjectxml"+ "/user/key_alter?user_id="+User_Id+"&User_mailchk="+key+"'>인증하기</a></p>"
				+ "(혹시 잘못 전달된 메일이라면 이 이메일을 무시하셔도 됩니다)";
		try {
			mail.setSubject("[본인인증] 인증메일입니다", "utf-8");
			mail.setText(htmlStr, "utf-8", "html");
			mail.addRecipient(RecipientType.TO, new InternetAddress(Usermail));
			mailSender.send(mail);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		return true;
		
	}
	
	public void alter_userKey(String user_id, String User_mailchk) {
		
		userDao.alter_userKey( User_mailchk);
		
		
	}
		
	public void getLoginUserInfo(UserBean tempLoginUserBean) {
		
		UserBean tempLoginUserBean2 = userDao.getLoginUserInfo(tempLoginUserBean);
		
		if(tempLoginUserBean2 != null) {
			String mailchk = getMailChk(tempLoginUserBean);
			
			if(mailchk.equals("Y")) { 
				
				loginUserBean.setUser_idx(tempLoginUserBean2.getUser_idx());
				loginUserBean.setUser_Name(tempLoginUserBean2.getUser_Name());
				loginUserBean.setUserLogin(true);
				loginUserBean.setUsermailchk("OK");
				
			}else {
				loginUserBean.setUserLogin(true);
				loginUserBean.setUsermailchk("NO");
				
			}
		}
			
	}
	
	public String getMailChk(UserBean tempLoginUserBean) {
		String wmailchk = userDao.getMailChk(tempLoginUserBean);
		
		return wmailchk;
					
	}
	
}
