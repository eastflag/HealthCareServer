<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- 스마트 지킴이 앱 (모바일웹) 의 더보기 메뉴 입니다. -->
<html lang="kr">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no"/>
    <title>health care</title>

	<link rel="Stylesheet" href="./css/health-care.css" />
	<link rel="Stylesheet" href="./css/jquery.mobile.structure-1.3.2.css" />
	<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.1/jquery.mobile-1.4.1.min.css">
	
	<!-- <link rel="Stylesheet" href="./css/basic.css" /> -->
	<link rel="Stylesheet" href="./css/more_basic.css" />
	
	
	<script type="text/javascript" src="./js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="./js/jquery.mobile-1.3.2.min.js"></script>
	<script type="text/javascript" src="./js/swipe.js"></script>
	<script type="text/javascript" src="./js/jquery.mobile.simpledialog2.min.js"></script>
	<script type="text/javascript" src="./js/jquery.jqplot.min.js"></script>
	<script class="include" type="text/javascript" src="./plugins/jqplot.barRenderer.min.js"></script>
	<script class="include" type="text/javascript" src="./plugins/jqplot.pointLabels.min.js"></script> 
	<script class="include" type="text/javascript" src="./plugins/jqplot.categoryAxisRenderer.min.js"></script> 
	<script class="include" type="text/javascript" src="./plugins/jqplot.highlighter.min.js"></script>
	
	<script type="text/javascript" src="./js/chart.js"></script>
	<script type="text/javascript" src="./js/chart_detail.js"></script>
	<link rel="stylesheet" href="./css/jquery.jqplot.min.css">
	
	<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.simple-accordion.js"></script>
	<script type="text/javascript" src="js/util.js"></script>
	
	<!--[if IE]><script type="text/javascript" src="./js/excanvas.js"></script><![endif]-->
	
	<script type="text/javascript">
		$.ajaxSetup ({
			cache: false
		});
	</script>
	
	<style>
	/*
	.p​olicy_layout {
		display:none;
		position:fixed;
		top:0;
		left:0;
		width:100%;
		height:100%;
		z-index:100;
	}
	.p​olicy_layout .popup_bg {
		position:absolute;
		top:0;
		left:0;
		width:100%;
		height:100%;
		background:#000;
		opacity:.5;
	}
	.p​olicy_layout .pop-layer {
		display:block;
	}
	.pop-layer {
		display:none;
		position:absolute;
		top:50%;
		left:50%;
		width:70%;
		height:20%;
		background-color:#fff;
		border:5px solid #3571B5;
		z-index: 10;
	} 
	.pop-layer .pop-container {
		padding:20px 25px;
	}
	.pop-layer .pop-container h1{
		font-weight:bold;
		padding-bottom:10px;
	}
	.pop-layer p.pop_txt {
		color:#666;
		line-height:22px;
		height:320px;
		overflow-y:auto;
		font-size:12px;
	}
	.pop-layer .btn-div {
		width:100%;
		margin:10px 0 20px;
		padding-top:10px;
		border-top:1px solid #DDD;
		text-align:right;
	}
	a.close_btn {
		display:inline-block;
		height:25px; padding:0 14px 0;
		border:1px solid #304a8a;
		background-color:#3f5a9d;
		font-size:13px;
		color:#fff;
		line-height:25px;
	}
	a.close_btn:hover {
		border:1px solid #091940;
		background-color:#1f326a;
		color:#fff;
	}
	*/
	.button_style{
		width:97%;
		text-align:left;
		cursor:pointer;
		font-size:22px;
		border-radius:0px;
		border:0px solid #ffffff;
		color:#fff;
		display:inline-block;
		padding:7px 0 7px 10px;
		box-shadow: 0px 2px 3px #424242;
		background: #87e0fd; /* Old browsers */
		background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#87e0fd), color-stop(40%,#53cbf1), color-stop(100%,#05abe0)); /* Chrome,Safari4+ */
		background: -webkit-linear-gradient(top,  #87e0fd 0%,#53cbf1 40%,#05abe0 100%); /* Chrome10+,Safari5.1+ */
		background: -o-linear-gradient(top,  #87e0fd 0%,#53cbf1 40%,#05abe0 100%); /* Opera 11.10+ */
		background: linear-gradient(to bottom,  #87e0fd 0%,#53cbf1 40%,#05abe0 100%); /* W3C */
	}.button_style:hover {
		background:-webkit-gradient( linear, left top, left bottom, color-stop(19%, #05abe0), color-stop(86%, #87e0fd) );
		background-color:#05abe0;
	}
	
	#inner_button1{
		margin:2px 0;
	}
	#inner_button2{
		margin:2px 0;
	}
	
	.right_circular{
		background:url('./images/common/right_circular.png') 0 0 no-repeat;
		background-size:20px 20px;
		width:20px;
		height:20px;
		margin-top:5px;
	}
	.noticeWrap{
		border:1px solid #ddd;
		border-radius:5px;
		width:95%;
		margin:0.5em 0 0 8px;
		/* padding-bottom:5px; */
		color:#444444;
	}
	.notice_accordion dt {
		cursor: pointer;
		padding: 15px 5px;
		font-size: 12px;
		font-weight: bold;
		border-bottom:1px solid #ddd;
	}
	.qa_tbl{
		width:95%;
		display:inline-block;
		padding:0.5em 0 0 0.8em;
		margin-bottom:23px;
		font-weight:bold;
	}
	.qa_tbl table{
		width:100%;
		border:0;
	}
	.qa_tbl tr td{
		padding:0px 7px 0px 0;
		vertical-align:middle;
	}
	.qa_tbl tr td input{
		width:80%;
		height:20px;
		border:1px solid #bbb;
		font-size:14px;
		/*padding:2px 0 3px 8px;*/
		border-radius:0px;
	}
	.textarea {
		display: block;
		height: 240px;
		width:100%;
		overflow-y:auto;
		overflow-x:hidden;
	}
	.qa_tbl tr td textarea{
		width:100%;
		height:230px;
		border-color:#bbb;
		font-size:13px;
		line-height:18px;
		resize:none;
		border-radius:0px;
	}
	</style>
</head>
<body>
<div class="wrap">
		<article class="more pageUI pageUI-active" id="page01">
			<section>
				<div class="more_list">
					<ul>
						<li><a class="button_style" id="button1">공지사항</a></li>
						<li><a class="button_style" id="button2">Q&#38;A</a></li>
					</ul>
				</div>
			</section>
		</article>
		
		<article class="notice pageUI" id="page02">
			<div>
				<ul>
					<li><a class="button_style" id="inner_button1">공지사항</a></li>
				</ul>
			</div>
			<section class="noticeWrap">
				<div class="noticeView">
					<div class="scroll_box">
						<!-- 
						<dl class="notice_accordion" id="notice_accordion">
							<dt>스마트 헬스케어는 어떤 서비스 인가요?<span>2013-12-17</span></dt>
							<dd>헬스케어는 건강지킴이 서비스 입니다.</dd>
							<dt>아이폰으로는 스마트헬스케어를 사용할수 없나요?<span>2012-09-05</span></dt>
							<dd>사용하실 수 있습니다.</dd>
							<dt>스마트 헬스 케어 이용약관 변경안내<span>2012-09-05</span></dt>
							<dd>변경되었습니다.</dd>
							<dt>스마트 헬스케어 개인정보취급방침 변경안내<span>2012-09-05</span></dt>
							<dd>변경되었습니다.</dd>
						</dl>
						 -->
						 <span id="noticeSpan"></span>
					</div>
				</div>
			</section>
		</article>
		
		<article class="question pageUI" id="page03">
			<div>
				<ul>
					<li><a class="button_style" id="inner_button2">Q&#38;A</a></li>
				</ul>
			</div>
			<section class="questionWrap">
				<form id="dataForm" action="">
					<div class="qa_tbl">
						<table>
							<caption class="caption_blind">문의사항 등록</caption>
							<tbody>
								<!-- <tr>
									<td style="vertical-align:middle">작성자 :</td><td><input type="hidden" id="name" name="name" maxlength="16"></td>
								</tr> -->
								<tr>
									<td   colspan="2">궁금한 점이 있으시면 무엇이든 물어보세요. 바로 답변해 드리도록 하겠습니다.</td>
								</tr>
								
								<tr>
									<td>
										<input placeholder="이름"  type="text"  id="name" name="name" maxlength="8">
									</td>
									 <td>
									 	<input placeholder="휴대폰번호"  type="number"  id="tel" name="tel" maxlength="13">
									 </td>
									
								</tr>
								<tr>
									<td  colspan="2">
										<input placeholder="이메일"  type="email"  id="email"  name="email" maxlength="26">
									</td>
								</tr>
								<tr>
									<td  colspan="2">
									  <input placeholder="제목"  type="text"  id="title"   name="title"   maxlength="25">
									</td>
								</tr>
								<tr>
								   <td colspan="2">
										<div class="textarea">
											<textarea id="content" name="content" placeholder="문의 내용을 입력해주세요." maxlength="300"></textarea>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div id="sendBtn" class="button_style" style="display:block;width:40%;text-align:center;position:relative;margin:0 auto;"><h1>보내기</h1></div>
				</form>
			</section>
		</article>
	</div>
	<!-- 
	<div class="p​olicy_layout" style="">
		<div class="popup_bg"></div>
		<div id="layer1" class="pop-layer">
			<div class="pop-container">
				<div class="pop-conts">
						<p><span id="writePolicy"></span></p>
					<div class="btn-div">
						<a href="#" class="close_btn" style="text-decoration:none;">닫기</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	 -->
</body>

<script type="text/javascript">

$('#page01 #button1').click(function(){
	setUserData('notice/GetList', '', getNotice);
	$("#page01").removeClass('pageUI-active');
	$("#page02").addClass('pageUI-active');
});

$('#page01 #button2').click(function(){
	$("#page01").removeClass('pageUI-active');
	$("#page03").addClass('pageUI-active');
});

$(function() {
    $('#question_accordion').simpleAccordion();
});

function goBack(){
}

function setUserData(pUrl, pars, func){
	$.ajax({
		type : "post",
		dataType : "json",
		url : rootPath + pUrl,
		contentType : 'application/json',
		cache : false,
		data : pars,
		success : func,
		error : function() {
			alert('Error while request..');
		}
	});
}

function getNotice(jo){
	var val = jo.value;
	var valLen = val.length;
	var pasteTxt='';

	if(0 == valLen){
		pasteTxt+='<dl class="notice_accordion" id="notice_accordion">';
		pasteTxt+='<dt>데이터가 없습니다.</dt>';
		pasteTxt+='</dl>';
	}else{
		pasteTxt+='<dl class="notice_accordion" id="notice_accordion">';
		
		for(var i=0; i<valLen;i++ ){
			
			pasteTxt+='<dt>'+val[i].title+'<span class="right_circular"></span>'+'</dt>';
			pasteTxt+='<dd>'+val[i].content+'</dd>';
		}
		
		pasteTxt+='</dl>';
	}
	
	$("#noticeSpan").html(pasteTxt);
	 
    $('#notice_accordion').simpleAccordion();
}

var i=0;
$('#sendBtn').click(function(){
	if (i==0) {
		//if(!$('input:checkbox[id="check1"]').is(":checked")){
			//alert('약관에 동의해주십시오.\n');
			//viewPolicy('약관에 동의해주십시오.\n');
			//$("#check1").focus();
			//return;
		//}
		if("" == $("#email").val() && "" == $("#tel").val()){
			//alert('연락처를 확인해주세요.\n');
			viewPolicy('연락처를 확인해주세요.\n');
            return;
		}
		if("" != $("#email").val()){
			if(!/^([0-9a-zA-Z_-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/.test($("#email").val())){
				//alert('이메일 형식이 잘못되었습니다\n');
				viewPolicy('이메일 형식이 잘못되었습니다\n');
				$("#email").focus();
				return;
			}
		}
		if("" != $("#tel").val()){
			if(!/^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/.test($("#tel").val())){
				//alert('연락처 형식이 잘못되었습니다\n');
				viewPolicy('연락처 형식이 잘못되었습니다\n');
				$("#tel").focus();
				return;
			}
		}
		if("" == $("#title").val()) {
			$("#title").focus();
			//alert('제목을 입력해주세요.\n');
			viewPolicy('제목을 입력해주세요.\n');
            return;
        }
		if("" == $("#content").val()) {
			$("#content").focus();
			//alert('내용을 입력해주세요.\n');
			viewPolicy('내용을 입력해주세요.\n');
            return;
        }
		
		$.ajax({
			type : "post",
			dataType : "json",
			url : rootPath + "/setQuestion",
			contentType : 'application/json',
			cache : false,
			data : "{\"email\":\"" + $("#email").val() + 
					"\",\"tel\":\"" + $("#tel").val() +
					"\",\"title\":\"" + $("#title").val() +
					"\",\"name\":\"" + $("#name").val() +
					"\",\"content\":\"" +  $("#content").val().replace(/\n/g, "<BR>") +"\"}",
			success : function(data){
				var val = data.value;
				if(val){
					//alert('요청하신 문의를 성공적으로 보냈습니다.\n');
					viewPolicy('요청하신 문의를 성공적으로 보냈습니다.\n');
					$("#dataForm")[0].reset();
					$("#page03").removeClass('pageUI-active');
					$("#page01").addClass('pageUI-active');
				}else{
					//alert('서버에 장애가 있습니다.\n');
					viewPolicy('서버에 장애가 있습니다.\n');
				}
			},
			beforeSend: function(xhr){
				i++;
			},
			error : function() {
				alert('서버에 장애가 있습니다.\n');
			}
		});
	}else{
		alert("처리중입니다.");
	}
});

function viewPolicy(refStr){
	var temp = $('#layer1');
	//var bg = temp.prev().hasClass('popup_bg');//dimmed 레이어를 감지하기 위한 boolean 변수
	$("#writePolicy").html(refStr);
	$('.p​olicy_layout').fadeIn();
	if(temp.outerHeight() < $(document).height()){
		temp.css('margin-top', '-'+temp.outerHeight()/2+'px');
	}else{
		temp.css('top', '0px');
	}
	if(temp.outerWidth() < $(document).width()){
		temp.css('margin-left', '-'+temp.outerWidth()/2+'px');
	}else{
		temp.css('left', '0px');
	}
	temp.find('a.close_btn').click(function(e){
		$('.p​olicy_layout').fadeOut();
		e.preventDefault();
	});
	
	$('.p​olicy_layout .popup_bg').click(function(e){  //배경을 클릭하면 레이어를 사라지게 하는 이벤트 핸들러
		$('.p​olicy_layout').fadeOut();
		e.preventDefault();
	});
}
</script>
</html>