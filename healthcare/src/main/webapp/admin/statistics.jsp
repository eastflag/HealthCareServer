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
	
	String nextNavURL = "statistics.jsp?";
	
	Statistics statistics = null;
	Statisticss statisticss = statisticsMgrHelper.getStatisticss(request);
	
	PageNavigator navigator = new PageNavigator(SessionManager.getPageNo(request),
			                                    statisticss.getTotRowCnt(), 
			                                    Config.NUM_OF_LINE10, 
			                                    Config.NUM_OF_LINE10, 
			                                    nextNavURL);
	
	Iterator it = statisticss.getStatisticss().iterator();
	
	int rCnt  = statisticss.getTotRowCnt();
	int Pgs   = SessionManager.getPageNo(request);
	int cNum  = rCnt-((Pgs-1)*Config.NUM_OF_LINE10);
	

	SchoolMgrHelper schoolMgrHelper = new SchoolMgrHelper();
	School school = null;
	Schools schools = schoolMgrHelper.getListSchool(request);
	Iterator itSchool = schools.getSchools().iterator(); 
%>
<!-- top include --> 
<%@ include file="/admin/admin_top.jsp" %>
<script type="text/javascript">

	var form = null;
	var gradeStr_selbox = new Array();
	gradeStr_selbox[0] = new Array("저체중", "정상", "과체중", "비만", "중도비만", "고도비만");
	gradeStr_selbox[1] = new Array("비흡연", "간접", "흡연중", "과다흡연");

	var school_grade_selbox = new Array();
	school_grade_selbox[0] = new Array("1학년", "2학년", "3학년", "4학년", "5학년", "6학년");
	school_grade_selbox[1] = new Array("1학년", "2학년", "3학년");
	school_grade_selbox[2] = new Array("1학년", "2학년", "3학년");
	
	var school_gradeID_selbox = new Array();
	school_gradeID_selbox[0] = new Array("1", "2", "3", "4", "5", "6");
	school_gradeID_selbox[1] = new Array("7", "8", "9");
	school_gradeID_selbox[2] = new Array("10", "11", "12");


	function changeSection () {
		var f_sel = form.section;
		var s_sel = form.grade_str;

		var sel = f_sel.selectedIndex;
		for(var i = s_sel.length; i >= 0; i--){
			s_sel.options[i] = null;
		}

		s_sel.options[0] = new Option ("전체", "");

		if(sel != 0){
			for(var i=0; i < gradeStr_selbox[sel-1].length; i++){
				s_sel.options[i+1] = new Option(gradeStr_selbox[sel-1][i], gradeStr_selbox[sel-1][i]);

				if(s_sel.options[i+1].value == '<%= SessionManager.getSearchKeys(request).get("grade_str") %>'){
					s_sel.options[i+1].selected = true;
				}
				
			}
		}
	}
	
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

		var s_sel = form.grade_id;

		for(var i = s_sel.length; i >= 0; i--){
			s_sel.options[i] = null;
		}

		s_sel.options[0] = new Option ("전체", "");

		if(selIdx != -1){
			for(var i=0; i < school_gradeID_selbox[selIdx].length; i++){
				s_sel.options[i+1] = new Option(school_grade_selbox[selIdx][i], school_gradeID_selbox[selIdx][i]);
	
				if(s_sel.options[i+1].value == '<%= SessionManager.getSearchKeys(request).get("grade_id") %>'){
					s_sel.options[i+1].selected = true;
				}
			}
			
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
		
		form.stu_class.value = "";
	}

	function changeGrade () {		
		form.stu_class.value = "";	
		form.stu_class.value = "";
	}


	function searchReset() {

		form.section.value = "";
		changeSection();	
		form.school_id_sel.value = ",";	
		changeSchool(",");
		form.contents.value = "";
		form.measure_date.value = "";
		form.stu_class.value = "";
		form.school_id.value = "";
		form.school_name.value = "";
		form.isReset.value = "Y";
		
		alert("검색조건이 초기화되었습니다.\n\n엑셀파일 다운로드하시려면, 다시 검색해 주십시오.");
	}
	
	function excelDownload() {
		
		if (form.isReset.value == "Y") {
			alert("검색조건이 초기화되었습니다.\n\n엑셀파일 다운로드하시려면, 다시 검색해 주십시오.");
			return;
		} else {
			document.location.href="statistics_excel.jsp?totRowCnt=<%=statisticss.getTotRowCnt()%>";
		}
	}
	function searchList() {	

		if (form.section.value == "") {
			alert("통계 구분을 선택해 주십시오.");
			form.section.focus();
			return;
		}
		
// 		if (form.school_id_sel.value == ",") {
// 			alert("학교를 선택해 주십시오.");
// 			form.school_id_sel.focus();
// 			return;
// 		}
		
		if (form.measure_date.value == "") {
			alert("통계작업의 대상 측정일을 입력해 주십시오.");
			form.measure_date.focus();
			return;
		} else if (!isInteger(form.measure_date.value) || form.measure_date.value.length < 8) {
			alert("측정일은 숫자로만 입력해 주십시오.\n예) 20140301");
			form.measure_date.focus();
			return;
		}
		
		if (form.stu_class.value != "") {

			if (!isInteger(form.stu_class.value) || (removeSpace(form.stu_class.value)).length < 1) {
					alert("반(학급)은 숫자로만 입력해 주십시오.");
					form.stu_class.focus();
					return;
			}
			if (form.school_id_sel.value == "," || form.grade_id.value == "") {
				alert("반(학급)을 입력하기 전에 학교와 학년을 먼저 선택해 주십시오.");
				form.stu_class.value = "";
				return;				
			}
		}

		form.isReset.value = "N";
	    form.action='statistics.jsp?pageNo=1';
	    form.submit();
	}
</script>
<td width="98%" valign="top" style="padding-left:5">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="53" valign="bottom" class="darkgray16b">통계 (신체/흡연현황)</td>
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
		<input type="hidden" name="isReset" value="N" >
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="25"  align="left" class="darkgray11" style="padding-left:2;">
					구분&nbsp;
					<select  class="darkgray12" id="section" name="section" onchange="JavaScript:changeSection();" style="width:140px;">
						<option value = "">선택</option>
		          		<option value = "BMI"   <%=(SessionManager.getSearchKeys(request).get("section").equals("BMI")) ? "selected" : "" %> >신장/체중/체형 통계</option>
		          		<option value = "SMOKE" <%=(SessionManager.getSearchKeys(request).get("section").equals("SMOKE")) ? "selected" : "" %> >흡연 통계</option>
					</select>  |
					판정&nbsp;<select  class="darkgray12" id="grade_str" name="grade_str" style="width:120px;"></select>  |
					결과&nbsp;
					<select  class="darkgray12" id="contents" name="contents" style="width:120px;">
		          		<option value = "LIST" <%=(SessionManager.getSearchKeys(request).get("contents").equals("LIST")) ? "selected" : "" %> >학생명단</option>
		          		<option value = "COUNT" <%=(SessionManager.getSearchKeys(request).get("contents").equals("COUNT")) ? "selected" : "" %> >학생분포</option>
					</select>  |
					측정일
					<input name="measure_date" class="darkgray11" value="<%= SessionManager.getSearchKeys(request).get("measure_date") %>" style="width:100px;" maxlength="8" placeholder="예) 20140301"> |<br>
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
					학년&nbsp;<select  class="darkgray12" id="grade_id" name="grade_id" onchange="JavaScript:changeGrade();"  style="width:120px;"></select> |
					성별&nbsp;
					<select  class="darkgray12" id="stu_sex" name="stu_sex" style="width:120px;">
						<option value = "">전체</option>
		          		<option value = "F" <%=(SessionManager.getSearchKeys(request).get("stu_sex").equals("F")) ? "selected" : "" %> >여학생</option>
		          		<option value = "M" <%=(SessionManager.getSearchKeys(request).get("stu_sex").equals("M")) ? "selected" : "" %> >남학생</option>
					</select>  |
					반
					<input name="stu_class" class="darkgray11" value="" style="width:70px;" maxlength="2"> |
					<a href="JavaScript:searchReset();"><img align="absmiddle" src="./images/btn_reset3.gif" border="0"></a> |
					<img align="absmiddle" src="./images/btn_search2.gif" border="0" onclick="searchList();">
				</td>
			</tr>
		</table>
	</form>
<% 
	if (SessionManager.getSearchKeys(request).get("contents").equals("LIST")) {
%>
	<table width="100%" border="0" cellspacing="2" cellpadding="0">
		<tr align="center"> 
			<td height="2" bgcolor="67AEB8" ><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="2" bgcolor="67AEB8" ><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="2" bgcolor="67AEB8" ><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="2" bgcolor="67AEB8" ><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="2" bgcolor="67AEB8" ><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="2" bgcolor="67AEB8" ><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="2" bgcolor="67AEB8" ><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="2" bgcolor="67AEB8" ><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="2" bgcolor="67AEB8" ><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="2" bgcolor="67AEB8" ><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="2" bgcolor="67AEB8" ><img src="./images/blank.gif" width="1" height="1"></td>
		</tr>
		<tr align="center">            
			<td width="7%" bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>SEQ</strong></td>
			<td width="12%" bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>측정년월</strong></td>
			<td width="17%" bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>학교명</strong></td>       
			<td width="7%" bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>학년</strong></td>
			<td width="7%" bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>반</strong></td>
			<td width="10%" bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>성명</strong></td>
			<td width="5%" bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>성별</strong></td>
			<td width="9%" bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>신장(cm)</strong></td>
			<td width="9%" bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>체중(kg)</strong></td>
			<td width="9%" bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong><%=(SessionManager.getSearchKeys(request).get("section").equals("BMI")) ? "BMI" : "PPM" %></strong></td>
			<td width="8%" bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong><%=(SessionManager.getSearchKeys(request).get("section").equals("BMI")) ? "판정(체형)" : "판정" %></strong></td>
		</tr>                     
		<tr align="center"> 
			<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
		</tr>
		<%	if(statisticss.getTotRowCnt() < 1) {
		%>
			<tr align="center">
				<td class="text_c" colspan="11">조회 결과가 없습니다.</td>
			</tr>
			<tr align="center"> 
				<td height="1" colspan="11" bgcolor="e1e1e1"><img src="./images/blank.gif" width="1" height="1"></td>
			</tr>
		<%
			} else {
				int index = 0;
				while(it.hasNext()){
					statistics = (Statistics)it.next();
		%>
			<tr align="center" onmouseover="javascript:doMouseOverOnTR(this);" onmouseout="javascript:doMouseOutOnTR(this);">
				<td align="center" style="padding-bottom:1;padding-top:3"><%= cNum - index %></td>
				<td align="center" style="padding-bottom:1;padding-top:3"><%= statistics.getYearMonth() %></td>
				<td align="center" style="padding-bottom:1;padding-top:3"><%= statistics.getSchool_name() %></td>
				<td align="center" style="padding-bottom:1;padding-top:3"><%= statistics.getSchhol_grade_str() %></td>
				<td align="center" style="padding-bottom:1;padding-top:3"><%= statistics.getSchool_class() %>반</td>
				<td align="center" style="padding-bottom:1;padding-top:3"><%= statistics.getStu_name() %></td>
				<td align="center" style="padding-bottom:1;padding-top:3"><%= statistics.getStu_sex() %></td>
				<td align="center" style="padding-bottom:1;padding-top:3"><%= statistics.getHeight_str() %></td>
				<td align="center" style="padding-bottom:1;padding-top:3"><%= statistics.getWeight_str() %></td>
				<td align="center" style="padding-bottom:1;padding-top:3"><%= statistics.getData() %></td>
				<td align="center" style="padding-bottom:1;padding-top:3"><%= statistics.getGrade_str() %></td>
			</tr>
			
			<tr align="center"> 
				<td height="1" colspan="11" bgcolor="e1e1e1"><img src="./images/blank.gif" width="1" height="1"></td>
			</tr>
		<%		
					index++;
				}
			}
		%>
	</table>
<% 
	} else {
%>
	<table width="100%" border="0" cellspacing="2" cellpadding="0">
		<tr align="center"> 
			<td height="2" bgcolor="67AEB8" ><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="2" bgcolor="67AEB8" ><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="2" bgcolor="67AEB8" ><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="2" bgcolor="67AEB8" ><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="2" bgcolor="67AEB8" ><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="2" bgcolor="67AEB8" ><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="2" bgcolor="67AEB8" ><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="2" bgcolor="67AEB8" ><img src="./images/blank.gif" width="1" height="1"></td>
		</tr>
		<tr align="center">            
			<td width="8%" bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>SEQ</strong></td>
			<td width="14%" bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>측정년월</strong></td>
			<td width="22%" bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>학교명</strong></td>       
			<td width="10%" bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>학년</strong></td>
			<td width="8%" bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>성별</strong></td>
			<td width="15%" bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong><%=(SessionManager.getSearchKeys(request).get("section").equals("BMI")) ? "판정(체형)" : "판정" %></strong></td>
			<td width="13%" bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>학생수</strong></td>
			<td width="15%" bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>백분율</strong></td>
		</tr>                     
		<tr align="center"> 
			<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
		</tr>
		<%	if(statisticss.getTotRowCnt() < 1) {
		%>
			<tr align="center">
				<td class="text_c" colspan="8">조회 결과가 없습니다.</td>
			</tr>
			<tr align="center"> 
				<td height="1" colspan="8" bgcolor="e1e1e1"><img src="./images/blank.gif" width="1" height="1"></td>
			</tr>
		<%
			} else {
				int index = 0;
				while(it.hasNext()){
					statistics = (Statistics)it.next();
		%>
			<tr align="center" onmouseover="javascript:doMouseOverOnTR(this);" onmouseout="javascript:doMouseOutOnTR(this);">
				<td align="center" style="padding-bottom:1;padding-top:3"><%= cNum - index %></td>
				<td align="center" style="padding-bottom:1;padding-top:3"><%= statistics.getYearMonth() %></td>
				<td align="center" style="padding-bottom:1;padding-top:3"><%= statistics.getSchool_name() %></td>
				<td align="center" style="padding-bottom:1;padding-top:3"><%= statistics.getSchhol_grade_str() %></td>
				<td align="center" style="padding-bottom:1;padding-top:3"><%= statistics.getStu_sex() %></td>
				<td align="center" style="padding-bottom:1;padding-top:3"><%= statistics.getGrade_str() %></td>
				<td align="center" style="padding-bottom:1;padding-top:3"><%= statistics.getCount() %> 명</td>
				<td align="center" style="padding-bottom:1;padding-top:3"><%= statistics.getPercentage() %>%</td>
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
<%
	}
%>
	
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
		changeSection();		
		
		changeSchool("<%= SessionManager.getSearchKeys(request).get("school_id_sel") %>");
		
		form.stu_class.value = "<%= SessionManager.getSearchKeys(request).get("stu_class") %>";
	};
</script>
<!-- bottom include -->
<%@ include file="/admin/admin_bottom.jsp" %>