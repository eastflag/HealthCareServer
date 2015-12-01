<%@page import="com.healthcare.common.Config"%>
<%@page import="com.healthcare.common.SessionManager"%>
<%@page import="com.healthcare.common.PageNavigator"%>
<%@page import="com.healthcare.admin.school.Schools"%>
<%@page import="com.healthcare.admin.school.School"%>
<%@page import="com.healthcare.common.JSPUtil"%>
<%@page import="com.healthcare.admin.helper.SchoolMgrHelper"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%  
	request.setCharacterEncoding("UTF-8");
%>
<%
	SchoolMgrHelper schoolMgrHelper = new SchoolMgrHelper();
	String locale = JSPUtil.getLocaleLanguage(request);
	
	String nextNavURL = "school.jsp?";
	
	School school = null;
	Schools schools = schoolMgrHelper.getSchools(request);
	
	PageNavigator navigator = new PageNavigator(SessionManager.getPageNo(request),
			                                     schools.getTotRowCnt(), 
			                                     Config.NUM_OF_LINE10, 
			                                     nextNavURL);
	
	Iterator it = schools.getSchools().iterator();
	
	int rCnt  = schools.getTotRowCnt();
	int Pgs   = SessionManager.getPageNo(request);
	int cNum  = rCnt-((Pgs-1)*Config.NUM_OF_LINE10);
%>
<!-- top include --> 
<%@ include file="/admin/admin_top.jsp" %>
<script type="text/javascript">
	function searchReset(form) {
		form.school_name.value  = "";
		form.section.value  = "";
		searchList();
	}
	function searchList() {	    
	    document.userList.action='school.jsp';
	    document.userList.submit();
	}
</script>
<td width="98%" valign="top" style="padding-left:5">
	<form name="userList" action="JavaScript:searchList();" method="post">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="53" valign="bottom" class="darkgray16b">학교관리</td>
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
				<td height="25"  align="right" class="darkgray11" style="padding-left:2;">
					학교명
					<input name="school_name" class="darkgray11" value="<%= SessionManager.getSearchKeys(request).get("school_name") %>" style="width:100px;"> |&nbsp;
					구분&nbsp;
					<select  class="darkgray12" id="section" name="section" onChange="JavaScript:searchList();">
						<option value = "">전체</option>
		          		<option value = "E" <%=(SessionManager.getSearchKeys(request).get("section").equals("E")) ? "selected" : "" %> >초등학교</option>
		          		<option value = "M" <%=(SessionManager.getSearchKeys(request).get("section").equals("M")) ? "selected" : "" %> >중학교</option>
		          		<option value = "H" <%=(SessionManager.getSearchKeys(request).get("section").equals("H")) ? "selected" : "" %> >고등학교</option>
					</select>  |
					&nbsp;
					<a href="JavaScript:searchReset(document.userList);"><img align="absmiddle" src="./images/btn_reset3.gif" border="0"></a> |
					&nbsp;
					<img align="absmiddle" src="./images/btn_search2.gif" border="0" onclick="searchList();">
				</td>
			</tr>
		</table>
	</form>
	<font color="red">※ 사업 확대에 의한 신규 학교 추가 시, 신규 학교의 school_id 발번을 위하여 아래 정보를 등록하여 주십시오.</font><br>
	<font color="red">※ 학교명 순으로 정렬되어 있습니다.</font><br>
	<table width="100%" border="0" cellspacing="2" cellpadding="0">
		<tr align="center"> 
			<td height="2" bgcolor="67AEB8" ><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="2" bgcolor="67AEB8" ><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="2" bgcolor="67AEB8" ><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="2" bgcolor="67AEB8" ><img src="./images/blank.gif" width="1" height="1"></td>		
		</tr>
		<tr align="center">            
			<td width='3%'  bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>SEQ</strong></td>
			<td width='10%' bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>Schoo_Id</strong></td>
			<td width='25%' bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>학교명</strong></td>
			<td width='5%' bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>구분</strong></td>
		</tr>                     
		<tr align="center"> 
			<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
		</tr>
		<%	if(schools.getTotRowCnt() < 1) {%>
			<tr align="center">
				<td class="text_c" colspan="4">조회할 자료가 없습니다.</td>
			</tr>
			<tr align="center"> 
				<td height="1" colspan="4" bgcolor="e1e1e1"><img src="./images/blank.gif" width="1" height="1"></td>
			</tr>
		<%
			}else{
				int index = 0;
				
				String sectionName = "";
				
				while(it.hasNext()){
					school = (School)it.next();
					
					if (school.getSection().equals("E")) {
						sectionName = "초등학교";
					} else if (school.getSection().equals("M")) {
						sectionName = "중학교";
					} else if (school.getSection().equals("H")) {
						sectionName = "고등학교";
					}
					
		%>
			<tr align="center" onmouseover="javascript:doMouseOverOnTR(this);" onmouseout="javascript:doMouseOutOnTR(this);">
				<td align="center" style="padding-bottom:1;padding-top:3">
					<%=cNum - index %>
				</td>
				<td align="center" style="padding-bottom:1;padding-top:3"><%=school.getSchool_id()%></td>
				<td align="center" style="padding-bottom:1;padding-top:3">
					<a href="school_modify.jsp?school_id=<%=school.getSchool_id()%>"><%=school.getSchool_name()%></a>
				</td>
				<td align="center" style="padding-bottom:1;padding-top:3"><%=sectionName%></td>
			</tr>
			
			<tr align="center"> 
				<td height="1" colspan="4" bgcolor="e1e1e1"><img src="./images/blank.gif" width="1" height="1"></td>
			</tr>
		<%		
					index++;
				}
			}
		%>
	</table>
	<!-- next /prev   page  : 시작-->
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="8"><img src="./images/blank.gif"></td>
		</tr>
		<tr>
			<td  height="2" align="center" ><%=navigator.getHtml()%></td>
		</tr>
	</table>
 	<!-- next /prev   page  : 끝-->
	<table width="100%" border="0" cellspacing="0" cellpadding="0">     
		<tr>
			<td align="right"><a href="school_write.jsp"><img src="./images/btn_write.gif"  border="0"></a></td>
		</tr>
	</table>
</td>
<td width="2%">&nbsp;</td>
</tr>
</table>
<!-- bottom include -->
<%@ include file="/admin/admin_bottom.jsp" %>