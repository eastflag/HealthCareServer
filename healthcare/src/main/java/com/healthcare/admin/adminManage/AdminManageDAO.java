package com.healthcare.admin.adminManage;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.healthcare.admin.menu.Menu;
import com.healthcare.admin.school.School;
import com.healthcare.common.AES256Util;
import com.healthcare.common.DBConnectionManager;

public class AdminManageDAO {
	
	private DBConnectionManager pool = null;
	private AES256Util aes = new AES256Util();
	
	public AdminManageDAO(){
		try{
			pool = DBConnectionManager.getInstance();
		}catch(Exception e){
			System.out.println("Error : DBConnectionManager !!");
		}
	}
	
	/**
	 * 계정 목록 조회 <BR>
	 * @param adminManage
	 * @throws SQLException
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws InvalidAlgorithmParameterException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws UnsupportedEncodingException 
	 * @throws InvalidKeyException 
	 */
	public Collection getAdminManages(AdminManage adminManage)throws SQLException, InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String joinqu = "WHERE 1=1 AND ADMIN_MANAGE.SCHOOL_ID = SCHOOL_INFO.SCHOOL_ID\n AND Admin_ID not in ('mtelomaster')\n";
        
        StringBuffer qr =new StringBuffer()
			.append(" SELECT ")
			.append(      "@RNUM := @RNUM +1 AS ROWNUM,                      \n")
			.append(      "ADMIN_MANAGE.ADMIN_ID,                            \n")
			.append(      "ADMIN_MANAGE.SCHOOL_ID,                           \n")
			.append(      "ADMIN_MANAGE.ADMIN_PASS,                          \n")
			.append(      "ADMIN_MANAGE.ADMIN_INFO,                          \n")
			.append(      "SCHOOL_INFO.SCHOOL_NAME                          \n")
			.append("   FROM ADMIN_MANAGE, (SELECT @RNUM := 0)R, SCHOOL_INFO \n")
			.append(joinqu)
			.append(" ORDER BY ADMIN_MANAGE.ADMIN_ID ASC ");
		String queryStr = qr.toString();
		
	    ArrayList adminManages = new ArrayList();
	    
	    try {
       		con = pool.getConnection();
        	pstmt = con.prepareStatement(queryStr);

        	rs = pstmt.executeQuery();

    		// startIndex 파라메타 수만큼 Skip한다.
			//while (startIndex-- > 0 && rs.next());
        	//while (count-- > 0 && rs.next())
        	while (rs.next())
        	{
        		adminManage = new AdminManage();
				
        		adminManage.setAdmin_seq(rs.getInt("ROWNUM"));
				adminManage.setAdmin_id(rs.getString("ADMIN_ID"));
				adminManage.setSchool_id(rs.getString("SCHOOL_ID"));
				adminManage.setAdmin_pass(rs.getString("ADMIN_PASS"));
				adminManage.setAdmin_info(rs.getString("ADMIN_INFO"));
				adminManage.setSchool_name(aes.decode(rs.getString("SCHOOL_NAME")));

				adminManages.add(adminManage);
            }
        }catch(SQLException se) {
			System.out.println(se);
		}finally {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con != null) con.close();
		}
        
        return adminManages;
	}
	
	/**
	 * 전체 계정 데이터 수 조회 <BR>
	 * @param adminManage
	 * @throws SQLException
	 */
	public int getAdminManageTotRowCnt(AdminManage adminManage) throws SQLException{
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs   = null;
        String joinqu = "WHERE 1=1 \n";
        
		StringBuffer qr =new StringBuffer()
			.append(" SELECT COUNT(*) AS cnt  ")
			.append("   FROM ADMIN_MANAGE ")
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
	
	/**
	 * 계정 단일 조회 <BR>
	 * @param admin_id
	 * @throws SQLException
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws InvalidAlgorithmParameterException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws UnsupportedEncodingException 
	 * @throws InvalidKeyException 
	 */
	public AdminManage getAdminManage(String admin_id)throws SQLException, InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        AdminManage adminManage = null;
        String joinqu = "";
        
        if (admin_id != null && !admin_id.equals("")) {
        	joinqu += "WHERE ADMIN_MANAGE.ADMIN_ID= '"+admin_id+"'\n";
        	joinqu += "AND ADMIN_MANAGE.SCHOOL_ID = SCHOOL_INFO.SCHOOL_ID\n";
        }
        
        StringBuffer qr =new StringBuffer()
			.append(" SELECT ")
			.append(      "ADMIN_MANAGE.ADMIN_ID,       \n")
			.append(      "ADMIN_MANAGE.SCHOOL_ID,      \n")
			.append(      "ADMIN_MANAGE.ADMIN_PASS,     \n")
			.append(      "ADMIN_MANAGE.ADMIN_INFO,     \n")
			.append(      "SCHOOL_INFO.SCHOOL_NAME     \n")
			.append("   FROM ADMIN_MANAGE, SCHOOL_INFO \n")
			.append(joinqu);
		String queryStr = qr.toString();
	    
	    try {
        	if (admin_id != null && !admin_id.equals("")) {
        		con = pool.getConnection();
            	pstmt = con.prepareStatement(queryStr);
            	
        		rs = pstmt.executeQuery();
            	adminManage = new AdminManage();

            	while (rs.next())
            	{
    				adminManage.setAdmin_id(rs.getString("ADMIN_ID"));
    				adminManage.setSchool_id(rs.getString("SCHOOL_ID"));
    				adminManage.setAdmin_pass(rs.getString("ADMIN_PASS"));
    				adminManage.setAdmin_info(rs.getString("ADMIN_INFO"));
    				adminManage.setSchool_name(aes.decode(rs.getString("SCHOOL_NAME")));
                }
        	}
        }catch(SQLException se) {
			System.out.println(se);
		}finally {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con != null) con.close();
		}
        
        return adminManage;
	}
	
	/**
	 * 계정 등록 <BR>
	 * @param adminManage
	 * @throws SQLException
	 */
	public void addAdminManage(AdminManage adminManage) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		StringBuffer qr =new StringBuffer()
		.append(" INSERT INTO ADMIN_MANAGE \n")
		.append(" (ADMIN_MANAGE.ADMIN_ID,  \n")
		.append(" ADMIN_MANAGE.SCHOOL_ID,  \n")
		.append(" ADMIN_MANAGE.ADMIN_PASS, \n")
		.append(" ADMIN_MANAGE.ADMIN_INFO) \n")
		.append(" VALUES (?,?,?,?)");
		
		String queryStr = qr.toString();
		
		try{
			con = pool.getConnection();
			pstmt = con.prepareStatement(queryStr);
			
			int j = 0;                                                         
	        
			pstmt.setString(++j, adminManage.getAdmin_id());
			pstmt.setString(++j, adminManage.getSchool_id());
			pstmt.setString(++j, adminManage.getAdmin_pass());
			pstmt.setString(++j, adminManage.getAdmin_info());
			                                                                   
			pstmt.executeUpdate(); 
			
		}catch(SQLException se){
			System.out.println(se);
		}finally {
			if(pstmt!=null) pstmt.close();
			if(con != null) con.close();
		}
	}
	
	/**
	 * 계정 정보 수정<BR>
	 * @param adminManage
	 * @throws SQLException
	 */
	public void setAdminManage (AdminManage adminManage) throws SQLException{
		Connection con = null;
        PreparedStatement pstmt = null;

		StringBuffer qr0 =new StringBuffer()
			.append(" UPDATE ADMIN_MANAGE SET    \n")
			//.append(   " ADMIN_MANAGE.ADMIN_ID    = ?, \n")
			.append(   " ADMIN_MANAGE.ADMIN_PASS  = ?, \n")
			.append(   " ADMIN_MANAGE.ADMIN_INFO  = ?  \n")
			.append(" WHERE ADMIN_MANAGE.ADMIN_ID = ? ");
			//.append(" WHERE ADMIN_MANAGE.SCHOOL_ID = ? ");
		String queryStr = qr0.toString();

        try {
			con = pool.getConnection();
			pstmt = con.prepareStatement(queryStr);

			int i = 0;

			//pstmt.setString (++i, adminManage.getAdmin_id()   );
			pstmt.setString (++i, adminManage.getAdmin_pass() );
			pstmt.setString (++i, adminManage.getAdmin_info() );
			//pstmt.setString (++i, adminManage.getSchool_id()  );
			pstmt.setString (++i, adminManage.getAdmin_id()   );

			int resultCount = pstmt.executeUpdate();
			
		}catch(SQLException se) {
			System.out.println(se);
		}finally {
			if(pstmt!=null) pstmt.close();
			if(con != null) con.close();
		}
	}
	
	/**
	 * 계정 정보 수정 <BR>
	 * 아이디가 같을경우 <BR>
	 * ※ 현재 admin_id는 고유의 컬럼이므로 아이디를 수정하지않는 경우에는 admin_id는 제외.<BR>
	 * @param adminManage
	 * @throws SQLException
	 */
	public void setAdminManageSameId (AdminManage adminManage) throws SQLException{
		Connection con = null;
        PreparedStatement pstmt = null;

		StringBuffer qr0 =new StringBuffer()
			.append(" UPDATE ADMIN_MANAGE SET    \n")
			.append(   " ADMIN_MANAGE.ADMIN_PASS  = ?, \n")
			.append(   " ADMIN_MANAGE.ADMIN_INFO  = ?  \n")
			.append(" WHERE ADMIN_MANAGE.SCHOOL_ID = ? ");
		String queryStr = qr0.toString();
		
        try {
			con = pool.getConnection();
			pstmt = con.prepareStatement(queryStr);

			int i = 0;

			pstmt.setString (++i, adminManage.getAdmin_pass() );
			pstmt.setString (++i, adminManage.getAdmin_info() );
			pstmt.setString (++i, adminManage.getSchool_id()  );
			
			int resultCount = pstmt.executeUpdate();
			
		}catch(SQLException se) {
			System.out.println(se);
		}finally {
			if(pstmt!=null) pstmt.close();
			if(con != null) con.close();
		}
	}
}
