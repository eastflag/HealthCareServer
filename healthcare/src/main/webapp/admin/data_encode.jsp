<%@page import="com.healthcare.admin.school.School"%>
<%@page import="com.healthcare.admin.school.Schools"%>
<%@ page import="java.util.Collection"%>
<%@ page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%  
 request.setCharacterEncoding("UTF-8");
%>
<%
%>
<!-- top include -->
<%@ include file="/admin/admin_top.jsp" %>
<script type="text/javascript">
	var i=0;
	
	function dataEncode(encode_type) { 
		var frm = document.docForm;
		frm.encode_type.value = encode_type;
		frm.action = "data_encode_action.jsp";
		if(confirm("해당 데이터를 암호화 하시겠습니까?")) {
			frm.submit();
		}
	}
</script>
    <td valign="top" style="padding-left:5"> 
<!-- 컨텐츠영역 시작 -->	
	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="53" valign="bottom" class="darkgray16b"> Data Encode</td>
        </tr>
		<tr>
			<td height="3" bgcolor="#FF5500"></td>
		</tr>
        <tr>
          <td height="20"></td>
        </tr>
      </table>
      <form name="docForm" method="post">
      <input type="hidden" name="encode_type" />
	      <table width="700" border="0" cellspacing="0" cellpadding="0">
	        <tr>                 
	          <td height="25"  align="left" class="darkgray12b" style="padding-left:2">[ 암호화 ]</td>
	        </tr>
	      </table>
	<!-- 시작 : 테이블 -->
	      <table width="700" border="0" cellspacing="2" cellpadding="1">
			<tr> 
	          <td width="150" height="2" bgcolor="#67AEB8"></td>
	          <td bgcolor="cccccc"></td>
	        </tr>
	        <tr> 
	          <td align="right" bgcolor="F8F6F6" style="padding-right:10">학교이름</td>
	          <td style="padding-left:10">
	          	<a href="javascript:dataEncode('school_name')">암호화</a>
	          </td>
	        </tr>
	        <tr> 
	          <td height="1" bgcolor="#67AEB8"></td>
	          <td bgcolor="cccccc"></td>
	        </tr>
	        <tr> 
	          <td align="right" bgcolor="F8F6F6" style="padding-right:10">학생이름</td>
	          <td style="padding-left:10">
	          	<a href="javascript:dataEncode('stu_name')">암호화</a>
	          </td>
	        </tr>
	        <tr> 
	          <td height="1" bgcolor="#67AEB8"></td>
	          <td bgcolor="cccccc"></td>
	        </tr>
	        <tr> 
	          <td align="right" bgcolor="F8F6F6" style="padding-right:10">전화번호</td>
	          <td style="padding-left:10">
	          	<a href="javascript:dataEncode('mdn')">암호화</a>
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
<!-- 끝 : 버튼 -->
	</td>
<!-- 컨텐츠영역 끝 -->
<td width="2%">&nbsp;</td>
<!-- bottom include -->
<%@ include file="/admin/admin_bottom.jsp" %>