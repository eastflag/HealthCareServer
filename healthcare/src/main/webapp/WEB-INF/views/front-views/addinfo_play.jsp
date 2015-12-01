<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%

	//String pType = request.getParameter("p") == null ? "height" : request.getParameter("p");
	String pUserId = request.getParameter("userId");
	String pToken = request.getParameter("token");
	String isUpdate="N";
	isUpdate = (String)request.getAttribute("isUpdate");
%>
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no"/>
    <title>health care</title>

	<link rel="stylesheet" href="css/web/food-basic.css">
	
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
  	<script type="text/javascript" src="js/jquery.mobile-events.min.js"></script>
  	<script type="text/javascript" src="js/util.js"></script>
	
	<script type="text/javascript">
		$.ajaxSetup ({
			cache: false
		});
	</script>
<style>
	
	.addMenu{width:100%; height:100%; margin:0px;}
	.addMenu table{width:100%; height:100%;}
	.addMenu table{width:70%; height:100%; margin:auto;}
	.addMenu table td{height:100%;  vertical-align:middle; color:#fff; font-weight:bold; text-align:center; }
	.addMenu table td ul li{width:100%; padding-top:15px; padding-bottom:15px; cursor:pointer; margin-bottom:25px;}
	
	.simliMenu{width:100%; height:100%;}
	.simliMenu table{width:70%; height:100%; margin:auto;}
	.simliMenu table td{height:100%; vertical-align:middle; color:#fff; font-weight:bold; font-size:18px; text-align:center; line-height:20px;}
	.simliMenu table td div{cursor:pointer;}
	
	.simliList{width:100%; height:100%;}
	.simliListTable{width:70%; height:100%; margin:auto;}
	.simliListTable td{height:100%;  vertical-align:middle; color:#fff; font-weight:bold; text-align:center; }
	.simliListTable td ul li{width:100%; padding-top:15px; padding-bottom:15px; cursor:pointer; margin-bottom:25px;}
	
	.title{width:91%; background-color:#7f7f7f; color:#fff; text-align:center; font-size:18px;
		margin:auto; margin-bottom:15px;  padding-top:10px; padding-bottom:10px; font-weight:bold; }
	.simliResultInfo{width:100%; padding-top:10px;}
	.simliResultInfo div div{padding-left:10px; padding-right:10px;}
	.simliResultInfo .result1{width:91%; margin:auto; margin-top:10px;background-color:#b1c4db; color:#fff; line-height:18px; 
		padding-top:10px; padding-bottom:10px; }
	.simliResultInfo .result2{width:91%; margin:auto; margin-top:10px; height:300px; background-color:#d9b2af; color:#fff; line-height:18px; 
		overflow-y:scroll; padding-top:10px; padding-bottom:10px;}
	.simliResultInfo .btnDiv{width:91%; margin:auto; margin-top:10px; background-color:#c7c7c7; height:30px; text-align:center;  
		font-weight:bold; padding-top:10px; cursor:pointer; }
</style>	
</head>
<body>
<div class="bodyWrap">
	<!-- addMenu start -->
	<div class="addMenu">
	</div>
	<!-- addMenu end -->
	
	<!-- simliMenu start -->
	<div class="simliMenu" style="display:none;">
	</div>
	<!-- simliMenu end -->
	
	<!-- simliList start -->
	<div class="simliList" style="display:none;">
	</div>
	<!-- simliList end -->
	
	<!-- simliQuestion start -->
	<div class="simliQuestion" style="display:none;">
	</div>
	<!-- simliQuestion end -->
	
	<!-- simliResultList start -->
	<div class="simliResultList" style="display:none;">
	</div>
	<!-- simliResultList end -->
	
	<!-- simliResultInfo start -->
	<div class="simliResultInfo" style="display:none;">
	</div>
	<!-- simliResultInfo end -->
</div>
</body>
<script type="text/javascript">
$(document).ready(function() {
	var device_width = 0;	//접속 디바이스 width값을 저장하는 변수
	var device_height = 0;	//접속 디바이스 height값을 저장하는 변수
	device_size();
	showAddMenu();
});
//디바이스 사이즈를 구해서 변수에 저장
function device_size(){
	 device_width = viewportSize().width;
	 device_height = viewportSize().height;
}

function viewportSize(){
	  var size_div = document.createElement( "div" );
	 
	  size_div.style.cssText = "position:fixed;top:0;left:0;bottom:0;right:0;";
	  document.documentElement.insertBefore(size_div, document.documentElement.firstChild );
	 
	  var size_res = {width:size_div.offsetWidth, height:size_div.offsetHeight };
	  document.documentElement.removeChild(size_div);
	 
	  return size_res;
}

function showAddMenu() {
	/*
	var addMenu = '';
	addMenu += '<table>';
	addMenu += '<tr><td style="height:'+device_height+'px;">';
	addMenu += '<ul>';
	addMenu += '<li onclick="showSimliMenu();" style="background-color:#9f4c08;">심리 검사</li>';
	//addMenu += '<li onclick="" style="background-color:#bccada;">----</li>';
	//addMenu += '<li onclick="" style="background-color:#e4edcf;">----</li>';
	//addMenu += '<li onclick="" style="background-color:#dcd2e6; margin-bottom:0px;">----</li>';
	addMenu += '</ul>';
	addMenu += '</td></tr>';
	addMenu += '</table>';


	addMenu += '';
	addMenu += '';
	addMenu += '';
	$(".addMenu").html(addMenu);
	*/
	
	var simliQuestList = '';
	//simliQuestList += "<div class='simlitestplay'  id='simlitestplay'";
	//simliQuestList += "<div class='fl'  id='video-container'  >";
	simliQuestList += "<table width='700px'>";
	//simliQuestList += "  <tr>";
	//simliQuestList += "	<td colspan='2' style='font-size:20px; font-weight:bold;padding-left:10px; padding-right:10px; padding-top:20px; padding-bottom:20px; line-height:24px;''>";
	//simliQuestList += "	   다음보기 문항보고 적절하다고 생각하는것을 고르세요. ";
	//simliQuestList += "	</td> ";
	//simliQuestList += "</tr> ";
	simliQuestList += "<tr> ";
	simliQuestList += "	<td> ";
	simliQuestList += "		<video id='container_quest1' width='300' height='180'  src='http://210.127.55.205/contents/simlitest_media/video/answer/A01-1.MP4' poster='http://210.127.55.205/contents/simlitest_media/thumnail/A01-1.png'   autoplay controls fullscreen='auto'></video>";
	simliQuestList += "	</td>";
	simliQuestList += "	<td>";
	simliQuestList += "		<video id='container_quest2' width='300' height='180'  src='http://210.127.55.205/contents/simlitest_media/video/answer/A01-1.MP4' poster='http://210.127.55.205/contents/simlitest_media/thumnail/A01-2.png' controls></video>";
	simliQuestList += "	</td>";
	simliQuestList += "	</tr> ";
	simliQuestList += "	<tr>";
	simliQuestList += "	<td> ";
	simliQuestList += "		<video id='container_quest3' width='300' height='180'  src='http://210.127.55.205/contents/simlitest_media/video/answer/A01-1.MP4' poster='http://210.127.55.205/contents/simlitest_media/thumnail/A01-3.png' controls></video>";
	simliQuestList += "	</td>";
	simliQuestList += "	<td>   <img src='simbol.jpg' width='300' height='180'  /></td>";
	simliQuestList += "  </tr>";
	simliQuestList += "  </table>";
	//simliQuestList += " </div>";
	//simliQuestList += " </div>";
	
	$(".addMenu").html(simliQuestList);
}

function showSimliMenu() {
	$(".addMenu").hide();
	$(".simliResultInfo").hide();
	$(".simliMenu").show();
	
	var simliMenu = '';
	simliMenu += '<table>';
	simliMenu += '<tr><td style="height:'+device_height+'px;">';
	simliMenu += '<div onclick="showSimliList();" style="padding:20px 0 20px 0;background-color:#9f4c08; margin-bottom:86px;">심리검사</div>';
	simliMenu += '<div onclick="simliResultList();" class="" style="padding:10px 0 10px 0;background-color:#53672b;">심리검사<br/>결과보기</div>';
	simliMenu += '</td></tr>';
	simliMenu += '</table>';
	$(".simliMenu").html(simliMenu);
}
function showSimliList() {
	$(".simliMenu").hide();
	$(".simliList").show();
	
	var simliList = '';
	simliList += '<table class="simliListTable">';
	simliList += '<tr><td style="height:'+device_height+'px;">';
	simliList += '<ul>';
	simliList += '<li onclick="showSimliStart(0);" style="background-color:#9f4c08;">CDI 소아 우울 척도</li>';
	simliList += '<li onclick="showSimliStart(1);" style="background-color:#bccada;">ADHD 검사</li>';
	simliList += '<li onclick="showSimliStart(2);" style="background-color:#e4edcf;">성격유형검사</li>';
	simliList += '<li onclick="showSimliStart(3);" style="background-color:#dcd2e6;">불안검사</li>';
	simliList += '<li onclick="showSimliStart(4);" style="background-color:#cbe9f1;">HOLLAND 진로검사</li>';
	simliList += '<li onclick="showSimliStart(5);" style="background-color:#ffe2c9; margin-bottom:0px;">강점 난점 검사</li>';
	simliList += '</ul>';
	simliList += '</td></tr>';
	simliList += '</table>';
	simliList += '';
	simliList += '';
	simliList += '';
	simliList += '';
	simliList += '';
	simliList += '';
	$(".simliList").html(simliList);
}
function showSimliStart(num) {
	if(num==0) {
		showSimliQuestStart();
	} else if(num==1) {
		//alert("아직 검사기간이 아닙니다.");
	}
}

function showSimliQuestStart(){
	$(".simliList").hide();
	$(".simliQuestion").show();
		var simliQuestList = '';
		//simliQuestList += "<div class='simlitestplay'  id='simlitestplay'";
		//simliQuestList += "<div class='fl'  id='video-container'  >";
		simliQuestList += "<table>";
		simliQuestList += "  <tr>";
		simliQuestList += "	<td colspan='2' style='font-size:20px; font-weight:bold;padding-left:10px; padding-right:10px; padding-top:20px; padding-bottom:20px; line-height:24px;''>";
		simliQuestList += "	   다음보기 문항보고 적절하다고 생각하는것을 고르세요. ";
		simliQuestList += "	</td> ";
		simliQuestList += "</tr> ";
		simliQuestList += "<tr> ";
		simliQuestList += "<td>1";
		simliQuestList += "</td>";
		simliQuestList += "";
		simliQuestList += "	<td> ";
		simliQuestList += "		<video id='container_quest1' width='180' height='140'  src='http://210.127.55.205/contents/simlitest_media/video/answer/A01-1.MP4' poster='http://210.127.55.205/contents/simlitest_media/thumnail/A01-1.png'   autoplay controls fullscreen='auto'></video>";
		simliQuestList += "	</td>";
		simliQuestList += "	<td>";
		simliQuestList += "		<video id='container_quest2' width='180' height='140'  src='http://210.127.55.205/contents/simlitest_media/video/answer/A01-1.MP4' poster='http://210.127.55.205/contents/simlitest_media/thumnail/A01-2.png'></video>";
		simliQuestList += "	</td>";
		simliQuestList += "	</tr> ";
		simliQuestList += "	<tr>";
		simliQuestList += "	<td>";
		simliQuestList += "		<video id='container_quest3' width='180' height='140'  src='http://210.127.55.205/contents/simlitest_media/video/answer/A01-1.MP4' poster='http://210.127.55.205/contents/simlitest_media/thumnail/A01-3.png'></video>";
		simliQuestList += "	</td>";
		simliQuestList += "	<td>   <img src='images/icon/simbol.jpg' width='180' height='140'  /></td>";
		simliQuestList += "  </tr>";
		simliQuestList += "  </table>";
		//simliQuestList += " </div>";
		//simliQuestList += " </div>";
		
		$(".simliQuestion").html(simliQuestList);
}

function simliResultList() {
	$(".simliMenu").hide();
	$(".simliResultList").show();
	
	var simliResultList = '';
	simliResultList += '<table class="simliListTable">';
	simliResultList += '<tr><td style="height:'+device_height+'px;">';
	simliResultList += '<ul>';
	simliResultList += '<li onclick="showSimliResultInfo(0);" style="background-color:#9f4c08;">CDI 소아 우울 척도 결과</li>';
	simliResultList += '<li onclick="showSimliResultInfo(1);" style="background-color:#bccada;">ADHD 검사 결과</li>';
	simliResultList += '<li onclick="showSimliResultInfo(2);" style="background-color:#e4edcf;">성격유형검사 결과</li>';
	simliResultList += '<li onclick="showSimliResultInfo(3);" style="background-color:#dcd2e6;">불안검사 결과</li>';
	simliResultList += '<li onclick="showSimliResultInfo(4);" style="background-color:#cbe9f1;">HOLLAND 진로검사 결과</li>';
	simliResultList += '<li onclick="showSimliResultInfo(5);" style="background-color:#ffe2c9;">강점 난점 검사 결과</li>';
	simliResultList += '<li onclick="showSimliResultInfo(5);" style="background-color:#e4e4e4; color:gray; margin-bottom:0px;">종합의견</li>';
	simliResultList += '</ul>';
	simliResultList += '</td></tr>';
	simliResultList += '</table>';
	simliResultList += '';
	simliResultList += '';
	simliResultList += '';
	simliResultList += '';
	simliResultList += '';
	simliResultList += '';
	
	
	$(".simliResultList").html(simliResultList);
}
function showSimliResultInfo(num) {
	$(".simliResultList").hide();
	$(".simliResultInfo").show();
	
	var simliResultInfo = '';
	simliResultInfo += '<div class="title">심리검사 결과</div>';
	simliResultInfo += '<div class="result1"><div>우울한 기분 없이 매일 즐겁게 지내고 있어요.</div></div>';
	simliResultInfo += '<div class="result2"><div>재는 심리적으로 안정되어있고 건강한 편입니다. 하지만 생활을 하다보면 가끔씩 우울한 기분도 느끼고 어려움을 당연 느끼게 될 것입니다. 부모님께서는 아이의 식욕, 수면 ... 현재는 심리적으로 안정되어있고 건강한 편입니다. 하지만 생활을 하다보면 가끔씩 우울한 기분도 느끼고 어려움을 당연 느끼게 될 것입니다. 부모님께서는 아이의 식욕, 수면 ... 현재는 심리적으로 안정되어있고 건강한 편입니다. 하지만 생활을 하다보면 가끔씩 우울한 기분도 느끼고 어려움을 당연 느끼게 될 것입니다. 부모님께서는 아이의 식욕, 수면 ... </div></div>';
	simliResultInfo += '<div class="btnDiv" onclick="showSimliMenu();">심리검사 화면으로</div>';
	simliResultInfo += '';
	simliResultInfo += '';
	simliResultInfo += '';
	
	$('.simliResultInfo').html(simliResultInfo);
	
}
</script>
</html>