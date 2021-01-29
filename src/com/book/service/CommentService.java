package com.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.beans.CommentBean;
import com.book.dao.CommentDao;

@Service
public class CommentService {

	@Autowired
	private CommentDao commentDao;
	
	public boolean commentwrite(String memo,int boardcode,int commentId) {
		return commentDao.commentwrite(memo, boardcode, commentId);
	}
	
	public List<CommentBean> commentread(int content_idx) {
		
		
		return commentDao.commentread(content_idx);
	}
}
