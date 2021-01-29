package com.book.service;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.book.beans.ContentBean;
import com.book.beans.PageBean;
import com.book.dao.BoardDao;

import com.book.beans.UserBean;

@Service
@PropertySource("/WEB-INF/properties/option.properties")
public class BoardService {

	@Value("${path.upload}")
	private String path_upload;
	
	@Value("${page.listcnt}")
	private int page_listcnt;
	
	@Value("${page.paginationcnt}")
	private int page_paginationcnt;
	
	@Autowired
	private BoardDao boardDao;
	
	@Resource(name = "loginUserBean")
	@Lazy
	private UserBean loginUserBean;
	
	private String saveUploadFile(MultipartFile upload_file) {
		String file_name = System.currentTimeMillis() + "_" +upload_file.getOriginalFilename();
		
		try {
			upload_file.transferTo(new File(path_upload +"/"+file_name));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return file_name;
	}
	public void addContentInfo(ContentBean writeContentBean) {
	
		MultipartFile upload_file = writeContentBean.getUpload_file();
		
		if(upload_file.getSize()>0) {
			String file_name =saveUploadFile(upload_file);
			writeContentBean.setContent_file(file_name);
		}
		
		writeContentBean.setContent_writer_idx(loginUserBean.getUser_idx());
		
		boardDao.addContentInfo(writeContentBean);
	}
	
	public String  getInfoName(int info_idx) {
		
		return boardDao.getInfoName(info_idx);
	}
	
	public List<ContentBean> getContentList(int info_idx,int page) {
		System.out.println("1111111111");
		//0*10 = 0  1*10=10
		int start =(page - 1) * page_listcnt;
		//RowBouds는 보여줄갯수 설정을 하는거다  (0,10)면 0부터 10개까지 (10,10) 10부터 10개 
		RowBounds rowBounds = new RowBounds(start,page_listcnt);
		
		
		
		return boardDao.getContentList(info_idx,rowBounds);
		
	}
	
	public ContentBean getContentInfo(int content_idx) {
		
		return boardDao.getContentInfo(content_idx);
	}
	
	public void modifyContentInfo(ContentBean modifyContentBean) {
		
		MultipartFile upload_file = modifyContentBean.getUpload_file();
		
		if(upload_file.getSize()>0) {
			String file_name = saveUploadFile(upload_file);
			modifyContentBean.setContent_file(file_name);
		}
		boardDao.modifyContentInfo(modifyContentBean);
	}
	public void deleteContentInfo(int content_idx) {
		
		boardDao.deleteContentInfo(content_idx);
	}
	
	public PageBean getContentCnt(int info_idx,int currentPage) {
		
		int content_cnt = boardDao.getContentCnt(info_idx);
		
		PageBean pageBean = new PageBean(content_cnt, currentPage, page_listcnt, page_paginationcnt);
		
		return pageBean;
	}
}
