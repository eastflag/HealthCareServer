package com.healthcare.admin.helper;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;

import com.healthcare.admin.excel.ExcelMgr;
import com.healthcare.common.AES256Util;
import com.healthcare.common.Config;
import com.healthcare.common.upload.http.Uploader;


public class ExcelMgrHelper {
	
	private ExcelMgr excelMgr = null;
	
	public ExcelMgrHelper()
    {
	    try{
			excelMgr = new ExcelMgr();
		}catch(Exception e){
        	System.out.println("ExcelMgrHelper : cont. error"+e.getMessage());
        }
	}
	
	public boolean excelfileUpload(HttpServletRequest req){
		
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

		boolean isSuccess = false;
		boolean isUpload = false;
		boolean isExcel = false;
		
		try {
			
			String upUrl = Config.SCHOOL_INFO_EXCEL_UPLOAD_DIR;
			String upAliasUrl = Config.SCHOOL_INFO_EXCEL_ALIAS_DIR;

			Uploader up = new Uploader(); //Upload라이브러리.
			
			up.setUploadDir(upUrl, 2);
			isUpload = up.doUpload(req, dateFormat.format(new java.util.Date()));

	    	String aliasFileName = up.getRenames();

			String excel_url = "";
			
			if(isUpload){
				//img_url = upAliasUrl+aliasFileName;
				excel_url = upUrl+aliasFileName;
				if(excel_url.endsWith(".xls")) {
					isExcel = excelMgr.readExcelXLS(excel_url);
				} else if(excel_url.endsWith(".xlsx")) {
					isExcel = excelMgr.readExcelXLSX(excel_url);
				}
				
			}
			isSuccess = isExcel;
			
		}catch(Exception e) {
        	System.out.println("excelfileUpload error"+e.getMessage());
		}
		
		return isSuccess;
	}
	public String aes256test(HttpServletRequest req) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		
		String aes_type = req.getParameter("aes_type");
		String text = req.getParameter("text");
		String result = "";
		AES256Util aes = new AES256Util();
		
		if(!text.equals("") && text!=null) {
			if(aes_type.equals("E")) {
				result = aes.encode(text);
			} else if(aes_type.equals("D")) {
				result = aes.decode(text);
			}
		}
		return result;
	}
	
	public boolean dataEncode(HttpServletRequest req) throws SQLException{
		
		boolean isSuccess = false;
		
		String encode_type = req.getParameter("encode_type");
		
		if(encode_type!=null && encode_type.equals("school_name")) {
			isSuccess = excelMgr.encodeSchoolName();
		} else if(encode_type!=null && encode_type.equals("stu_name")) {
			isSuccess = excelMgr.encodeStuName();
		} else if(encode_type!=null && encode_type.equals("mdn")) {
			isSuccess = excelMgr.encodeMdn();
		}
		
		return isSuccess;
	}
	
public boolean dataDecode(HttpServletRequest req) throws SQLException{
		
		boolean isSuccess = false;
		
		String decode_type = req.getParameter("decode_type");
//		
//		if(encode_type!=null && encode_type.equals("school_name")) {
//			isSuccess = excelMgr.encodeSchoolName();
//		} else if(encode_type!=null && encode_type.equals("stu_name")) {
//			isSuccess = excelMgr.encodeStuName();
//		} else
		if(decode_type!=null && decode_type.equals("mdn")) {
			isSuccess = excelMgr.decodeMdn();
		}
		
		return isSuccess;
	}
	
}
