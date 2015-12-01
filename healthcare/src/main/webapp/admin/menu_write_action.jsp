<%@page import="com.healthcare.admin.helper.MenuMgrHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%  
	request.setCharacterEncoding("UTF-8");
	
	boolean isExist = false;

	MenuMgrHelper menuMgrHelper = new MenuMgrHelper();
	isExist = menuMgrHelper.addMenu(request);

	if(isExist){
%>
<html>
<head> 
<title></title>
<script type="text/javascript">
alert('이미 같은 식단이 존재합니다.\n기존의 식단을 수정하거나\n다른 날짜의 식단을 입력해 주십시오.');
history.back(-1);
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
<META HTTP-EQUIV="refresh" CONTENT="0;URL=./menu.jsp">
</head>
<body bgcolor="#FFFFFF" text="#000000">
</body>
</html>

<%
	}
%>
