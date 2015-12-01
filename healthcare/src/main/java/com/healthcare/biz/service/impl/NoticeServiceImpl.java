package com.healthcare.biz.service.impl;

import java.util.List;

import javax.swing.text.AbstractDocument.Content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.admin.board.Board;
import com.healthcare.biz.mybatis.persistence.NoticeMapper;
import com.healthcare.biz.service.NoticeService;
import com.healthcare.common.Util;

@Service("notice")
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	NoticeMapper noticeMapper;
	
	@Override
	public int getNoticeCount() {
		return noticeMapper.getNoticeCount();
	}
	
	public List<Board> getNoticeList(){
		
		List<Board> list =noticeMapper.getNoticeList();
		/*
		Board board = null;
		
		for(int i=0;i<list.size();i++){
			board = new Board();
			if(Util.existSpecialCharToHtml(list.get(i).getContent(), '\n')){
				board = list.get(i);
				board.setConvertContent(Util.convertSpecialCharToHtml(board.getContent(), '\n', "<BR>"));
				//board.setConvertContent(board.getContent().replaceAll("\r\n", "<br>"));
				list.set(i, board);
			}else{
				board = list.get(i);
				board.setConvertContent(board.getContent());
				list.set(i, board);
			}
		}
		*/
		
		return list;
	}

	public void setQuestion(Board board) throws Exception{
		
		board.setBoard_type("2");
		board.setUse_yn("Y");
		//board.setReg_id("abcMart");
		//board.setUpd_id("abcMart");
		//board.setContent(board.getContent().replaceAll("(?i)\r\n", "&lt;BR&gt;"));

		noticeMapper.setQuestion(board);
	}


}
