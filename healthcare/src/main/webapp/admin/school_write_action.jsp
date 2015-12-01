<%@page import="com.healthcare.admin.helper.SchoolMgrHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%  
	request.setCharacterEncoding("UTF-8");
	
	SchoolMgrHelper schoolMgrHelper = new SchoolMgrHelper();
	schoolMgrHelper.addSchool(request);
%>
<html>
<head> 
<title></title>
<META HTTP-EQUIV="refresh" CONTENT="0;URL=./school.jsp">
</head>
<body bgcolor="#FFFFFF" text="#000000">
</body>
</html>
