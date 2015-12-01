<%@page import="com.healthcare.admin.helper.ExcelMgrHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%  
	request.setCharacterEncoding("UTF-8");
	
	boolean isSuccess = false;	

	ExcelMgrHelper excelMgrHelper = new ExcelMgrHelper();
	isSuccess = excelMgrHelper.dataEncode(request);
	System.out.println("isSuccess"+isSuccess);

	if(isSuccess){
%>
<html>
<head> 
<title></title>
<script type="text/javascript">
alert('암호화에 성공하였습니다.');
location.href = 'data_encode.jsp';
</script>
</head>
<body bgcolor="#FFFFFF" text="#000000">
</body>
</html>

<%
	}else{
%>
<html>
<head> 
<title></title>
<script type="text/javascript">
alert('암호화에 실패하였습니다.');
location.href = 'data_encode.jsp';
</script>
</head>
<body bgcolor="#FFFFFF" text="#000000">
</body>
</html>

<%
	}
%>
