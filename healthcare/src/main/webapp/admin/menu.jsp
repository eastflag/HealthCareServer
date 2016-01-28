<%@page import="com.healthcare.admin.school.School"%>
<%@page import="com.healthcare.admin.school.Schools"%>
<%@page import="java.util.*"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.healthcare.admin.menu.Menu"%>
<%@page import="com.healthcare.admin.menu.Menus"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%  
	request.setCharacterEncoding("UTF-8");
%>
<%
	MenuMgrHelper menuMgrHelper = new MenuMgrHelper();
	String locale = JSPUtil.getLocaleLanguage(request);
	
	String nextNavURL = "menu.jsp?";
	
	Menu menu = null;
	Menus menus = menuMgrHelper.getMenus(request); 


	PageNavigator navigator = new PageNavigator(SessionManager.getPageNo(request),
			menus.getTotRowCnt(), 
			                                    Config.NUM_OF_LINE10, 
			                                    Config.NUM_OF_LINE50, 
			                                    nextNavURL);

	Iterator it = menus.getMenus().iterator();

	int rCnt  = menus.getTotRowCnt();
	int Pgs   = SessionManager.getPageNo(request);
	int cNum  = rCnt-((Pgs-1)*Config.NUM_OF_LINE50);
	
	SchoolMgrHelper schoolMgrHelper = new SchoolMgrHelper();
	School school = null;
	Schools schools = schoolMgrHelper.getListSchool(request);
	Iterator itSchool = schools.getSchools().iterator(); 
%>
<!-- top include --> 
<%@ include file="/admin/admin_top.jsp" %>
<script type="text/javascript">
	function searchReset(form) {
		form.school_id.value  = "";
		form.school_year.value  = "";
		form.school_month.value  = "";
		searchList();
	}
	function searchList() {	    
	    document.userList.action='menu.jsp';
	    document.userList.submit();
	}
	function imgPopup(url){
		var popOption = "width=400, height=300,top=100,left=100 status=no, directories=no, location=no, toolbar=no, menubar=no";
		if(1<url.length){
			window.open(rootPath + url,"",popOption);
		}else{
			alert("이미지가 없습니다.");
		}
	}
</script>
<td width="98%" valign="top" style="padding-left:5">
	<form name="userList" action="JavaScript:searchList();" method="post">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="53" valign="bottom" class="darkgray16b">식단관리</td>
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
					학교&nbsp;
					<select  class="darkgray12" id="school_id" name="school_id" onChange="JavaScript:searchList();">
						<option value = "">전체</option>
						<%
							while(itSchool.hasNext()){
								school = (School)itSchool.next();
						%>
							<option value="<%=school.getSchool_id()%>" <%= (SessionManager.getSearchKeys(request).get("school_id").equals(school.getSchool_id())) ? "selected" : "" %>><%=school.getSchool_name()%>&nbsp;&nbsp;&nbsp;&nbsp;</option>
	                	<%
							}
	                	%>
					</select> |&nbsp;
					연도&nbsp;
					<select  class="darkgray12" id="school_year" name="school_year" onChange="JavaScript:searchList();">
						<option value = "">전체</option>
		          		<%
	          				for(int i=2013;i<2021;i++){
		          		%>
		          		<option value = "<%=i%>" <%=(SessionManager.getSearchKeys(request).get("school_year").equals(Integer.toString(i))) ? "selected" : "" %> ><%=i%></option>
		          		<%
		          			}
		          		%>
					</select> |&nbsp;
					월&nbsp;
					<select  class="darkgray12" id="school_month" name="school_month" onChange="JavaScript:searchList();">
						<option value = "">전체</option>
						<%
	          				for(int i=1;i<13;i++){
		          		%>
		          		<option value = "<%=i%>" <%=(SessionManager.getSearchKeys(request).get("school_month").equals(Integer.toString(i))) ? "selected" : "" %> ><%=i%></option>
		          		<%
		          			}
		          		%>
					</select> |&nbsp;
						<a href="JavaScript:searchReset(document.userList);"><img align="absmiddle" src="./images/btn_reset3.gif" border="0"></a> |&nbsp;<input type="image" align="absmiddle" src="./images/btn_search2.gif" border="0"> |&nbsp;
						<a href="menu_write.jsp"><img src="./images/btn_write.gif" align="absmiddle" border="0"></a>
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
			<!-- <td height="2" bgcolor="67AEB8" ><img src="./images/blank.gif" width="1" height="1"></td> -->		
			<td height="2" bgcolor="67AEB8" ><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="2" bgcolor="67AEB8" ><img src="./images/blank.gif" width="1" height="1"></td>
		</tr>
		<tr align="center">            
			<td width='5%'  bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>SEQ</strong></td>
			<td width='15%'  bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>학교</strong></td>
			<td width='20%' bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>중식메뉴</strong></td>
			<!-- <td width='10%' bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>석식메뉴</strong></td> -->
			<td width='15%' bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>날짜</strong></td>
			<td width='10%' bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>등록자</strong></td>	
			<td width='10%' bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>사진</strong></td>	
			
		</tr>                     
		<tr align="center"> 
			<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
			<!-- <td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td> -->
			<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
		</tr>
		<%	if(menus.getTotRowCnt() < 1) {%>
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
					menu = (Menu)it.next();
		%>
			<tr align="center" onmouseover="javascript:doMouseOverOnTR(this);" onmouseout="javascript:doMouseOutOnTR(this);">
				<td align="center" style="padding-bottom:1;padding-top:3">
					<a href="menu_modify.jsp?MENU_ID=<%=menu.getMenu_id()%>"><%=menu.getMenu_id()%></a>
				</td>
				<td align="center" style="padding-bottom:1;padding-top:3"><a href="menu_modify.jsp?MENU_ID=<%=menu.getMenu_id()%>"><%=menu.getSchool_name()%></a></td>
				<td align="center" style="padding-bottom:1;padding-top:3"><%=menu.getLunch_main()%></td>
				<%-- <td align="center" style="padding-bottom:1;padding-top:3"><%=menu.getDinner_main()%></td> --%>
				<td align="center" style="padding-bottom:1;padding-top:3"><%=menu.getSchool_year()%>년&nbsp;<%=menu.getSchool_month()%>월&nbsp;<%=menu.getSchool_date()%>일</td>
				<td align="center" style="padding-bottom:1;padding-top:3"><%=menu.getReg_id()%></td>
				<td align="center" style="padding-bottom:1;padding-top:3">
		<%
				if (menu.getImg_url() != null && !"".equals(menu.getImg_url())) {
		%>
					<a href="javascript:imgPopup('<%=menu.getImg_url()%>');">
						<img src='./images/icon_file.gif'>
					</a>
		<%
				} else {
		%>
					&nbsp;
		<%
				}
		%>
				</td>
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
			<td align="right"><a href="menu_write.jsp"><img src="./images/btn_write.gif"  border="0"></a></td>
		</tr>
	</table>
</td>
<td width="2%">&nbsp;</td>
</tr>
</table>
<!-- bottom include -->
<%@ include file="/admin/admin_bottom.jsp" %>