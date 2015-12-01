package com.healthcare.biz.mybatis.persistence;

import java.util.List;

import com.healthcare.admin.board.Board;


public interface NoticeMapper {
	
	int getNoticeCount();
	List<Board> getNoticeList();
	void setQuestion(Board board) throws Exception;

}
