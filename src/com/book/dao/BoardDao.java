package com.book.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.book.beans.ContentBean;

@Repository
public class BoardDao {

	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public void addContentInfo(ContentBean writeContentBean) {
		
		sqlSessionTemplate.insert("board.addContentInfo", writeContentBean);
	}
	
	public String getInfoName(int info_idx) {
		
		return sqlSessionTemplate.selectOne("board.getInfoName", info_idx);
	}
	public List<ContentBean> getContentList(int info_idx,RowBounds rowBounds) {
		
		return sqlSessionTemplate.selectList("board.getContentList", info_idx,rowBounds);
	}
	
	public ContentBean getContentInfo(int content_idx) {
		
		return sqlSessionTemplate.selectOne("board.getContentInfo",content_idx);
	}
	
	public void modifyContentInfo(ContentBean modifyContentBean) {
		
		 sqlSessionTemplate.update("board.modifyContentInfo",modifyContentBean);
	}
	public void deleteContentInfo(int content_idx) {
		
		 sqlSessionTemplate.delete("board.deleteContentInfo",content_idx);
	}
	
	public int getContentCnt(int info_idx) {
		
		return sqlSessionTemplate.selectOne("board.getContentCnt",info_idx);
	}
	
	
	
	
}