<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.healthcare.admin.helper.*"%>
<%
	String loginFilePath = "";
	
	LoginMgrHelper lmh = new LoginMgrHelper();
	lmh.setLoginSession(request,response); 
%>