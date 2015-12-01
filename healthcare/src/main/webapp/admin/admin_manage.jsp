<%@page import="java.awt.print.Printable"%>
<%@page import="com.healthcare.admin.school.School"%>
<%@page import="com.healthcare.admin.school.Schools"%>
<%@page import="java.util.*"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.healthcare.admin.adminManage.AdminManage"%>
<%@page import="com.healthcare.admin.adminManage.AdminManages"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%  
	request.setCharacterEncoding("UTF-8");
%>
<%
	AdminManageMgrHelper adminManageMgrHelper = new AdminManageMgrHelper();
	String locale = JSPUtil.getLocaleLanguage(request);
	
	//String nextNavURL = "coupon.jsp?";
	
	AdminManage adminManage = null;
	AdminManages adminManages = adminManageMgrHelper.getAdminManages(request); 
	/*
	PageNavigator pageNavigator = new PageNavigator(SessionManager.getPageNo(request),
													coupons.getTotRowCnt(), 
										            Config.NUM_OF_LINE10, nextNavURL);
	*/
	Iterator it = adminManages.getAdminManages().iterator();
	
	/*
	int rCnt  = coupons.getTotRowCnt();
	int Pgs   = SessionManager.getPageNo(request);
	int cNum  = rCnt-((Pgs-1)*Config.NUM_OF_LINE10);
	*/
	
	SchoolMgrHelper schoolMgrHelper = new SchoolMgrHelper();
	School school = null;
	Schools schools = schoolMgrHelper.getListSchool(request);
	Iterator itSchool = schools.getSchools().iterator(); 
%>
<!-- top include --> 
<%@ include file="/admin/admin_top.jsp" %>
<script type="text/javascript">
</script>
<td width="98%" valign="top" style="padding-left:5">
	<form name="userList" action="JavaScript:searchList();" method="post">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="53" valign="bottom" class="darkgray16b">계정관리</td>
			</tr>
			<tr>
				<td height="3" bgcolor="#FF5500"></td>
			</tr>
			<tr>
				<td height="20"></td>
			</tr>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="25"  align="left" class="darkgray12b" style="padding-left:2">[ 목록보기 ]</td>
			</tr>
		</table>
	</form>
	<table width="100%" border="0" cellspacing="2" cellpadding="0">
		<tr align="center"> 
			<td height="2" bgcolor="67AEB8" ><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="2" bgcolor="67AEB8" ><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="2" bgcolor="67AEB8" ><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="2" bgcolor="67AEB8" ><img src="./images/blank.gif" width="1" height="1"></td>
		</tr>
		<tr align="center">            
			<td width='5%'  bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>SEQ</strong></td>
			<td width='15%'  bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>아이디</strong></td>
			<td width='20%' bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>담당학교</strong></td>
			<td width='20%' bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>비고</strong></td>
			
		</tr>                     
		<tr align="center"> 
			<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
		</tr>
		<%	if(adminManages.getTotRowCnt() < 1) {%>
			<tr align="center">
				<td class="text_c" colspan="8">조회할 자료가 없습니다.</td>
			</tr>
			<tr align="center"> 
				<td height="1" colspan="8" bgcolor="e1e1e1"><img src="./images/blank.gif" width="1" height="1"></td>
			</tr>
		<%
			}else{
				int index = 0;
				String com_nm = "";
				
				while(it.hasNext()){
					adminManage = (AdminManage)it.next();
		%>
			<tr align="center" onmouseover="javascript:doMouseOverOnTR(this);" onmouseout="javascript:doMouseOutOnTR(this);">
				<td align="center" style="padding-bottom:1;padding-top:3">
					<a href="admin_manage_modify.jsp?admin_id=<%=adminManage.getAdmin_id()%>"><%=adminManage.getAdmin_seq()%></a>
				</td>
				<td align="center" style="padding-bottom:1;padding-top:3"><%=adminManage.getAdmin_id()%></td>
				<td align="center" style="padding-bottom:1;padding-top:3"><%=adminManage.getSchool_name()%></td>
				<td align="center" style="padding-bottom:1;padding-top:3"><%=adminManage.getAdmin_info()%></td>
			</tr>
			
			<tr align="center"> 
				<td height="1" colspan="8" bgcolor="e1e1e1"><img src="./images/blank.gif" width="1" height="1"></td>
			</tr>
		<%		
					index++;
				}
			}
		%>
	</table>
	<!-- next /prev   page  : 시작-->
	<%-- 
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="8"><img src="./images/blank.gif"></td>
		</tr>
		<tr>
			<td  height="2" align="center" ><%=pageNavigator.getHtml()%></td>
		</tr>
	</table>
	 --%>
 	<!-- next /prev   page  : 끝-->
	<table width="100%" border="0" cellspacing="0" cellpadding="0">     
		<tr>
			<td align="right"><a href="admin_manage_write.jsp"><img src="./images/btn_write.gif"  border="0"></a></td>
		</tr>
	</table>
</td>
<td width="2%">&nbsp;</td>
</tr>
</table>
<!-- bottom include -->
<%@ include file="/admin/admin_bottom.jsp" %>