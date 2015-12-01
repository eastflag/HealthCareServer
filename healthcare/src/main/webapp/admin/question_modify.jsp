<%@page import="com.healthcare.admin.board.Board"%>
<%@ page import="java.util.Collection"%>
<%@ page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%  
	request.setCharacterEncoding("UTF-8");

	BoardMgrHelper boardMgrHelper = new BoardMgrHelper();
	
	Board board = new Board();
	
	board = boardMgrHelper.getBoard(request);
%>
<!-- top include -->
<%@ include file="/admin/admin_top.jsp" %>
<script type="text/javascript">
	
	var special_pattern = /[&\>\<]/g;
	
	var i=0;
	
	function save(form, pAction) { 
		if (i==0) {
			
			i++;
			form.action= pAction;
			form.submit();
			
        	ButtonProcessingSmall(form);  // Loading Image
				
		}else {
			alert("처리중입니다.");
			document.location.href="./question.jsp";
		}
	}
</script>
    <td valign="top" style="padding-left:5"> 
<!-- 컨텐츠영역 시작 -->	
	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="53" valign="bottom" class="darkgray16b"> 질의응답</td>
        </tr>
		<tr>
			<td height="3" bgcolor="#FF5500"></td>
		</tr>
        <tr>
          <td height="20"></td>
        </tr>
      </table>
      <form name="docForm" method="post">
      		<input type="hidden" id="board_id" name="board_id" value="<%=board.getBoard_id()%>">
      		<input type="hidden" id="board_type" name="board_type" value="2">
      		<input type="hidden" id="title" name="title" value="<%=board.getTitle()%>">
      		<input type="hidden" id="name" name="name" value="<%=Util.notNullValueOf (board.getName(), "")%>">
      		<input type="hidden" id="email" name="email" value="<%=board.getEmail()%>">
      		<input type="hidden" id="tel" name="tel" value="<%=board.getTel()%>">
      		<input type="hidden" id="use_yn" name="use_yn" value="<%=board.getUse_yn()%>">
	      <table width="700" border="0" cellspacing="0" cellpadding="0">
	        <tr>                 
	          <td height="25"  align="left" class="darkgray12b" style="padding-left:2">[ 내용 확인 ]</td>
	        </tr>
	      </table>
	<!-- 시작 : 테이블 -->
	      <table width="700" border="0" cellspacing="2" cellpadding="1">
			<tr> 
	          <td width="150" height="2" bgcolor="#67AEB8"></td>
	          <td bgcolor="cccccc"></td>
	        </tr>
	        <tr> 
	          <td align="right" bgcolor="F8F6F6" style="padding-right:10">제목</td>
	          <td style="padding-left:10"><%=board.getTitle() %></td>
	        </tr>
	        <tr> 
	          <td height="1" bgcolor="#67AEB8"></td>
	          <td bgcolor="cccccc"></td>
	        </tr>
	        <tr> 
	          <td align="right" bgcolor="F8F6F6" style="padding-right:10">이름</td>
	          <td style="padding-left:10"><%=Util.notNullValueOf (board.getName(), "") %></td>
	        </tr>
	        <tr> 
	          <td height="1" bgcolor="#67AEB8"></td>
	          <td bgcolor="cccccc"></td>
	        </tr>
	        <tr> 
	          <td align="right" bgcolor="F8F6F6" style="padding-right:10">E-mail</td>
	          <td style="padding-left:10"><a href="mailto:<%=board.getEmail() %>"><%=board.getEmail() %></a></td>
	        </tr>
	        <tr> 
	          <td height="1" bgcolor="#67AEB8"></td>
	          <td bgcolor="cccccc"></td>
	        </tr>
	        <tr> 
	          <td align="right" bgcolor="F8F6F6" style="padding-right:10">연락처</td>
	          <td style="padding-left:10"><%=board.getTel() %></td>
	        </tr>
	        <tr> 
	          <td height="1" bgcolor="#67AEB8"></td>
	          <td bgcolor="cccccc"></td>
	        </tr>
	        <tr> 
	          <td align="right" bgcolor="F8F6F6" style="padding-right:10">내용</td>
	          <td style="padding-left:10">
	          	<textarea name="content" rows="5" class="darkgray12" style="WIDTH: 100%; border:solid 1 #cccccc" readonly="readonly"><%=board.getConvertContent() %></textarea>
			  </td>
	        </tr>
	        <tr> 
	          <td height="1" bgcolor="#67AEB8"></td>
	          <td bgcolor="cccccc"></td>
	        </tr>
	        <tr> 
	          <td align="right" bgcolor="F8F6F6" style="padding-right:10">비고</td>
	          <td style="padding-left:10">
	          	<textarea name="remark" rows="5" class="darkgray12" style="WIDTH: 100%; border:solid 1 #cccccc"  maxlength="119"><%=board.getRemark() %></textarea>
	          </td>
	        </tr>
	        <tr> 
	          <td height="1" bgcolor="#67AEB8"></td>
	          <td bgcolor="cccccc"></td>
	        </tr>
	      </table>   	
	</form>	
<!-- 끝 : 테이블 -->		
<!-- 시작 : 버튼 -->
	<table width="700" border="0" cellspacing="0" cellpadding="0">
		<tr> 
			<td align="right" >
				<a href="JavaScript:save(document.docForm, 'question_modify_action.jsp');"><img src="./images/btn_save.gif" border="0"></a>&nbsp;
				<a href="./question.jsp"><img src="./images/btn_list.gif" border="0"></a>
			</td>
		</tr>
	</table>  
<!-- 끝 : 버튼 -->
	</td>
<!-- 컨텐츠영역 끝 -->
<td width="2%">&nbsp;</td>
<!-- bottom include -->
<%@ include file="/admin/admin_bottom.jsp" %>