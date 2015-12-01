<%@page import="com.healthcare.admin.adminManage.AdminManage"%>
<%@page import="com.healthcare.admin.adminManage.AdminManages"%>
<%@page import="java.util.*"%>
<%@page import="com.healthcare.admin.helper.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%  
	request.setCharacterEncoding("UTF-8");
%>
<%
	//request.setAttribute("admin_id", request.getParameter("admin_id"));
	AdminManageMgrHelper adminManageMgrHelper = new AdminManageMgrHelper();
	Map<String, Object> pmap = adminManageMgrHelper.adminIdDuplicationChk(request);
	out.println(pmap.get("duplicationChk"));
%>