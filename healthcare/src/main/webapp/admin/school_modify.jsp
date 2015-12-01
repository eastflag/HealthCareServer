<%@page import="com.healthcare.admin.school.School"%>
<%@ page import="java.util.Collection"%>
<%@ page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%  
	request.setCharacterEncoding("UTF-8");

	SchoolMgrHelper schoolMgrHelper = new SchoolMgrHelper();
	
	School school = new School();
	
	school = schoolMgrHelper.getSchool(request);

%>
<!-- top include -->
<%@ include file="/admin/admin_top.jsp" %>
<script type="text/javascript">
	
	var special_pattern = /[&\>\<]/g;
	
	var i=0;

	function save(form, pAction) { 
		if (i==0) {
			
			if("" == form.school_name.value) {
				alert("학교명을 입력해 주십시오.\n");
	            form.school_name.focus();
	            return;
	        }
			
			if(special_pattern.test(form.school_name.value)){
				alert("학교명에 금지된 특수문자는 사용할 수 없습니다..\n");
	            form.school_name.focus();
	            return;
			}		
			
			i++;
			form.action= pAction;
			form.submit();
			
        	ButtonProcessingSmall(form);  // Loading Image
				
		}else {
			alert("처리중입니다.");
			document.location.href="./school.jsp";
		}
	}
	
	function chkSection()
	{
	    if (document.docForm.section_radio[0].checked)
	    { 
	    	document.docForm.section.value = 'E';
	    	return;
	    }
	    else if (document.docForm.section_radio[1].checked)
	    { 
	    	document.docForm.section.value = 'M';
	    	return;
	    }
	    else if (document.docForm.section_radio[2].checked)
	    { 
	    	document.docForm.section.value = 'H';
	    	return;
	    }
	}
</script>
    <td valign="top" style="padding-left:5"> 
<!-- 컨텐츠영역 시작 -->	
	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="53" valign="bottom" class="darkgray16b">학교관리</td>
        </tr>
		<tr>
			<td height="3" bgcolor="#FF5500"></td>
		</tr>
        <tr>
          <td height="20"></td>
        </tr>
      </table>
      <form name="docForm" method="post">
      	  	<input type="hidden" name="section" value="<%=school.getSection()%>">
      		<input type="hidden" id="school_id" name="school_id" value="<%=school.getSchool_id()%>">
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
	          <td align="right" bgcolor="F8F6F6" style="padding-right:10">학교명</td>
	          <td style="padding-left:10">
	          	<input name="school_name" type="text" value="<%= school.getSchool_name() %>" class="darkgray12" maxlength="30" style="WIDTH: 130px; HEIGHT: 19px; border:solid 1 #cccccc" >
			  </td>
	        </tr>
	        <tr>
	          <td height="1" bgcolor="#67AEB8"></td>
	          <td bgcolor="cccccc"></td>
	        </tr>
	        <tr> 
	          <td align="right" bgcolor="F8F6F6" style="padding-right:10">구분</td>
	          <td style="padding-left:10">
	          	<input type="radio" name="section_radio" checked style='margin:0 0 -2 2' onClick="JavaScript:chkSection();">초등학교&nbsp;
	            <input type="radio" name="section_radio" style='margin:0 0 -2 2' onClick="JavaScript:chkSection();">중학교&nbsp;
	            <input type="radio" name="section_radio" style='margin:0 0 -2 2' onClick="JavaScript:chkSection();">고등학교&nbsp;
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
				<a href="JavaScript:save(document.docForm, 'school_modify_action.jsp');"><img src="./images/btn_save.gif" border="0"></a>&nbsp;
				<a href="./school.jsp"><img src="./images/btn_list.gif" border="0"></a>
			</td>
		</tr>
	</table>  
<!-- 끝 : 버튼 -->
	</td>
<!-- 컨텐츠영역 끝 -->
<td width="2%">&nbsp;</td>
<script type="text/javascript">
	
	window.onload = function(){
		var section = document.docForm.section.value;
	    
	    if(section == "E"){
	    	document.docForm.section_radio[0].checked = true;
	    	document.docForm.section_radio[1].checked = false;
	    	document.docForm.section_radio[2].checked = false;
	    } else if(section == "M"){
	    	document.docForm.section_radio[0].checked = false;
	    	document.docForm.section_radio[1].checked = true;
	    	document.docForm.section_radio[2].checked = false;
	    } else if(section == "H"){
	    	document.docForm.section_radio[0].checked = false;
	    	document.docForm.section_radio[1].checked = false;
	    	document.docForm.section_radio[2].checked = true;
	    }
	};
</script>
<!-- bottom include -->
<%@ include file="/admin/admin_bottom.jsp" %>