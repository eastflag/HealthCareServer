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
	
	String nextNavURL = "statisticsAVG.jsp?";
	
	Statistics statistics = null;
	Statisticss statisticss = statisticsMgrHelper.getStatisticssAVG(request);
	
	PageNavigator navigator = new PageNavigator(SessionManager.getPageNo(request),
			                                    statisticss.getTotRowCnt(), 
			                                    Config.NUM_OF_LINE20, 
			                                    Config.NUM_OF_LINE20, 
			                                    nextNavURL);
	
	Iterator it = statisticss.getStatisticss().iterator();
	
	int rCnt  = statisticss.getTotRowCnt();
	int Pgs   = SessionManager.getPageNo(request);
	int cNum  = (rCnt-((Pgs-1)*Config.NUM_OF_LINE20)) / 2;
	

	SchoolMgrHelper schoolMgrHelper = new SchoolMgrHelper();
	School school = null;
	Schools schools = schoolMgrHelper.getListSchool(request);
	Iterator itSchool = schools.getSchools().iterator(); 
%>
<!-- top include --> 
<%@ include file="/admin/admin_top.jsp" %>
<script type="text/javascript">

	var form = null;

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

		var sel = f_sel.selectedIndex;

		switch (sel) {
			case 0:
				form.school_id_sel.value = ",";
				form.grade_id.value = "";
				form.stu_class.value = "";
				form.school_id_sel.disabled = true;
				form.grade_id.disabled = true;
				form.stu_class.disabled = true;
				break;
			case 1:
				form.school_id_sel.value = ",";
				form.grade_id.value = "";
				form.stu_class.value = "";
				form.school_id_sel.disabled = true;
				form.grade_id.disabled = true;
				form.stu_class.disabled = true;
				break;
			case 2:
				form.school_id_sel.value = ",";
				form.grade_id.value = "";
				form.stu_class.value = "";
				form.school_id_sel.disabled = true;
				form.grade_id.disabled = true;
				form.stu_class.disabled = true;
				break;
			case 3:
				form.school_id_sel.value = ",";
				form.grade_id.value = "";
				form.stu_class.value = "";
				form.school_id_sel.disabled = true;
				form.grade_id.disabled = true;
				form.stu_class.disabled = true;
				break;
			case 4:
				form.school_id_sel.value = ",";
				form.grade_id.value = "";
				form.stu_class.value = "";
				form.school_id_sel.disabled = false;
				form.grade_id.disabled = true;
				form.stu_class.disabled = true;
				break;
			case 5:
				form.school_id_sel.value = ",";
				form.grade_id.value = "";
				form.stu_class.value = "";
				form.school_id_sel.disabled = false;
				form.grade_id.disabled = false;
				form.stu_class.disabled = true;
				break;
			case 6:
				form.school_id_sel.value = ",";
				form.grade_id.value = "";
				form.stu_class.value = "";
				form.school_id_sel.disabled = false;
				form.grade_id.disabled = false;
				form.stu_class.disabled = false;
				break;
	   }
	}
	

	function changeSection1 () {
		var f_sel = form.section;

		var sel = f_sel.selectedIndex;

		switch (sel) {
			case 0:
				form.school_id_sel.disabled = true;
				form.grade_id.disabled = true;
				form.stu_class.disabled = true;
				break;
			case 1:
				form.school_id_sel.disabled = true;
				form.grade_id.disabled = true;
				form.stu_class.disabled = true;
				break;
			case 2:
				form.school_id_sel.disabled = true;
				form.grade_id.disabled = true;
				form.stu_class.disabled = true;
				break;
			case 3:
				form.school_id_sel.disabled = true;
				form.grade_id.disabled = true;
				form.stu_class.disabled = true;
				break;
			case 4:
				form.school_id_sel.disabled = false;
				form.grade_id.disabled = true;
				form.stu_class.disabled = true;
				break;
			case 5:
				form.school_id_sel.disabled = false;
				form.grade_id.disabled = false;
				form.stu_class.disabled = true;
				break;
			case 6:
				form.school_id_sel.disabled = false;
				form.grade_id.disabled = false;
				form.stu_class.disabled = false;
				break;
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
	}


	function searchReset() {
		form.section.value = "";
		changeSection();	
		form.school_id_sel.value = ",";	
		changeSchool(",");
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
			document.location.href="statisticsAVG_excel.jsp?totRowCnt=<%=statisticss.getTotRowCnt()%>";
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

		if (form.section.value == "SCHOOL" && form.school_id_sel.value == "," ) {
			alert("학교를 지정해 주십시오.");
			return;
		}
		if (form.section.value == "GRADE" && ( form.school_id_sel.value == ","  || form.grade_id.value == "" )) {
			alert("학교와 학년을 지정해 주십시오.");
			return;
		}
		if (form.section.value == "CLASS" && ( form.school_id_sel.value == ","  || form.grade_id.value == ""  || form.stu_class.value == "" )) {
			alert("학교와 학년, 반을 지정해 주십시오.");
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
				
	    form.action='statisticsAVG.jsp?pageNo=1';
	    form.submit();
	}
</script>
<td width="98%" valign="top" style="padding-left:5">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="53" valign="bottom" class="darkgray16b">통계 (신장/체중평균)</td>
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
					<select  class="darkgray12" id="section" name="section" onchange="JavaScript:changeSection();" style="width:160px;">
						<option value = "">선택</option>
		          		<option value = "ALL_EL"   <%=(SessionManager.getSearchKeys(request).get("section").equals("ALL_EL")) ? "selected" : "" %> >전체 - 초등학교</option>
		          		<option value = "ALL_MD"   <%=(SessionManager.getSearchKeys(request).get("section").equals("ALL_MD")) ? "selected" : "" %> >전체 - 중학교</option>
		          		<option value = "ALL_HI"   <%=(SessionManager.getSearchKeys(request).get("section").equals("ALL_HI")) ? "selected" : "" %> >전체 - 고등학교</option>
		          		<option value = "SCHOOL"   <%=(SessionManager.getSearchKeys(request).get("section").equals("SCHOOL")) ? "selected" : "" %> >학교별(학교지정)</option>
		          		<option value = "GRADE"    <%=(SessionManager.getSearchKeys(request).get("section").equals("GRADE" )) ? "selected" : "" %> >학년별(학교/학년지정)</option>
		          		<option value = "CLASS"    <%=(SessionManager.getSearchKeys(request).get("section").equals("CLASS" )) ? "selected" : "" %> >학급별(학교/학년/반지정)</option>
					</select>  |
					측정일
					<input name="measure_date" class="darkgray11" value="<%= SessionManager.getSearchKeys(request).get("measure_date") %>" style="width:80px;" maxlength="8" placeholder="예) 20140301"> |<br>
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
					학년&nbsp;<select  class="darkgray12" id="grade_id" name="grade_id" onchange="JavaScript:changeGrade();"  style="width:100px;"></select> |
					반
					<input name="stu_class" class="darkgray11" value="" style="width:60px;" maxlength="2"> |
					<a href="JavaScript:searchReset();"><img align="absmiddle" src="./images/btn_reset3.gif" border="0"></a> |
					<img align="absmiddle" src="./images/btn_search2.gif" border="0" onclick="searchList();">
				</td>
			</tr>
		</table>
	</form>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="8">여학생 신장 평균 (cm) : <%= statisticss.getAllFemaleAvgH() %></td>
			<td height="8">여학생 체중 평균 (kg) : <%= statisticss.getAllFemaleAvgW() %></td>
			<td height="8">&nbsp;</td>
		</tr>
		<tr>
			<td height="8">남학생 신장 평균 (cm) : <%= statisticss.getAllMaleAvgH() %></td>
			<td height="8">남학생 체중 평균 (kg) : <%= statisticss.getAllMaleAvgW() %></td>
			<td height="8">&nbsp;</td>
		</tr>
	</table>
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
			<td width="7%"  bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>SEQ</strong></td>
			<td width="15%" bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>측정년월</strong></td>
			<td width="20%" bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>학교명</strong></td>       
			<td width="10%" bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>학년</strong></td>
			<td width="10%" bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>반</strong></td>
			<td width="8%"  bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>성별</strong></td>
			<td width="15%" bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>신장평균(cm)</strong></td>
			<td width="15%" bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>체중평균(kg)</strong></td>
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
				int result = 0;
				while(it.hasNext()){
					statistics = (Statistics)it.next();
					result = index % 2;
					
					if (result == 0) {
		%>
			<tr align="center" onmouseover="javascript:doMouseOverOnTR(this);" onmouseout="javascript:doMouseOutOnTR(this);">
				<td align="center" style="padding-bottom:1;padding-top:3" rowspan="3"><%= cNum - (index/2) %></td>
				<td align="center" style="padding-bottom:1;padding-top:3" rowspan="3"><%= statistics.getYearMonth() %></td>
				<td align="center" style="padding-bottom:1;padding-top:3" rowspan="3"><%= statistics.getSchool_name() %></td>
				<td align="center" style="padding-bottom:1;padding-top:3" rowspan="3"><%= statistics.getSchhol_grade_str() %></td>
				<td align="center" style="padding-bottom:1;padding-top:3" rowspan="3"><%=(statistics.getSchool_class().equals("-")) ? "-" : statistics.getSchool_class() + "반" %></td>
				<td align="center" style="padding-bottom:1;padding-top:3"><%= statistics.getStu_sex() %></td>
				<td align="center" style="padding-bottom:1;padding-top:3"><%= statistics.getHeight_str() %></td>
				<td align="center" style="padding-bottom:1;padding-top:3"><%= statistics.getWeight_str() %></td>
			</tr>
			
			<tr align="center"> 
				<td height="1" colspan="3" bgcolor="e1e1e1"><img src="./images/blank.gif" width="1" height="1"></td>
			</tr>
		<%		
					} else {						
		%>
			<tr align="center" onmouseover="javascript:doMouseOverOnTR(this);" onmouseout="javascript:doMouseOutOnTR(this);">
				<td align="center" style="padding-bottom:1;padding-top:3"><%= statistics.getStu_sex() %></td>
				<td align="center" style="padding-bottom:1;padding-top:3"><%= statistics.getHeight_str() %></td>
				<td align="center" style="padding-bottom:1;padding-top:3"><%= statistics.getWeight_str() %></td>
			</tr>
			
			<tr align="center"> 
				<td height="1" colspan="8" bgcolor="e1e1e1"><img src="./images/blank.gif" width="1" height="1"></td>
			</tr>		
		<%			}
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
		changeSection1();		
		
		changeSchool("<%= SessionManager.getSearchKeys(request).get("school_id_sel") %>");
		form.stu_class.value = "<%= SessionManager.getSearchKeys(request).get("stu_class") %>";
	};
</script>
<!-- bottom include -->
<%@ include file="/admin/admin_bottom.jsp" %>