package com.healthcare.admin.school;

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
import java.util.Collections;
import java.util.Comparator;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.healthcare.common.AES256Util;
import com.healthcare.common.DBConnectionManager;

public class SchoolDAO {

	private DBConnectionManager pool = null;
	private AES256Util aes = new AES256Util();
	
	public SchoolDAO(){
		try{
			pool = DBConnectionManager.getInstance();
		}catch(Exception e){
			System.out.println("Error : DBConnectionManager !!");
		}
	}
	
	/**
	 * Select Option 용
	 * @return
	 * @throws SQLException
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws InvalidAlgorithmParameterException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws UnsupportedEncodingException 
	 * @throws InvalidKeyException 
	 */
	public Collection getListSchool()throws SQLException, InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        School school = null;
        String joinqu = "";
        
        StringBuffer qr =new StringBuffer()
			.append(" SELECT ")
			.append(      "SCHOOL_ID, \n")
			.append(      "SCHOOL_NAME, \n")
			.append(      "SECTION, \n")
			.append(      "ADDRESS \n")
			.append("   FROM SCHOOL_INFO \n")
	        //.append(" WHERE SCHOOL_ID NOT IN ( '2', '5', '6')   \n")
			//.append(" WHERE SCHOOL_ID NOT IN ( '1','2', '5', '6', '19' )   \n")
			.append(" ORDER BY CONVERT(SCHOOL_ID, UNSIGNED) DESC ");
		String queryStr = qr.toString();
	    ArrayList schools = new ArrayList();
	    
	    try {
       		con = pool.getConnection();
        	pstmt = con.prepareStatement(queryStr);

        	rs = pstmt.executeQuery();

    		// startIndex 파라메타 수만큼 Skip한다.
			//while (startIndex-- > 0 && rs.next());
        	//while (count-- > 0 && rs.next())
        	while (rs.next())
        	{
        		school = new School();
				
        		school.setSchool_id(rs.getString("SCHOOL_ID"));
        		school.setSchool_name(aes.decode(rs.getString("SCHOOL_NAME")));
        		school.setSection(rs.getString("SECTION"));
        		school.setAddress(rs.getString("ADDRESS"));
        		

        		schools.add(school);
            }
        }catch(SQLException se) {
			System.out.println(se);
		}finally {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con != null) con.close();
		}
        
        return schools;
	}
	

    int getMxNo() throws SQLException {
		int mxno = 1;
		Connection con = null;
		PreparedStatement stmt =null;
		ResultSet rs = null;
		try {
				con = pool.getConnection();
	       		String sql = "SELECT COALESCE(MAX(currval), 0) + 1 FROM  SEQUENCES WHERE NAME = 'School_seq' ";
	       		stmt = con.prepareStatement(sql);
	       		rs = stmt.executeQuery();
	       		if( rs.next() )	
	       		mxno = rs.getInt(1);
	       		
   		}catch(SQLException se) {
			System.out.println(se);
        }finally {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			if(con != null) con.close();
		}	 		
		return mxno;
    }
	
	/**
	 * 학교 정보 등록
	 * @param school
	 * @throws SQLException
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws InvalidAlgorithmParameterException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws UnsupportedEncodingException 
	 * @throws InvalidKeyException 
	 */
	public void addSchool(School school) throws SQLException, InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		
		int tmpNo = getMxNo();
		
		StringBuffer qr =new StringBuffer()
		.append(" INSERT INTO SCHOOL_INFO \n")
		.append(" 	(SCHOOL_ID, SCHOOL_NAME, SECTION, ADDRESS,SIDO, SCHOOL_CODE) \n")
		.append(" VALUES (?,?,?,?,?,?)");
		
		String queryStr = qr.toString();
		
		StringBuffer qrSeq =new StringBuffer()
		.append(" UPDATE SEQUENCES SET CURRVAL = " + tmpNo)
		.append("  WHERE NAME = 'School_seq' ");
		
		String qrSeqStr = qrSeq.toString();
		
		try{
			con = pool.getConnection();
			pstmt = con.prepareStatement(queryStr);
			pstmt2 = con.prepareStatement(qrSeqStr);
			
			int j = 0;
			
			pstmt.setString(++j, Integer.toString(tmpNo));
			pstmt.setString(++j, aes.encode(school.getSchool_name()));
			pstmt.setString(++j, school.getSection());
			pstmt.setString(++j, school.getAddress());
			pstmt.setString(++j, school.getSido());
			pstmt.setString(++j, school.getSchool_code());
			
			int result = pstmt.executeUpdate(); 
			
			if (result > 0) {
				pstmt2.executeUpdate(); 
			}
			
		}catch(SQLException se){
			System.out.println(se);
		}finally {
			if(pstmt!=null) pstmt.close();
			if(con != null) con.close();
		}
	}

	/**
	 * 학교 목록 검색
	 * @param school_name
	 * @param section
	 * @param startIndex
	 * @param count
	 * @return
	 * @throws SQLException
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws InvalidAlgorithmParameterException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws UnsupportedEncodingException 
	 * @throws InvalidKeyException 
	 */
	@SuppressWarnings("unchecked")
	public Collection getSchools (String school_name, String section, int startIndex, int count) throws SQLException, InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		
		Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String joinqu = "";

        if (school_name != null && !("".equals(school_name))) {
        	//school_name = aes.encode(school_name);
			//joinqu += " AND SCHOOL_NAME LIKE '%" + school_name + "%' \n";
			//joinqu += " AND SCHOOL_NAME = '" + school_name + "' \n";
        	System.out.println(" :: 학교 검색 쿼리 111 ::: " + joinqu);
        	
        	System.out.println(" :: 학교 검색어  111 ::: " + school_name);
        	joinqu += " AND "+aes.decode(" SCHOOL_NAME ")+" LIKE '%" + school_name + "%' \n";
        	System.out.println(" :: 학교 검색 쿼리 123 ::: " + joinqu);
		
		}
        if (section != null && !("".equals(section))) {
			joinqu += " AND SECTION = '" + section + "' \n";
		}

        StringBuffer qr =new StringBuffer()
			.append(" SELECT * ")
			.append("   FROM SCHOOL_INFO \n")
			.append("  WHERE 1=1 \n")
	       // .append("   AND SCHOOL_ID NOT IN ( '2', '5', '6')     \n")
			// .append("   AND SCHOOL_ID NOT IN ( '1','2', '5', '6','19')     \n")
			.append(joinqu)
			.append(" ");
        String queryStr = qr.toString();
        
    	System.out.println(" :: 학교 검색 쿼리 ::: " + queryStr);

        ArrayList schools = new ArrayList();
        ArrayList schools_result = new ArrayList();

        School school = null;
        
        try {
        	
        	con = pool.getConnection();
        	pstmt = con.prepareStatement(queryStr);

        	rs = pstmt.executeQuery();

    		// startIndex 파라메타 수만큼 Skip한다.
			//while (startIndex-- > 0 && rs.next());
        	//while (count-- > 0 && rs.next())
            	while (rs.next())
        	{
        		school = new School();

    			school.setSchool_id       (rs.getString("SCHOOL_ID"));
    			school.setSchool_name     (aes.decode(rs.getString("SCHOOL_NAME")));
    			school.setSection         (rs.getString("SECTION"));
    			school.setAddress         (rs.getString("address"));
        		
				schools.add(school);
        	}
	        	final int size = schools.size();
    	        
		        Collections.sort(schools, new Comparator<School>() {
	
					@Override
					public int compare(School arg0, School arg1) {
						
						int ret = 0;
						
						for(int i=0; i<size; i++) {

							ret = arg0.getSchool_name().compareTo(arg1.getSchool_name());
						}
						
						return ret;
					}
				});
		        
		        int fori = startIndex+count;
		        if(size < startIndex+count ){
		        	fori = size;
		        }
		        for(int i=startIndex; i<fori; i++) {
		        	schools_result.add(schools.get(i));    
		        }
		        schools = schools_result;
        	
        }catch(SQLException se) {
			System.out.println(se);
		}finally {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con != null) con.close();
		}
        return schools;
	}
	
	
	

	public int getSchoolsTotRowCnt (String school_name, String section) throws SQLException, InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		
		Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String joinqu = "";

        if (school_name != null && !("".equals(school_name))) {
        	school_name = aes.encode(school_name);
			//joinqu += " AND SCHOOL_NAME LIKE '%" + school_name + "%' \n";
			joinqu += " AND SCHOOL_NAME = '" + school_name + "' \n";
		}
        if (section != null && !("".equals(section))) {
			joinqu += " AND SECTION = '" + section + "' \n";
		}

        StringBuffer qr =new StringBuffer()
			.append(" SELECT COUNT(*) AS cnt ")
			.append("   FROM SCHOOL_INFO \n")
			.append("  WHERE 1=1 \n")
	       // .append("   AND SCHOOL_ID NOT IN ( '2', '5', '6')     \n")
			// .append("   AND SCHOOL_ID NOT IN ( '1','2', '5', '6','19')     \n")
			.append(joinqu);
        String queryStr = qr.toString();
        
        int totRowCnt = 0;
        
        try {
        	
        	con = pool.getConnection();
        	pstmt = con.prepareStatement(queryStr);

        	rs = pstmt.executeQuery();
        	
        	if (rs.next()) {
        		totRowCnt = rs.getInt("cnt");
        	}
        	
        }catch(SQLException se) {
			System.out.println(se);
		}finally {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con != null) con.close();
		}
        return totRowCnt;
	}
	
	/**
	 * 학교 정보 조회
	 * @param school_id
	 * @return
	 * @throws SQLException
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws InvalidAlgorithmParameterException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws UnsupportedEncodingException 
	 * @throws InvalidKeyException 
	 */
	public School getSchool (String school_id) throws SQLException, InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		
		Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        School school = new School();
                
        StringBuffer qr =new StringBuffer()
			.append(" SELECT SCHOOL_ID, SCHOOL_NAME, SECTION, ADDRESS, IFNULL(SIDO,'') SIDO, IFNULL(SCHOOL_CODE,'') SCHOOL_CODE ")
			.append("   FROM SCHOOL_INFO \n")
			.append("WHERE SCHOOL_ID = '"+school_id+"'");
		String queryStr = qr.toString();
	    
	    try {
       		con = pool.getConnection();
        	pstmt = con.prepareStatement(queryStr);

        	rs = pstmt.executeQuery();

        	if( rs.next() ) {
    			school.setSchool_id       (rs.getString("SCHOOL_ID"));
    			school.setSchool_name     (aes.decode(rs.getString("SCHOOL_NAME")));
    			school.setSection         (rs.getString("SECTION"));
    			school.setAddress         (rs.getString("ADDRESS"));
    			school.setSido         (rs.getString("SIDO"));
    			school.setSchool_code         (rs.getString("SCHOOL_CODE"));
            }
        }catch(SQLException se) {
			System.out.println(se);
		}finally {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con != null) con.close();
		}
        
        return school;
	}
	
	/**
	 * 학교 정보 수정
	 * @param school
	 * @throws SQLException
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws InvalidAlgorithmParameterException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws UnsupportedEncodingException 
	 * @throws InvalidKeyException 
	 */
	public void setSchool (School school) throws SQLException, InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		Connection con = null;
        PreparedStatement pstmt = null;

		StringBuffer qr0 =new StringBuffer()
			.append(" UPDATE SCHOOL_INFO SET \n")
			.append(   " SCHOOL_NAME     = ?, \n")
			.append(   " SECTION         = ?,  \n")
			.append(   " ADDRESS         = ?,  \n")
			.append(   " SIDO         	= ?,  \n")
			.append(   " SCHOOL_CODE	= ?  \n")
			.append(" WHERE SCHOOL_ID = ? ");
		String queryStr = qr0.toString();

        try {
			con = pool.getConnection();
			pstmt = con.prepareStatement(queryStr);

			int i = 0;

			pstmt.setString (++i, aes.encode(school.getSchool_name    ()));
			pstmt.setString (++i, school.getSection        ());
			pstmt.setString (++i, school.getAddress()  );
			pstmt.setString (++i, school.getSido());
			pstmt.setString (++i, school.getSchool_code());
			pstmt.setString (++i, school.getSchool_id  ());

			int resultCount = pstmt.executeUpdate();
			
		}catch(SQLException se) {
			System.out.println(se);
		}finally {
			if(pstmt!=null) pstmt.close();
			if(con != null) con.close();
		}
	}
	
	/**
	 * 관리자 계정이 없는 학교 목록 
	 * @throws SQLException
	 * @return schools
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws InvalidAlgorithmParameterException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws UnsupportedEncodingException 
	 * @throws InvalidKeyException 
	 */
	public Collection getNotExistAdminSchoolList()throws SQLException, InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        School school = null;
        String joinqu = "";
        
        StringBuffer qr =new StringBuffer()
	        .append(" SELECT")
			.append("   AA.SCHOOL_ID, \n")
			.append("   AA.SCHOOL_NAME \n")
			.append(" FROM SCHOOL_INFO AA \n")
			.append(" LEFT OUTER JOIN \n")
			.append("   ADMIN_MANAGE AS BB \n")
			.append(" ON AA.SCHOOL_ID = BB.SCHOOL_ID \n")
			.append(" WHERE BB.SCHOOL_ID IS NULL \n")
			.append(" ORDER BY CONVERT(AA.SCHOOL_ID , UNSIGNED)  ASC \n");
		String queryStr = qr.toString();
	    ArrayList schools = new ArrayList();
	    
	    try {
       		con = pool.getConnection();
        	pstmt = con.prepareStatement(queryStr);

        	rs = pstmt.executeQuery();

    		// startIndex 파라메타 수만큼 Skip한다.
			//while (startIndex-- > 0 && rs.next());
        	//while (count-- > 0 && rs.next())
        	while (rs.next())
        	{
        		school = new School();
				
        		school.setSchool_id(rs.getString("SCHOOL_ID"));
        		school.setSchool_name(aes.decode(rs.getString("SCHOOL_NAME")));

        		schools.add(school);
            }
        }catch(SQLException se) {
			System.out.println(se);
		}finally {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con != null) con.close();			
		}
        
        return schools;
	}
}
