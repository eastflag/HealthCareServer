<%@page import="com.healthcare.admin.adminManage.*"%>
<%@ page import="java.util.Collection"%>
<%@ page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%  
 request.setCharacterEncoding("UTF-8");
%>
<%
	String masterId[] = {"manager","mtelomaster"};
	AdminManageMgrHelper adminManageMgrHelper =	new AdminManageMgrHelper();
	AdminManage adminManage = null;
	adminManage = adminManageMgrHelper.getAdminManage(request);
%>
<!-- top include -->
<%@ include file="/admin/admin_top.jsp" %>
<script type="text/javascript">

	var idChk = 'Y';
	var buttonClickCnt=0;
	var originalId = '<%=adminManage.getAdmin_id()%>';
	var finalChkId = '<%=adminManage.getAdmin_id()%>';
	
	function duplicationAdminId(){
		
		var admin_id = document.getElementsByName("admin_id")[0].value;
		
		if(isChkValidPolicyStr(admin_id)){
			document.getElementsByName("admin_id")[0].focus();
			return;
		}
		
		if('' == admin_id ||
			null == admin_id ||
			'undefined' == admin_id){
			
			alert('아이디를 입력해주세요.\n');
			document.getElementsByName("admin_id")[0].focus();
			
		}else{
			if(originalId == admin_id){
				alert('동일한 아이디입니다.\n');
				idChk = 'Y';
				finalChkId = admin_id;
			}else{
				var parameterVal = "admin_id="+admin_id;
				
				var xmlhttp;
				if (window.XMLHttpRequest){// code for IE7+, Firefox, Chrome, Opera, Safari
					xmlhttp=new XMLHttpRequest();
				}else{// code for IE6, IE5
					xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
				}
				xmlhttp.onreadystatechange=function(){
					if (xmlhttp.readyState==4 && xmlhttp.status==200){
						var chkresponseText = xmlhttp.responseText;
						chkresponseText = chkresponseText.replace(/(^\s*)|(\s*$)/, '');
						chkresponseText = chkresponseText.replace(/\s*$/, '');
						if(chkresponseText == 'N'){
							alert('사용 가능한 아이디입니다.');
							idChk = 'Y';
							finalChkId = admin_id;
						}else{
							alert('중복된 아이디입니다.');
						}
					}
				}
				xmlhttp.open("POST","./admin_manage_duplication_chk.jsp",true);
				xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
				xmlhttp.send(parameterVal);
			}
		}
	}
	function save(form, pAction) {
		if(0 == buttonClickCnt){
			if("" == form.admin_id.value) {
	            alert("아이디를 입력해 주십시오.\n");
	            form.admin_id.focus();
	            return;
	        }
			if("" == form.admin_pass.value) {
	            alert("비밀번호를 입력해 주십시오.\n");
	            form.admin_pass.focus();
	            return;
	        }
			if("" == form.admin_pass_confirm.value) {
	            alert("재확인 비밀번호를 입력해 주십시오.\n");
	            form.admin_pass_confirm.focus();
	            return;
	        }
			/*
			if(('N'==idChk)||
				(finalChkId != form.admin_id.value && idChk == 'Y')){
				alert("중복체크 해주십시오.\n");
				return;
			}
			if(isChkValidPolicyStr(form.admin_id.value)){
	            form.admin_id.focus();
	            return;
			}
			*/
			if(isChkValidPolicyStr(form.admin_pass.value)){
	            form.admin_pass.focus();
	            return;
			}
			if(form.admin_pass.value != form.admin_pass_confirm.value){
				alert("비밀번호가 일치하지않습니다.\n");
	            form.admin_pass_confirm.focus();
	            return;
			}else{
				buttonClickCnt++;
				form.action= pAction;
				form.submit();
				
	        	ButtonProcessingSmall(form);  // Loading Image
	            return false;
			}
		}else {
			alert("처리중입니다.");
			document.location.href="./admin_manage.jsp";
		}
	}
	
	function changeIdChk(){
		var modifyId = document.getElementsByName("admin_id")[0].value;
		if(originalId == modifyId){
			idChk = 'Y';
		}else{
			idChk = 'N';
		}
	}
	
	function isChkValidPolicyStr(pStr){
		var ch;
		if( (pStr.length < 4) || (pStr.length > 15) ){
			alert("문자열의 길이제한은 4 ~ 15자이며 \n숫자와 영문 대소문자는 사용가능하지만 \n[', \", ?, %, ^, &, \] 해당 특수문자는 사용할 수 없습니다.");
			return true;
		}else{
			for (var i=0;i<pStr.length;i++) {
				ch = pStr.charAt(i);
				if(ch=="'"||ch=="\""||ch=="?"||ch=="%"||ch=="^"||ch=="&"||ch=="\\"||!((ch >= "0" && ch <= "9") || (ch >= "a"  && ch <= "z") ||(ch >= "A"  && ch <= "Z"))){
					alert("문자열의 길이제한은 4 ~ 15자이며 \n숫자와 영문 대소문자는 사용가능하지만 \n[', \", ?, %, ^, &, \] 해당 특수문자는 사용할 수 없습니다.");
					return true;
				}
			}
		}
		return false;
	}
	
</script>
    <td valign="top" style="padding-left:5"> 
<!-- 컨텐츠영역 시작 -->	
	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="53" valign="bottom" class="darkgray16b">계정관리</td>
        </tr>
		<tr>
			<td height="3" bgcolor="#FF5500"></td>
		</tr>
        <tr>
          <td height="20"></td>
        </tr>
      </table>
      <form name="docForm" method="post">
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
	          <td align="right" bgcolor="F8F6F6" style="padding-right:10">학교명</td>
	          <td style="padding-left:10">
	          	<input type="hidden" name="school_id" value="<%=adminManage.getSchool_id() %>">
	          	<%=adminManage.getSchool_name() %>
	          </td>
	        </tr>
	        <tr> 
	          <td height="1" bgcolor="#67AEB8"></td>
	          <td bgcolor="cccccc"></td>
	        </tr>
	        <tr> 
	          <td align="right" bgcolor="F8F6F6" style="padding-right:10">아이디</td>
	          <td style="padding-left:10">
	          <%-- 
	          	<input name="admin_id" type="text" class="darkgray12" maxlength="15" style="WIDTH: 130px; HEIGHT: 19px; border:solid 1 #cccccc" value="<%=adminManage.getAdmin_id()%>" onblur="javascript:changeIdChk();">
	          	<input type="button" value="중복체크" onclick="duplicationAdminId();">
	          	 --%>
	          	 <input type="hidden" name="admin_id" value="<%=adminManage.getAdmin_id() %>">
	          	<%=adminManage.getAdmin_id()%>
			  </td>
	        </tr>
	        <tr> 
	          <td height="1" bgcolor="#67AEB8"></td>
	          <td bgcolor="cccccc"></td>
	        </tr>
	        <tr> 
	          <td align="right" bgcolor="F8F6F6" style="padding-right:10">비밀번호</td>
	          <td style="padding-left:10">
	          	<input name="admin_pass" type="password" class="darkgray12" maxlength="15" style="WIDTH: 130px; HEIGHT: 19px; border:solid 1 #cccccc" value="<%=adminManage.getAdmin_pass()%>">
			  </td>
	        </tr>
	        <tr> 
	          <td height="1" bgcolor="#67AEB8"></td>
	          <td bgcolor="cccccc"></td>
	        </tr>
	        <tr> 
	          <td align="right" bgcolor="F8F6F6" style="padding-right:10">비밀번호 재확인</td>
	          <td style="padding-left:10">
	          	<input name="admin_pass_confirm" type="password" class="darkgray12" maxlength="15" style="WIDTH: 130px; HEIGHT: 19px; border:solid 1 #cccccc" >
			  </td>
	        </tr>
	        <tr> 
	          <td height="1" bgcolor="#67AEB8"></td>
	          <td bgcolor="cccccc"></td>
	        </tr>
	        <tr> 
	          <td align="right" bgcolor="F8F6F6" style="padding-right:10">관리자 정보</td>
	          <td style="padding-left:10">
	          	<input name="admin_info" type="text" class="darkgray12" maxlength="60" style="WIDTH: 130px; HEIGHT: 19px; border:solid 1 #cccccc" value="<%=adminManage.getAdmin_info()%>">
			  </td>
	        </tr>
	        <tr> 
	          <td height="1" bgcolor="#67AEB8"></td>
	          <td bgcolor="cccccc"></td>
	        </tr>
	        <tr <%if(!(masterId[0].equals(menuLoginName)||masterId[1].equals(menuLoginName)))out.print("style='display:none'");%>> 
	          <td align="right" bgcolor="F8F6F6" style="padding-right:10">사용 여부</td>
	          <td style="padding-left:10">
	          	<select class="darkgray12" id="use_yn" name="use_yn">
	          		<option value="y">허용</option>
	          		<option value="n" <%if("n".equals(adminManage.getUse_yn()))out.print("selected");%>>금지</option>
	          	</select>
			  </td>
	        </tr>
	        <tr <%if(!masterId.equals(menuLoginName))out.print("style='display:none'");%>> 
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
				<a href="JavaScript:save(document.docForm, 'admin_manage_modify_action.jsp');"><img src="./images/btn_save.gif" border="0"></a>&nbsp;
				<a href="./admin_manage.jsp"><img src="./images/btn_list.gif" border="0"></a>
			</td>
		</tr>
	</table>  
<!-- 끝 : 버튼 -->
	</td>
<!-- 컨텐츠영역 끝 -->
<td width="2%">&nbsp;</td>
<!-- bottom include -->
<%@ include file="/admin/admin_bottom.jsp" %>