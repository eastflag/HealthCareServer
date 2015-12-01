<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,
				java.io.*,
				org.apache.poi.xssf.usermodel.*,
				org.apache.poi.ss.util.CellRangeAddress,
				org.apache.poi.ss.usermodel.*,
				org.apache.poi.hssf.*,
				com.healthcare.common.Config,
				com.healthcare.common.SessionManager,
				com.healthcare.common.PageNavigator,
				com.healthcare.admin.board.Boards,
				com.healthcare.admin.board.Board,
				com.healthcare.common.JSPUtil,
				com.healthcare.admin.helper.BoardMgrHelper" %>
<%  
	request.setCharacterEncoding("UTF-8");

	BoardMgrHelper boardMgrHelper = new BoardMgrHelper();
	String locale = JSPUtil.getLocaleLanguage(request);
	
	int totRowCnt = Integer.parseInt(request.getParameter("totRowCnt"));
	
	Board board = null;
	
	Boards boards = boardMgrHelper.getBoardsExcel(request, "2", totRowCnt);
	
	
	Iterator it = boards.getBoards().iterator();
	
	//response.setHeader("Content-Disposition","attachment;filename=healthcareQuestion("+totRowCnt+").xls");
	//response.setHeader("Content-Description", "JSP Generated Data");
	
	//excel 생성 시작
	String sFileName = "AURA_healthcareQuestion("+totRowCnt+").xlsx";
	sFileName = new String ( sFileName.getBytes("UTF-8"), "UTF-8");
	out.clear();
	out = pageContext.pushBody();
	response.reset(); 	// 이 문장이 없으면 excel 등의 파일에서 한글이 깨지는 문제 발생.

	String strClient = request.getHeader("User-Agent");

	String fileName = sFileName;

	if (strClient.indexOf("MSIE 5.5") > -1) {
		//response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "filename=" + fileName + ";");
	} else {
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ";");
	}

	OutputStream fileOut = null;
	
	//워크북 생성
	XSSFWorkbook objWorkBook = new XSSFWorkbook();
	//워크시트 생성
	XSSFSheet objSheet = objWorkBook.createSheet();
	//시트 이름
	objWorkBook.setSheetName(0 , "AURA_Question("+totRowCnt+")");
	//행생성
	XSSFRow objRow = null;
	//셀 생성
	XSSFCell objCell = null;
	//가운데 정렬 cell 스타일 생성
	XSSFCellStyle objAlignStyle = objWorkBook.createCellStyle();
	objAlignStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
	//굵은 폰트와 백그라운드 색상 정의 객체 생성
	XSSFCellStyle objFontStyle = objWorkBook.createCellStyle();
	XSSFFont objFont = objWorkBook.createFont();
	objFont.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
	objFontStyle.setFont(objFont);
	//objFontStyle.setFillBackgroundColor(new XSSFColor(java.awt.Color.yellow));
	//objFontStyle.setFillPattern(XSSFCellStyle.ALT_BARS);
	objFontStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
	
	//엑셀 시트 헤더 부분
	objRow = objSheet.createRow((short)0);
	objCell = objRow.createCell((short)0);
	objCell.setCellValue(new XSSFRichTextString("SEQ"));
	objCell.setCellStyle(objFontStyle);
	objCell = objRow.createCell((short)1);
	objCell.setCellValue(new XSSFRichTextString("제목"));
	objCell.setCellStyle(objFontStyle);
	objCell = objRow.createCell((short)2);
	objCell.setCellValue(new XSSFRichTextString("글쓴이"));
	objCell.setCellStyle(objFontStyle);
	objCell = objRow.createCell((short)3);
	objCell.setCellValue(new XSSFRichTextString("비고"));
	objCell.setCellStyle(objFontStyle);
	
	int index = 1;
	while(it.hasNext()){
		board = (Board)it.next();
		objRow = objSheet.createRow(index);
		objCell = objRow.createCell((short)0);
		objCell.setCellValue(new XSSFRichTextString(Integer.toString(board.getBoard_id())));
		objCell.setCellStyle(objAlignStyle);
		objCell = objRow.createCell((short)1);
		objCell.setCellValue(new XSSFRichTextString(board.getTitle()));
		objCell.setCellStyle(objAlignStyle);
		objCell = objRow.createCell((short)2);
		objCell.setCellValue(new XSSFRichTextString(board.getName()));
		objCell.setCellStyle(objAlignStyle);
		objCell = objRow.createCell((short)3);
		objCell.setCellValue(new XSSFRichTextString(board.getRemark()));
		objCell.setCellStyle(objAlignStyle);
		index++;
	}
	
	objSheet.setColumnWidth((short)0,(short)2000);
	objSheet.setColumnWidth((short)1,(short)20000);
	objSheet.setColumnWidth((short)2,(short)3000);
	objSheet.setColumnWidth((short)3,(short)3000);
	
	fileOut = response.getOutputStream(); 
	objWorkBook.write(fileOut);
	fileOut.close();
%>