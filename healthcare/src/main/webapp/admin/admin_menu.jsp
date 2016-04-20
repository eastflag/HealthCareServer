<%@ page import="java.util.Calendar"%>
<%@ page import="com.healthcare.common.*"%>
<%@ page import="com.healthcare.admin.helper.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	LoginMgrHelper lmh = new LoginMgrHelper();
	lmh.isLogin(request,response); 
	
	String menuLoginName = (String)request.getSession().getAttribute(WebKeys.UserKey);
%>
	<!-- 왼쪽 메뉴 부분 시작 -->
		<td width="146" valign="top" style="padding-left:15;padding-right:16">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="2">&nbsp;</td>
				</tr>  
				<tr>
					<td><a href="./menu.jsp?pageNo=1&INIT"><img src="./images/logo.png" width="146" height="36" border="0"></a></td>
				</tr>
				<tr>
					<!--td height="3" bgcolor="#FF5500"></td-->
					<td height="3">&nbsp;</td>
				</tr>
			</table>
			<!-- 로긴정보 -->
			<table width="100%" border="0" cellpadding="3" cellspacing="0" bgcolor="#FFFFFF">
				<tr>
					<td class="darkgray11" style="padding-top:5"><span class="blue11">'<strong><%=menuLoginName%></strong>'</span>님<br>
					오늘도 즐거운 하루되세요~ </td>
				</tr>
				<tr>
					<td align="center" style="padding-bottom:5">&nbsp;</td>
				</tr>
			</table>
			<!-- 메뉴 -->
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="30"></td>
				</tr>
				<tr>
					<td><img src="./images/pic04.gif" width="146" height="4"></td>
				</tr>
				<tr>
					<td height="25" class="darkgray11" ><img src="./images/Bullet70-Box-Orange.png" align="absmiddle">
					<a href="./statistics.jsp?pageNo=1&INIT">통계 (신체/흡연현황)</a></td>
				</tr>
				<tr>
					<td height="25" class="darkgray11" ><img src="./images/Bullet70-Box-Orange.png" align="absmiddle">
					<a href="./statisticsAVG.jsp?pageNo=1&INIT">통계 (신체평균)</a></td>
				</tr>
				<tr>
					<td height="25" class="darkgray11" ><img src="./images/Bullet70-Box-Orange.png" align="absmiddle">
					<a href="./student_count.jsp">측정학생수</a></td>
				</tr>
				<tr>
					<td height="25" class="darkgray11" ><img src="./images/Bullet70-Box-Orange.png" align="absmiddle">
					<a href="./notice.jsp?pageNo=1&INIT">공지사항</a></td>
				</tr>
				<tr>
					<td height="25" class="darkgray11" ><img src="./images/Bullet70-Box-Orange.png" align="absmiddle">
					<a href="./question.jsp?pageNo=1&INIT">질의응답</a></td>
				</tr>
				<tr>
					<td height="25" class="darkgray11" ><img src="./images/Bullet70-Box-Orange.png" align="absmiddle">
					<a href="./school.jsp?pageNo=1&INIT">학교관리</a></td>
				</tr>
				<tr>
					<td height="25" class="darkgray11" ><img src="./images/Bullet70-Box-Orange.png" align="absmiddle">
					<a href="./menu.jsp?pageNo=1&INIT">식단관리</a></td>
				</tr>
				<tr>
					<td height="25" class="darkgray11" ><img src="./images/Bullet70-Box-Orange.png" align="absmiddle">
					<a href="./admin_manage.jsp?pageNo=1&INIT">측정SW계정관리자</a></td>
				</tr>
				<tr>
					<td height="25" class="darkgray11" ><img src="./images/Bullet70-Box-Orange.png" align="absmiddle">
					<a href="./device_mapping.jsp">디바이스맵핑</a></td>
				</tr>
				<tr>
					<td height="25" class="darkgray11" ><img src="./images/Bullet70-Box-Orange.png" align="absmiddle">
					<a href="./device_manage.jsp">디바이스관리</a></td>
				</tr>
				<!-- tr>
					<td height="25" class="darkgray11" ><img src="./images/Bullet70-Box-Orange.png" align="absmiddle">
					<a href="JavaScript:openCallGCM();">학교별 업데이트알림</a></td>
				</tr -->
				<tr>
					<td height="1" bgcolor="#CCCCCC"></td>
				</tr>
			</table>
		</td>
		<!-- 왼쪽 메뉴 부분 끝 -->