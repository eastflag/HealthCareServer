<%@page import="com.healthcare.admin.helper.MenuMgrHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%  
	request.setCharacterEncoding("UTF-8");
	
	MenuMgrHelper menuMgrHelper = new MenuMgrHelper();
	menuMgrHelper.setMenu(request);

%>
<html>
<head> 
<title></title>
<META HTTP-EQUIV="refresh" CONTENT="0;URL=./menu.jsp">
</head>
<body bgcolor="#FFFFFF" text="#000000">
</body>
</html>
