package com.healthcare.biz.service;

import java.util.List;

import com.healthcare.admin.board.Board;

public interface NoticeService {
	public int getNoticeCount();
	public List<Board> getNoticeList();
	public void setQuestion(Board board) throws Exception;
}
