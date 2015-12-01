package com.healthcare.admin.excel;

import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.healthcare.common.AES256Util;




public class ExcelMgr {

	public boolean readExcelXLS(String excel_url) {
		boolean isSuccess = false;
		System.out.println("excel_url:"+excel_url);
		
		try{
			ExcelDAO excelDAO = new ExcelDAO();
			//String key = "mtelo2014aurahealthcare123456789";
			
			if(!excel_url.equals("") && excel_url != null){
				POIFSFileSystem fileSystem = new POIFSFileSystem(new FileInputStream(new File(excel_url)));
				
				HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
				
				
				// 시트수 (첫번째 시트만 읽어 오므로 0)
				HSSFSheet sheet = workbook.getSheetAt(0);
				
				// 행의 수
				int rows = sheet.getPhysicalNumberOfRows();
				System.out.println("행 "+rows+"개");
				
				for(int rowindex=1; rowindex<rows; rowindex++) {
					// 행을 읽는다
					HSSFRow row = sheet.getRow(rowindex);
					if(row!=null) {
						//System.out.println(rowindex+"row 시작");
						// 셀의 수
						int cells = row.getPhysicalNumberOfCells();
						String[] arr = new String[cells];
						Boolean error = false;
						
						for(int columnindex = 0; columnindex<cells; columnindex++) {
							HSSFCell cell = row.getCell(columnindex);
							String value = "";
							String type = "";
							
							if(cell==null) {
								continue;
							} else {
								switch (cell.getCellType()){
									case HSSFCell.CELL_TYPE_FORMULA:	
										type = "CELL_TYPE_FORMULA";
										value=cell.getCellFormula();
										break;
									case HSSFCell.CELL_TYPE_NUMERIC:
										type = "CELL_TYPE_NUMERIC";
										 if (DateUtil.isCellDateFormatted(cell)) {
											 SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
											 value = date.format(cell.getDateCellValue());
										 } else {
												cell.setCellType(Cell.CELL_TYPE_STRING);
												value=cell.getStringCellValue()+"";
										 }
										break;
									case HSSFCell.CELL_TYPE_STRING:
										type = "CELL_TYPE_STRING";
										value=cell.getStringCellValue()+"";
										break;
									case HSSFCell.CELL_TYPE_BLANK:
										type = "CELL_TYPE_BLANK";
										//value=cell.getBooleanCellValue()+"";
										value="";
										break;
									case HSSFCell.CELL_TYPE_ERROR:
										type = "CELL_TYPE_ERROR";
										error = true;
										value=cell.getErrorCellValue()+"";
										break;
								}																
								arr[columnindex] = value;
								//System.out.println(type + rowindex+"row "+columnindex+"cell 내용: "+value+ " 암호화: "+aes256.encode(value) +" 복호화: "+aes256.decode(aes256.encode(value)));
							}
						}
						
						if(!error) {
							
							isSuccess = excelDAO.insertDB(arr);
							
						} else {
							isSuccess = false;
							System.out.println("***Error***");
						}
						//System.out.println(rowindex+"row 끝");
					}
				}
				
			}
			

			
		}catch(Exception se) {
			System.out.println(se.getMessage());
		}
		System.out.println("isSuccess "+isSuccess);
		return isSuccess;
	}

	public boolean readExcelXLSX(String excel_url) {
		boolean isSuccess = false;
		System.out.println("excel_url:"+excel_url);
		
		try{
			ExcelDAO excelDAO = new ExcelDAO();
			//String key = "mtelo2014aurahealthcare123456789";
			
			if(!excel_url.equals("") && excel_url != null){
				FileInputStream fis = new FileInputStream(new File(excel_url));
				
				XSSFWorkbook workbook = new XSSFWorkbook(fis);
				
				
				// 시트수 (첫번째 시트만 읽어 오므로 0)
				XSSFSheet sheet = workbook.getSheetAt(0);
				
				// 행의 수
				int rows = sheet.getPhysicalNumberOfRows();
				System.out.println("행 "+rows+"개");
				
				for(int rowindex=1; rowindex<rows; rowindex++) {
					// 행을 읽는다
					XSSFRow row = sheet.getRow(rowindex);
					if(row!=null) {
						//System.out.println(rowindex+"row 시작");
						// 셀의 수
						int cells = row.getPhysicalNumberOfCells();
						String[] arr = new String[cells];
						Boolean error = false;
						
						for(int columnindex = 0; columnindex<cells; columnindex++) {
							XSSFCell cell = row.getCell(columnindex);
							String value = "";
							String type = "";
							
							if(cell==null) {
								continue;
							} else {
								switch (cell.getCellType()){
									case HSSFCell.CELL_TYPE_FORMULA:	
										type = "CELL_TYPE_FORMULA";
										value=cell.getCellFormula();
										break;
									case HSSFCell.CELL_TYPE_NUMERIC:
										type = "CELL_TYPE_NUMERIC";
										 if (DateUtil.isCellDateFormatted(cell)) {
											 SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
											 value = date.format(cell.getDateCellValue());
										 } else {
												cell.setCellType(Cell.CELL_TYPE_STRING);
												value=cell.getStringCellValue()+"";
										 }
										break;
									case HSSFCell.CELL_TYPE_STRING:
										type = "CELL_TYPE_STRING";
										value=cell.getStringCellValue()+"";
										break;
									case HSSFCell.CELL_TYPE_BLANK:
										type = "CELL_TYPE_BLANK";
										//value=cell.getBooleanCellValue()+"";
										value="";
										break;
									case HSSFCell.CELL_TYPE_ERROR:
										type = "CELL_TYPE_ERROR";
										error = true;
										value=cell.getErrorCellValue()+"";
										break;
								}																
								arr[columnindex] = value;
								//System.out.println(type + rowindex+"row "+columnindex+"cell 내용: "+value+ " 암호화: "+aes256.encode(value) +" 복호화: "+aes256.decode(aes256.encode(value)));
							}
						}
						
						if(!error) {
							
							isSuccess = excelDAO.insertDB(arr);
							
						} else {
							isSuccess = false;
							System.out.println("***Error***");
						}
						//System.out.println(rowindex+"row 끝");
					}
				}
				
			}
			

			
		}catch(Exception se) {
			System.out.println(se.getMessage());
		}
		System.out.println("isSuccess "+isSuccess);
		return isSuccess;
	}

	public boolean encodeSchoolName() throws SQLException {
		ExcelDAO excelDAO = new ExcelDAO();
		boolean isSuccess = true;
		isSuccess = excelDAO.encodeSchoolName();
		return isSuccess;
	}

	public boolean encodeStuName() throws SQLException {
		ExcelDAO excelDAO = new ExcelDAO();
		boolean isSuccess = true;
		isSuccess = excelDAO.encodeStuName();
		return isSuccess;
	}

	public boolean encodeMdn() throws SQLException {
		ExcelDAO excelDAO = new ExcelDAO();
		boolean isSuccess = true;
		isSuccess = excelDAO.encodeMdn();
		return isSuccess;
	}
	
	public boolean decodeMdn() throws SQLException{
		ExcelDAO excelDAO = new ExcelDAO();
		boolean isSuccess = true;
		isSuccess = excelDAO.decodeMdn();
		return isSuccess;
	}

}
