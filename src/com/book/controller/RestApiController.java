package com.book.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.book.beans.CommentBean;
import com.book.service.BoardService;
import com.book.service.CommentService;
import com.book.service.UserService;

@RestController
public class RestApiController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CommentService commentService;
	
	@GetMapping("/user/checkUserIdExist/{User_id}")
	public String checkUserIdExist(@PathVariable String User_id) {
		boolean chk = userService.checkUserIdExist(User_id);
		
		return chk +"";
	}
	
	@GetMapping("/user/remailchk/{User_mail}/{User_Id}")
	public String remailchk(@PathVariable String User_mail,@PathVariable String User_Id ,HttpServletRequest request) {
		System.out.println("11111111111111111111111111111");
		boolean chk = userService.checkUserRemailchk(User_mail,User_Id,request);
		
		return chk +"";
	}
	
	@GetMapping("/comment/commentwrite/{memo}/{boardcode}/{commentId}")
	public String commentwrite(@PathVariable String memo,@PathVariable int boardcode,@PathVariable int commentId ) {
		boolean chk = commentService.commentwrite(memo,boardcode,commentId);
		
		return chk +"";
	}
	
	@GetMapping("/comment/commentread/{content_idx}")
	@ResponseBody
	public List<CommentBean> commentread(@PathVariable int content_idx) {
		    
		
		System.out.println("테스트"+content_idx);
			
	        List<CommentBean> list = commentService.commentread(content_idx);
	        
	        return list;
	        

	}
	
	
	
	
}
