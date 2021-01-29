package com.book.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.book.beans.CommentBean;

@Repository
public class CommentDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public boolean commentwrite(String memo,int boardcode,int commentId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("memo",memo);
		map.put("boardcode",boardcode);
		map.put("commentId",commentId);
		sqlSessionTemplate.insert("comment.commentwrite", map);
		
		return true;
	}

	public List<CommentBean> commentread(int content_idx) {
		
		
		return sqlSessionTemplate.selectList("comment.commentread",content_idx);
	}
}
