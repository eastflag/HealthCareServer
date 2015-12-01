<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0061)http://mi-talk.kakao.com/android/notice -->
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> -->
<!-- <HTML xmlns="http://www.w3.org/1999/xhtml"> -->
<HTML>
<HEAD>
<META name="viewport" 
	content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
<META content="text/html;charset=UTF-8" http-equiv="content-type">
<META content="no-cache" http-equiv="cache-control">
<META content="nocache" http-equiv="pragma">
<META content="-1" http-equiv="Expires">
<TITLE>스마트 건강지킴이</TITLE>
<STYLE type="text/css">
html {
	overflow-x: auto;
	overflow-y: scroll;
}

body {
	margin: 0;
	padding: 0;
	font-size: 14px;
	font-family: dotum, gulim, AppleGothic, Sans-serif;
}

div,span,table,tr,th,td,thead,tbody,form,fieldset,label,input,p,select,adddress
	{
	margin: 0;
	padding: 0;
}

h1,h2,h3,h4,h5,h6 {
	margin: 0;
	padding: 0;
}

ul,ol,li,dl,dt,dd {
	list-style: none;
	margin: 0;
	padding: 0;
}

li {
	vertical-align: bottom;
}

img,fieldset {
	border: 0;
}

body {
	background-color: #f0f0ef;
	-webkit-text-size-adjust: none;
}

.pre-loading {
	position: absolute;
	top: -5000px;
	left: -5000px;
	background:
		url(http://mi-talk.kakao.com/images/v2/loading.gif?1292465768)
		no-repeat;
}

.content {
	width: 100%;
	overflow: hidden;
}

.content .description,.content .title {
	margin: 0;
}

.content .description {
	padding: 10px 14px 10px 14px;
	font: 14px Helvetica;
	color: #321f23;
	display: block;
}

.content .description.loading {
	min-height: 36px;
	background:
		url(http://mi-talk.kakao.com/images/v2/loading.gif?1292465768)
		no-repeat center center;
}

.content .description img {
	margin-left: 0;
}

.content .description .activated_at {
	color: #799eb7;
	font-size: 12px;
	padding-top: 20px;
}

.content .title {
	cursor: pointer;
	border-color: #dddddd;
	border-style: solid;
	border-width: 1px 0 0 0;
	background:
		url(http://mi-talk.kakao.com/images/iphone/bg_help_title.png?1293609694)
		no-repeat;
	font: 14px Helvetica;
	color: #000;
	height: 52px;
	padding: 0 10px 0 50px;
}

.content .title.notice {
	display: block;
	position: relative;
}

.content .title.on {
	border-bottom-width: 1px;
	background:
		url(http://mi-talk.kakao.com/images/iphone/bg_help_title_on.png?1293609694)
		no-repeat;
}

.content .title .activated_at {
	font-size: 10px;
	color: #799eb7;
}

.content td.title>div:first-child {
	height: 52px;
	display: table-cell;
	vertical-align: middle;
}

.content .new {
	color: #d03838;
	font-weight: bold;
	position: absolute;
	top: 12px;
	left: 5px;
}

.content .banner {
	width: 100%;
	background:
		url(http://mi-talk.kakao.com/images/iphone/banner_bg.gif?1298446563)
		repeat-x;
}

.content .banner_2 {
	width: 100%;
	background:
		url(http://mi-talk.kakao.com/images/iphone/m_banner_bg.png?1323053812 )
		repeat-x;
}

.content .banner a {
	display: block;
	width: 100%;
}

div.category {
	background:
		url(http://mi-talk.kakao.com/images/iphone/bg_category.png?1318387365)
		repeat-x repeat-y;
	vertical-align: middle;
	float: left;
	height: 37px;
	width: 100%;
	line-height: 37px;
	font-size: 12px;
	color: #67869B;
	padding-left: 5px;
}

div.category .button {
	font-size: 12px;
	color: #67869B;
	text-decoration: none;
	float: left;
	padding-left: 5px;
}
</STYLE>

<SCRIPT type="text/javascript">
	var request = false;
	try {
		request = new XMLHttpRequest();
	} catch (failed) {
		request = false;
	}

	if (!request) {
		alert("Error initializing XMLHttpRequest!");
	}

	function showNews(/*newsType,*/ newsId) {
		// web에서 받는 방식으로 수정 요망 : 동적으로 구성해서 보일수 있도록 구성 요망.
		//var url = "/android/" + newsType + "/show/" + newsId;
		var url = "/android/" + newsType + "/show/" + newsId;
		request.open("GET", url, true);
		request.onreadystatechange = function() {
		if (request.readyState == 4) {
				if (request.status == 200) {
					var description = document.getElementById('description-' + newsId);
					var descriptionBody = document.getElementById('description-show-' + newsId);
					descriptionBody.className = 'description';
					descriptionBody.innerHTML = request.responseText + descriptionBody.innerHTML;
					//descriptionBody.innerHTML = notifyStr + descriptionBody.innerHTML;
					description.setAttribute('loaded', true);
				}
			}
		}
		request.send(null);
	}

	function toggleNews(newsId) {
		var description = document.getElementById('description-' + newsId);
		var title = document.getElementById('title-' + newsId);
		if (description.style.display != 'none') {
			description.style.display = 'none';
			description.firstChild.className = 'description';
			//title.className = [ 'title', description.getAttribute('type') ].join(' ');
		} else {
			description.style.display = 'block';
			//title.className = [ 'title', description.getAttribute('type'), 'on' ].join(' ');

			if (!description.getAttribute('loaded')) {
				description.firstChild.className = 'description loading';
				//showNews(description.getAttribute('type'), newsId);
				//showNews(newsId);
			}
		}
		return false;
	}
	
</SCRIPT>

<META name="GENERATOR" content="MSHTML 9.00.8112.16443">
</HEAD>
<BODY>
	<DIV class="pre-loading"></DIV>
	<DIV class="content">
		<TABLE
			style="border: currentColor; width: 100%; border-collapse: collapse;">
			<TBODY>
				<TR>
					<TD id="title-1" class="title notice" onclick="toggleNews('1')"
						vAlign="middle">
						<DIV>
							스마트 건강지킴이는 어떤 서비스인가요? [2013/09/02]
							<SPAN class="activated_at">2013/09/02</SPAN>
						</DIV>
					</TD>
				</TR>
				<TR style="display: none;" id="description-1">
					<TD id="description-show-1" class="description">
						<DIV>
							<p>
							스마트 건강지킴이는 각 교육기관에서 매달 체격검사를 통해 학생의 건강상태 정보를 수집하고 <br>
							학생의 건강 발달 상태 정보와 이상징후, 발달 상황 통계 등의 정보를 각 학부모에게 실시간으로 전송함으로써 <br>
							통합 학생 건강 관리를 돕는 서비스입니다.
							</p><br>
						</DIV>
						<DIV class="activated_at">2013/09/02</DIV>
					</TD>
				</TR>
				
			</TBODY>
		</TABLE>
	</DIV>
</BODY>
</HTML>
