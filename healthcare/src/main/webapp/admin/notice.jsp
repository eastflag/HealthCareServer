<%@page import="com.healthcare.common.Config"%>
<%@page import="com.healthcare.common.SessionManager"%>
<%@page import="com.healthcare.common.PageNavigator"%>
<%@page import="com.healthcare.admin.board.Boards"%>
<%@page import="com.healthcare.admin.board.Board"%>
<%@page import="com.healthcare.common.JSPUtil"%>
<%@page import="com.healthcare.admin.helper.BoardMgrHelper"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%  
	request.setCharacterEncoding("UTF-8");
%>
<%
	BoardMgrHelper boardMgrHelper = new BoardMgrHelper();
	String locale = JSPUtil.getLocaleLanguage(request);
	
	String nextNavURL = "notice.jsp?";
	
	Board board = null;
	Boards boards = boardMgrHelper.getBoards(request, "1");
	
	PageNavigator navigator = new PageNavigator(SessionManager.getPageNo(request),boards.getTotRowCnt(), Config.NUM_OF_LINE10, nextNavURL);
	
	Iterator it = boards.getBoards().iterator();
	
	int rCnt  = boards.getTotRowCnt();
	int Pgs   = SessionManager.getPageNo(request);
	int cNum  = rCnt-((Pgs-1)*Config.NUM_OF_LINE10);
%>
<!-- top include --> 
<%@ include file="/admin/admin_top.jsp" %>
<script type="text/javascript">
	function searchReset(form) {
		form.title.value  = "";
		searchList();
	}
	function searchList() {	    
	    document.userList.action='notice.jsp';
	    document.userList.submit();
	}
</script>
<td width="98%" valign="top" style="padding-left:5">
	<form name="userList" action="JavaScript:searchList();" method="post">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="53" valign="bottom" class="darkgray16b">공지사항</td>
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
					제목
					<input name="title" class="darkgray11" value="<%= SessionManager.getSearchKeys(request).get("title") %>" style="width:100px;"> |
					&nbsp;
					<a href="JavaScript:searchReset(document.userList);"><img align="absmiddle" src="./images/btn_reset3.gif" border="0"></a> |
					&nbsp;
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
			<!-- <td height="2" bgcolor="67AEB8" ><img src="./images/blank.gif" width="1" height="1"></td> -->
		</tr>
		<tr align="center">            
			<td width='3%'  bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>SEQ</strong></td>
			<td width='25%' bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>제목</strong></td>
			<td width='5%' bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>사용여부</strong></td>
			<td width='10%' bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>공지기간</strong></td>
			<!-- <td width='20%' bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>비고</strong></td> -->	
			
		</tr>                     
		<tr align="center"> 
			<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
			<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
			<!-- <td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td> -->
		</tr>
		<%	if(boards.getTotRowCnt() < 1) {%>
			<tr align="center">
				<td class="text_c" colspan="4">조회할 자료가 없습니다.</td>
			</tr>
			<tr align="center"> 
				<td height="1" colspan="4" bgcolor="e1e1e1"><img src="./images/blank.gif" width="1" height="1"></td>
			</tr>
		<%
			}else{
				int index = 0;
				while(it.hasNext()){
					board = (Board)it.next();
		%>
			<tr align="center" onmouseover="javascript:doMouseOverOnTR(this);" onmouseout="javascript:doMouseOutOnTR(this);">
				<td align="center" style="padding-bottom:1;padding-top:3">
					<a href="notice_modify.jsp?board_id=<%=board.getBoard_id()%>"><%=board.getBoard_id()%></a>
				</td>
				<td align="center" style="padding-bottom:1;padding-top:3">
					<a href="notice_modify.jsp?board_id=<%=board.getBoard_id()%>"><%=board.getTitle()%></a>
				</td>
				<td align="center" style="padding-bottom:1;padding-top:3"><%=board.getUse_yn()%></td>
				<td align="center" style="padding-bottom:1;padding-top:3"><%=board.getEnd_date()%></td>
				<%-- <td align="center" style="padding-bottom:1;padding-top:3"><%=board.getRemark()%></td> --%>
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
			<td align="right"><a href="notice_write.jsp"><img src="./images/btn_write.gif"  border="0"></a></td>
		</tr>
	</table>
</td>
<td width="2%">&nbsp;</td>
</tr>
</table>
<!-- bottom include -->
<%@ include file="/admin/admin_bottom.jsp" %>