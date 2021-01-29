package com.book.Interceptor;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.HandlerInterceptor;

import com.book.beans.BoardInfoBean;
import com.book.beans.UserBean;
import com.book.service.TopMenuService;


public class TopMenuInterceptor implements HandlerInterceptor{

	
	@Autowired
	private TopMenuService topMenuService;
	@Resource(name = "loginUserBean")
	@Lazy
	private UserBean loginUserBean;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		List<BoardInfoBean> topMenuList = topMenuService.getTopMenuList();
		request.setAttribute("topMenuList",topMenuList);
		request.setAttribute("loginUserBean", loginUserBean);
		
		return true;
	}
}
