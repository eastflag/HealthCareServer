package com.healthcare.admin.excel;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.healthcare.admin.school.School;
import com.healthcare.common.AES256Util;
import com.healthcare.common.DBConnectionManager;

public class ExcelDAO {
	
	private DBConnectionManager pool = null;
	private AES256Util aes = new AES256Util();

	
	public ExcelDAO() {
		try {
			pool = DBConnectionManager.getInstance();
		} catch (Exception e) {
			System.out.println("Error : ExcelDAO");
		}
	}

	public boolean insertDB(String[] arr) throws SQLException, InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean isSuccess = false;

		System.out.println(arr[0]+"|"+arr[1]+"|"+arr[2]+"|"+arr[3]+"|"+arr[4]+"|"+arr[5]+"|"+arr[6]+"|"+arr[7]+"|"+arr[8]+"|"+arr[9]+"|"+arr[10]+"|"+arr[11]);
		arr[5] = aes.encode(arr[5]); // 이름
		if(!arr[8].equals("") && arr[8] != null) {
			arr[8] = aes.encode(arr[8]); // 보호자 휴대폰번호
		}
		if(!arr[9].equals("") && arr[9] != null) {
			arr[9] = aes.encode(arr[9]); // 학생 휴대폰번호
		}
		
        StringBuffer qr =new StringBuffer()
        .append(" INSERT INTO temp_student_info(  \n")
        .append("    Student_ID                   \n")
        .append("   ,STU_Name                     \n")
        .append("   ,STU_Sex                      \n")
        .append("   ,STU_Birth                    \n")
        .append(" ) VALUES (?,?,?,?)              \n");
		String queryStr = qr.toString();
		
		try {
			con = pool.getConnection();
			pstmt = con.prepareStatement(queryStr);
			
			int j = 0;        

			pstmt.setString(++j, arr[1]);
			pstmt.setString(++j, arr[5]);
			pstmt.setString(++j, arr[6]);
			pstmt.setString(++j, arr[7]);
            
			int result = pstmt.executeUpdate();
			
			if(result>0) { // temp_student_info insert 성공 시 
				insertSchoolRegister(arr);
				
				if(!arr[8].equals("") && arr[8]!=null) {
					insertGuardianInfo(arr[1], arr[8]);
				}
				
				if(!arr[9].equals("") && arr[9]!=null) {
					insertGuardianInfo(arr[1], arr[9]);
				}
				isSuccess = true;
			}

        }catch(SQLException se) {
			System.out.println("*** insertDB "+se);
			isSuccess = false;
		}finally {
			if(pstmt!=null) pstmt.close();
			if(con != null) con.close();
		}
		return isSuccess;
	}

	public void insertSchoolRegister(String[] arr)throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		
        StringBuffer qr =new StringBuffer()
        .append(" INSERT INTO temp_school_register( \n")
        .append("    School_Year                    \n")
        .append("   ,Student_ID                     \n")
        .append("   ,School_ID                      \n")
        .append("   ,Class                          \n")
        .append("   ,Mod_Date                       \n")
        .append("   ,Status                         \n")
        .append("   ,Grade_ID                       \n")
        .append(" ) VALUES (?,?,?,?,?,?,?)          \n");
		String queryStr = qr.toString();
		
		try {
			con = pool.getConnection();
			pstmt = con.prepareStatement(queryStr);
			
			int j = 0;        

			pstmt.setString(++j, arr[0]);
			pstmt.setString(++j, arr[1]);
			pstmt.setString(++j, arr[2]);
			pstmt.setString(++j, arr[4]);
			pstmt.setString(++j, arr[11]);
			pstmt.setString(++j, arr[10]);
			pstmt.setString(++j, arr[3]);
            
			pstmt.executeUpdate(); 

        }catch(SQLException se) {
			System.out.println("*** insertSchoolRegister "+se);
		}finally {
			if(pstmt!=null) pstmt.close();
			if(con != null) con.close();
		}
	}
	
	private void insertGuardianInfo(String student_id, String mdn) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtcnt = null;
        ResultSet rs = null;

        StringBuffer qr =new StringBuffer()
        .append(" INSERT INTO temp_guardian_info( \n")
        .append("    MDN                    	  \n")
        .append(" ) VALUES (?)          		  \n");
		String queryStr = qr.toString();
		
        StringBuffer qrcnt =new StringBuffer()
        .append(" select count(*) cnt from temp_guardian_info \n")
        .append(" where mdn = ?                   	  \n");
		String queryCountStr = qrcnt.toString();
		
		try {
			con = pool.getConnection();
			pstmt = con.prepareStatement(queryStr);
			pstmtcnt = con.prepareStatement(queryCountStr);
			
			int result = 0;
			int totCnt = 0;
			
			pstmtcnt.setString(1, mdn);

        	rs = pstmtcnt.executeQuery();
        	
        	if (rs.next()) {
        		totCnt = rs.getInt("cnt");
        	}
        	
        	if(totCnt==0) {
				pstmt.setString(1, mdn);
				result = pstmt.executeUpdate(); 
        	} else {
        		result = 1;
        	}
        	
        	if(result>0) {
    			insertStudentGuardianMapping(student_id, mdn);     
        	}      

        }catch(SQLException se) {
			System.out.println("*** insertGuardianInfo "+se);
		}finally {
			if(pstmt!=null) pstmt.close();
			if(con != null) con.close();
			if(pstmtcnt!=null) pstmt.close();
			if(rs!=null) rs.close();
		}
	}

	private void insertStudentGuardianMapping(String student_id, String mdn) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtcnt = null;
        ResultSet rs = null;
		
        StringBuffer qr =new StringBuffer()
        .append(" INSERT INTO temp_student_guardian_mapping( \n")
        .append("    Student_ID                     \n")
        .append("    ,MDN                    	  \n")
        .append(" ) VALUES (?, ?)          		  \n");
		String queryStr = qr.toString();
		
        StringBuffer qrcnt =new StringBuffer()
        .append(" select count(*) cnt from temp_student_guardian_mapping \n")
        .append(" where student_id = ?   and  mdn = ?               	  \n");
		String queryCountStr = qrcnt.toString();
		
		try {
			con = pool.getConnection();
			pstmt = con.prepareStatement(queryStr);
			pstmtcnt = con.prepareStatement(queryCountStr);

			int totCnt = 0;    

			pstmtcnt.setString(1, student_id);
			pstmtcnt.setString(2, mdn);
        	rs = pstmtcnt.executeQuery();
        	
        	if (rs.next()) {
        		totCnt = rs.getInt("cnt");
        	}    
        	
        	if(totCnt==0) {
    			pstmt.setString(1, student_id);
    			pstmt.setString(2, mdn);
                
    			pstmt.executeUpdate(); 
        	}


        }catch(SQLException se) {
			System.out.println("*** insertStudentGuardianMapping "+se);
		}finally {
			if(pstmt!=null) pstmt.close();
			if(con != null) con.close();
			if(pstmtcnt!=null) pstmt.close();
			if(rs!=null) rs.close();
		}
	}

	public boolean encodeSchoolName() throws SQLException {

		boolean isSuccess = true;
		Connection con = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmtUpdate = null;
        ResultSet rs = null;
        AES256Util aes = new AES256Util();

        StringBuffer qr =new StringBuffer()
			.append(" SELECT school_id, school_name ")
			.append("   FROM SCHOOL_INFO \n")
			.append("   where school_name not like '%==' \n")
			.append(" ORDER BY SCHOOL_ID asc ");
        String queryStr = qr.toString();

		StringBuffer qr0 =new StringBuffer()
			.append(" UPDATE SCHOOL_INFO SET \n")
			.append(   " SCHOOL_NAME     = ? \n")
			.append(" WHERE SCHOOL_ID = ? ");
		String queryUpdateStr = qr0.toString();

    	try {
        	con = pool.getConnection();
    		pstmt = con.prepareStatement(queryStr);
    		pstmtUpdate = con.prepareStatement(queryUpdateStr);
    		
			rs = pstmt.executeQuery();

	    	String school_id = "";
	    	String school_name = "";
	    	while (rs.next())
	    	{
	    		school_id = rs.getString("SCHOOL_ID");
	    		school_name = rs.getString("school_name");
	    		
				school_name = aes.encode(school_name);
	    		System.out.println("school_id:"+school_id+" school_name:"+school_name);

				int i = 0;

				pstmtUpdate.setString (++i, school_name);
				pstmtUpdate.setString (++i, school_id);

				int resultCount = pstmtUpdate.executeUpdate();
				if(isSuccess==true && resultCount==0) {
					isSuccess = false;
				}
	    		
	    	}
	    	
		} catch (SQLException | InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) pstmt.close();
			if(pstmtUpdate!=null) pstmt.close();
			if(con != null) con.close();
		}
    	return isSuccess;
	}

	public boolean encodeStuName() throws SQLException {

		boolean isSuccess = true;
		Connection con = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmtUpdate = null;
        ResultSet rs = null;
        AES256Util aes = new AES256Util();

        StringBuffer qr =new StringBuffer()
			.append(" SELECT student_id, stu_name ")
			.append("   FROM student_info \n")
			.append("   where stu_name not like '%==' \n")
			.append(" ORDER BY student_id asc ");
        String queryStr = qr.toString();

		StringBuffer qr0 =new StringBuffer()
			.append(" UPDATE student_info SET \n")
			.append(   " stu_name     = ? \n")
			.append(" WHERE student_id = ? ");
		String queryUpdateStr = qr0.toString();

    	try {
        	con = pool.getConnection();
    		pstmt = con.prepareStatement(queryStr);
    		pstmtUpdate = con.prepareStatement(queryUpdateStr);
    		
			rs = pstmt.executeQuery();

	    	String student_id = "";
	    	String stu_name = "";
	    	while (rs.next())
	    	{
	    		student_id = rs.getString("student_id");
	    		stu_name = rs.getString("stu_name");
	    		System.out.println("student_id:"+student_id+" stu_name:"+stu_name);
	    		
	    		stu_name = aes.encode(stu_name);

				int i = 0;

				pstmtUpdate.setString (++i, stu_name);
				pstmtUpdate.setString (++i, student_id);

				int resultCount = pstmtUpdate.executeUpdate();
				if(isSuccess==true && resultCount==0) {
					isSuccess = false;
				}
	    		
	    	}
	    	
		} catch (SQLException | InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) pstmt.close();
			if(pstmtUpdate!=null) pstmt.close();
			if(con != null) con.close();
		}
    	return isSuccess;
	}

	public boolean encodeMdn() throws SQLException {

		boolean isSuccess = true;
		Connection con = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmtUpdate = null;
        ResultSet rs = null;
        AES256Util aes = new AES256Util();

        StringBuffer qr =new StringBuffer()
			.append(" SELECT mdn ")
			.append("   FROM guardian_info \n")
			.append("   where mdn not like '%==' \n")
			.append(" ORDER BY mdn asc ");
        String queryStr = qr.toString();

		StringBuffer qr0 =new StringBuffer()
			.append(" UPDATE guardian_info SET \n")
			.append(   " mdn     = ? \n")
			.append(" WHERE mdn = ? ");
		String queryUpdateStr = qr0.toString();

    	try {
        	con = pool.getConnection();
    		pstmt = con.prepareStatement(queryStr);
    		pstmtUpdate = con.prepareStatement(queryUpdateStr);
    		
			rs = pstmt.executeQuery();

	    	String mdn = "";
	    	String mdnEncode = "";
	    	while (rs.next())
	    	{
	    		mdn = rs.getString("mdn");
	    		mdnEncode = aes.encode(mdn);
	    		
	    		System.out.println("mdn:"+mdn+" mdnEncode:"+mdnEncode);

				int i = 0;

				pstmtUpdate.setString (++i, mdnEncode);
				pstmtUpdate.setString (++i, mdn);

				int resultCount = pstmtUpdate.executeUpdate();
				if(isSuccess==true && resultCount==0) {
					isSuccess = false;
				}
	    		
	    	}
	    	
		} catch (SQLException | InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) pstmt.close();
			if(pstmtUpdate!=null) pstmt.close();
			if(con != null) con.close();
		}
    	return isSuccess;
	}
	
	
	public boolean decodeMdn() throws SQLException {

		boolean isSuccess = true;
		Connection con = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmtUpdate = null;
        ResultSet rs = null;
        AES256Util aes = new AES256Util();

        StringBuffer qr =new StringBuffer()
			.append(" SELECT mdn ")
			.append("   FROM temp_guardian_info_1506162328 \n")
			.append("   where Mdn_RegId is not null and   trim(mdn_regid) !='' \n")
			.append(" ORDER BY mdn asc ");
        String queryStr = qr.toString();

		StringBuffer qr0 =new StringBuffer()
			.append(" UPDATE temp_guardian_info_1506162328 SET \n")
			.append(   " Decode_MDN     = ? \n")
			.append(" WHERE mdn = ?  \n")
			.append("  AND      Mdn_RegId is not null AND   trim(mdn_regid) !=''");
		String queryUpdateStr = qr0.toString();

    	try {
        	con = pool.getConnection();
    		pstmt = con.prepareStatement(queryStr);
    		pstmtUpdate = con.prepareStatement(queryUpdateStr);
    		
			rs = pstmt.executeQuery();

	    	String mdn = "";
	    	String mdnDecode = "";
	    	while (rs.next())
	    	{
	    		mdn = rs.getString("mdn");
	    		mdnDecode = aes.decode(mdn);
	    		
	    		System.out.println("mdn:"+mdn+" mdnDecode:"+mdnDecode);

				int i = 0;

				pstmtUpdate.setString (++i, mdnDecode);
				pstmtUpdate.setString (++i, mdn);

				int resultCount = pstmtUpdate.executeUpdate();
				if(isSuccess==true && resultCount==0) {
					isSuccess = false;
				}
	    		
	    	}
	    	
		} catch (SQLException | InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) pstmt.close();
			if(pstmtUpdate!=null) pstmt.close();
			if(con != null) con.close();
		}
    	return isSuccess;
	}
}
