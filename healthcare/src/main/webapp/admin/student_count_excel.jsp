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
				com.healthcare.admin.statistics.Statisticss,
				com.healthcare.admin.statistics.Statistics,
				com.healthcare.common.JSPUtil,
				com.healthcare.admin.helper.StatisticsMgrHelper" %>
<%  
	request.setCharacterEncoding("UTF-8");

	StatisticsMgrHelper statisticsMgrHelper = new StatisticsMgrHelper();
	String locale = JSPUtil.getLocaleLanguage(request);
	
	Statistics statistics = null;
	
	Statisticss statisticss = statisticsMgrHelper.getStudentCount(request);
	
	Iterator it = statisticss.getStatisticss().iterator();

	String school_name = (String)SessionManager.getSearchKeys(request).get("school_name");
	
	if ("".equals(school_name)) {
		school_name = "전체학교";
	}
	String section = (String)SessionManager.getSearchKeys(request).get("section");
	String measure_date = (String)SessionManager.getSearchKeys(request).get("measure_date");
	
	//excel 생성 시작
	String sFileName = "AURA_" + section + "_" + measure_date + ".xlsx";
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
	objWorkBook.setSheetName(0 , school_name + "_" + section + "_" + measure_date);
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
	
	objRow = objSheet.createRow((short)0);
	objCell = objRow.createCell((short)0);
	objCell.setCellValue(new XSSFRichTextString(school_name + "_" + section + "_" + measure_date));
	objCell.setCellStyle(objAlignStyle);
	objSheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
	
	
	objRow = objSheet.createRow((short)1);
	objCell = objRow.createCell((short)0);
	objCell.setCellValue(new XSSFRichTextString("학년"));
	objCell.setCellStyle(objFontStyle);
	objCell = objRow.createCell((short)1);
	objCell.setCellValue(new XSSFRichTextString("남자"));
	objCell.setCellStyle(objFontStyle);
	objCell = objRow.createCell((short)2);
	objCell.setCellValue(new XSSFRichTextString("여자"));
	objCell.setCellStyle(objFontStyle);
	objCell = objRow.createCell((short)3);
	objCell.setCellValue(new XSSFRichTextString("전체"));
	objCell.setCellStyle(objFontStyle);
	
	int index = 1;
	while(it.hasNext()){
		statistics = (Statistics)it.next();
		objRow = objSheet.createRow(index+1);
		objCell = objRow.createCell((short)0);
		objCell.setCellValue(new XSSFRichTextString(statistics.getSchhol_grade_str()));
		objCell.setCellStyle(objAlignStyle);
		objCell = objRow.createCell((short)1);
		objCell.setCellValue(new XSSFRichTextString(Integer.toString(statistics.getCount_m())+"명"));
		objCell.setCellStyle(objAlignStyle);
		objCell = objRow.createCell((short)2);
		objCell.setCellValue(new XSSFRichTextString(Integer.toString(statistics.getCount_f())+"명"));
		objCell.setCellStyle(objAlignStyle);
		objCell = objRow.createCell((short)3);
		objCell.setCellValue(new XSSFRichTextString(Integer.toString(statistics.getCount())+"명"));
		objCell.setCellStyle(objAlignStyle);
		index++;
	}
	for(int i=0;i<10;i++){
		switch(i){
			case 0:
				objSheet.setColumnWidth((short)i,(short)1000);
				break;
			case 1:
				objSheet.setColumnWidth((short)i,(short)4000);
				break;
			case 2:
				objSheet.setColumnWidth((short)i,(short)4000);
				break;
			default:
				objSheet.setColumnWidth((short)i,(short)3000);
		}
	}
	
	
	fileOut = response.getOutputStream(); 
	objWorkBook.write(fileOut);
	fileOut.close();
%>