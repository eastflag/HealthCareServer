<%@page import="com.healthcare.admin.school.School"%>
<%@page import="com.healthcare.admin.school.Schools"%>
<%@ page import="java.util.Collection"%>
<%@ page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%  
 request.setCharacterEncoding("UTF-8");

String text = request.getParameter("text");
String result = "";
if(text==null) text="";

if(text!=null && !text.equals("")){
	ExcelMgrHelper excelMgrHelper = new ExcelMgrHelper();
	result = excelMgrHelper.aes256test(request);
}
%>
<%
%>
<!-- top include -->
<%@ include file="/admin/admin_top.jsp" %>
<script type="text/javascript">
	function aes256(type) { 
		var frm = document.docForm;
		if(frm.text.value=="") {
			alert("문자를 입력해주세요.");
			frm.text.focus();
			return;
		} else {
			frm.aes_type.value=type;
			frm.action = "aes256.jsp";
			frm. submit();
		}
	}
</script>
    <td valign="top" style="padding-left:5"> 
<!-- 컨텐츠영역 시작 -->	
	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="53" valign="bottom" class="darkgray16b"> AES256</td>
        </tr>
		<tr>
			<td height="3" bgcolor="#FF5500"></td>
		</tr>
        <tr>
          <td height="20"></td>
        </tr>
      </table>
      <form name="docForm" method="post">
      	<input type="hidden" name="aes_type" id="aes_type" />
	      <table width="700" border="0" cellspacing="0" cellpadding="0">
	        <tr>                 
	          <td height="25"  align="left" class="darkgray12b" style="padding-left:2">[ 내용 등록 ]</td>
	        </tr>
	      </table>
	<!-- 시작 : 테이블 -->
	      <table width="700" border="0" cellspacing="2" cellpadding="1">
			<tr> 
	          <td width="150" height="2" bgcolor="#67AEB8"></td>
	          <td bgcolor="cccccc"></td>
	        </tr>
	        <tr> 
	          <td align="right" bgcolor="F8F6F6" style="padding-right:10">TEXT</td>
	          <td style="padding-left:10">
	          	<input type="text" name="text" value="<%=text%>"/>
	          </td>
	        </tr>
	        <tr> 
	          <td height="1" bgcolor="#67AEB8"></td>
	          <td bgcolor="cccccc"></td>
	        </tr>
	        <%if(result!=null && !result.equals("")){ %>
	        <tr> 
	          <td align="right" bgcolor="F8F6F6" style="padding-right:10">결과</td>
	          <td style="padding-left:10">
	          	<%=result %>
	          </td>
	        </tr>
	        <tr> 
	          <td height="1" bgcolor="#67AEB8"></td>
	          <td bgcolor="cccccc"></td>
	        </tr>
	        <%} %>
	      </table>   	
	</form>	
<!-- 끝 : 테이블 -->		
<!-- 시작 : 버튼 -->
	<table width="700" border="0" cellspacing="0" cellpadding="0">
		<tr> 
			<td align="right" >
				<a href="JavaScript:aes256('E');">암호화</a>&nbsp;
				<a href="JavaScript:aes256('D');">복호화</a>
			</td>
		</tr>
	</table>  
<!-- 끝 : 버튼 -->
	</td>
<!-- 컨텐츠영역 끝 -->
<td width="2%">&nbsp;</td>
<!-- bottom include -->
<%@ include file="/admin/admin_bottom.jsp" %>