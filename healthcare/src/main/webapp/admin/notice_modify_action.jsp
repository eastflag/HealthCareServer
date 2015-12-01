<%@page import="com.healthcare.admin.helper.BoardMgrHelper"%>
<%@page import="com.healthcare.admin.helper.MenuMgrHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%  
	request.setCharacterEncoding("UTF-8");
	
	BoardMgrHelper boardMgrHelper = new BoardMgrHelper();
	
	boardMgrHelper.setBoard(request);

%>
<html>
<head> 
<title></title>
<META HTTP-EQUIV="refresh" CONTENT="0;URL=./notice.jsp">
</head>
<body bgcolor="#FFFFFF" text="#000000">
</body>
</html>
