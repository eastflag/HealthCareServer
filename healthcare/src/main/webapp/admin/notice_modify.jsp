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
			
			if("" == form.end_date.value) {
				form.end_date.value = "20991231";
	        }
			
			/* 
			if(!(isDateYmd(form.end_date.value))){
				alert("날짜의 형태가 잘못되었습니다.\n");
	            form.end_date.focus();
	            return;
			}
			 */
			if(8!=form.end_date.value.length){
				alert("날짜의 형식이 잘못되었습니다.\n");
	            form.end_date.focus();
	            return;
			}
			if(form.content.value.length > 500){
				alert("글 내용의 최대 길이는 500 입니다.\n");
	            form.content.focus();
	            return;
			}
			
			if("" == form.title.value) {
				alert("제목을 입력해 주십시오.\n");
	            form.title.focus();
	            return;
	        }
			
			if(special_pattern.test(form.content.value)){
				alert("금지된 특수문자가 포함되어 있습니다.\n");
	            form.content.focus();
	            return;
			}
		
			
			i++;
			form.action= pAction;
			form.submit();
			
        	ButtonProcessingSmall(form);  // Loading Image
				
		}else {
			alert("처리중입니다.");
			document.location.href="./notice.jsp";
		}
	}
	
	function chkIsFile()
	{
	    if (document.docForm.use_yn_radio[0].checked)
	    { 
	    	document.docForm.use_yn.value = 'Y';
	    	return;
	    }
	    else
	    {
	    	document.docForm.use_yn.value = 'N';
	    	return;
	    }
	}
	
	function isDateYmd(sYmd) {
		if(isEmpty(sYmd) || sYmd.length < 8)   { 
			return false;
		}
		
		var dateArr = new Array();
		
		dateArr[0] = sYmd.substring(0,3);
		dateArr[1] = sYmd.substring(3,5);
		dateArr[2] = sYmd.substring(5,7);

		var dateYmd = new Date(dateArr[0], dateArr[1], dateArr[2]);
			
	    if( dateArr[0] == dateYmd.getFullYear()  && 
	    	dateArr[1] == dateYmd.getMonth()     && 
	    	dateArr[2] == dateYmd.getDate() ) {
	    	return true;
	    	
	    } else {
	    	return false;
	    }
	}
</script>
    <td valign="top" style="padding-left:5"> 
<!-- 컨텐츠영역 시작 -->	
	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="53" valign="bottom" class="darkgray16b"> 공지사항</td>
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
      		<input type="hidden" id="board_type" name="board_type" value="1">
      		<input type="hidden" id="use_yn" name="use_yn" value="<%=board.getUse_yn()%>">
	      <table width="700" border="0" cellspacing="0" cellpadding="0">
	        <tr>                 
	          <td height="25"  align="left" class="darkgray12b" style="padding-left:2">[ 내용 수정 ]</td>
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
	          <td style="padding-left:10">
	          	<input name="title" type="text" class="darkgray12" maxlength="60" value="<%=board.getTitle() %>" style="WIDTH: 130px; HEIGHT: 19px; border:solid 1 #cccccc" >
			  </td>
	        </tr>
	        <tr> 
	          <td height="1" bgcolor="#67AEB8"></td>
	          <td bgcolor="cccccc"></td>
	        </tr>
	        <tr> 
	          <td align="right" bgcolor="F8F6F6" style="padding-right:10">내용</td>
	          <td style="padding-left:10">
	          	<textarea name="content" rows="5" class="darkgray12" style="WIDTH: 100%; border:solid 1 #cccccc"><%=board.getConvertContent() %></textarea>
	          	<br><font color="red">※ &#38; , &#60;, &#62; 문자 사용 불가</font>
			  </td>
	        </tr>
	        <tr>
	          <td height="1" bgcolor="#67AEB8"></td>
	          <td bgcolor="cccccc"></td>
	        </tr>
	        <tr> 
	          <td align="right" bgcolor="F8F6F6" style="padding-right:10">사용여부</td>
	          <td style="padding-left:10">
	          	<input type="radio" name="use_yn_radio" style='margin:0 0 -2 2' onClick="JavaScript:chkIsFile();">활성
	            <input type="radio" name="use_yn_radio" checked style='margin:0 0 -2 2' onClick="JavaScript:chkIsFile();">비활성
			  </td>
	        </tr>
	        <tr> 
	          <td height="1" bgcolor="#67AEB8"></td>
	          <td bgcolor="cccccc"></td>
	        </tr>
	        <tr> 
	          <td align="right" bgcolor="F8F6F6" style="padding-right:10">마감일자</td>
	          <td style="padding-left:10">
	          	<input name="end_date" type="text" class="darkgray12" maxlength="8" value="<%=board.getEnd_date() %>" style="WIDTH: 130px; HEIGHT: 19px; border:solid 1 #cccccc" >
	          	<br><font color="red">※ 입력 방법 : YYYYMMDD(ex. 20140101)</font>
			  </td>
	        </tr>
	        <tr> 
	          <td height="1" bgcolor="#67AEB8"></td>
	          <td bgcolor="cccccc"></td>
	        </tr>
	        <tr> 
	          <td align="right" bgcolor="F8F6F6" style="padding-right:10">비고</td>
	          <td style="padding-left:10">
	          	<textarea name="remark" rows="5" class="darkgray12" style="WIDTH: 100%; border:solid 1 #cccccc" maxlength="60"><%=board.getRemark() %></textarea>
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
				<a href="JavaScript:save(document.docForm, 'notice_modify_action.jsp');"><img src="./images/btn_save.gif" border="0"></a>&nbsp;
				<a href="./notice.jsp"><img src="./images/btn_list.gif" border="0"></a>
			</td>
		</tr>
	</table>  
<!-- 끝 : 버튼 -->
	</td>
<!-- 컨텐츠영역 끝 -->
<td width="2%">&nbsp;</td>
<script type="text/javascript">
	
	window.onload = function(){
		var use_yn = document.docForm.use_yn.value;
	    
	    if(use_yn == "Y"){
	    	document.docForm.use_yn_radio[0].checked = true;
	    	document.docForm.use_yn_radio[1].checked = false;
	    }else if(use_yn == "N"){
	    	document.docForm.use_yn_radio[0].checked = false;
	    	document.docForm.use_yn_radio[1].checked = true;
	    }else{
	    	document.docForm.use_yn_radio[0].checked = false;
	    	document.docForm.use_yn_radio[1].checked = false;
	    }
	};
</script>
<!-- bottom include -->
<%@ include file="/admin/admin_bottom.jsp" %>