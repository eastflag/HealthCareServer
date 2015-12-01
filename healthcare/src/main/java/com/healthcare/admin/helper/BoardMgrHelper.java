package com.healthcare.admin.helper;


import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.healthcare.admin.board.Board;
import com.healthcare.admin.board.BoardMgr;
import com.healthcare.admin.board.Boards;
import com.healthcare.common.Config;
import com.healthcare.common.HttpParameter;
import com.healthcare.common.SessionManager;
import com.healthcare.common.Util;
import com.healthcare.common.WebKeys;

public class BoardMgrHelper {

	private BoardMgr boardMgr = null;
	
	public BoardMgrHelper(){
		try{
			boardMgr = new BoardMgr();
		}catch(Exception e){
        	System.out.println("setContent error"+e.getMessage());
        }
	}
	
	public void addBoard(HttpServletRequest req){
		Board board  = null;
		try{
			String board_type = HttpParameter.getString(req,"board_type");
			String title = HttpParameter.getString(req,"title");     
			String content = HttpParameter.getString(req,"content");   
			String name = HttpParameter.getString(req,"name");      
			String email = HttpParameter.getString(req,"email");     
			String tel = HttpParameter.getString(req,"tel");       
			String use_yn = HttpParameter.getString(req,"use_yn");    
			String end_date = HttpParameter.getString(req,"end_date");  
			String remark = HttpParameter.getString(req,"remark");
			String loginId = (String)req.getSession().getAttribute(WebKeys.UserKey);
			
			board = new Board();
			
			board.setBoard_type (board_type);
			board.setTitle      (title     );
			//board.setContent    (content   );
			//board.setContent    (content.replaceAll("(?i)\r\n", "&lt;BR&gt;"));
			board.setContent    (content.replaceAll("(?i)\r\n", "<BR>"));
			board.setName       (name      );
			board.setEmail      (email     );
			board.setTel        (tel       );
			board.setUse_yn     (use_yn    );
			board.setEnd_date   (end_date  );
			//board.setRemark     (remark    );
			//board.setRemark     (remark.replaceAll("(?i)\r\n", "&lt;BR&gt;"));
			board.setRemark     (remark.replaceAll("(?i)\r\n", "<BR>"));
			board.setReg_id     (loginId   );
			board.setUpd_id     (loginId   );
			
			boardMgr.addBoard(board);
		}catch(Exception e){
        	System.out.println("addBoard error"+e.getMessage());
        }
	}
	
	public Boards getBoards(HttpServletRequest req, String board_type){
		
		Boards boards = null;
		Board board = null;
		
		String[] pSearchKeys = new String[] {"title"};
		
		SessionManager.setSearchKeys(req, pSearchKeys);
		
		HashMap searchKeys = SessionManager.getSearchKeys(req);
		
		String title  = ((String)searchKeys.get("title")).trim();
		
		int startIndex = (SessionManager.getPageNo(req)-1)*Config.NUM_OF_LINE10;
		
		int count = Config.NUM_OF_LINE10;
		
		try{
			board = new Board();
			
			board.setBoard_type(board_type);
			board.setTitle(title);
			
			boards = boardMgr.getBoards(board, startIndex, count);
			
		}catch(Exception e){
			System.out.println("getBoards error"+e.getMessage());
		}
		return boards;
	}
	
	public Boards getBoardsExcel(HttpServletRequest req, String board_type, int totRowCnt){
		
		Boards boards = null;
		Board board = null;
		
		String[] pSearchKeys = new String[] {"title"};
		
		SessionManager.setSearchKeys(req, pSearchKeys);
		
		HashMap searchKeys = SessionManager.getSearchKeys(req);
		
		String title  = ((String)searchKeys.get("title")).trim();
		
		int startIndex = 1;
		
		
		try{
			board = new Board();
			
			board.setBoard_type(board_type);
			board.setTitle(title);
			
			boards = boardMgr.getBoards(board, startIndex, totRowCnt);
			
		}catch(Exception e){
			System.out.println("getBoards error"+e.getMessage());
		}
		return boards;
	}
	
	public Board getBoard(HttpServletRequest req){
		
		String board_id  = HttpParameter.getString(req, "board_id");
		
		Board board = new Board();
		
		try{
			board = boardMgr.getBoard(board_id);
			
			//board.setConvertContent(board.getContent().replaceAll("(?i)&lt;BR&gt;", "\r\n"));
			//board.setRemark(board.getRemark().replaceAll("(?i)&lt;BR&gt;", "\r\n"));
			board.setConvertContent(Util.notNullValueOf(board.getContent(), "").replaceAll("(?i)<BR>", "\r\n"));
			board.setRemark(Util.notNullValueOf(board.getRemark(), "").replaceAll("(?i)<BR>", "\r\n"));
			/*
			if(Util.existSpecialCharToHtml(str, '\n')){
				board.setConvertContent(str.replaceAll("\r\n", "&lt;BR&gt;"));
			}else{
				board.setConvertContent(str);
			}
			*/
			
			
		}catch(Exception e){
        	System.out.println("getBoard error"+e.getMessage());
        }
		
		return board;
	}
	
	public void setBoard(HttpServletRequest req){
		
		Board board = null;
		
		try{
			
			String board_type = HttpParameter.getString(req,"board_type");
			String title = HttpParameter.getString(req,"title");     
			String content = HttpParameter.getString(req,"content");   
			String name = HttpParameter.getString(req,"name");      
			String email = HttpParameter.getString(req,"email");     
			String tel = HttpParameter.getString(req,"tel");       
			String use_yn = HttpParameter.getString(req,"use_yn");    
			String end_date = HttpParameter.getString(req,"end_date");  
			String remark = HttpParameter.getString(req,"remark");
			String loginId = (String)req.getSession().getAttribute(WebKeys.UserKey);
			int board_id = HttpParameter.getInt(req, "board_id");
			
			board = new Board();
			
			board.setBoard_type (board_type);
			board.setTitle      (title     );
			//board.setContent    (content   );
			//board.setContent    (content.replaceAll("(?i)\r\n", "&lt;BR&gt;"));
			board.setContent    (content.replaceAll("(?i)\r\n", "<BR>"));
			board.setName       (name      );
			board.setEmail      (email     );
			board.setTel        (tel       );
			board.setUse_yn     (use_yn    );
			board.setEnd_date   (end_date  );
			//board.setRemark     (remark    );
			//board.setRemark     (remark.replaceAll("(?i)\r\n", "&lt;BR&gt;"));
			board.setRemark     (remark.replaceAll("(?i)\r\n", "<BR>"));
			board.setUpd_id     (loginId   );
			board.setBoard_id   (board_id  );
			
			boardMgr.setBoard(board);
			
		}catch(Exception e){
        	System.out.println("setBoard error"+e.getMessage());
        }
	}
}
