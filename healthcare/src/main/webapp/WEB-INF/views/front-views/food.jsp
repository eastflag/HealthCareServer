<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.*" %> 
<%

	//String pType = request.getParameter("p") == null ? "height" : request.getParameter("p");
	String pUserId = request.getParameter("userId");
	String pToken = request.getParameter("token");
	String isUpdate="N";
	isUpdate = (String)request.getAttribute("isUpdate");
	
	String pUserName = (String)request.getAttribute("userName");
	String pUserSex = (String)request.getAttribute("userSex");
	String pUserSexStr = "";

	if (pUserSex != null && "M".equals(pUserSex)) {
		pUserSexStr = "군의";
	} else if (pUserSex != null && "F".equals(pUserSex)) {
		pUserSexStr = "양의";
	} else {
		pUserSexStr = "님의";
	}
	
	String hostname, serverAddress;
    hostname = "error";
    serverAddress = "error";
    try {
        InetAddress inetAddress;
        inetAddress = InetAddress.getLocalHost();
        hostname = inetAddress.getHostName();
        //serverAddress = inetAddress.toString();
        serverAddress = inetAddress.getHostAddress();
    } catch (UnknownHostException e) {

        e.printStackTrace();
    }
    
	String ip = request.getLocalName();
	int port = request.getLocalPort();
	
	String rootPath = String.format("%s:%d", serverAddress, port);
	
%>
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no"/>
    <title>health care</title>

	 <link rel="stylesheet" href="./css/web/food-basic.css"> 	
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
  	<script type="text/javascript" src="js/jquery.mobile-events.min.js"></script>
  	<script type="text/javascript" src="js/util.js"></script>
	
	<script type="text/javascript">
		$.ajaxSetup ({
			cache: false
		});
	</script>
	
</head>
<style>
</style>
<body>

<div class="divWrap">
	<div class="lunchMenu">                                                                                                                                                                                                                                       
		<div class="slide_box"></div>
		<div id="arrowBox" style="display:none;">
			<img id="arrowTop" style="border:0" src="./images/inbody/ArrowUp.png" alt="top">
			<img id="arrowDown" style="border:0" src="./images/inbody/ArrowDown.png" alt="down">
			<img id="arrowLeft" style="border:0" src="./images/inbody/ArrowLeft.png" alt="left">
			<img id="arrowRight" style="border:0" src="./images/inbody/ArrowRight.png" alt="right">
		</div>  
	</div>   
	<!-- lunchMenu end -->
	
	
	<div class="stuInfo wrapDiv" style="display:none;">
	</div>   
	<!-- stuInfo end -->     
	 
	<div class="menuSetting wrapDiv" style="display:none;">                                                                                                                                                                                                                    
		<div class="slide_box2">
			<div></div><div></div>
		</div>
 		<div class="rollingCircle" id="circleLocation">
			<a class="arrowLeft on"><span></span></a>
			<a class="arrowRight"><span></span></a>
		</div>
		<img id="rightInfoBtn" style="border:0; display:none;" src="./images/inbody/ArrowRight.png" alt="right">
	</div>
	<!-- menuSetting end -->
	
	<div class="selectMenu wrapDiv" style="display:none;">
	</div>
	<!-- selectMenu end -->
	
	<div class="menuInfo wrapDiv" style="display:none;">
	</div>
	<!-- menuInfo end -->
	
	<div class="recipe wrapDiv" style="display:none">
	</div>
	<!-- recipe end -->
</div>


</body>

<script type="text/javascript">
$(document).ready(function() {

});

	// TODO : 서버의 외부 IP가 틀림으로 도메인을 사용하는 방식으로 변경을 하던가?
	// Cross-Origin Resource Sharing 을 처리할 수 있도록 AJAX의 내용을 변경을 하던가 결정을 해야
	// Test시 해당 값을 설정 요망.
	// "http://210.127.55.205:82/HealthCare/"
	// rootPath = "http://<%=rootPath%>/HealthCare/";
	
	//alert(rootPath);
	
	var pUserId = "<%= pUserId%>";

	var device_width = 0;	//접속 디바이스 width값을 저장하는 변수
	var device_height = 0;	//접속 디바이스 height값을 저장하는 변수
	var width_position = 0;
	var height_position = 0;
	var tmpScrollTop_Y = 0;
	var flag = true;		//swipe 중복 이벤트를 방지하기 위한 변수
	
	window.onload = function(){
		//finger fade 이벤트
		effectFadeIn('arrowBox', 1);
		
		getFoodList();
		//접속 디바이스 width, height값을 확인하는 함수
		device_size();
	};
	
	function effectFadeIn(idname,val){
		document.body.style.background = '#ddd';
		//++fingerImgNumber;
		$('#'+idname).fadeIn(500);
		setTimeout( function(){effectFadeOut(idname,val);},500); 
	}
	function effectFadeOut(idname,val) {
		if(val == 3){
			$('#'+idname).fadeOut(500); 
			clearTimeout('#'+idname); 
			document.body.style.background = '#fff';
			return; 
		}
		else { val = val + 1; }

		$('#'+idname).fadeOut(500);
		setTimeout( function(){effectFadeIn(idname,val);},500); 
	}
	
	//디바이스 사이즈를 구해서 변수에 저장
	function device_size(){
		 device_width = viewportSize().width;
		 device_height = viewportSize().height;
		 tmpScrollTop_Y = device_height * 4;
	}
	
	function viewportSize(){
		  var size_div = document.createElement( "div" );
		 
		  size_div.style.cssText = "position:fixed;top:0;left:0;bottom:0;right:0;";
		  document.documentElement.insertBefore(size_div, document.documentElement.firstChild );
		 
		  var size_res = {width:size_div.offsetWidth, height:size_div.offsetHeight };
		  document.documentElement.removeChild(size_div);
		 
		  return size_res;
		 }
	
	var pToken = "<%= pToken%>";
	function getFoodList(){
		var pars = "{\"userId\":\"" + pUserId + 
		"\",\"token\":\"" + pToken +"\"}";
		width_position = 0;
		$.ajax({
			type : "post",
			dataType : "json",
			url : rootPath+"food/GetList",
			contentType : 'application/json',
			cache : false,
			data : pars,
			success : function(jo){
				var val = jo.value;                                                                                                                
				var valLen = val.length;                                                                                                           
				var pasteTxt = '';                                                                                                                 
				var remain = 0;                                                                                                                    
				var idxSchoolSet = 1;                                                                                                              
				var idxDateSet = 1;                                                                                                                
				                                                                                                                                   
				if(0 < valLen){                                                                                                                    
					for(var i=0; i < valLen; i++ ){                                                                                                                               
						remain = i % 5;                                                                                                            
						                                                                                                                           
						if (remain == 0) {
							idxDateSet = 1;
							pasteTxt += '<div style="position:absolute;top:'+device_height+'px;left:'+width_position+'px;width:100%;" class="school_layout" id="school' + idxSchoolSet + '">';
							width_position += device_width;
						} else {
							height_position -= device_height;
							pasteTxt += '<div style="position:absolute;top:'+height_position+'px;left:0;width:100%;" class="school1_inner' + remain + '">'; 
							
							if(remain == 4){
								height_position = 0;
							}
						}                                                                                                                          
						                                                                                                                           
						if(val[i].use_yn == null || val[i].use_yn == 'N'){                                                                         
							pasteTxt += '<h1>'+val[i].school_name+'<h2>'+getFullDateString(val[i].qw)+'</h2></h1>';
							
							pasteTxt += '<div class="image_box"><img class="image" src="./images/noimage.gif" alt="식단사진"></div>';
							pasteTxt += '<table border="1" style="position:relative;margin:auto; margin-top:15px;width:91%;">';
							pasteTxt += '<tr>';
							pasteTxt += '<td style="border:1px gray solid;padding:15px;text-align:center;font-weight:bold;">급식 정보가 없습니다.</td>';
							pasteTxt += '</tr>';
							pasteTxt += '</table>';
						}else{                                                                                                                     
							pasteTxt += '<h1>'+val[i].school_name+'<h2>'+getFullDateString(val[i].qw)+'</h2></h1>';
							if(typeof val[i].img_url != 'undefined'){
								pasteTxt += '<div class="image_box"><img class="image" src="../' + val[i].img_url + '" alt="식단사진"></div>';
							}
							else {
								pasteTxt += '<div class="image_box"><img class="image" src="./images/noimage.gif" alt="식단사진"></div>';   	
							}
							pasteTxt += '<table border="1" style="position:relative; margin:auto; margin-top:15px; width:91%;">';
							
							pasteTxt += '<tr>';
							pasteTxt += '<td style="width:25%;border:1px gray solid;vertical-align:middle;text-align:center;padding:3px;font-weight:bold;color:#fff;background:#444444;">중식</td>';
							pasteTxt += '<td style="width:75%;border:1px gray solid;padding:3px;line-height:18px;">'+val[i].lunch_detl+'</td>';
							pasteTxt += '</tr>';
							pasteTxt += '</table>';
							
							pasteTxt += '<table style="position:relative;margin:auto; margin-top:15px;width:91%;">';
							pasteTxt += '<tr>';
							pasteTxt += '<td style="width:25%;border:1px gray solid;padding:3px;text-align:center;font-weight:bold;background-color:#444444;color:#fff;vertical-align:middle;">칼로리</td>';
							//2014.05.15 칼로리 수치가 없을경우 공백처리
							pasteTxt += '<td style="width:25%;font-size:14px;border:1px gray solid;padding:3px;line-height:18px;vertical-align:middle;text-align:right;">'+((0<=(val[i].lunch_calorie*1))?val[i].lunch_calorie+'kcal</td>':'</td>');
							pasteTxt += '<td style="width:25%;font-size:14px;border:1px gray solid;padding:3px;text-align:center;font-weight:bold;background-color:#444444;color:#fff;vertical-align:middle;">칼슘</td>';
							pasteTxt += '<td style="width:25%;font-size:14px;border:1px gray solid;padding:3px;line-height:18px;vertical-align:middle;text-align:right;">'+((0<=(val[i].lunch_calorie*1))?val[i].calcium+'mg</td>':'</td>');
							pasteTxt += '</tr>';
							pasteTxt += '<tr>';
							pasteTxt += '<td style="width:25%;font-size:14px;border:1px gray solid;padding:3px;text-align:center;font-weight:bold;background-color:#444444;color:#fff;vertical-align:middle;">탄수화물</td>';
							pasteTxt += '<td style="width:25%;font-size:14px;border:1px gray solid;padding:3px;line-height:18px;vertical-align:middle;text-align:right;">'+((0<=(val[i].lunch_calorie*1))?val[i].carbohydrate+'g</td>':'</td>');
							pasteTxt += '<td style="width:25%;font-size:14px;border:1px gray solid;padding:3px;text-align:center;font-weight:bold;background-color:#444444;color:#fff;vertical-align:middle;">비타민A</td>';
							pasteTxt += '<td style="width:25%;font-size:14px;border:1px gray solid;padding:3px;line-height:18px;vertical-align:middle;text-align:right;">'+((0<=(val[i].lunch_calorie*1))?val[i].vitamin_A+'R.E</td>':'</td>');
							pasteTxt += '</tr>';
							pasteTxt += '<tr>';
							pasteTxt += '<td style="width:25%;font-size:14px;border:1px gray solid;padding:3px;text-align:center;font-weight:bold;background-color:#444444;color:#fff;vertical-align:middle;">단백질</td>';
							pasteTxt += '<td style="width:25%;font-size:14px;border:1px gray solid;padding:3px;line-height:18px;vertical-align:middle;text-align:right;">'+((0<=(val[i].lunch_calorie*1))?val[i].protein+'g</td>':'</td>');
							pasteTxt += '<td style="width:25%;font-size:14px;border:1px gray solid;padding:3px;text-align:center;font-weight:bold;background-color:#444444;color:#fff;vertical-align:middle;">비타민C</td>';
							pasteTxt += '<td style="width:25%;font-size:14px;border:1px gray solid;padding:3px;line-height:18px;vertical-align:middle;text-align:right;">'+((0<=(val[i].lunch_calorie*1))?val[i].vitamin_C+'mg</td>':'</td>');
							pasteTxt += '</tr>';
							pasteTxt += '<tr>';
							pasteTxt += '<td style="width:25%;font-size:14px;border:1px gray solid;padding:3px;text-align:center;font-weight:bold;background-color:#444444;color:#fff;vertical-align:middle;">지방</td>';
							pasteTxt += '<td style="width:25%;font-size:14px;border:1px gray solid;padding:3px;line-height:18px;vertical-align:middle;text-align:right;">'+((0<=(val[i].lunch_calorie*1))?val[i].fat+'g</td>':'</td>');
							pasteTxt += '<td style="width:25%;font-size:14px;border:1px gray solid;padding:3px;text-align:center;font-weight:bold;background-color:#444444;color:#fff;vertical-align:middle;">기타</td>';
							pasteTxt += '<td style="width:25%;font-size:14px;border:1px gray solid;padding:3px;line-height:18px;vertical-align:middle;text-align:right;">'+((0<=(val[i].lunch_calorie*1))?val[i].nutrient_etc+'</td>':'</td>');
							pasteTxt += '</tr>';
							pasteTxt += '</table>';
							  
						}
						//저녁 추천 식단 제외할때  주석처리 
					
					pasteTxt += '<div class="btnDiv" onclick="chkSetInfo();"><%= pUserName + pUserSexStr%> 저녁추천식단</div>';    
				
						if (remain > 0) { 
							pasteTxt += '</div>';
						}                                                                                                                          
						                                                                                                                           
						idxDateSet ++;                                                                                                             
						if (idxDateSet == 6) {                                                                                                     
							pasteTxt += '</div>';                                                                                                  
							idxSchoolSet ++;
						}
					}                                                                                                                              
				}else{                                                                                                                             
					pasteTxt += '<div class="school_layout" id="school1">';                                                                        
					pasteTxt += '<h1>식단 목록이 없습니다.</h1>';                                                                                           
					pasteTxt += '</div>';                                                                                                          
				}      
				$('.slide_box').html(pasteTxt);    
			},
			error : function() {
				$('.slide_box').html("Error while request..");    
			}
		});
	}
// 설정이 되어 있는지 검사
function chkSetInfo() {
	var pars = "{\"userId\":\"" + pUserId + 
	"\"}";
	
	// 학생 저녁 추천 정보 가져오기
	$.ajax({
		type : "post",
		dataType : "json",
		url : rootPath+"food/cntSetInfo",
		contentType : 'application/json',
		cache : false,
		data : pars,
		success : function(jo){
			var val = jo.value; 
			if(val==0) { // 최초 접속 설정 로딩
				showMenuSetting();
			}else {
				showStuInfo();
			}
			
	    },
		error : function () {
			alert('error');
			return false;
		}
	});	

}
function showLunchMenu() {
	effectFadeIn('arrowBox', 1);
	device_size();
	getFoodList();
	$(".selectMenu").hide();
	$(".lunchMenu").show();
}
function showStuInfo() {
	$('.slide_box').html("");
	$(".lunchMenu").hide();
	$(".menuSetting").hide();
	$(".stuInfo").show();

	var pars = "{\"userId\":\"" + pUserId + 
		"\"}";

	// 학생 저녁 추천 정보 가져오기
	$.ajax({
		type : "post",
		dataType : "json",
		url : rootPath+"food/getStudentDinnerInfo",
		contentType : 'application/json',
		cache : false,
		data : pars,
		success : function(jo){
			var val = jo.value; 
			/*
			$("#stuHeight").html(val.height);
			$("#stuWeight").html(val.weight);
			$("#stuBMI").html(val.bmi);
			$("#stuKcal").html(Math.round(val.cal*3));
			$("#dinnerCal").html(Math.round(val.cal));
			$("#dinnerCarbohy").html(val.carbohy);
			$("#dinnerProtein").html(val.protein);
			$("#dinnerFat").html(val.fat);
			$("#dinnerVitaminA").html(val.vitamin_a);
			$("#dinnerVitaminC").html(val.vitamin_c);
			$("#dinnerCalcium").html(val.calcium);*/
			var menuList = JSON.stringify(val.menu_list);
			var menuObj = {"value":escape(menuList)};
			
			var stuInfo = '';
			stuInfo += '<div class="title">신체정보</div>';
			stuInfo += '<table class="menuInfoTb">';
			stuInfo += '<colgroup>';
			stuInfo += '</colgroup>	';
			stuInfo += '<tr>';
			stuInfo += '<th>신장</th>';
			stuInfo += '<th>체중</th>';
			stuInfo += '<th>BMI</th>';
			stuInfo += '</tr>';
			stuInfo += '<tr>';
			stuInfo += '<td><span id="stuHeight">'+val.height+'</span></td>';
			stuInfo += '<td><span id="stuWeight">'+val.weight+'</span></td>';
			stuInfo += '<td><span id="stuBMI">'+val.bmi+'</span></td>';
			stuInfo += '</tr>';
			stuInfo += '</table>';
			stuInfo += '';
			stuInfo += '<div class="title">저녁 추천식단 영양량</div>';
			if(val.menu_list[0].menu_cal!=null) {
				stuInfo += '<table class="menuInfoTb">';
				stuInfo += '<colgroup>';
				stuInfo += '<col style="width:25%"/>';
				stuInfo += '<col style="width:25%"/>';
				stuInfo += '<col style="width:25%"/>';
				stuInfo += '<col style="width:25%"/>';
				stuInfo += '</colgroup>';
				stuInfo += '<tr>';
				stuInfo += '<th>칼로리</th>';
				stuInfo += '<td><span id="dinnerCal">'+val.menu_list[0].menu_cal+'</span>kal</td>';
				stuInfo += '<th>칼슘</th>';
				stuInfo += '<td><span id="dinnerCalcium">'+val.menu_list[0].calcium+'</span>mg</td>';
				stuInfo += '</tr>';
				stuInfo += '<tr>';
				stuInfo += '<th>탄수화물</th>';
				stuInfo += '<td><span id="dinnerCarbohy">'+val.menu_list[0].carbohy+'</span>g</td>';
				stuInfo += '<th>비타민A</th>';
				stuInfo += '<td><span id="dinnerVitaminA">'+val.menu_list[0].vitamin_a+'</span>R.E</td>';
				stuInfo += '</tr>';
				stuInfo += '<tr>';
				stuInfo += '<th>단백질</th>';
				stuInfo += '<td><span id="dinnerProtein">'+val.menu_list[0].protein+'</span>g</td>';
				stuInfo += '<th>비타민C</th>';
				stuInfo += '<td><span id="dinnerVitaminC">'+val.menu_list[0].vitamin_c+'</span>mg</td>';
				stuInfo += '</tr>';
				stuInfo += '<tr>';
				stuInfo += '<th>지방</th>';
				stuInfo += '<td><span id="dinnerFat">'+val.menu_list[0].fat+'</span>g</td>';
				stuInfo += '<th>기타</th>';
				stuInfo += '<td>-</td>';
				stuInfo += '</tr>';
				stuInfo += '</table>';
				stuInfo += '<table class="btnTable">';
				stuInfo += '<colgroup>';
				stuInfo += '<col style="width:75%"/>';
				stuInfo += '<col style="width:5%"/>';
				stuInfo += '<col style="width:20%"/>';
				stuInfo += '</colgroup>	';
				stuInfo += '<tr>';
				stuInfo += '<th><div id="showNewMenu">저녁 식단 메뉴</div></th>';
				stuInfo += '<td></td>';
				stuInfo += '<td><div onclick="showMenuSetting();">설정</div></td>';
				stuInfo += '</tr>';
				stuInfo += '</table>';
				stuInfo += '';
			} else {
				stuInfo += '<table class="menuInfoTb">';
				stuInfo += '<colgroup>';
				stuInfo += '<col style="width:100%"/>';
				stuInfo += '</colgroup>';
				stuInfo += '<tr>';
				stuInfo += '<td>추천 식단이 없습니다.</td>';
				stuInfo += '</table>';
				stuInfo += '<table class="btnTable">';
				stuInfo += '<colgroup>';
				stuInfo += '<col style="width:100%"/>';
				stuInfo += '</colgroup>	';
				stuInfo += '<tr>';
				stuInfo += '<td><div onclick="showMenuSetting();">설정</div></td>';
				stuInfo += '</tr>';
				stuInfo += '</table>';
				stuInfo += '';
			}

			$(".stuInfo").html(stuInfo);	
			
			$("#showNewMenu").on("click",function() {
				//onclick="showSelectMenu(\'NEW\','+menuObj+');"
				showSelectMenu("NEW",val.menu_list);
			});
	    },
		error : function () {
			alert('error');
			return false;
		}
	});	
	
	
}

function showMenuSetting() {
	$("#circleLocation a").removeClass('on').eq(0).addClass('on');
	$('.slide_box').html("");
	$(".lunchMenu").hide();
	$(".stuInfo").hide();
	$(".menuSetting").show();  
	var pars = "{\"userId\":\"" + pUserId + 
	"\",\"token\":\"" + pToken +"\"}";
	
	effectFadeIn('rightInfoBtn', 1)
	
	
	var settingDiv = "";
	width_position=0;
	// 아침식사
	settingDiv += '<div style="position:absolute;top:0px;left:'+width_position+'px;width:100%;" class="subDiv">';
	settingDiv += '<div class="title" style="background-color:#5995de;">아침 식사</div>';
	settingDiv += '<table class="breakfast" id="breakfastTable">';
	settingDiv += '</table>';
	settingDiv += '</div>';
	
	// 알레르기 검색
	settingDiv += '<div style="position:absolute;top:0px;left:'+(width_position + device_width)+'px;width:100%;" class="subDiv allergy">';
	settingDiv += '<div class="title" style="background-color:#ff9d4a;">알레르기</div>';
	settingDiv += '<table id="mainAlgList" class="allergyList">';
	settingDiv += '</table>';
	settingDiv += '';
	/*settingDiv += '<table class="search">';
	settingDiv += '<colgroup>';
	settingDiv += '<col style="width:90%"/>';
	settingDiv += '<col style="width:10%"/>';
	settingDiv += '</colgroup>';
	settingDiv += '<tr>';
	settingDiv += '<td><input type="text" name="searchAlg" id="searchAlg" style="height:28px; padding-left:10px;"/></td>';
	settingDiv += '<td><div class="btnSearch" onclick="searchAlg();">검색</div></td>';
	settingDiv += '</tr>';
	settingDiv += '<tr>';
	settingDiv += '<th colspan="2">';
	settingDiv += '<div id="searchResult">';
	settingDiv += '</div>';
	settingDiv += '</th>';
	settingDiv += '</tr>';
	settingDiv += '</table>';*/
	settingDiv += '<div class="btnDiv" onclick="saveSetting();">저장</div>';
	settingDiv += '</div>';
	$('.slide_box2').html(settingDiv);
	
	getBreakfastCate();
	stuSelctedMainAlg();
	//stuSelectedAlg();
}
// 아침식사 분류 가젹오기
function getBreakfastCate() {
	var pars = "{\"userId\":\"" + pUserId + "\""
	+ " }";

	var breakfastTable = '';
	$.ajax({ 
		type : "post",
		dataType : "json",
		url : rootPath+"food/getBreakfastCate",
		contentType : 'application/json',
		cache : false,
		data : pars,
		success : function(jo){
			var val = jo.value;                                                                                                                
			var valLen = val.length;       
			var bkst_y_cnt = 0;                                                                                                                               
			if(0 < valLen){                  
				for(var i=0; i < valLen; i++ ){   
					
					breakfastTable += '<tr><td><input type="radio" name="bkst_id" value="'+val[i].bkst_id+'" id="bkst_id'+val[i].bkst_id+'"';
					if(val[i].bkst_yn=="Y"){
						breakfastTable += ' checked ';
						bkst_y_cnt++;
					}
					breakfastTable += '/><label for="bkst_id'+val[i].bkst_id+'">'+val[i].bkst_name+'</label></td></tr>';
					breakfastTable += '<tr><th>';
					if(val[i].bkst_info!="") {
						breakfastTable += 'ex) '+val[i].bkst_info;
					} 
					breakfastTable += '</th></tr>';

					
				}    
			}
			$("#breakfastTable").html(breakfastTable);
			if(bkst_y_cnt==0) {
				$("input:radio[name='bkst_id']:radio[value='1']").attr("checked",true);
			}
	    },
		error : function () {
			console.log('error');
			return false;
		}
	});	
}
// 알레르기 검색
function searchAlg(){
	var searchAlg = $("#searchAlg").val(); // 검색어
	var pars = "{\"searchAlg\":\"" + searchAlg + "\" "
	+",\"userId\":\"" + pUserId + "\""
	+ " }";
	
	if(searchAlg=="") {
		stuSelectedAlg();
	} else {
		var searchResult = '';
		$.ajax({ 
			type : "post",
			dataType : "json",
			url : rootPath+"food/searchAlg",
			contentType : 'application/json',
			cache : false,
			data : pars,
			success : function(jo){
				var val = jo.value;                                                                                                                
				var valLen = val.length;       
				                                                                                                                                   
				if(0 < valLen){                  
					searchResult += '<ul>';
					for(var i=0; i < valLen; i++ ){   

						searchResult += '<li>'+val[i].alg_name+'<span onclick="selectAlg(\''+val[i].alg_id+'\',\'Y\',\'N\');">선택</span></li>';
						
					}    
					searchResult += '</ul>';
				}
				$("#searchResult").html(searchResult);
		    },
			error : function () {
				console.log('error');
				return false;
			}
		});	
	}
}
// 알레르기 선택
function selectAlg(alg_id,alg_state,alg_main_yn){
	var pars = "{\"alg_id\":\"" + alg_id + "\"" 
		+",\"userId\":\"" + pUserId + "\""
		+",\"alg_state\":\"" + alg_state + "\""
		+ " }";
	var searchResult = '';
	$.ajax({ 
		type : "post",
		dataType : "json",
		url : rootPath+"food/selectAlg",
		contentType : 'application/json',
		cache : false,
		data : pars,
		success : function(data){
			if(alg_main_yn=="N" && data.value=="1") {
				$("#searchAlg").val("");
				stuSelectedAlg();
			}
	    },
		error : function () {
			console.log('error');
			return false;
		}
	});	
}
// 학생이 선택한 알레르기 목록 불러오기
function stuSelectedAlg() {
	var pars = "{\"userId\":\"" + pUserId + "\"" 
	+ " }";
	var searchResult = '';
	$.ajax({ 
		type : "post",
		dataType : "json",
		url : rootPath+"food/getStuSelectedAlg", // 나의 알러지 가져오기
		contentType : 'application/json',
		cache : false,
		data : pars,
		success : function(jo){
			var val = jo.value;                                                                                                                
			var valLen = val.length;       
			                                                                                                                                   
			if(0 < valLen){                  
				searchResult += '<ul>';
				for(var i=0; i < valLen; i++ ){   

					searchResult += '<li>'+val[i].alg_name+'<span onclick="selectAlg(\''+val[i].alg_id+'\',\'N\',\'N\');">삭제</span></li>';
					
				}    
				searchResult += '</ul>';
			}
			$("#searchResult").html(searchResult);
	    },
		error : function () {
			console.log('error');
			return false;
		}
	});	
}
//주요 알레르기 정보 가져오기
function stuSelctedMainAlg() {
	var pars = "{\"userId\":\"" + pUserId + "\"}";
	var main_alg_info = '';
	$.ajax({ 
		type : "post",
		dataType : "json",
		url : rootPath+"food/getAlgMainList",
		contentType : 'application/json',
		cache : false,
		data : pars,
		success : function(jo){
			var val = jo.value;                                                                                                                
			var valLen = val.length;       
			                                                                                                                                   
			if(0 < valLen){                                                                                                                    
				for(var i=0; i < valLen; i++ ){  
					if(i==0){
						main_alg_info += '<tr>';
					}
					main_alg_info += '<td><input type="checkbox" name="alg_main_id" value="'+val[i].alg_id+'" onclick="chgMainAlg(\''+val[i].alg_id+'\');" ';
						if(val[i].stu_alg_set_yn=="Y"){
							main_alg_info += ' checked ';
						}
					main_alg_info += ' id="alg_main_id'+val[i].alg_id+'" /><label for="alg_main_id'+val[i].alg_id+'">'+val[i].alg_name+'</label></td>';
					
					if(i==valLen-1) {
						main_alg_info += '</tr>';
					}else if(i>0 && (i+1)%2==0){
						main_alg_info += '</tr><tr>';
					}
				}
			}
			$("#mainAlgList").html(main_alg_info);
	    },
		error : function () {
			console.log('error');
			return false;
		}
	});	
}
// 주요 알러지 체크박스 체크
function chgMainAlg(alg_id) {
	if($("input:checkbox[name='alg_main_id']:checkbox[value='"+alg_id+"']").is(":checked")) { // 체크 했으면
		selectAlg(alg_id,"Y","Y");
	} else {
		selectAlg(alg_id,"N","Y");
	}
}
/* 저장저장 */
function saveSetting() {
	
	var bkst_id = $("input:radio[name='bkst_id']:checked").val();
	var pars = "{\"userId\":\"" + pUserId + "\"" 
	+",\"bkst_id\":\"" + bkst_id + "\""
	+ " }";
	var searchResult = '';
	$.ajax({ 
		type : "post",
		dataType : "json",
		url : rootPath+"food/saveStuAlg", // 나의 알러지 가져오기
		contentType : 'application/json',
		cache : false,
		data : pars,
		success : function(jo){
			var val = jo.value;             
			if(val>=0){                  
				showStuInfo();
			}
	    },
		error : function () {
			console.log('error');
			return false;
		}
	});	
}
// 저녁식단메뉴
function showSelectMenu(mode,val) {
	$(".menuInfo").hide();
	$(".stuInfo").hide();
	$(".selectMenu").show();

	if(mode=="NEW") {
		var selectMenu = "";
		selectMenu += '<div class="title" style="background-color:#bbb58e;">식단 선택</div>';
		selectMenu += '<div id="dinnerMenuListWrap">';
		selectMenu += '</div>';
		selectMenu += '';
		selectMenu += '<table class="btnTable">';
		selectMenu += '<colgroup>';
		selectMenu += '<col style="width:100%"/>';
		selectMenu += '</colgroup>';
		selectMenu += '<tr>';
		selectMenu += '<th><div onclick="showLunchMenu();">점심 식단</div></th>';
		selectMenu += '</tr>';
		selectMenu += '</table>';
		selectMenu += '';
		selectMenu += '';
		
		$(".selectMenu").html(selectMenu);
	    var valLen = val.length;
		if(valLen>0){     
			var dinnerMenuList = "";
			for(var i=0; i < valLen; i++ ){   
				if(val[i].menu_id!=null) {
					dinnerMenuList += '<table onclick="showMenuInfo(\''+val[i].menu_id+'\')">';
					dinnerMenuList += '<colgroup>';
					dinnerMenuList += '<col style="width:50%"/>';
					dinnerMenuList += '<col style="width:50%"/>';
					dinnerMenuList += '</colgroup>';
					dinnerMenuList += '<tr>';
					dinnerMenuList += '<th>'+(i+1)+'</th>'; // colspan="2"
					dinnerMenuList += '</tr>'
					dinnerMenuList += '<tr>';
					//dinnerMenuList += '<td><img src="http://210.127.55.205/contents/dinner_food_img/'+val[i].menu_photo+'.jpg"/></td>';
					dinnerMenuList += '<td><ul>';
					if(val[i].menu_meal!=""){dinnerMenuList += '<li>'+val[i].menu_meal+'</li>';}
					if(val[i].menu_souop!=""){dinnerMenuList += '<li>'+val[i].menu_souop+'</li>';}
					if(val[i].menu_side1!=""){dinnerMenuList += '<li>'+val[i].menu_side1+'</li>';}
					if(val[i].menu_side2!=""){dinnerMenuList += '<li>'+val[i].menu_side2+'</li>';}
					if(val[i].menu_side3!=""){dinnerMenuList += '<li>'+val[i].menu_side3+'</li>';}
					if(val[i].menu_dessert!=""){dinnerMenuList += '<li>'+val[i].menu_dessert+'</li>';}
					if(val[i].menu_sauce!=""){dinnerMenuList += '<li>'+val[i].menu_sauce+'</li>';}
					dinnerMenuList += '</ul></td>';
					dinnerMenuList += '</tr>';
					dinnerMenuList += '<table>';
				} 
			}
		}
		$("#dinnerMenuListWrap").html(dinnerMenuList);
	}
}
// 저녁식단 상세 정보
function showMenuInfo(menu_id) {
	$(".recipe").hide();
	$(".selectMenu").hide();
	$(".menuInfo").show();

	var pars = "{\"menu_id\":\"" + menu_id + "\"" 
	+ " }";
	$.ajax({
		type : "post",
		dataType : "json",
		url : rootPath+"food/getDinnerInfo",
		contentType : 'application/json',
		cache : false,
		data : pars,
		success : function(jo){
			var val = jo.value; 
			var menuInfo = '';
			menuInfo += '<div class="title"><%= pUserName + pUserSexStr%> 저녁추천식단</div>';
			//menuInfo += '<div class="image_box"><img class="image" src="http://210.127.55.205/contents/dinner_food_img/'+val.menu_photo+'.jpg"/></div>';
			menuInfo += '<table class="menuInfoTb">';
			menuInfo += '<colgroup>';
			menuInfo += '<col style="width:25%"/>';
			menuInfo += '<col style="width:75%"/>';
			menuInfo += '</colgroup>';
			menuInfo += '<tr>';
			menuInfo += '<th rowspan="2">석식</th>';
			menuInfo += '<td>'+val.menu_meal+' '+val.menu_souop+'</td>';
			menuInfo += '</tr>';
			menuInfo += '<tr>';
			menuInfo += '<td>'+val.menu_side1+' '+val.menu_side2+' '+val.menu_side3+' '+val.menu_dessert+' '+val.menu_sauce+'</td>';
			menuInfo += '</tr>';
			menuInfo += '</table>';
			menuInfo += '';
			menuInfo += '<table class="menuInfoTb">';
			menuInfo += '<colgroup>';
			menuInfo += '<col style="width:25%"/>';
			menuInfo += '<col style="width:25%"/>';
			menuInfo += '<col style="width:25%"/>';
			menuInfo += '<col style="width:25%"/>';
			menuInfo += '</colgroup>';
			menuInfo += '<tr>';
			menuInfo += '<th>칼로리</th>';
			menuInfo += '<td><span id="dinnerCal">'+val.menu_cal+'</span>kal</td>';
			menuInfo += '<th>칼슘</th>';
			menuInfo += '<td><span id="dinnerCalcium">'+val.calcium+'</span>mg</td>';
			menuInfo += '</tr>';
			menuInfo += '<tr>';
			menuInfo += '<th>탄수화물</th>';
			menuInfo += '<td><span id="dinnerCarbohy">'+val.carbohy+'</span>g</td>';
			menuInfo += '<th>비타민A</th>';
			menuInfo += '<td><span id="dinnerVitaminA">'+val.vitamin_a+'</span>R.E</td>';
			menuInfo += '</tr>';
			menuInfo += '<tr>';
			menuInfo += '<th>단백질</th>';
			menuInfo += '<td><span id="dinnerProtein">'+val.protein+'</span>g</td>';
			menuInfo += '<th>비타민C</th>';
			menuInfo += '<td><span id="dinnerVitaminC">'+val.vitamin_c+'</span>mg</td>';
			menuInfo += '</tr>';
			menuInfo += '<tr>';
			menuInfo += '<th>지방</th>';
			menuInfo += '<td><span id="dinnerFat">'+val.fat+'</span>g</td>';
			menuInfo += '<th>기타</th>';
			menuInfo += '<td>-</td>';
			menuInfo += '</tr>';
			menuInfo += '</table>';
			menuInfo += '';
			menuInfo += '<table class="btnTable">';
			menuInfo += '<colgroup>';
			menuInfo += '<col style="width:45%"/>';
			menuInfo += '<col style="width:10%"/>';
			menuInfo += '<col style="width:45%"/>';
			menuInfo += '</colgroup>';
			menuInfo += '<tr>';
			menuInfo += '<th><div onclick="showRecipe(\''+val.menu_id+'\');">레시피 보기</div></th>';
			menuInfo += '<td></td>';
			menuInfo += '<td><div onclick="showSelectMenu(\'OLD\');">다른 메뉴</div></td>';
			menuInfo += '</tr>';
			menuInfo += '</table>';
			menuInfo += '';
			menuInfo += '';
			menuInfo += '';

			$('.menuInfo').html(menuInfo);
	    },
		error : function () {
			alert('error');
			return false;
		}
	});	
	
	
}
function showRecipe(menu_id) {
	$(".menuInfo").hide();
	$(".recipe").show();

	var pars = "{\"menu_id\":\"" + menu_id + "\"" 
	+ " }";
	$.ajax({
		type : "post",
		dataType : "json",
		url : rootPath+"food/getDinnerInfo",
		contentType : 'application/json',
		cache : false,
		data : pars,
		success : function(jo){
			var val = jo.value; 
			var recipe = '';
			recipe += '<div class="title">레시피보기</div>';
			recipe += '<table>';
			recipe += '<colgroup>';
			recipe += '<col style="width:45%"/>';
			recipe += '<col style="width:10%"/>';
			recipe += '<col style="width:45%"/>';
			recipe += '</colgroup>';
			
			recipe += '<tr>';
			if(val.menu_meal!="") {recipe += '<td style="background-color:#c3d5ea;cursor:pointer;" onclick="searchRecipe(\''+val.menu_meal+'\'); ">'+val.menu_meal+'</td>';}
			else{recipe += '<td style="background-color:#c3d5ea;" > </td>';}
			recipe += '';
			recipe += '<th></th>';
			if(val.menu_souop!="") {recipe += '<td style="background-color:#fcdec1;cursor:pointer;" onclick="searchRecipe(\''+val.menu_souop+'\'); ">'+val.menu_souop+'</td>';}
			else{recipe += '<td style="background-color:#fcdec1;" > </td>';}
			recipe += '</tr>';
			recipe += '<tr><th colspan="3"></th></tr>';

			recipe += '<tr>';
			if(val.menu_side1!="") {recipe += '<td style="background-color:#d8d1e2;cursor:pointer;" onclick="searchRecipe(\''+val.menu_side1+'\'); ">'+val.menu_side1+'</td>';}
			else{recipe += '<td style="background-color:#d8d1e2;" > </td>';}
			recipe += '<th></th>';
			if(val.menu_side2!="") {recipe += '<td style="background-color:#e9c8cc;cursor:pointer;" onclick="searchRecipe(\''+val.menu_side2+'\'); ">'+val.menu_side2+'</td>';}
			else{recipe += '<td style="background-color:#e9c8cc;" > </td>';}
			recipe += '</tr>';
			recipe += '<tr><th colspan="3"></th></tr>';
			
			recipe += '<tr>';
			if(val.menu_side3!="") {recipe += '<td style="background-color:#f4ece1;cursor:pointer;" onclick="searchRecipe(\''+val.menu_side3+'\'); ">'+val.menu_side3+'</td>';}
			else{recipe += '<td style="background-color:#f4ece1;" > </td>';}
			recipe += '<th></th>';
			if(val.menu_dessert!="") {recipe += '<td style="background-color:#c5e8b0;cursor:pointer;" onclick="searchRecipe(\''+val.menu_dessert+'\'); ">'+val.menu_dessert+'</td>';}
			else{recipe += '<td style="background-color:#c5e8b0;" > </td>';}
			recipe += '</tr>';
			recipe += '</table>';
			recipe += '';
			recipe += '<table class="btnTable">';
			recipe += '<colgroup>';
			recipe += '<col style="width:100%"/>';
			recipe += '</colgroup>';
			recipe += '<tr>';
			recipe += '<th><div onclick="showMenuInfo(\''+val.menu_id+'\');">메뉴 보기</div></th>';
			recipe += '</tr>';
			recipe += '</table>';
			recipe += '';
			
			$(".recipe").html(recipe);
	    },
		error : function () {
			alert('error');
			return false;
		}
	});	
}
function searchRecipe(str){
	str += " 레시피";
	window.open("http://search.naver.com/search.naver?where=nexearch&query="+str+"&sm=top_hty&fbm=1&ie=utf8", '_blank');
}

function preventSwipe(){
	flag = true;
}

/* swipeup */
$('.slide_box').on('swipeup',function(){
	if(!flag) return;
	if($(".slide_box > div:first-child").css("top") != "0px"){
		$('.slide_box .school_layout div').parents('.school_layout').animate({"top":'-='+device_height+''},"500");
	}else{
		return;
	}
	flag = false;
	setTimeout(preventSwipe,400);
});

/* swipedown */
$('.slide_box').on('swipedown',function(){
	if(!flag) return;
	if($(".slide_box > div:first-child").css("top") != tmpScrollTop_Y + "px"){
		$('.slide_box .school_layout div').parents('.school_layout').animate({"top":'+='+device_height+''},"500");
	}else{
		return;
	}
	flag = false;
	setTimeout(preventSwipe,400);
});

/* swipeleft */
$('.slide_box').on('swipeleft',function(){
	if(!flag) return;
	if($(".slide_box > div:last-child").css("left") != "0px"){
		$('.slide_box .school_layout').animate({"left":'-='+device_width+''},"500");
	}else{
		return;
	}
	flag = false;
	setTimeout(preventSwipe,400);
});

/* swiperight */
$('.slide_box').on('swiperight',function(){
	if(!flag) return;
	if($(".slide_box > div:first-child").css("left") != "0px"){
		$('.slide_box .school_layout').animate({"left":'+='+device_width+''},"500");
	}else{
		return;
	}
	flag = false;
	setTimeout(preventSwipe,400);
});
/* swipeleft */
$('.slide_box2').on('swipeleft',function(){
	if(!flag) return;
	if($(".slide_box2 > div:last-child").css("left") != "0px"){
		$('.slide_box2 .subDiv').animate({"left":'-='+device_width+''},"500");
	}else{
		return;
	}
	flag = false;
	setTimeout(preventSwipe,500);
	$("#circleLocation a").removeClass('on').eq(1).addClass('on');
});

/* swiperight */
$('.slide_box2').on('swiperight',function(){
	if(!flag) return;
	if($(".slide_box2 > div:first-child").css("left") != "0px"){
		$('.slide_box2 .subDiv').animate({"left":'+='+device_width+''},"500");
	}else{
		return;
	}
	flag = false;
	setTimeout(preventSwipe,500);
	$("#circleLocation a").removeClass('on').eq(0).addClass('on');
});
// 왼쪽동그라미 클릭
$('.arrowLeft').click(function(){
	if(!flag) return;
	if($(".slide_box2 > div:first-child").css("left") != "0px"){
		$('.slide_box2 .subDiv').animate({"left":'+='+device_width+''},"500");
	}else{
		return;
	}
	flag = false;
	setTimeout(preventSwipe,500);
	$("#circleLocation a").removeClass('on').eq(0).addClass('on');
});

//오른쪽동그라미 클릭
$('.arrowRight').click(function(){
	if(!flag) return;
	if($(".slide_box2 > div:last-child").css("left") != "0px"){
		$('.slide_box2 .subDiv').animate({"left":'-='+device_width+''},"500");
	}else{
		return;
	}
	flag = false;
	setTimeout(preventSwipe,500);
	$("#circleLocation a").removeClass('on').eq(1).addClass('on');
});
</script>
</html>