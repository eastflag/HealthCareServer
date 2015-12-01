<%@page import="com.healthcare.admin.adminManage.AdminManage"%>
<%@page import="com.healthcare.admin.adminManage.AdminManages"%>
<%@page import="java.util.*"%>
<%@page import="com.healthcare.admin.helper.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	AdminManageMgrHelper adminManageMgrHelper = new AdminManageMgrHelper();
	adminManageMgrHelper.setAdminManage(request);
%>
<html>
<head> 
<title></title>
<META HTTP-EQUIV="refresh" CONTENT="0;URL=./admin_manage.jsp">
</head>
<body bgcolor="#FFFFFF" text="#000000">
</body>
</html>