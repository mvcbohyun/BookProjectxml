package com.book.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.book.beans.UserBean;
import com.book.service.UserService;
import com.book.validator.UserValidator;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Resource(name = "loginUserBean")
	@Lazy
	private UserBean loginUserBean;
	
	@GetMapping("/login")
	public String login(@ModelAttribute("tempLoginUserBean") UserBean tempLoginUserBean,
						@RequestParam(value= "fail",defaultValue="false") boolean fail,
						@RequestParam(value= "mailfail",defaultValue="false") boolean mailfail,
						Model model){
		model.addAttribute("fail",fail);
		model.addAttribute("mailfail",mailfail);
		
		return "user/login";
	}
	@PostMapping("/login_pro")
	public String login_pro(@Valid @ModelAttribute("tempLoginUserBean")  UserBean tempLoginUserBean, BindingResult result) {
		
		if(result.hasErrors()) {
			return "user/login";
		}
		
		
		userService.getLoginUserInfo(tempLoginUserBean);
		if(loginUserBean.isUserLogin() ==true){
			if(loginUserBean.getUsermailchk().equals("OK")) {
				
				return "user/login_success";	
			}else {
				loginUserBean.setUserLogin(false);
				return "user/mail_fail";
			}
			
		}else {
			return "user/login_fail";
		}
		
		
	}
	
	
	@GetMapping("/join")
	public String join(@ModelAttribute("joinUserBean") UserBean joinUserBean) {
		
		return "user/join";
	}
	
	@PostMapping("/join_pro")
	public String join_pro(@ModelAttribute("joinUserBean") UserBean joinUserBean,BindingResult result,HttpServletRequest  request) {
		if(result.hasErrors()) {
			return "user/join";
		}
		
		userService.addUserInfo(joinUserBean);
		checkUsermailchk(joinUserBean,request);
		return "user/join_success";
	}
	
	@GetMapping("/modify")
	public String modify(@ModelAttribute("modifyUserBean")UserBean modifyUserBean) {
		
		userService.getModifyUserInfo(modifyUserBean);
		return "user/modify";
	}
	@PostMapping("/modify_pro")
	public String modify_pro(@Valid @ModelAttribute("modifyUserBean") UserBean modifyUserBean, BindingResult result) {
		/*System.out.println("11111111111111111111111111111");
		System.out.println(modifyUserBean.getUser_Id());
		System.out.println(modifyUserBean.getUser_Name());
		System.out.println(modifyUserBean.getUser_mail());
		System.out.println(modifyUserBean.getUser_Pw());
		System.out.println(modifyUserBean.getUser_Pw2());
		System.out.println(modifyUserBean.getUser_CellPhone());
		System.out.println(modifyUserBean.getUser_Gender());
		System.out.println("11111111111111111111111111111");
		*/
		modifyUserBean.setUser_Pw(modifyUserBean.getUser_Pw()==null?"":modifyUserBean.getUser_Pw());
		modifyUserBean.setUser_Pw2(modifyUserBean.getUser_Pw2()==null?"":modifyUserBean.getUser_Pw2());
		modifyUserBean.setUser_CellPhone(modifyUserBean.getUser_CellPhone()==null?"":modifyUserBean.getUser_CellPhone());
		modifyUserBean.setUser_Gender(modifyUserBean.getUser_Gender()==null?"0":modifyUserBean.getUser_Gender());
		if(modifyUserBean.getUser_Pw() != modifyUserBean.getUser_Pw2()) {
			return "user/modify";
		}
		
		userService.setModifyUserInfo(modifyUserBean);
		return "user/modify_success";
	}
	
	@GetMapping("/logout")
	public String logout() {
		
		loginUserBean.setUserLogin(false);
		
		return "user/logout";
	}
	
	@GetMapping("/not_login")
	public String not_login() {
		
		
		
		return "user/not_login";
	}
	
	@GetMapping("/checkUsermailchk")
	public void checkUsermailchk(UserBean joinUserBean,HttpServletRequest  request) {
		 userService.checkUsermailchk(joinUserBean, request);
		
		
	}
	
	@GetMapping("/key_alter")
	public String key_alterConfirm(@RequestParam("user_id") String user_id, @RequestParam("User_mailchk") String User_mailchk) {

		userService.alter_userKey(user_id, User_mailchk); // mailsender의 경우 @Autowired

		return "user/mail_success";
	}



	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		UserValidator validator1 = new UserValidator();
		binder.addValidators(validator1);
	}
	
	
	
}
