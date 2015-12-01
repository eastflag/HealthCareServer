package com.healthcare.admin.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.healthcare.common.DBConnectionManager;

public class BoardDAO {
	
	private DBConnectionManager pool = null;
	
	public BoardDAO(){
		try{
			pool = DBConnectionManager.getInstance();
		}catch(Exception e){
			System.out.println("Error : DBConnectionManager !!");
		}
	}
	
	public void addBoard(Board board) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		StringBuffer qr =new StringBuffer()
		.append(" INSERT INTO BOARD_INFO \n")
		.append(" (BOARD_TYPE,           \n")
		.append(" TITLE,                 \n")
		.append(" CONTENT,               \n")
		.append(" NAME,                  \n")
		.append(" EMAIL,                 \n")
		.append(" TEL,                   \n")
		.append(" USE_YN,                \n")
		.append(" END_DATE,              \n")
		.append(" REMARK,                \n")
		.append(" REG_DATE,              \n")
		.append(" REG_ID,                \n")
		.append(" UPD_DATE,              \n")
		.append(" UPD_ID)                \n")
		.append(" VALUES (?,?,?,?,?,?,?,?,?,NOW(),?,NOW(),?)");
		
		String queryStr = qr.toString();
		
		try{
			con = pool.getConnection();
			pstmt = con.prepareStatement(queryStr);
			
			int j = 0;
			
			pstmt.setString(++j, board.getBoard_type());
			pstmt.setString(++j, board.getTitle());
			pstmt.setString(++j, board.getContent());
			pstmt.setString(++j, board.getName());
			pstmt.setString(++j, board.getEmail());
			pstmt.setString(++j, board.getTel());
			pstmt.setString(++j, board.getUse_yn());
			pstmt.setString(++j, board.getEnd_date());
			pstmt.setString(++j, board.getRemark());
			pstmt.setString(++j, board.getReg_id());
			pstmt.setString(++j, board.getUpd_id());
			
			pstmt.executeUpdate(); 
			
		}catch(SQLException se){
			System.out.println(se);
		}finally {
			if(pstmt!=null) pstmt.close();
			if(con != null) con.close();
		}
	}

	public Collection getBoards(Board board, int startIndex, int count)throws SQLException{
		
		Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String joinqu = "";
        
        if (board.getBoard_type() != null && !board.getBoard_type().equals("")) {
			joinqu += " WHERE BOARD_TYPE = '"+board.getBoard_type()+"' \n";
		}
        
        if (board.getTitle() != null && !board.getTitle().equals("")) {
			joinqu += " AND TITLE LIKE '%"+board.getTitle()+"%' \n";
		}
        
        StringBuffer qr =new StringBuffer()
			.append(" SELECT ")
			.append(      "BOARD_ID,    \n")
			.append(      "BOARD_TYPE,  \n")
			.append(      "TITLE,       \n")
			.append(      "CONTENT,     \n")
			.append(      "NAME,        \n")
			.append(      "EMAIL,       \n")
			.append(      "TEL,         \n")
			.append(      "USE_YN,      \n")
			.append(      "END_DATE,    \n")
			.append(      "REMARK,      \n")
			.append(      "REG_DATE,    \n")
			.append(      "REG_ID,     \n")
			.append(      "UPD_DATE,   \n")
			.append(      "UPD_ID      \n")
			.append("   FROM BOARD_INFO \n")
			.append(joinqu + "  and USE_YN = 'Y' ")
			.append(" ORDER BY BOARD_ID DESC ");
        String queryStr = qr.toString();
        
        ArrayList boards = new ArrayList();
        
        try {
        	
        	con = pool.getConnection();
        	pstmt = con.prepareStatement(queryStr);

        	rs = pstmt.executeQuery();

    		// startIndex 파라메타 수만큼 Skip한다.
			while (startIndex-- > 0 && rs.next());
        	while (count-- > 0 && rs.next())
        	{
        		board = new Board();

        		board.setBoard_id   (rs.getInt   ("BOARD_ID"));
        		board.setBoard_type (rs.getString("BOARD_TYPE"));
        		board.setTitle      (rs.getString("TITLE"));
        		board.setContent    (rs.getString("CONTENT"));
        		board.setName       (rs.getString("NAME"));
        		board.setEmail      (rs.getString("EMAIL"));
        		board.setTel        (rs.getString("TEL"));
        		board.setUse_yn     (rs.getString("USE_YN"));
        		board.setEnd_date   (rs.getString("END_DATE"));
        		board.setRemark     (rs.getString("REMARK"));
        		board.setReg_date   (rs.getString("REG_DATE"));
        		board.setReg_id     (rs.getString("REG_ID"));
        		board.setUpd_date   (rs.getString("UPD_DATE"));
        		board.setUpd_id     (rs.getString("UPD_ID"));
        		
				boards.add(board);
        	}
        	
        }catch(SQLException se) {
			System.out.println(se);
		}finally {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con != null) con.close();
		}
        return boards;
	}
	
	public int getBoardTotRowCnt(Board board) throws SQLException{
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs   = null;
		String joinqu = "";

		if (board.getBoard_type() != null && !board.getBoard_type().equals("")) {
			joinqu += " WHERE BOARD_TYPE = '"+board.getBoard_type()+"' \n";
		}

		StringBuffer qr =new StringBuffer()
			.append(" SELECT COUNT(*) AS cnt  ")
			.append("   FROM BOARD_INFO ")
			.append(joinqu);
		String queryStr = qr.toString();

		int total_count = 0;
		try {
			con = pool.getConnection();
			pstmt = con.prepareStatement(queryStr);
			rs = pstmt.executeQuery();
			rs.next();
			total_count = rs.getInt("cnt");
		}catch(SQLException se) {
			System.out.println(se);
		}finally {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con != null) con.close();
		}
		return total_count;
    }
	
	public Board getBoard(String board_id) throws SQLException{
		Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Board board = null;
        String joinqu = "";
        
        if (board_id != null && !board_id.equals("")) {
        	joinqu += "WHERE BOARD_ID= '"+board_id+"'";
        }
        
        StringBuffer qr =new StringBuffer()
			.append(" SELECT ")
			.append(      "BOARD_ID,    \n")
			.append(      "BOARD_TYPE,  \n")
			.append(      "TITLE,       \n")
			.append(      "CONTENT,     \n")
			.append(      "NAME,        \n")
			.append(      "EMAIL,       \n")
			.append(      "TEL,         \n")
			.append(      "USE_YN,      \n")
			.append(      "END_DATE,    \n")
			.append(      "REMARK,      \n")
			.append(      "REG_DATE,    \n")
			.append(      "REG_ID,      \n")
			.append(      "UPD_DATE,    \n")
			.append(      "UPD_ID      \n")
			.append("   FROM BOARD_INFO \n")
			.append(joinqu);
		String queryStr = qr.toString();
	    
	    try {
       		con = pool.getConnection();
        	pstmt = con.prepareStatement(queryStr);

        	rs = pstmt.executeQuery();

        	if (rs.next())
        	{
        		board = new Board();
				board.setBoard_id(rs.getInt("BOARD_ID"));
				board.setBoard_type(rs.getString("BOARD_TYPE"));
				board.setTitle(rs.getString("TITLE"));
				board.setContent(rs.getString("CONTENT"));
				board.setName(rs.getString("NAME"));
				board.setEmail(rs.getString("EMAIL"));
				board.setTel(rs.getString("TEL"));
				board.setUse_yn(rs.getString("USE_YN"));
				board.setEnd_date(rs.getString("END_DATE"));
				board.setRemark(rs.getString("REMARK"));
				board.setReg_date(rs.getString("REG_DATE"));
				board.setReg_id(rs.getString("REG_ID"));
				board.setUpd_date(rs.getString("UPD_DATE"));
				board.setUpd_id(rs.getString("UPD_ID"));
            }
        }catch(SQLException se) {
			System.out.println(se);
		}finally {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con != null) con.close();
		}
        
        return board;
	}
	
	public void setBoard(Board board) throws SQLException{
		Connection con = null;
        PreparedStatement pstmt = null;

		StringBuffer qr0 =new StringBuffer()
			.append(" UPDATE BOARD_INFO SET \n")
			.append(   " BOARD_TYPE    = ?, \n")
			.append(   " TITLE         = ?, \n")
			.append(   " CONTENT       = ?, \n")
			.append(   " NAME          = ?, \n")
			.append(   " EMAIL         = ?, \n")
			.append(   " TEL           = ?, \n")
			.append(   " USE_YN        = ?, \n")
			.append(   " END_DATE      = ?, \n")
			.append(   " REMARK        = ?, \n")
			.append(   " UPD_DATE  = NOW(), \n")
			.append(   " UPD_ID         = ? \n")
			.append(" WHERE BOARD_ID = ? ");
		String queryStr = qr0.toString();

        try {
			con = pool.getConnection();
			pstmt = con.prepareStatement(queryStr);

			int i = 0;
			pstmt.setString(++i, board.getBoard_type());
			pstmt.setString(++i, board.getTitle     ());
			pstmt.setString(++i, board.getContent   ());
			pstmt.setString(++i, board.getName      ());
			pstmt.setString(++i, board.getEmail     ());
			pstmt.setString(++i, board.getTel       ());
			pstmt.setString(++i, board.getUse_yn    ());
			pstmt.setString(++i, board.getEnd_date  ());
			pstmt.setString(++i, board.getRemark    ());
			pstmt.setString(++i, board.getUpd_id    ());
			pstmt.setInt   (++i, board.getBoard_id  ());

			int resultCount = pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se);
		}finally {
			if(pstmt!=null) pstmt.close();
			if(con != null) con.close();
		}
	}
}
