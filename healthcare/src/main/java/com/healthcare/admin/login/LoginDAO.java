package com.healthcare.admin.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.healthcare.common.DBConnectionManager;
import com.healthcare.admin.login.Login;

public class LoginDAO {
	
	private DBConnectionManager pool = null;

	
	public LoginDAO() {
		try {
			pool = DBConnectionManager.getInstance();
		} catch (Exception e) {
			System.out.println("Error : LoginDAO");
		}
	}
	
	public Login getLogin(String loginId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Login login = null;

		StringBuffer qr = new StringBuffer().append(" SELECT ")
				.append(" 	ADMIN_ID, ADMIN_PASS, SCHOOL_ID, ADMIN_INFO ")
				.append(" FROM ADMIN_MANAGE ").append(" WHERE ADMIN_ID=? ");
		String queryStr = qr.toString();
		try {
			con = pool.getConnection();
			pstmt = con.prepareStatement(queryStr);
			int i = 0;
			pstmt.setString(++i, loginId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				login = new Login(rs.getString("ADMIN_ID"),
						rs.getString("ADMIN_PASS"), rs.getString("SCHOOL_ID"), rs.getString("ADMIN_INFO"));
			}

		} catch (SQLException se) {
			System.out.println(se);
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		}
		return login;
	}
}
