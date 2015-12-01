<%@ page import="com.healthcare.admin.helper.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>▒ HealthCare - Admin ▒</title>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="./css/style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="./js/util.js"></script>

<script language="JavaScript">
	var i=0;
	function loginCheck(form,pAction) {
	//두번저장 방지
		if (i==0) {
	//*****************************************************************
	// 필수값 확인
	//*****************************************************************

			if(form.id.value=="") {
	            alert("아이디를 입력해 주십시오.\n");
	            form.id.focus();
	            return;
	        }
	        if(form.passwd.value=="") {
	            alert("비밀번호를 입력해 주십시오.\n");
	            form.passwd.focus();
	            return;
	        }else {
				i++;
				form.action= pAction;
				form.submit();
			}

	//두번이상 저장버튼 클릭한 경우
		}else {
			alert("처리중입니다.");
				document.location.href="admin_login.jsp";
		}
	}
	function f_load(){
		document.loginInput.id.focus();
	}

	function pressEnterKey(){
		if(event.keyCode == 13){
			loginCheck(document.loginInput,'login_action.jsp');
		}else{
			return false;
		}
	}
</script>
</head>

<body leftmargin="0" topmargin="0" onload="f_load();">
<form name="loginInput" method="post">
<table cellpadding="0" cellspacing="0" border="0" width="100%" height="100%">
	<tr>
		<td align="center" valign="middle">
			
	      <table width="324" border="0" cellpadding="0" cellspacing="0" bgcolor="709185">
	        <tr>
	          <td height="30" colspan="3">&nbsp;</td>
	        </tr>
	        <tr>
	          <td width="82" align="right"><font color="#FFFFFF">아이디</font></td>
	          <td width="124" align="center">
				<input name="id" type="text" tabindex=1 class="darkgray12"   style="border:1 solid  #ffffff;height:19px;width:100px" ></td>
	          <td width="118" rowspan="2" align="center">
	          <img src="./images/btn_go.gif" width="59" height="59" border="0" onMouseOver="this.style.cursor='hand'" onclick="JavaScript:loginCheck(document.loginInput,'login_action.jsp');">
	          </td>
	        </tr>
	        <tr>
	          <td align="right"><font color="#FFFFFF">비밀번호</font></td>
	          <td align="center">
				<input name="passwd" type="password" tabindex=2 class="darkgray12"   style="border:1 solid  #ffffff;height:19px;width:100px" onkeypress="javascript:pressEnterKey();"></td>
	        </tr>
	        <tr>
	          <td height="20" colspan="3">&nbsp;</td>
	        </tr>
			  <tr>
			    <td class="gray11" align="center" colspan="3"><font color="white" bold>&nbsp;Copyright (c) MTELO CO., LTD. All rights reserved.</font></td>
			  </tr>
	      </table>
		</td>
	</tr>
</table>
</form>
</body>
</html>
