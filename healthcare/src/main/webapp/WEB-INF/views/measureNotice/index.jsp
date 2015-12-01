<%@page import="com.healthcare.admin.helper.SchoolMgrHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.healthcare.admin.school.School"%>
<%@page import="com.healthcare.admin.school.Schools"%>
<%@ page import="java.util.Collection"%>
<%@ page import="java.util.Iterator"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%  
 request.setCharacterEncoding("UTF-8");
%>
<%
	SchoolMgrHelper schoolMgrHelper = new SchoolMgrHelper();
	School school = null;
	Schools schools = schoolMgrHelper.getListSchool(request);
	Iterator itSchool = schools.getSchools().iterator(); 
%>
<!DOCTYPE html>
<html>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

<meta charset="UTF-8">
<title>HealthCare-Server Demo</title>
<style>
Table.GridOne {
	padding: 3px;
	margin: 0;
	background: lightyellow;
	border-collapse: collapse;
	width: 50%;
}

Table.GridOne Td {
	padding: 2px;
	border: 1px solid #ff9900;
	border-collapse: collapse;
}

#result{
width:50%; 
height:100%;
position:absolute;
left:50%;
top:0%;
margin-left:0%;
border: 1px solid #ff9900;
}
</style>

<c:set var="baseURL" value="${pageContext.request.contextPath}"/>

<script type="text/javascript">
	var rootPath = "${baseURL}";
	
	function callGcm() {
		if($("#schoolId option:selected").val() == null ||
			$("#schoolId option:selected").val() == ''){
			alert('학교를 선택해주십시오.');
			return;
		}
		$.ajax({
			type : "post",
			dataType : "json",
			url : rootPath + "/measureNotice/CallGcm",
			contentType : 'application/json',
			cache : false,
			data : "{\"schoolId\":\"" + $("#schoolId option:selected").val() + "\"," +
					"\"pushType\":\"" + $("#pushType option:selected").val() + "\"}",
			success : function(response) {
				//alert(JSON.stringify(response));
				//$('#result').html("");
				//var obj = JSON.parse(response);
				$('#result').html(JSON.stringify(response));
			},
			error : function() {
				alert('Error while request..');
			}
		});
	}
	

</script>
</head>
<body>

	<h3>CallGcm</h3>
	<form name="CallGcm" method="post">
		<table border="1" class="GridOne">
			<tr>
				<td colspan="2" align="left">HealthCare/CallGcm
					<input type="button" value="CallGcm" onclick="callGcm();">
				</td>
			</tr>
			<tr>
				<td>schoolId</td>
				<td>
					<select id="schoolId" name="schoolId">
						<option value = "">선택</option>
						<%
							while(itSchool.hasNext()){
								school = (School)itSchool.next();
						%>
						<option value="<%=school.getSchool_id()%>"><%=school.getSchool_name()%>&nbsp;&nbsp;&nbsp;&nbsp;</option>
		               	<%
							}
		               	%>
					</select>
				</td>
			</tr>
			<tr>
				<td>pushType</td>
				<td>
					<select id="pushType" name="pushType">
						<option value = "1">정보 업데이트</option>
						<option value = "2">이벤트 알림</option>
					</select>
				</td>
			</tr>
		</table>
		<br>
		측정을 종료하였음을 학부모에게 통보할 학교의 school_id를 입력하세요.
	</form>
	<!-- 
	<h3>CallGcm</h3>
	<form name="CallGcm" method="post">
		<table border="1" class="GridOne">
			<tr>
				<td colspan="2" align="left">HealthCare/CallGcm <input
					type="button" value="CallGcm"
					onclick="callGcm();">
				</td>
			</tr>
			<tr>
				<td>schoolId</td>
				<td><input type="text" id="schoolId" value="4"></td>
			</tr>
		</table>
		<br>
		측정을 종료하였음을 학부모에게 통보할 학교의 school_id를 입력하세요.
	</form>
	 -->

	<div id="result"></div>
</body>
</html>