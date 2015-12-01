<%@page import="com.healthcare.common.Config"%>
<%@page import="com.healthcare.common.SessionManager"%>
<%@page import="com.healthcare.common.PageNavigator"%>
<%@page import="com.healthcare.admin.statistics.Statisticss"%>
<%@page import="com.healthcare.admin.statistics.Statistics"%>
<%@page import="com.healthcare.admin.school.Schools"%>
<%@page import="com.healthcare.admin.school.School"%>
<%@page import="com.healthcare.common.JSPUtil"%>
<%@page import="com.healthcare.admin.helper.SchoolMgrHelper"%>
<%@page import="com.healthcare.admin.helper.StatisticsMgrHelper"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%  
	request.setCharacterEncoding("UTF-8");
%>
<%
	StatisticsMgrHelper statisticsMgrHelper = new StatisticsMgrHelper();
	String locale = JSPUtil.getLocaleLanguage(request);
	
	Statistics statistics = null;
	Statisticss statisticss = statisticsMgrHelper.getStudentCount(request);
	
	Iterator it = statisticss.getStatisticss().iterator();

	SchoolMgrHelper schoolMgrHelper = new SchoolMgrHelper();
	School school = null;
	Schools schools = schoolMgrHelper.getListSchool(request);
	Iterator itSchool = schools.getSchools().iterator(); 
	
	String isReset = request.getParameter("isReset");
	if(isReset==null) {
		isReset = "Y";
	}
%>
<!-- top include --> 
<%@ include file="/admin/admin_top.jsp" %>
<script type="text/javascript">

	var form = null;

	
	function changeSchool (sel) {
		
		selData = sel.split(",");
		
		selIdx = -1;
		
		if (selData[1] == "E") {
			selIdx = 0;
		} else if (selData[1] == "M") {
			selIdx = 1;
		} else if (selData[1] == "H") {
			selIdx = 2;
		}

		if(selIdx != -1){
			if (sel != ",") {
				 
				var optionObj = form.school_id_sel.options;
				var selSchoolIdx = optionObj.selectedIndex;
				
				form.school_id.value = selData[0];
				form.school_name.value = optionObj[selSchoolIdx].text;
			}
		} else {
			form.school_id.value = "";
			form.school_name.value = "";
		}
	}
	
	function searchReset() {
		form.section.value = "";
		form.school_id_sel.value = ",";	
		changeSchool(",");
		form.measure_date.value = "";
		form.isReset.value = "Y";
		
		alert("검색조건이 초기화되었습니다.\n\n엑셀파일 다운로드하시려면, 다시 검색해 주십시오.");
	}
	
	function excelDownload() {
		
		if (form.isReset.value == "Y") {
			alert("검색조건이 초기화되었습니다.\n\n엑셀파일 다운로드하시려면, 다시 검색해 주십시오.");
			return;
		} else {
			document.location.href="student_count_excel.jsp";
		}
	}
	function searchList() {	

		if (form.section.value == "") {
			alert("구분을 선택해 주십시오.");
			form.section.focus();
			return;
		}
		
 		if (form.school_id_sel.value == ",") {
 			alert("학교를 선택해 주십시오.");
 			form.school_id_sel.focus();
 			return;
 		}
		
		if (form.measure_date.value == "") {
			alert("측정일을 입력해 주십시오.");
			form.measure_date.focus();
			return;
		} else if (!isInteger(form.measure_date.value) || form.measure_date.value.length < 8) {
			alert("측정일은 숫자로만 입력해 주십시오.\n예) 20140301");
			form.measure_date.focus();
			return;
		}
		
		form.isReset.value = "N";
	    form.action='student_count.jsp';
	    form.submit();
	}
</script>
<td width="98%" valign="top" style="padding-left:5">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="53" valign="bottom" class="darkgray16b">측정 학생수</td>
			</tr>
			<tr>
				<td height="3" bgcolor="#FF5500"></td>
			</tr>
			<tr>
				<td height="20"></td>
			</tr>
		</table>
	<form name="form" action="JavaScript:searchList();" method="post">
		<input type="hidden" name="school_id" value="<%= SessionManager.getSearchKeys(request).get("school_id")%>" >
		<input type="hidden" name="school_name" value="<%= SessionManager.getSearchKeys(request).get("school_name")%>" >
		<input type="hidden" name="isReset" value="<%=isReset %>" >
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="25"  align="left" class="darkgray11" style="padding-left:2;">
					구분&nbsp;
					<select  class="darkgray12" id="section" name="section" style="width:140px;">
						<option value = "">선택</option>
		          		<option value = "BMI"   <%=(SessionManager.getSearchKeys(request).get("section").equals("BMI")) ? "selected" : "" %> >신장/체중/체형</option>
		          		<option value = "SMOKE" <%=(SessionManager.getSearchKeys(request).get("section").equals("SMOKE")) ? "selected" : "" %> >흡연 통계</option>
					</select>  |
					학교&nbsp;
					<select  class="darkgray12" id="school_id_sel" name="school_id_sel" onChange="JavaScript:changeSchool(this.value);" style="width:140px;">
						<option value = ",">전체</option>
						<%
							while(itSchool.hasNext()){
								school = (School)itSchool.next();
						%>
							<option value="<%=school.getSchool_id()%>,<%=school.getSection()%>" <%= (SessionManager.getSearchKeys(request).get("school_id_sel").equals(school.getSchool_id() +","+ school.getSection())) ? "selected" : "" %>><%=school.getSchool_name()%></option>
	                	<%
							}
	                	%>
					</select> |
					측정일
					<input name="measure_date" class="darkgray11" value="<%= SessionManager.getSearchKeys(request).get("measure_date") %>" style="width:100px;" maxlength="8" placeholder="예) 20140301"> |
					<a href="JavaScript:searchReset();"><img align="absmiddle" src="./images/btn_reset3.gif" border="0"></a> |
					<img align="absmiddle" src="./images/btn_search2.gif" border="0" onclick="searchList();">
				</td>
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
			<td width="25%" bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>학년</strong></td>
			<td width="25%" bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>남자</strong></td>
			<td width="25%" bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>여자</strong></td>       
			<td width="25%" bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>전체</strong></td>
		</tr>                     
		<tr align="center"> 
			<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
		</tr>
		<%
				int index = 0;
				int sum = 0;
				int sum_m = 0;
				int sum_f = 0;
				while(it.hasNext()){
					statistics = (Statistics)it.next();
					
		%>
			<tr align="center" onmouseover="javascript:doMouseOverOnTR(this);" onmouseout="javascript:doMouseOutOnTR(this);">
				<td align="center" style="padding-bottom:1;padding-top:3"><%= statistics.getSchhol_grade_str() %></td>
				<td align="center" style="padding-bottom:1;padding-top:3"><%= statistics.getCount_m() %>명</td>
				<td align="center" style="padding-bottom:1;padding-top:3"><%= statistics.getCount_f() %>명</td>
				<td align="center" style="padding-bottom:1;padding-top:3"><%= statistics.getCount() %>명</td>
			</tr>
			<tr align="center"> 
				<td height="1" colspan="4" bgcolor="e1e1e1"><img src="./images/blank.gif" width="1" height="1"></td>
			</tr>
		<%			
					sum += statistics.getCount();
					sum_m += statistics.getCount_m();
					sum_f += statistics.getCount_f();
					index++;
				}
				%>	
			<tr align="center" onmouseover="javascript:doMouseOverOnTR(this);" onmouseout="javascript:doMouseOutOnTR(this);">
				<td align="center" style="padding-bottom:1;padding-top:3">전체</td>
				<td align="center" style="padding-bottom:1;padding-top:3"><%= sum_m %>명</td>
				<td align="center" style="padding-bottom:1;padding-top:3"><%= sum_f %>명</td>
				<td align="center" style="padding-bottom:1;padding-top:3"><%= sum %>명</td>
			</tr>
			<tr align="center"> 
				<td height="1" colspan="4" bgcolor="e1e1e1"><img src="./images/blank.gif" width="1" height="1"></td>
			</tr>
	</table>
	
	<table width="100%" border="0" cellspacing="0" cellpadding="0">     
		<tr>
			<td align="right">
				<a href="JavaScript:excelDownload();"><img src="./images/down.gif"  border="0"></a>
			</td>
		</tr>
	</table>
</td>
<td width="2%">&nbsp;</td>
</tr>
</table>
<script type="text/javascript">
	
	window.onload = function(){
		form = document.form;
		
		changeSchool("<%= SessionManager.getSearchKeys(request).get("school_id_sel") %>");
	};
</script>
<!-- bottom include -->
<%@ include file="/admin/admin_bottom.jsp" %>