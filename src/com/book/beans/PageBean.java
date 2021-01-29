package com.book.beans;

public class PageBean {

	// 최소 페이지 번호
	private int min;
	// 최대 페이지 번호
	private int max;
	// 이전 버튼의 페이지 번호
	private int prevPage;
	// 다음 버튼의 페이지 번호
	private int nextPage;
	// 전체 페이지 갯수
	private int pageCnt;
	// 현재 페이지 번호
	private int currentPage;
	
	//전체 글 갯수 , 현재페이지번호 , 페이지당 글의 갯수, 페이지 버튼의 갯수
	public PageBean(int contentCnt, int currentPage, int contentPageCnt, int paginationCnt) {
		//현재 페이지 번호
		this.currentPage = currentPage;
		
		// 전체 페이지 갯수       전체 글 갯수 / 페이지당 글 갯수
		pageCnt= contentCnt / contentPageCnt;
	
		// 33/10 =3  한페이지가 더 필요함 
		
		//  전체 글 갯수 / 페이지당 글 갯수 나머지가 0보다 크다면 한페이지가 더 필요하기 때문 1을 더해줌
		if(contentCnt%contentPageCnt>0) {
			pageCnt++;
		}
		//1~10 :최소 1, 최대10
		//11~20:최소 11, 최대20
		// ((현재페이지번호 - 1) /페이지당 글의 갯수)*페이지당 글의 갯수 +1
		min =((currentPage-1)/contentPageCnt)*contentPageCnt +1;
		// 최소값 + 페이지 버튼의 갯수 -1
		max = min+paginationCnt -1;
		
		// 최대값이 전체 페이지 갯수 보다 커지면 전체 페이지 갯수로 설정  
		if(max> pageCnt) {
			max = pageCnt;
		}
	}
	
	public int getMin() {
		return min;
	}
	public int getMax() {
		return max;
	}
	public int getPrevPage() {
		return prevPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public int getPageCnt() {
		return pageCnt;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	
	
}
