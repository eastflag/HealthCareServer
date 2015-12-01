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
	
	function save(form, pAction) { 
		if (i==0) {
			if("" == form.excel_url.value) {
	            alert("파일을 선택해 주십시오.");
	            form.excel_url.focus();
	            return;
	        }
			if(!checkFileType(form.excel_url.value)) {
				alert("엑셀파일만 업로드 해주세요.");
	            form.excel_url.focus();
	            return;
			}
			i++;
			form.action= pAction;
			form.submit();

        	ButtonProcessingSmall(form);  // Loading Image
				
		}else {
			alert("처리중입니다.");
			document.location.href="./excel_upload.jsp";
		}
	}
	function checkFileType(filePath) {
		 var i = filePath.lastIndexOf(".");  
		 var fileFormat = filePath.substring(i).toLowerCase();  
		 console.log(fileFormat);
		 
		 if (fileFormat == ".xls" || fileFormat == ".xlsx") {  
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
          <td height="53" valign="bottom" class="darkgray16b"> Excel Upload</td>
        </tr>
		<tr>
			<td height="3" bgcolor="#FF5500"></td>
		</tr>
        <tr>
          <td height="20"></td>
        </tr>
      </table>
      <form name="docForm" method="post" enctype="multipart/form-data">
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
	          <td align="right" bgcolor="F8F6F6" style="padding-right:10">Excel File</td>
	          <td style="padding-left:10">
	          	<input type="file" name="excel_url"/>
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
				<a href="JavaScript:save(document.docForm, 'excel_upload_action.jsp');"><img src="./images/btn_save.gif" border="0"></a>&nbsp;
				<a href="./excel_upload.jsp"><img src="./images/btn_list.gif" border="0"></a>
			</td>
		</tr>
	</table>  
<!-- 끝 : 버튼 -->
	</td>
<!-- 컨텐츠영역 끝 -->
<td width="2%">&nbsp;</td>
<!-- bottom include -->
<%@ include file="/admin/admin_bottom.jsp" %>