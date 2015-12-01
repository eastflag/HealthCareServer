package com.healthcare.admin.board;

public class BoardMgr {

	public void addBoard(Board board){
		try{
			BoardDAO boardDAO = new BoardDAO();
			boardDAO.addBoard(board);
			
		}catch(Exception se) {
			System.out.println(se.getMessage());
		}
	}
	
	public Boards getBoards(Board board, int startIndex, int count){
		Boards boards = null;
		
		try{
			BoardDAO boardDAO = new BoardDAO();
			
			boards = new Boards(boardDAO.getBoards(board, startIndex, count), boardDAO.getBoardTotRowCnt(board));
			
		}catch(Exception se) {
			System.out.println(se.getMessage());
		}
		return boards;
	}
	
	public Board getBoard(String board_id){
		Board board = new Board();
		
		try{
			BoardDAO boardDAO = new BoardDAO();
			board = boardDAO.getBoard(board_id);
		}catch(Exception se){
			System.out.println(se.getMessage());
		}
		return board;
	}
	
	public void setBoard(Board board){
		try {
			BoardDAO boardDAO = new BoardDAO();
			boardDAO.setBoard(board);
		}catch(Exception se) {
			System.out.println(se.getMessage());
		}
	}
}
