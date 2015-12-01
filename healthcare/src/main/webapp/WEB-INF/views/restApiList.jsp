<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

<meta charset="UTF-8">
<title>HealthCare-Server RestAPI List</title>
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
	
	function ajaxCall() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : rootPath + "/" + $("#methodName > option:selected").val(),
			contentType : 'application/json',
			cache : false,
			data : "{\"userId\":\"" + $("#userId").val() + 
					"\",\"token\":\"" + $("#token").val() + 
					"\"}",
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

	<h3>Service API</h3>
	<form name="GetHeight" method="post">
		<table border="1" class="GridOne">
			<tr>
				<td colspan="2" align="left">HealthCare/					
					<select id="methodName">
						<option value="GetBodyMeasureSummary">GetBodyMeasureSummary
						<option value="GetHeight">GetHeight
						<option value="GetHeightHistory">GetHeightHistory
						<option value="GetWeight">GetWeight
						<option value="GetWeightHistory">GetWeightHistory
						<option value="GetBmi">GetBmi
						<option value="GetSmoke">GetSmoke
						<option value="GetRankingOfHeightPerClass">GetRankingOfHeightPerClass
						<option value="GetRankingOfWeightPerClass">GetRankingOfWeightPerClass
						<option value="GetRankingOfBmiPerClass">GetRankingOfBmiPerClass
						<option value="GetScore">GetScore
					</select>
					 <input
					type="button" value="ajaxCall"
					onclick="ajaxCall();">
				</td>
			</tr>
			<tr>
				<td>userId</td>
				<td><input type="text" id="userId" value="377"></td>
			</tr>
			<tr>
				<td>token</td>
				<td><input type="text" id="token" value="token"></td>
			</tr>
		</table>
	</form>
	

	<div id="result"></div>
</body>
</html>