<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<title>헬스케어 Main</title>
	<link rel="stylesheet" href="css/web/basic.css">
	<link rel="stylesheet" href="css/web/video-js.css">
	
	<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
  	<script type="text/javascript" src="js/jquery.mobile-1.3.2.js"></script>
  	<script type="text/javascript" src="js/video.js"></script>
  	<script type="text/javascript" src="js/util.js"></script>
  	
<script type="text/javascript">
  	$.ajaxSetup ({
		cache: false
	});
	
  	var resultRes = getDevice();
  	
  	var subChart_index = "";	//page index 번호를 담기 위한 변수
  	var isLocSubChart = false;	//현재 detail 페이지에 있는지 여부를 확인하기 위한 변수

</script>
</head>
<body OnLoad="checkEventPopup()">
    <div id="popup_layer">
    	<div id="popup_contents"></div>
    	<div id="popup_btn" onclick="popupHide('popup_layer');">닫기</div>
    </div><!-- 이벤트 팝업 -->
	<div class="ie_Diversion_layer"><!-- ie7으로 접속 시 전체를 덮는 레이어 --></div>
	<div class="wrap">
		<div class="header">
			<div class="logo"><img src="images/web/logo.png" alt="로고"></div>
			<div class="headerMenu">|
				<a id="smart" style="color:red;">스마트건강지킴이</a>&nbsp;|
				<a id="notice">공지사항</a>&nbsp;|
				<a id="qAnda">Q&#38;A</a>&nbsp;|
			</div>
		</div>
		<!-- intro page  -->
		<div class="container pageUI-active" id="page01">
			<div class="contents">
					<img class="phone" src="images/web/phone.png" alt="핸드폰이미지">
					<img class="intro_bg" src="images/web/intro_bg.png" alt="intro 화면">
			</div>
			
			<div class="aside">
				<img class="leaf" src="images/web/leaf.png" alt="나뭇잎">
				<img class="text_mainImage" src="images/web/text_maintitle.png" alt="메인화면 텍스트">
				<div class="text_mainTitle">
					<p>
					스마트건강지킴이는<br>
					아동과 청소년들의 건강하고, 바른성장을 돕기 위해 만들어진 App입니다.<br>
					<br>
					비만과 흡연예방에 특화된 지킴이 서비스는<br>
					신뢰도 높은 측정장비와의 연동을 통하여 수집된 개인별 신체건강정보를 분석하여,<br>
					정확한 신체건강정보(키,몸무게,BMI지수,체지방,흡연지수등)와<br>
					표준/또래들과의 비교DATA를 알기 쉽게 그래프와 애니메이션으로 구현하였으며,<br>
					개인별 건강증진정보와 맞춤운동동영상을 보호자와 함께 서비스 받을수 있습니다
					</p>
				</div>
			</div>
		</div>
		
		<!-- 사용자 확인 page -->
		<div class="container pageUI" id="page02" style="display:none;">
			<div class="contents">
			<img class="phone" src="images/web/phone.png" alt="핸드폰이미지">
			<img class="bg" src="images/web/bg.png" alt="핸드폰바탕화면">
			<div class="headerInfo"><h1>사용자 확인</h1></div>
				 <form class="confirmForm" action="" name="confirmForm">
				 	<div class="formText">헬스케어를 이용하기 위하여<br>전화번호 인증이 필요합니다.</div>
				 	<fieldset>
				 		<legend></legend>
				 		<label for="mobileNumber">
				 			<input type="text" id="mobileNumber" name="mobileNumber" placeholder="핸드폰번호. '-' 제외" maxlength="11">
				 		</label>
				 		<div class="requestConfirmBtn"><h1>인증번호요청</h1></div>
				 		<label for="confirmNumber">
				 			<input type="text" id="confirmNumber" name="confirmNumber" placeholder="인증번호" maxlength="11">
				 		</label>
				 	</fieldset>
				 	<div class="loginBtn"><h1>로그인</h1></div>
				 	<div class="guestBtn"><h1>게스트 모드</h1></div>
				 		
				 	<div class="formInfoMsg">전화번호는 본인 확인 및 부정 이용방지를 위한<br>
				 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 	용도로만 사용됩니다.
				 	</div>
				 </form>
			</div>
			<div class="aside">
				<img class="leaf" src="images/web/leaf.png" alt="나뭇잎">
				<div class="text_mainTitle">
					<h1>[사용자확인]</h1>
					<p>
					스마트건강지킴이 웹은 앱과 동일한 사용자 환경을<br>
					제공합니다. 왼쪽 스마트 폰에서 아래 단계를 따라주시면<br>
					쉽게 이용하실 수 있습니다.<br>
					</p>
					<br>
					<p>
					1. 휴대전화번호 입력창에 가입자의 전화번호를 입력 해주세요.<br>
					2. 인증 번호 요청 버튼을 누르시면 잠시 후 입력하신<br>
					&nbsp;&nbsp;&nbsp;전화번호로 인증번호가 전송됩니다.<br>
					3. 전송 받으신 인증번호를 좌측의 입력창에 입력해주세요.<br>
					4. 로그인 버튼을 클릭해주세요.<br>
					</p>
					<br>
					<p>
					가입자가 아닌 경우는 게스트모드를 사용해주세요.<br>
					가상 학생에 대한 시뮬레이션 서비스를 이용하실 수 있습니다.<br>
					</p>
				</div>
			</div>
		</div>
		
		<!-- 자녀 선택 page -->
		<div class="container pageUI" id="page03" style="display:none;">
			<div class="contents">
				<img class="phone" src="images/web/phone.png" alt="핸드폰이미지">
				<img class="bg" src="images/web/bg.png" alt="핸드폰바탕화면">
				<div class="headerInfo"><h1>자녀 선택</h1></div>
				<div class="listWrap">
					<span id="studentList" style="z-index:100;"></span>
				</div>
			</div>
			<div class="aside">
				<img class="leaf" src="images/web/leaf.png" alt="나뭇잎">
				<div class="text_mainTitle">
					<h1>[자녀 선택]</h1>
					<p>
					현재 등록된 자녀가 있다면 목록에 보여집니다.<br>
					건강 정보를 확인하고 싶은 자녀를 선택해주세요.<br>
					</p>
					<br>
					<p>
					사진 등록이 되지 않은 경우 자녀분의 사진은 보이지 않습니다.<br>
					<br>
					자녀의 정보가 다르거나 추가/변경 등을 원하시는 경우<br>
					고객 센터로 문의 바랍니다.
					</p>
				</div>
			</div>
		</div>
		
		<!-- 건강지킴이 메인 page -->
		<div class="container pageUI" id="page04" style="display:none;">
			<div class="contents">
				<img class="phone" src="images/web/phone.png" alt="핸드폰이미지">
				<img class="bg" src="images/web/bg.png" alt="핸드폰바탕화면">
				<div class="headerInfo">
					<span class="children_back"></span>
					<div class="detail_children_info"><!-- 자녀정보 --></div>
				</div>
			<div class="menuWrap">
				<ul class="menu">
					<li class="cm"><a href="JavaScript:goto_Location('0101','front-views/view?p=height&userId=')"><span title="신장" class="cm" id="writeCm">175.7<br><small style="font-size:small">표준이상</small></span></a></li>
					<li class="kg"><a href="JavaScript:goto_Location('0102','front-views/view?p=weight&userId=')"><span title="체중" class="kg" id="writeKg">84.1</span></a></li>
					<li class="bmi"><a href="JavaScript:goto_Location('0103','front-views/view?p=bmi&userId=')"><span title="BMI" class="bmi" id="writeBmi">27.2<br><small style="font-size:small">비만B</small></span></a></li>
					<li class="smoking"><a href="JavaScript:goto_Location('0104','front-views/view?p=smoke&userId=')"><span title="흡연" class="smoking" id="writeSmoking">초기&#47;<br>간접흡연</span></a></li>
					
					<!-- 추가정보 수정 tharaud 2015.04.17 start -->
					<!-- <li class="add_info"><a href="JavaScript:goto_Location('0105')"><span title="추가정보" class="add_info"></span></a></li> -->
					<li class="add_info"><a href="JavaScript:goto_Location('0105','front-views/view?p=add_info&userId=')"><span title="추가정보" class="add_info"></span></a></li>
					<!-- <li class="add_play"><a href="JavaScript:goto_Location('0105','front-views/view?p=add_play&userId=')"><span title="추가정보" class="add_play"></span></a></li> -->
					<!-- 추가정보 수정 tharaud 2015.04.17 end -->
					
					<li class="ranking"><a href="JavaScript:goto_Location('0106','front-views/view?p=rank&userId=')"><span title="랭킹" class="ranking"></span></a></li>
					<li class="g_poing"><a href="JavaScript:goto_Location('0107','front-views/view?p=score&userId=')"><span title="성장점수" class="g_poing" id="writeG_poing">76</span></a></li>
					<li class="diet"><a href="JavaScript:goto_Location('0108','front-views/view?p=food&userId=')"><span title="식단" class="diet"></span></a></li>
					<li class="videolist"><a href="JavaScript:goto_Location('0109', '')"><span title="추천운동" class="videolist"></span></a></li>
				</ul>
			</div>
			</div>
			<div class="aside">
				<img class="leaf" src="images/web/leaf.png" alt="나뭇잎">
				<div class="text_mainTitle">
					<h1>[건강지킴이 메인]</h1>
					<p>
					이전 페이지에서 선택한 자녀에 대한 상세한 건강 정보를<br>
					확인 하실 수 있습니다.<br>
					</p>
					<br>
					<p>
					<span>신장</span> 성장 상태 및 표본집단 비교해 보세요.<br>
					<span>체중</span> 체중 관련 상태 및 다양한 비교를 볼 수 있어요.<br>
					<span>BMI</span> 체지방률 확인을 통한 비만상태를 확인 할 수 있어요.<br>
					<span>흡연</span> 혹시 모를 자녀의 흡연 여부를 확인 할 수 있습니다.<br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					흡연 판정이 되면 조기 금연 유도를 하는 것이 중요합니다.<br>
					<span>추가정보</span> 제공 예정<br>
					<span>랭킹</span> 다른 친구들과 자녀의 랭킹 비교를 할 수 있습니다.<br>
					<span>성장점수</span> 성장 점수를 직접 수치로 확인해 보세요.<br>
					<span>추천운동</span> 건강 상태에 따른 운동을 간고등어 코치<br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					최성조 트레이너를 통해 따라해 보세요.<br>
					<span>식단</span> 오늘 자녀가 무엇을 먹었는지 실제 촬영한 사진과 함께<br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					확인해 보세요.<br>
					</p>
				</div>
			</div>
		</div>
		
		<div class="container pageUI" id="page05" style="display:none;">
			<div class="contents">
				<img class="phone" src="images/web/phone.png" alt="핸드폰이미지">
				<img class="bg" src="images/web/bg.png" alt="핸드폰바탕화면">
				<div class="headerInfo">
					<span class="default_back"></span>
					<div class="detail_children_info"><!-- 자녀정보 --></div>
				</div>
				<div id="cm_result" style="position:absolute;width:296px;height:467px;margin:0 0 0 -130px;overflow:hidden;"><!-- 챠트 --></div>
			</div>
		</div>
		
		<div class="container pageUI" id="page07" style="display:none;">
			<div class="contents">
				<img class="phone" src="images/web/phone.png" alt="핸드폰이미지">
				<img class="bg" src="images/web/bg.png" alt="핸드폰바탕화면">
				<div class="headerInfo">
					<span class="default_back"></span>
					<div class="detail_children_info"><!-- 자녀정보 --></div>
				</div>
				<div id="kg_result" style="position:absolute;width:296px;height:467px;margin:0 0 0 -130px;overflow:hidden;"><!-- 챠트 --></div>
			</div>
		</div>
		
		<div class="container pageUI" id="page09" style="display:none;">
			<div class="contents">
				<img class="phone" src="images/web/phone.png" alt="핸드폰이미지">
				<img class="bg" src="images/web/bg.png" alt="핸드폰바탕화면">
				<div class="headerInfo">
					<span class="default_back"></span>
					<div class="detail_children_info"><!-- 자녀정보 --></div>
				</div>
				<div id="bmi_result" style="position:absolute;width:296px;height:467px;margin:0 0 0 -130px;overflow:hidden;"><!-- 챠트 --></div>
			</div>
		</div>
		
		<div class="container pageUI" id="page10" style="display:none;">
			<div class="contents">
				<img class="phone" src="images/web/phone.png" alt="핸드폰이미지">
				<img class="bg" src="images/web/bg.png" alt="핸드폰바탕화면">
				<div class="headerInfo">
					<span class="default_back"></span>
					<div class="detail_children_info"><!-- 자녀정보 --></div>
				</div>
				<div id="smoking_result" style="position:absolute;width:296px;height:467px;margin:0 0 0 -130px;overflow:hidden;"><!-- 챠트 --></div>
			</div>
		</div>
		<!-- 추가정보메뉴 tharaud 2015.04.17  start-->
		<div class="container pageUI" id="page11" style="display:none;">
			<div class="contents">
				<img class="phone" src="images/web/phone.png" alt="핸드폰이미지">
				<div class="headerInfo">
					<span class="default_back"></span>
					<div class="detail_children_info"><!-- 자녀정보 --></div>
				</div>
				<div class="slide_box"></div>
			</div>
			<div class="aside">
				<img class="leaf" src="images/web/leaf.png" alt="나뭇잎">
				<div class="text_mainTitle">
					<h1>[추천정보메뉴]</h1>
					<p>
					먼저 심리검사 메뉴를 추가 합니다.<br>
					심리검사에 참여해 보세요.<br>
					</p>
					
				</div>
			
			</div>
		</div>
			<!-- 추가정보메뉴 tharaud 2015.04.17  end-->
		
		<div class="container pageUI" id="page12" style="display:none;">
			<div class="contents">
				<img class="phone" src="images/web/phone.png" alt="핸드폰이미지">
				<img class="bg" src="images/web/bg.png" alt="핸드폰바탕화면">
				<div class="headerInfo">
					<span class="default_back"></span>
					<div class="detail_children_info"><!-- 자녀정보 --></div>
				</div>
				<div id="ranking_result" style="position:absolute;width:296px;height:467px;margin:0 0 0 -130px;overflow:hidden;"><!-- 챠트 --></div>
			</div>
		</div>
		
		<div class="container pageUI" id="page15" style="display:none;">
			<div class="contents">
				<img class="phone" src="images/web/phone.png" alt="핸드폰이미지">
				<img class="bg" src="images/web/bg.png" alt="핸드폰바탕화면">
				<div class="headerInfo">
					<span class="default_back"></span>
					<div class="detail_children_info"><!-- 자녀정보 --></div>
				</div>
				<div id="g_poing_result" style="position:absolute;width:296px;height:467px;margin:0 0 0 -130px;overflow:hidden;"><!-- 챠트 --></div>
			</div>
		</div>
		
		<div class="container pageUI" id="page17" style="display:none;">
			<div class="contents">
				<img class="phone" src="images/web/phone.png" alt="핸드폰이미지">
				<div class="headerInfo">
					<span class="default_back"></span>
					<h1>오늘의 식1단</h1>
				</div>
				<div class="slide_box"></div>
			</div>
			<div class="aside">
				<img class="leaf" src="images/web/leaf.png" alt="나뭇잎">
				<div class="text_mainTitle">
					<h1>[오늘의 식단]</h1>
					<p>
					오늘 자녀가 무엇을 먹었는지 실제 촬영한 사진과 함께<br>
					확인 해 보세요.<br>
					</p>
						<br>
					<p>
						<span>오늘의 중식</span> 학교에서 자녀가 먹은 오늘의 중식 메뉴입니다.<br>
						<!-- <span>추천 석식</span> 오늘의 중식을 고려하여 영양사가 추천하는 저녁메뉴 입니다.<br> -->
						골고루 먹는 습관을 키울 수 있도록 부모님께서도 함께 노력 해 주세요.<br>
						<br>
					</p>
					<p style="position:absolute;margin:195px 0 0 100px;font-size:13px;line-height:19px;">
						좌측 화살표를 이용하여 지난 식단 및 다른 학교 식단을 확인할 수 있습니다.<br>
						상*하 : 날짜 이동<br>
						좌*우 : 학교 변경<br>
					</p>
				</div>
				<img class="arrow-Up" style="position:absolute;margin:145px 0 0 40px;cursor:pointer;" src="images/web/Arrow-Up-green-48.png" alt="위쪽화살표">
				<img class="arrow-Left" style="position:absolute;margin:170px 0 0 15px;cursor:pointer;"  src="images/web/Arrow-Left-green-48.png" alt="왼쪽화살표">
				<img class="arrow-Right" style="position:absolute;margin:170px 0 0 65px;cursor:pointer;"  src="images/web/Arrow-Right-green-48.png" alt="오른쪽화살표">
				<img class="arrow-Down" style="position:absolute;margin:195px 0 0 40px;cursor:pointer;"  src="images/web/Arrow-Down-green-48.png" alt="아래쪽화살표">
			</div>
		</div>

		<!-- 이용약관  -->
		<div class="rules_layout">
			<div class="popup_bg"></div>
			<div id="layer1" class="pop-layer">
				<div class="pop-container">
					<div class="pop-conts">
						<!-- content -->
							<h1>서비스 이용약관</h1>
							<p class="pop_txt">
								제 1 장 총칙<br>
								<br>
								제 1 조 (목적)<br>
								<br>
								이 약관은 주식회사 아우라(이하 “회사”)이 제공하는 ‘스마트 건강지킴이’ (이하 “서비스”)를 사용하고자 하는 이용자(이하 “회원”)가 “회사”가 제공하는 “서비스”를 이용함에 있어 “회사”와 “회원”과의 권리, 의무 및 책임사항, 및 “회원”이 “서비스”을 설치하고 이용 하는 데에 따른 이용조건 및 절차 등 기본적인 사항을 규정함을 목적으로 합니다..<br>
								<br>
								<br>
								제 2 조 (약관의 효력 및 변경)
								<br>
								1) 이 약관은 “서비스” 설치 과정 상에 공시되며 이에 대하여 “회원”이 동의함으로써 효력이 발생합니다. 만약, 동 약관에 동의하지 않으시면 프로그램 설치 시 ‘취소’버튼을 누르시면 됩니다. 이 경우 “서비스”는 설치되지 아니합니다.<br>
								<br>
								2) “회사”는 합리적인 사유가 발생할 경우 "약관의 규제에 관한 법률", "정보통신망이용촉진 및 정보보호 등에 관한 법률(이하 "정보통신망법")" 등 관련법을 위배하지 않는 범위에서 이 약관을 개정할 수 있습니다.<br>
								<br>
								3) 회사는 이 약관을 개정할 경우 적용일자 및 개정사유를 명시하여 현행약관과 함께 그 적용일자 7일 이전부터 적용일자 전일까지 “서비스” 화면에 온라인으로 게시하거나 기타 이와 유사한 방법으로 고지합니다.<br>
								<br>
								4) “회사”가 전항에 따라 개정약관을 공지 또는 통지하면서 “회원”이 약관 개정 적용 일까지 “회원”이 명시적으로 거부의사를 표시하지 아니하거나 본 “서비스”를 계속 사용할 경우, “회원”이 개정약관에 동의한 것으로 봅니다.<br>
								<br>
								5) “회원”이 개정약관의 적용에 동의하지 않는 경우 “회사”는 개정 약관의 내용을 적용할 수 없으며, 이 경우 “회원”은 이용계약을 해지할 수 있습니다.<br>
								<br>
								6) “회원”이 변경된 약관을 알지 못하여 발생한 손해에 대하여 “회사”는 책임을 지지 아니합니다.<br>
								<br>
								<br>
								제 3 조 (약관 외 준칙)<br>
								<br>
								본 약관에 명시되지 않은 사항에 대해서는 전기통신기본법, 전기통신사업법 등 관계법령, 상관례 및 “회사”가 정한 “서비스”의 세부이용지침 등의 규정에 의합니다.<br>
								<br>
								<br>
								제 4 조 (용어의 정의 )<br>
								<br>
								이 약관에서 사용하는 용어의 정의는 다음과 같습니다.<br>
								<br>
								1) “서비스”:<br>
								“회원”이 PC, 휴대 전화 단말기 등의 각종 유무선 장치 등 단말기(이하 “단말기”라 함)에서 “컨텐츠”를 등록하면 전화통화 발신 또는 수신 시 자신이 등록한 “컨텐츠”를 본인 또는 상대방이 각각 볼 수 있도록 하는 발신자/수신자표시 제공 서비스를 의미합니다.<br>
								<br>
								2) “회원”:<br>
								“회사”의 “서비스”에 접속하여 본 약관에 동의하고 “회사”가 제공하는 “서비스”를 이용하는 고객을 말합니다.<br>
								<br>
								3) "아이디(ID)":<br>
								“회원”이 “서비스”를 이용하고 “회사”가 회원을 식별하여 “서비스”를 제공하는 데 필요한 기본정보가 되는 ”회원”의 단말기 전화번호를 의미합니다.<br>
								<br>
								4) "컨텐츠":<br>
								“회원”이 “서비스”를 이용함에 있어 다운로드 한 부호ㆍ문자ㆍ음성ㆍ음향ㆍ화상ㆍ동영상 등의 정보 형태의 글, 사진, 동영상 및 각종 파일을 의미합니다.<br>
								<br>
								5) 5) “보관함” :<br>
								“회원”이 다운로드하여 등록하였던 “컨텐츠”를 “서비스”내 ‘보관함’ 메뉴에서 구매한 내역을 확인하고 “컨텐츠”를 다시 볼 수 있는 기능을 의미합니다.<br>
								<br>
								<br>
								제 2 장 “서비스” 이용 계약<br>
								<br>
								<br>
								제 5 조 (이용계약 성립 및 승낙)<br>
								<br>
								1) 이용계약은 “회원”이 본 “서비스” 설치 과정 상의 본 약관, 개인정보 수집 활용이 명시된 창에서 ‘동의’ 버튼을 누르면 본 약관에 동의하는 것으로 간주됩니다.<br>
								<br>
								2) “회사”는 "가입신청자"의 신청에 대하여 “서비스” 이용을 승낙함을 원칙으로 하며, 이용계약은 “회원”의 “서비스” 신청에 대하여 “회사”가 승낙함으로써 성립됩니다.<br>
								<br>
								<br>
								제 6 조 (이용 신청에 대한 승낙의 제한)<br>
								<br>
								“회사”는 다음 각 호에 해당하는 신청에 대하여는 승낙을 하지 않거나 사후에 이용계약을 해지할 수 있습니다.<br>
								<br>
								1) “회원”이 소지한 이동전화 단말기가 본 “서비스”을 지원하지 않는 등 기술상 “서비스” 제공이 불가능 한 경우 또는 “서비스” 관련설비의 여유가 없거나 업무 상 문제가 있는 경우<br>
								<br>
								2) 가입신청자가 이 약관에 의하여 이전에 “회원”자격을 상실한 적이 있는 경우, 단 “회사”의 “회원” 재가입 승낙을 얻은 경우에는 예외로 함.<br>
								<br>
								3) 타인의 명의를 이용한 경우<br>
								<br>
								4) 허위의 정보를 기재하거나, “회사”가 제시하는 내용을 기재하지 않은 경우<br>
								<br>
								5) “회원”의 귀책사유로 인하여 승인이 불가능하거나 기타 규정한 제반 사항을 위반하며 신청하는 경우<br>
								<br>
								① 제2항과 제3항에 따른 신청에 있어 “회사”는 “회원”의 종류에 따라 전문기관을 통한 실명확인 및 본인인증을 요청할 수 있습니다.<br>
								② 제1항에 따라 “회원”가입신청의 승낙을 하지 아니하거나 유보한 경우, “회사”는 원칙적으로 이를 가입신청자에게 알리도록 합니다.<br>
								③ “회사”는 “회원”에 대해 “회사”정책에 따라 등급별로 구분하여 이용시간, 이용횟수, “서비스” 메뉴 등을 세분하여 이용에 차등을 둘 수 있습니다.<br>
								④ ’”회사”"는 “회원”에 대하여 "영화 및 비디오물의 진흥에 관한 법률" 및 "청소년보호법"등에 따른 등급 및 연령 준수를 위해 이용제한이나 등급별 제한을 할 수 있습니다.<br>
								<br>
								<br>
								제 7 조 (“회원”정보의 변경)<br>
								<br>
								1) “회원”은 “서비스”내 "프로필" 화면을 통하여 언제든지 본인의 개인정보를 열람 할 수 있습니다. 다만, “서비스” 관리를 위해 필요한 단말기 식별번호 (디바이스 아이디 또는 IMEI), 전화번호, 아이디 등은 수정이 불가능합니다.<br>
								<br>
								2) “회원”은 “회원” 가입신청 시 기재한 사항이 변경되었을 경우 온라인으로 수정을 하거나 전자우편 기타 방법으로 “회사”에 대하여 그 변경사항을 알려야 합니다. 변경사항을 “회사”에 알리지 않아 발생한 불이익에 대하여 “회사”는 책임지지 않습니다.<br>
								<br>
								<br>
								제 3 장 “서비스” 이용<br>
								<br>
								<br>
								제 8 조 (“서비스”의 이용 개시)<br>
								<br>
								1) “회사”는 “회원”의 이용 신청을 승낙한 시점부터 “서비스”를 개시합니다. 단, 일부 “서비스”의 경우에는 지정된 일자부터 “서비스”을 개시합니다.<br>
								<br>
								2) “회사”의 업무상 또는 기술상의 장애로 인하여 “서비스”을 개시하지 못하는 경우에는 “서비스” 사이트에 공시하거나 “회원”에게 이를 통지합니다.<br>
								<br>
								<br>
								제 9 조 (“서비스”의 이용시간)<br>
								<br>
								1) “서비스”의 이용은 연중무휴 1일 24시간을 원칙으로 합니다. 단, “회사” 업무 상이나 기술상의 이유, 또한 이동통신 서비스의 불안정이나 기술상의 이유로 “서비스”이 일시 중지될 수 있고, 또한 운영상의 목적으로 “회사”가 정한 기간에는 “서비스”이 일시 중지될 수 있습니다. 이러한 경우 “회사”는 사전 또는 사후에 이를 공지합니다.<br>
								<br>
								2) “회사”는 “서비스”을 일정 범위로 분할하여 각 범위 별로 이용 가능한 시간을 별도로 정할 수 있으며 이 경우 그 내용을 공지합니다.<br>
								<br>
								<br>
								제 10 조 (“서비스”의 변경 및 중지)<br>
								<br>
								1) “회사”는 이용 감소로 인한 원활한 “서비스” 제공의 곤란 및 수익성 악화, 기술 진보에 따른 차세대 “서비스”으로의 전환 필요성, “서비스” 제공과 관련한 “회사” 정책의 변경 등 기타 상당한 이유가 있는 경우에 운영상, 기술상의 필요에 따라 제공하고 있는 전부 또는 일부 “서비스”을 변경 또는 중단할 수 있습니다.<br>
								<br>
								2) “회사”는 “서비스”의 제공에 필요한 경우 정기점검을 실시할 수 있으며, 정기점검시간은 “서비스” 제공화면에 공지한 바에 따릅니다.<br>
								<br>
								3) “회사”는 다음 각 호에 해당하는 경우 “서비스”의 전부 또는 일부를 제한하거나 중지할 수 있습니다.<br>
								<br>
								① “서비스”용 설비의 보수 등 공사로 인한 부득이한 경우<br>
								② 정전, 제반 설비의 장애 또는 이용량의 폭주 등으로 정상적인 “서비스” 이용에 지장이 있는 경우<br>
								③ “회원”이 “회사”의 영업활동을 방해하는 경우<br>
								④ 기타 천재지변, 국가비상사태 등 불가항력적인 사유가 있는 경우<br>
								<br>
								4) “회사”는 “서비스”의 일부 또는 전부를 “회사”의 정책 및 운영의 필요상 수정, 중단, 변경할 수 있으며, 이에 대하여 관련법에 특별한 규정이 없는 한 “회원”에게 별도의 보상을 하지 않습니다.<br>
								<br>
								5) “서비스”의 내용, 이용방법, 이용시간에 대하여 변경 또는 “서비스” 중단이 있는 경우에는 변경 또는 중단될 “서비스”의 내용 및 사유와 일자 등은 그 변경 또는 중단 전에 “회사” 웹사이트 또는 “서비스”내 공지사항 화면, 기타 SMS 등 “회원”이 충분이 인지할 수 있는 방법으로 [30]일의 기간을 두고 사전에 공지합니다. 다만, “회사”가 통제할 수 없는 사유로 인한 “서비스”의 중단(운영자의 고의,과실이 없는 장애, 관련 시스템 다운 등)으로 인하여 사전에 통지가 불가능한 경우에는 그러지 아니합니다.<br>
								<br>
								<br>
								제 11 조 (“서비스” 서비스의 제공)<br>
								<br>
								1) “회사”는 “회원”에게 아래와 같은 서비스를 제공합니다.<br>
								<br>
								① 발신자/수신자 표시 서비스<br>
								② 다시보기 서비스<br>
								③ 사진편집 서비스<br>
								④ 컨텐츠 다운로드 서비스<br>
								⑤ 무료 컨텐츠 제공 서비스<br>
								⑥ 유료 컨텐츠 제공 서비스, 유료 컨텐츠의 경우 컨텐츠 판매가격은 컨텐츠 별 명시된 가격으로 한다.<br>
								⑦ 기타 “회사”가 추가 개발하거나 다른 “회사”와의 제휴계약 등을 통해 “회원”에게 제공하는 일체의 서비스<br>
								<br>
								2) “회사”는 “서비스”를 일정범위로 분할하여 각 범위 별로 이용가능시간을 별도로 지정할 수 있습니다. 다만, 이러한 경우에는 그 내용을 사전에 공지합니다.<br>
								<br>
								<br>
								제 4 장 결제에 관한 규정<br>
								<br>
								<br>
								제 12 조 (사용 가능한 결제 수단)<br>
								<br>
								 “서비스”에서 구매한 컨텐츠에 대한 대금지급방법은 다음 각 호의 하나로 할 수 있습니다.<br>
								- 구글 플레이 결제 시스템<br>
								- 기타 회사가 추가 정의하는 결제수단<br>
								<br>
								<br>
								제 13 조 (구매 방법)<br>
								<br>
								회원은 “서비스”에서의 아래의 절차에 따라 방법에 의하여 구매를 신청합니다.<br>
								<br>
								1. 컨텐츠의 선택<br>
								2. 결제 방법의 선택<br>
								3. 결제 후 컨텐츠 설정 및 보관<br>
								<br>
								<br>
								제 13 조 (정보의 제공 및 광고의 게재)<br>
								<br>
								1) “회사”는 “회원”이 “서비스” 이용 중 필요하다고 인정되는 다양한 정보를 “서비스”내 공지사항, “서비스” 화면, SMS, 전자우편 등의 방법으로 “회원”에게 제공할 수 있습니다. 다만, “회원”은 관련법에 따른 거래관련 정보 및 고객문의 등에 대한 답변 등을 제외하고는 언제든지 위 정보제공에 대해서 수신 거절을 할 수 있습니다.<br>
								<br>
								2) “회사”는 “서비스”의 운영과 관련하여 “서비스” 화면, “서비스” 발신자/수신자 표시, 홈페이지 등에 광고를 게재할 수 있습니다.<br>
								<br>
								<br>
								제 4 장 계약당사자의 의무<br>
								<br>
								<br>
								제 14 조 (“회사”의 의무)<br>
								<br>
								1) “회사”는 “회원”에게 본 “서비스”를 설치하고 사용할 수 있는 사용권을 부여합니다.<br>
								<br>
								2) “회사”는 계속적이고 안정적인 “서비스” 제공을 위하여 설비 및 본 “서비스”에 장애가 생기거나 파손된 때에는 부득이한 사유가 없는 한 지체 없이 이를 복구 또는 수리합니다.<br>
								<br>
								3) “회사”는 본 “서비스”가 업데이트 될 경우 “회원”에게 “서비스” 업데이트용 설치파일을 제공합니다. 업데이트 상황에 따라 이미 제공된 본 “서비스” 기능의 일부를 사용할 수 없게 되거나 새로운 기능이 추가되는 경우가 발생합니다.<br>
								<br>
								4) “회사”는 "정보통신망법" 등 관계 법령이 정하는 바에 따라 “회원”의 개인정보를 보호하기 위해 노력합니다. 개인정보의 보호 및 사용에 대해서는 관련법 및 “회사”의 개인정보취급방침이 적용됩니다. “회사”는 “서비스” 제공과 관련하여 알고 있는 “회원”의 신상정보를 본인의 승낙 없이 제3자에게 누설, 배포하지 않습니다. 단, 관계법령에 의한 수사상의 목적으로 관계기관으로부터 요구 받은 경우 등 법률의 규정에 따른 적법한 절차에 의한 요청이 있는 경우에는 예외로 합니다.<br>
								<br>
								5) “회사”는 관련법과 이 약관이 금지하거나 미풍양속에 반하는 행위를 하지 않으며, 계속적이고 안정적으로 “서비스”를 제공하기 위하여 최선을 다하여 노력합니다.<br>
								<br>
								6) “회사”는 “회원”이 안전하게 “서비스”를 이용할 수 있도록 개인정보(신용정보 포함)보호를 위해 보안시스템을 갖추어야 하며 개인정보취급방침을 공시하고 준수합니다.<br>
								<br>
								7) “회사”는 “서비스”이용과 관련하여 “회원”으로부터 제기된 의견이나 불만이 정당하다고 인정되는 경우에는 이를 처리하여야 합니다. “회원”이 제기한 의견이나 불만사항에 대해서는 게시판을 활용하거나 전자우편 등을 통하여 “회원”에게 처리과정 및 결과를 전달합니다.<br>
								<br>
								<br>
								제 15 조 (“회원”의 의무)<br>
								<br>
								1) “회원”의 "아이디" 의 관리에 대한 의무<br>
								<br>
								① “회원”의 "아이디" 에 관한 관리책임은 “회원”에게 있으며, 이를 제3자가 이용하도록 하여서는 안 됩니다.<br>
								② “회사”는 “회원”의 "아이디"가 개인정보 유출 우려가 있거나, 반사회적 또는 미풍양속에 어긋나거나 “회사” 및 “회사”의 운영자로 오인한 우려가 있는 경우, 해당 "아이디"의 이용을 제한할 수 있습니다.<br>
								③ ”회원”은 "아이디" 가 도용되거나 제3자가 사용하고 있음을 인지한 경우에는 이를 즉시 “회사”에 통지하고 “회사”의 안내에 따라야 합니다.<br>
								④ 제3항의 경우에 해당 “회원”이 “회사”에 그 사실을 통지하지 않거나, 통지한 경우에도 “회사”의 안내에 따르지 않아 발생한 불이익에 대하여 “회사”는 책임지지 않습니다.<br>
								<br>
								2) “회원”은 다음 행위를 하여서는 안 됩니다.<br>
								<br>
								① 신청 또는 변경 시 허위내용의 등록<br>
								② 타인의 정보도용<br>
								③ “회사”가 게시한 정보의 변경<br>
								④ 다른 “회원”의 개인정보 및 계정정보를 수집 및 공개하는 행위<br>
								⑤ “회사”의 사전 동의 없이 영리 목적의 광고성 정보를 전송하기 위하여 이용하는 행위<br>
								⑥ 리버스엔지니어링, 디컴파일, 디스어셈블 및 기타 일체의 가공행위를 통하여 “서비스”를 복제, 분해 또는 모방 기타 변형하는 행위<br>
								⑦ 자동 접속 프로그램 등을 사용하는 등 정상적인 용법과 다른 방법으로 “서비스”를 이용하여 “회사”의 서버에 부하를 일으켜 “회사”의 정상적인 “서비스”를 방해하는 행위<br>
								⑧ 본인 아닌 제3자에게 접속권한을 부여하는 행위<br>
								⑨ “회사”와 기타 제3자의 저작권 등 지적재산권에 대한 침해<br>
								⑩ “회사” 및 기타 제3자의 명예를 손상시키거나 업무를 방해하는 행위<br>
								⑪ 외설 또는 폭력적인 메시지, 화상, 음성, 기타 미풍양속에 반하는 정보를 “서비스”에 공개 또는 게시하는 행위<br>
								⑫ “회사”의 동의 없이 영리를 목적으로 “서비스”를 사용하는 행위<br>
								⑬ 기타 불법적이거나 부당한 행위<br>
								<br>
								3) “회원”은 관계법, 이 약관의 규정, 이용안내 및 “서비스”와 관련하여 공지한 주의사항, “회사”가 통지하는 사항 등을 준수하여야 하며, 기타 “회사”의 업무에 방해되는 행위를 하여서는 안 됩니다.<br>
								<br>
								<br>
								제 16 조 (“회원”에 대한 통지)<br>
								<br>
								1) “회사”가 “회원”에 대한 통지를 하는 경우 이 약관에 별도 규정이 없는 한 “서비스” 내 공지사항 또는 SMS등으로 할 수 있습니다.<br>
								<br>
								2) “회사”는 “회원” 전체에 대한 통지의 경우 7일 이상 “회사”의 "웹사이트" 또는 “서비스”내 "공지사항" 화면에 게시함으로써 제1항의 통지에 갈음할 수 있습니다.<br>
								<br>
								<br>
								제 17 조 ("컨텐츠"의 저작권)<br>
								<br>
								1) “회원”이 “서비스”내에서 다운로드한 “컨텐츠”의 저작권은 컨텐츠 저작권자에게 있으며, “회원”은 동 컨텐츠를 “서비스”에서만 활용할 수 있습니다. 컨텐츠 저작권자의 사전 승낙 없이 복제 또는 유통시키거나 상업적으로 이용하는 행위에 대한 책임은 “회원”에게 있으며, “회사”는 이에 대하여 일체의 책임을 지지 아니합니다.<br>
								<br>
								<br>
								제 18 조 ("컨텐츠"의 관리)<br>
								<br>
								1) “회원”의 "컨텐츠"가 "정보통신망법" 및 "저작권법"등 관련법에 위반되는 내용을 포함하는 경우, 권리자는 관련법이 정한 절차에 따라 해당 "컨텐츠"의 사용중단 및 삭제 등을 요청할 수 있으며, “회사”는 관련법에 따라 조치를 취하여야 합니다.<br>
								<br>
								① “회사”는 전항에 따른 권리자의 요청이 없는 경우라도 권리침해가 인정될 만한 사유가 있거나 기타 “회사” 정책 및 관련법에 위반되는 경우에는 관련법에 따라 해당 "게시물"에 대해 임시조치 등을 취할 수 있습니다.<br>
								② 18조에 따른 세부절차는 "정보통신망법" 및 "저작권법"이 규정한 범위 내에서 “회사”가 정한 "게시중단요청서비스"에 따릅니다.<br>
								<br>
								<br>
								제 19 조 (권리의 귀속)<br>
								<br>
								1) “서비스”에 대한 저작권 및 지적재산권은 “회사”에 귀속됩니다. 단, “회원”이 업로드한 "컨텐츠" 및 제휴계약에 따라 제공된 저작물 등은 제외합니다.<br>
								<br>
								2) “회사”가 제공하는 “서비스”의 디자인, “회사”가 만든 텍스트, 스크립트(script), 그래픽, “회원” 상호간 전송 기능 등 “회사”가 제공하는 “서비스”에 관련된 모든 상표, “서비스” 마크, 로고 등에 관한 저작권 기타 지적재산권은 대한민국 및 외국의 법령에 기하여 “회사”가 보유하고 있거나 “회사”에게 소유권 또는 사용권이 있습니다.<br>
								<br>
								3) “회원”은 본 이용약관으로 인하여 “서비스”를 소유하거나 “서비스”에 관한 저작권을 보유하게 되는 것이 아니라, “회사”로부터 “서비스”의 이용을 허락 받게 되는바, “서비스”는 개인용도로만 제공되는 형태로 “회원”이 이용할 수 있습니다.<br>
								<br>
								4) “회원”은 명시적으로 허락된 내용을 제외하고는 “서비스”를 통해 얻어지는 “회원” 상태정보를 영리 목적으로 사용, 복사, 유통하는 것을 포함하여 “회사”가 만든 텍스트, 스크립트, 그래픽의 “회원” 상호간 전송기능 등을 복사하거나 유통할 수 없습니다.<br>
								<br>
								5) ”회사”는 “서비스”와 관련하여 “회원”에게 “회사”가 정한 이용조건에 따라 계정, "아이디", 컨텐츠 등을 이용할 수 있는 이용권만을 부여하며, “회원”은 이를 양도, 판매, 담보제공 등의 처분행위를 할 수 없습니다.<br>
								<br>
								<br>
								제 5 장 계약 해지 및 이용제한<br>
								<br>
								<br>
								제 20 조 (계약해지 및 이용제한)<br>
								<br>
								1) “회원”은 언제든지 “더보기” 내 "해지” 메뉴 화면을 통하여 이용계약 해지 신청을 할 수 있으며, “회사”는 관련법 등이 정하는 바에 따라 이를 즉시 처리하여야 합니다.<br>
								<br>
								2) “회원”이 계약을 해지할 경우, 관련법 및 개인정보취급방침에 따라 “회사”가 “회원”정보를 보유하는 경우를 제외하고는 해지 즉시 “회원”이 보유하고 있는 “회원”의 모든 데이터는 소멸되며, 재 가입 후에도 재 생성되지 아니합니다.<br>
								<br>
								3) “회사”는 “회원”이 이 약관의 의무를 위반하거나 “서비스”의 정상적인 운영을 방해한 경우, 경고, 일시 정지, 영구이용정지 등으로 “서비스” 이용을 단계적으로 제한할 수 있습니다.<br>
								<br>
								4) “회사”는 전항에도 불구하고, "주민등록법"을 위반한 명의도용 및 결제도용, 전화번호 도용, "저작권법" 및 "컴퓨터프로그램보호법"을 위반한 불법프로그램의 제공 및 운영방해, "정보통신망법"을 위반한 불법통신 및 해킹, 악성프로그램의 배포, 접속권한 초과행위 등과 같이 관련법을 위반한 경우에는 즉시 영구이용정지를 할 수 있습니다. 본 항에 따른 영구이용 정지 시 “서비스” 이용을 통해 획득한 혜택 등도 모두 소멸되며, “회사”는 이에 대해 별도로 보상하지 않습니다.<br>
								<br>
								5) “회사”는 “회원”이 계속해서 60일 이상 사용하지 않는 경우, “회원”정보의 보호 및 운영의 효율성을 위해 이용을 제한할 수 있습니다.<br>
								<br>
								6) “회원”탈퇴로 인해 발생한 불이익에 대한 책임은 “회원” 본인에게 있으며, 이용계약이 종료되면 “회사”는 “회원”에게 부가적으로 제공한 각종 혜택을 회수할 수 있습니다.<br>
								<br>
								7) 본 조의 이용제한 범위 내에서 제한의 조건 및 세부내용은 “회사”의 이용제한정책에서 정하는 바에 의합니다.<br>
								<br>
								8) 본 조에 따라 “서비스” 이용을 제한하거나 계약을 해지하는 경우에는 “회사”는 제16조[“회원”에 대한 통지]에 따라 통지합니다.<br>
								<br>
								9) “회원”은 본 조에 따른 이용제한 등에 대해 “회사”가 정한 절차에 따라 이의신청을 할 수 있습니다. 이 때 이의가 정당하다고 “회사”가 인정하는 경우 “회사”는 즉시 “서비스”의 이용을 재개합니다.<br>
								<br>
								<br>
								제 21 조 (책임제한)<br>
								<br>
								1) “회사”는 천재지변 또는 이에 준하는 불가항력으로 인하여 “서비스”를 제공할 수 없는 경우에는 “서비스” 제공에 관한 책임이 면제됩니다.<br>
								<br>
								2) “회사”는 “회원”의 귀책사유로 인한 “서비스” 이용의 장애에 대하여는 책임을 지지 않습니다.<br>
								<br>
								3) “회사”는 “회원”이 “서비스”와 관련하여 게재한 “컨텐츠”관련 정보, 자료, 사실의 신뢰도, 정확성 등의 내용에 관하여는 책임을 지지 않습니다.<br>
								<br>
								4) “회사”는 “회원” 간 또는 “회원”과 제3자 상호간에 “서비스”를 매개로 하여 거래 등을 한 경우에는 책임이 면제됩니다.<br>
								<br>
								5) “회사”는 무료로 제공되는 “서비스” 이용과 관련하여 관련법에 특별한 규정이 없는 한 책임을 지지 않습니다.<br>
								<br>
								6) “회사”는 제3자가 “서비스”내 화면 또는 링크된 웹사이트를 통하여 광고한 제품 또는 “서비스”의 내용과 품질에 대하여 감시할 의무 기타 어떠한 책임도 지지 아니합니다.<br>
								<br>
								7) “회사”및 “회사”의 임직원 그리고 대리인은 다음과 같은 사항으로부터 발생하는 손해에 대해 책임을 지지 아니합니다.<br>
								<br>
								① “회원” 상태정보의 허위 또는 부정확성에 기인하는 손해<br>
								② 그 성질과 경위를 불문하고 “서비스”에 대한 접속 및 “서비스”의 이용과정에서 발생하는 개인적인 손해<br>
								③ 서버에 대한 제3자의 모든 불법적인 접속 또는 서버의 불법적인 이용으로부터 발생하는 손해<br>
								④ 서버에 대한 전송 또는 서버로부터의 전송에 대한 제3자의 모든 불법적인 방해 또는 중단행위로부터 발생하는 손해<br>
								⑤ 제3자가 “서비스”를 이용하여 불법적으로 전송, 유포하거나 또는 전송, 유포되도록 한 모든 바이러스, 스파이웨어 및 기타 악성 프로그램으로 인한 손해<br>
								⑥ 전송된 데이터의 오류 및 생략, 누락, 파괴 등으로 발생되는 손해<br>
								⑦ “회원”간의 “서비스” 이용 과정에서 발생하는 명예훼손 기타 불법행위로 인한 각종 민형사상 책임<br>
								<br>
								<br>
								제 22 조 (해외이용)<br>
								<br>
								“회사”는 대한민국 법령에 따라 대한민국 내에 설치된 서버를 기반으로 “서비스”를 제공, 관리하고 있습니다. 따라서 “회사”는 대한민국 통신 가입자를 대상으로 “서비스”를 제공하며 대한민국 외 해외 통신가입자를 대상으로는 서비스하지 아니합니다. 단, 대한민국 통신사를 가입 후 해외에서 사용 시에 서비스는 가능하나, “회사”는 해외 사용 시 “서비스” 서비스 품질에 대한 책임이 없습니다.<br>
								<br>
								<br>
								제 23 조 (준거법 및 재판관할)<br>
								<br>
								① “회사”와 “회원”간 제기된 소송은 대한민국법을 준거법으로 합니다.<br>
								② ”회사”와 “회원”간 발생한 분쟁에 관한 소송은 서울중앙지방법원을 관할법원으로 합니다.<br>
								<br>
								<br>
								부칙<br>
								<br>
								공고일자: 2012년 11월 20일<br>
								시행일자: 2012년 11월 20일<br>
								<br>
							</p>
						<div class="btn-div">
							<a href="#" class="close_btn">Close</a>
						</div>
						<!-- content -->
					</div>
				</div>
			</div>
		</div>

		<!-- 개인정보취급방침 -->
		<div class="p​olicy_layout">
			<div class="popup_bg"></div>
			<div id="layer2" class="pop-layer">
				<div class="pop-container">
					<div class="pop-conts">
						<!-- content -->
							<h1>서비스 개인정보취급방침</h1>
							<p class="pop_txt">
								주식회사 아우라(이하 “회사”)는 스마트 건강지킴이 서비스(이하 “서비스”)를 제공하면서 이하 통신비밀보호법, 전기통신사업법, 정보 통신망 이용촉진 및 정보보호 등에 관한 법률 등 정보통신서비스 제공자가 준수하여야 할 관련 법령상의 개인정보보호 규정을 준수하며, 관련 법령에 의거한 개인정보취급방침을 정하여 “회원” 권익 보호에 최선을 다하고 있습니다.<br>
								<br>
								회사는 개인정보취급방침을 통하여 회원께서 제공하시는 개인정보가 어떠한 용도와 방식으로 이용되고 있으며, 개인정보보호를 위해 어떠한 조치가 취해지고 있는지 알려드립니다. 회사는 개인정보취급방침을 개정하는 경우 애플리케이션 공지(또는 웹사이트 공지사항, 개별공지)를 통하여 공지할 것입니다.<br>
								<br>
								<br>
								1. 수집하는 개인정보의 항목 및 수집방법<br>
								<br>
								가. 수집하는 개인정보의 항목<br>
								<br>
								1) 회사는 회원가입, 원활한 고객상담, 각종 서비스의 제공을 위해 최초 회원가입 당시 아래와 같은 개인정보를 수집하고 있습니다.<br>
								- 수집항목 : 휴대전화번호<br>
								<br>
								2) “서비스” 이용과정에서 아래와 같은 정보들이 생성되어 수집될 수 있습니다.<br>
								“회원” 메시지, “회원” 이름, 휴대전화번호, “회원”이 다운로드한 컨텐츠, 방문 일시, 컨텐츠 설정/구매 이력, 불량 이용 기록<br>
								<br>
								나. 개인정보 수집방법<br>
								<br>
								“회사”는 다음과 같은 방법으로 개인정보를 수집합니다.<br>
								- “서비스” 프로그램을 실행 또는 사용함으로써 자동으로 수집<br>
								- “서비스” 가입 시 또는 사용 중 “회원”의 자발적 제공을 통한 수집<br>
								- 홈페이지, 서면양식, 팩스, 전화, 상담 게시판, 이메일, 이벤트 응모, 배송요청<br>
								<br>
								<br>
								2. 개인정보의 수집 및 이용목적<br>
								<br>
								가. “서비스” 기본 기능의 제공<br>
								“회원”의 휴대전화번호 및 ”회원”의 전화기 내의 전화번호부에 저장되어 있는 제3자의 전화번호를 이용하여 친구추천 등과 같은 “서비스”의 서비스를 제공합니다. 또한 “서비스”는 “회원”의 가입 시에 단말기 정보 (디바이스 아이디 또는 IMEI)를 이용하여 생성된 관리용 번호를 이용하여 “회원”의 전화번호와 조합하여 개인식별을 위한 “회사” 관리용 회원 계정으로 사용하게 됩니다.<br>
								<br>
								나. “서비스” 제공에 관한 계약 이행 및 “서비스” 제공에 따른 요금정산<br>
								컨텐츠 제공, 특정 맞춤 “서비스” 제공, 본인인증, 구매 및 요금 결제, 요금추심<br>
								<br>
								다. “회원”관리<br>
								“회원”제 “서비스” 이용 및 제한적 본인 확인제에 따른 본인확인, 개인식별, 불량회원 (“서비스” 이용약관 제15조 위반 등으로 인하여 제20조 제4항 영구이용정지 “회원” 및 동 조 제5항에 따라 계약 해지된 영구이용정지 “회원”)의 부정 이용방지와 비인가 사용방지, 가입의사 확인, 가입 및 가입횟수 제한, 만14세 미만 아동 개인정보 수집 시 법정 대리인 동의여부 확인, 추후 법정 대리인 본인확인, 분쟁 조정을 위한 기록보존, 불만처리 등 민원처리, 고지사항 전달<br>
								<br>
								라. 신규 “서비스” 개발 및 마케팅 또는 광고에의 활용<br>
								신규 “서비스” 개발, 통계학적 특성에 따른 “서비스” 제공 및 광고 게재, 이벤트 및 광고성 정보 제공 및 참여기회 제공, 접속빈도 파악, “회원”의 “서비스”이용에 대한 통계<br>
								<br>
								<br>
								3. 개인정보의 공유 및 제공<br>
								<br>
								“회사”는 “회원”들의 개인정보를 "2. 개인정보의 수집목적 및 이용목적"에서 고지한 범위 내에서 사용하며, “회원”의 사전 동의 없이는 동 범위를 초과하여 이용하거나 “회원”의 개인정보를 외부에 공개하지 않습니다. 다만, 아래의 경우에는 예외로 합니다.<br>
								<br>
								- “회원”들이 사전에 공개에 동의한 경우<br>
								- 법령의 규정에 의거하거나, 수사 목적으로 법령에 정해진 절차와 방법에 따라 수사기관의 요구가 있는 경우<br>
								<br>
								<br>
								4. 개인정보의 취급위탁<br>
								<br>
								회사는 전문적인 원활한 서비스 운영을 위해 아래와 같이 개인정보 취급 업무를 외부 업체에 위탁하여 운영할 수 있습니다. 위탁계약시 개인정보보호의 안전을 기하기 위하여 개인정보보호 관련 지시 엄수, 개인정보에 관한 유출금지 및 사고시의 책임부담 등을 명확히 규정하고 위탁계약 내용에 포함되어 있습니다.<br>
								 또한 회사는 “회원”에 대하여 위탁처리기관, 업무내용 및 개인정보의 보유 및 이용기간 등을 명확히 공지하는 등 관계법령을 준수합니다.<br>
								<br>
								[서비스 제공 위탁업체]<br>
								 - 위탁업체명: 주식회사 엠텔로<br>
								 - 위탁업무: 주식회사 엠텔로<br>
								<br>
								<br>
								5. 개인정보의 보유 및 이용기간<br>
								<br>
								서비스 가입일로부터 해지일 까지 보유 및 이용 (다만 약관, 정보통신망 이용촉진 및 정보보호 등에 관한 법률, 통신비밀보호법, 국세기본법 등 관계법령에 따라 보존할 필요성이 있는 경우는 규정에 따라 보존)<br>
								<br>
								<br>
								6. 개인정보 파기절차 및 방법<br>
								<br>
								이용자의 개인정보는 원칙적으로 개인정보의 수집 및 이용목적이 달성되면 지체 없이 파기합니다.<br>
								회사의 개인정보 파기절차 및 방법은 다음과 같습니다.<br>
								<br>
								가. 파기절차<br>
								- 이용자가 회원가입 등을 위해 입력한 정보는 목적이 달성된 후 별도의 DB로 옮겨져(종이의 경우 별도의 서류함) 내부 방침 및 기타 관련 법령에 의한 정보보호 사유에 따라(보유 및 이용기간 참조)일정 기간 저장된 후 파기됩니다.<br>
								- 동 개인정보는 법률에 의한 경우가 아니고서는 보유되는 이외의 다른 목적으로 이용되지 않습니다.<br>
								나. 파기방법<br>
								- 종이에 출력된 개인정보는 분쇄기로 분쇄하거나 소각을 통하여 파기합니다.<br>
								- 전자적 파일 형태로 저장된 개인정보는 기록을 재생할 수 없는 기술적 방법을 사용하여 삭제합니다.<br>
								<br>
								<br>
								7. “회원” 및 법정대리인의 권리와 그 행사방법<br>
								<br>
								- “회원” 및 법정 대리인은 언제든지 등록되어 있는 자신 혹은 당해 만 14세 미만 아동의 개인정보를 조회하거나 수정할 수 있으며 가입 해지를 요청할 수도 있습니다.<br>
								- “회원” 혹은 만 14세 미만 아동의 개인정보 조회, 수정을 위해서는 “서비스”내 "프로필" 변경을, 가입해지(동의철회)를 위해서는 “서비스”내 " 아이디” 탈퇴를 클릭하여 탈퇴가 가능합니다.<br>
								- “회원”이 개인정보의 오류에 대한 정정을 요청하신 경우에는 정정을 완료하기 전까지 당해 개인정보를 이용 또는 제공하지 않습니다. 또한 잘못된 개인정보를 제3 자에게 이미 제공한 경우에는 정정 처리결과를 제3자에게 지체 없이 통지하여 정정이 이루어지도록 하겠습니다.<br>
								- “회사”는 “회원” 혹은 법정 대리인의 요청에 의해 해지 또는 삭제된 개인정보는 "5. 개인정보의 보유 및 이용기간"에 명시된 바에 따라 처리하고 그 외의 용도로 열람 또는 이용할 수 없도록 처리하고 있습니다.<br>
								<br>
								<br>
								8. 개인정보 자동 수집 장치의 설치/운영 및 거부에 관한 사항<br>
								<br>
								“회사”는 계정정보를 생성하기 위해 “회원”이 “서비스” 프로그램을 실행 시 단말기 정보 (디바이스 아이디 또는 IMEI)를 활용하여 관리용 번호를 생성하게 됩니다. 또한 친구추천 등의 기본기능을 제공하기 위하여 “회원”의 전화기 내의 전화번호부에 저장되어 있는 제3자의 전화번호를 자동으로 확인하게 됩니다.<br>
								“회원”이 기기식별번호를 자동으로 수집하는 것을 거부하는 경우 “서비스”을 이용할 수 없습니다.<br>
								<br>
								<br>
								9. 개인정보의 기술적/관리적 보호 대책<br>
								<br>
								“회사”는 “회원”들의 개인정보를 취급함에 있어 개인정보가 분실, 도난, 누출, 변조 또는 훼손되지 않도록 안전성 확보를 위하여 다음과 같은 기술적/관리적 대책을 강구하고 있습니다.<br>
								가. 해킹 등에 대비한 대책<br>
								“회사”는 해킹이나 컴퓨터 바이러스 등에 의해 “회원”의 개인정보가 유출되거나 훼손되는 것을 막기 위해 최선을 다하고 있습니다. 개인정보의 훼손에 대비해서 자료를 수시로 백업하고 있고, 최신 백신프로그램을 이용하여 “회원”들의 개인정보나 자료가 누출되거나 손상되지 않도록 방지하고 있으며, 암호화 통신 등을 통하여 네트워크상에서 개인정보를 안전하게 전송할 수 있도록 하고 있습니다. 그리고 침입차단시스템을 이용하여 외부로부터의 무단 접근을 통제하고 있으며, 기타 시스템적으로 보안성을 확보하기 위한 가능한 모든 기술적 장치를 갖추려 노력하고 있습니다.<br>
								나. 취급 직원의 최소화 및 교육<br>
								“회사”의 개인정보관련 취급 직원은 담당자에 한정시키고 있고 이를 위한 별도의 비밀번호를 부여하여 정기적으로 갱신하고 있으며, 담당자에 대한 수시 교육을 통하여 “서비스” 개인정보취급방침의 준수를 항상 강조하고 있습니다.<br>
								다. 개인정보보호전담기구의 운영<br>
								그리고 사내 개인정보보호전담기구 등을 통하여 “서비스” 개인정보취급방침의 이행사항 및 담당자의 준수여부를 확인하여 문제가 발견될 경우 즉시 수정하고 바로 잡을 수 있도록 노력하고 있습니다. 단, “회원” 본인의 부주의나 인터넷상의 문제로 개인정보가 유출되어 발생한 문제에 대해 “회사”는 일체의 책임을 지지 않습니다.<br>
								<br>
								<br>
								10. 고지의 의무<br>
								<br>
								현 개인정보취급방침의 내용 추가, 삭제 및 수정이 있을 시에는 시행일자 최소 7일전부터 “더보기”내 "공지사항" 화면을 통해 공고할 것입니다.<br>
								<br>
								<br>
								공고일자: 2012년 11월 20일<br>
								시행일자: 2012년 11월 20일<br>
								<br>
							</p>

						<div class="btn-div">
							<a href="#" class="close_btn">Close</a>
						</div>
						<!-- content -->
					</div>
				</div>
			</div>
		</div>

		<!-- 개인정보 활용동의 -->
		<div class="consent_layout">
			<div class="popup_bg"></div>
			<div id="layer3" class="pop-layer">
				<div class="pop-container">
					<div class="pop-conts">
						<!-- content -->
							<h1>개인정보 활용동의</h1>
							<p class="pop_txt">
								■ 개인정보 수집 및 이용<br>
								<br>
								- 수집항목: 휴대 전화번호, 단말기 내 주소록에 저장된 제3자의 연락처(전화번호, 성명 포함), 단말기정보, 이용내역, 통계데이터, 접속로그 등.<br>
								- 수집 및 이용목적: 본인확인, 서비스 제공, 회원관리 등<br>
								- 보유 및 이용기간: 가입기간 및 회원탈퇴 후 6개월<br>
								<br>
								<br>
								■ 개인정보 취급 위탁<br>
								<br>
								- 고객상담, A/S 등 고객관리를 위한 고객센터 운영: 주식회사 아우라<br>
								- 서비스 개발 및 운영, 관리: 주식회사 엠텔로<br>
								- 전산시스템 개발/운영 및 유지보수 : 주식회사 엠텔로<br>
								<br>
								귀사가 위와 같이 수집 및 이용함에 동의함<br>
								<br>
							</p>

						<div class="btn-div">
							<a href="#" class="close_btn">Close</a>
						</div>
						<!-- content -->
					</div>
				</div>
			</div>
		</div>
		
		
		<!-- page05 aside -->
		<div class="aside" id="page05_aside" style="display:none">
			<img class="leaf" src="images/web/leaf.png" alt="나뭇잎">
			<div class="text_mainTitle">
				<h1>[신장]</h1>
				<p>
				자녀에 대한 상세한 신장 정보를 확인 하실 수 있습니다.<br>
				</p>
				<br>
				<p>
				<span>나</span> 이번 달 자녀 키를 확인해 보세요.<br>
				<span>표준</span> 자녀 나이의 표준 신장입니다. 자녀키와 비교해 보세요.<br>
				<span>반평균</span> 자녀의 반 평균입니다.<br>
				<span>학교평균</span> 자녀의 학교 동급생 전체 평균입니다.<br>
				<span>우리학교등수</span> 다른 친구들과 비교한 자녀의 등수를 알 수 있습니다.<br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				키가 클수록 1등에 가까워 집니다.<br>
				<span>종합평가</span> 표준 신장과 비교한 평가입니다.<br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				표준이상: 친구들보다 키가 큰편입니다.<br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				표준이하: 친구들보다 키가 작은 편입니다.<br>
				<span>상세보기</span> 4개월간의 신장발달 이력을 확인 할 수 있습니다.<br>
				<span>추천운동-신장</span> 키 크는 운동을 따라해 보세요.<br>
				</p>
			</div>
		</div>
		
		<!-- page07 aside -->
		<div class="aside" id="page07_aside" style="display:none">
				<img class="leaf" src="images/web/leaf.png" alt="나뭇잎">
				<div class="text_mainTitle">
					<h1>[체중]</h1>
					<p>
					자녀에 대한 상세한 체중 정보를 확인 하실 수 있습니다.<br>
					</p>
					<br>
					<p>
					<span>나</span> 이번 달 자녀 몸무게를 확인해 보세요.<br>
					<span>표준</span> 자녀 나이의 표준 체중입니다. 자녀 체중과 비교해 보세요.<br>
					<span>반평균</span> 자녀의 반 평균입니다.<br>
					<span>학교평균</span> 자녀의 학교 동급생 전체 평균입니다.<br>
					<span>우리학교등수</span> 다른 친구들과 비교한 자녀의 등수를 알 수 있습니다.<br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					체중이 클수록 1등에 가까워 집니다.<br>
					<span>종합평가</span> 표준 체중과 비교한 평가입니다.<br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					표준이상: 친구들보다 체중이 큰편입니다.<br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					표준이하: 친구들보다 체중이 적은 편입니다.<br>
					<span>상세보기</span> 4개월간의 체중발달 이력을 확인 할 수 있습니다.<br>
					<span>추천운동-체중</span> 체중 관리에 좋은 운동을 따라해 보세요.<br>
					</p>
				</div>
			</div>
			
			<!-- page09 aside -->
			<div class="aside" id="page09_aside" style="display:none">
				<img class="leaf" src="images/web/leaf.png" alt="나뭇잎">
				<div class="text_mainTitle">
					<h1>[체형]</h1>
					<p>
					자녀에 대한 BMI와 체지방률을 통한 비만상태를 확인 할 수 있습니다.<br>
					</p>
					<br>
					<p>
					<span>체형</span><br>
					BMI 지수에 따라 저체중, 정상, 과체중, 비만, 중도비만, 고도비만으로 구분됩니다.<br>
					<br>
					<span>BMI</span><br>
					체질량지수.<br>
					체중(kg)을 키의 제곱(m<sup>2</sup>)으로 나눈 값을 통해 지방의 양을 추정하는 <br>
					비만측정법입니다. 수치가 클수록 체격이 커집니다.<br>
					<br>
					<span>체지방률</span><br>
					체중에서 체지방이 차지하는 비율.<br>
					수치가 작을수록 근육형체형이 됩니다.
					</p>
				</div>
			</div>
			
			<!-- page10 aside -->
			<div class="aside" id="page10_aside" style="display:none">
				<img class="leaf" src="images/web/leaf.png" alt="나뭇잎">
				<div class="text_mainTitle">
					<h1>[흡연지수]</h1>
					<p>
					혹시 모를 자녀의 흡연 여부를 확인 할 수 있습니다.<br>
					흡연 판정이 되면 조기 금연 유도를 하는 것이 중요합니다.<br>
					</p>
					<br>
					<p>
					<span>CO</span><br>
					일산화탄소.<br>
					흡연 시 혈액중의 헤모글로빈과 결합하여 체내 산소 공급을 방해합니다.<br>
					<br>
					<span>COHb</span><br>
					카복시헤모글로빈.<br>
					일산화탄소와 헤모글로빈의 결합분자로 산소공급을 방해하여 일산화탄소 <br>
					중독을 일으킵니다.<br>
					<br>
					<span>금연도움사이트</span><br>
					흡연 예방 및 금연유도에 도움을 받을 수 있습니다.
					</p>
				</div>
			</div>
			
			<!-- page12 aside -->
			<div class="aside" id="page12_aside" style="display:none">
				<img class="leaf" src="images/web/leaf.png" alt="나뭇잎">
				<div class="text_mainTitle">
					<h1>[랭킹]</h1>
					<p>
					다른 반 친구들과 학급별 신장, 체중, BMI 랭킹 비교를 할 수 있습니다.<br>
					</p>
					<br>
					<p>
					<span>신장</span><br>
					반별 평균 신장입니다. 자녀의 반 순위를 확인해 보세요.<br>
					<br>
					<span>체중</span><br>
					반별 평균 체중입니다. 자녀의 반 순위를 확인해 보세요.<br>
					<br>
					<span>BMI</span><br>
					반별 평균 BMI입니다. 자녀의 반 순위를 확인해 보세요.
					</p>
				</div>
			</div>
			
			<!-- page15 aside -->
			<div class="aside" id="page15_aside" style="display:none">
				<img class="leaf" src="images/web/leaf.png" alt="나뭇잎">
				<div class="text_mainTitle">
					<h1>[성장점수]</h1>
					<p>
					신장, 체중, 체지방률, BMI 등을 반영한 성장점수입니다.<br>
					100점 이상을 목표로 해봅시다!
					</p>
					<br>
					<p>
					<span>추천운동</span><br>
					건강 상태에 따른 운동을 간고등어 코치 최성조 트레이너를 통해 따라해 보세요.<br>
					<br>
					<span>성장점수 올리기</span><br>
					비만관련질환위험도: BMI 지수에 따른 위험도 입니다.<br>
					위험도에 따라 식습관과 운동을 꾸준히 해주세요.<br>
					<br>
					최성조 간고등어 코치를 클릭해주세요.<br>
					자세한 내용을 확인할 수 있습니다.
					</p>
				</div>
			</div>
	
	</div><!-- wrap div-->
	
	<div class="video_layout">
		<div class="popup_bg"></div>
		<div id="layer4" class="pop-layer">
			<div class="pop-container">
				<div class="videoArea">
					<video id="my_video_1" class="video-js vjs-default-skin" controls 
						   preload="auto" width="670" height="264" 
						   data-setup='{"techOrder":["flash","html5"]}'>
						<source src="" type="video/mp4"></source>
						<!-- video 요소를 지원하지 않는 브라우저입니다. 동영상은 다운로드 후 볼 수 있습니다. -->
					</video>
					<span class="videoListArea"></span>
				</div>
			</div>
		</div>
	</div>
	
	<div class="footer">
		<div class="footer_align">
			<div>
				<a href="#" onclick="company_info('layer1');return false;">이용약관 |</a>
				<a href="#" onclick="company_info('layer2');return false;">개인정보취급방침 |</a>
				<a href="#" onclick="company_info('layer3');return false;">개인정보 활용동의</a>
			</div>
			<!-- 2015-06-30 황종호 팀장 메일요청에 의해 하서호 대표이사  제거 성재혁 -> 성제혁으로 오타 수정 -->
			<!-- <p>대표이사 하서호 성재혁 | 사업자 등록번호 : 140-81-73625</p> -->
			<p>대표이사   성제혁 | 사업자 등록번호 : 140-81-73625</p> 
			<p>대표번호: 1544-1284 | 팩스: 02)2688-8052 | 개인정보/웹사이트/제품 서비스 문의: 070)4699-3679</p>
		</div>
	</div>
</body>

<script>

	checkIEVersion();

	/* ie버전이 7이하이면 alert을 띄우고 화면을 display none 한다. */
	function checkIEVersion(){
		if (navigator.appName == 'Microsoft Internet Explorer'){
			var msg = 'Internet Explorer 7 버전은 지원하지 않습니다.\n8 이상으로 버전을 업데이트 해주세요.';
			var trident = navigator.userAgent.match(/Trident\/(\d.\d)/i);
			if(trident == null){
				if (confirm("\n\n::스마트 건강 지킴이::\n\n\n 1. 인터넷 익스플로러8 이상에서 최적화 되었습니다. \n\n 2. 익스플로러8(Explorer8.0) 크롬(Chrome8.0) 파이어폭스(Firefox3.6)에서 최적화 됨.\n\n 3. 정상적이 페이지를 보시려면 익스플로러8.0 이상으로 설치하시기 바랍니다.\n\n 4. 보안을 위해 익스플로러8 (Explorer8.0) 이상으로 업그레이드 하시기 바랍니다. \n\n\n익스플로러8 최신버전으로 설치 하시겠습니까? \n\n\n 확인: (받으러 가기) 취소: (그냥 접속하기)")==true) {
					location.href="http://www.microsoft.com/korea/windows/internet-explorer";
				} else {
					alert(msg);
					$('.ie_Diversion_layer').css('disply','block').show();
				}
			}
		}
	}
	
	/* ie버전이 7이하이면 alert을 띄우고 화면을 display none 한다. */
// 	function checkIEVersion(){
// 		var msg = 'Internet Explorer 7 버전은 지원하지 않습니다.\n8 이상으로 버전을 업데이트 해주세요.';
// 		var ver = getInternetExplorerVersion();
// 		if ( ver> -1 ){
// 			console.log(ver);
// 			if (  ver == 7.0 ){
// 				alert(msg);
// 				$('.ie_Diversion_layer').css('disply','block').show();
// 			}
// 		}
// 	}
	
// 	/* ie버전 체크 함수 */
// 	function getInternetExplorerVersion(){
// 	   var rv = -1;
// 	   if (navigator.appName == 'Microsoft Internet Explorer'){
// 		  var ua = navigator.userAgent;
// 		  var re  = new RegExp("MSIE ([0-9]{1,}[\.0-9]{0,})");
// 		  if (re.exec(ua) != null)
// 			 rv = parseFloat( RegExp.$1 );
// 	   }
// 	   return rv;
// 	}
	

// 	var $j = jQuery.noConflict();
// 	if ($j.browser.msie == true) { 
// 		if ($j.browser.msie && parseFloat($j.browser.version) < 7) {
// 			if (confirm("\n\n::스마트 건강 지킴이::\n\n\n 1. 인터넷 익스플로러8 이상에서 최적화 되었습니다. \n\n 2. 익스플로러8(Explorer8.0) 크롬(Chrome8.0) 파이어폭스(Firefox3.6)에서 최적화 됨.\n\n 3. 정상적이 페이지를 보시려면 익스플로러8.0 이상으로 설치하시기 바랍니다.\n\n 4. 보안을 위해 익스플로러8 (Explorer8.0) 이상으로 업그레이드 하시기 바랍니다. \n\n\n익스플로러8 최신버전으로 설치 하시겠습니까? \n\n\n 확인: (받으러 가기) 취소: (그냥 접속하기)")==true) {
// 				location.href="http://www.microsoft.com/korea/windows/internet-explorer";
// 			}
// 		}
// 	}

	var pUserName = "";
	var pUserSex = "";
	var pUpdateDate = "";
	var pBmiGradeId = "";
	
	var flag = true;		//swipe 중복 이벤트를 방지하기 위한 변수
	
	//디테일 화면에 자녀 정보를 넣기 위한 변수
	var detailHeaderTxt = '';
	
	function setPageHeader() {

		if(resultRes != '1'){
			detailHeaderTxt ="<h1>" + pUserName + pUserSex + " 건강지킴이</h1><span>update<br>" + pUpdateDate + "</span>";

			$(".detail_children_info").html(detailHeaderTxt);
		}
	}

	//footer 영역 이용약관, 개인정보취급방침, 개인정보 활용동의
	function company_info(layerNumber){
	
		var temp = $('#' + layerNumber);
		var bg = temp.prev().hasClass('popup_bg');//dimmed 레이어를 감지하기 위한 boolean 변수
			if(bg && layerNumber == 'layer1'){
				$('.rules_layout').fadeIn();//'bg' 클래스가 존재하면 레이어가 나타나고 배경은 dimmed 된다.
			}else if(bg && layerNumber == 'layer2'){
				$('.p​olicy_layout').fadeIn();
			}else if(bg && layerNumber == 'layer3'){
				$('.consent_layout').fadeIn();
			}else{
				temp.fadeIn();
			}
		
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
			
			if(bg && layerNumber == 'layer1'){
				$('.rules_layout').fadeOut(); //'bg' 클래스가 존재하면 레이어를 사라지게 한다.
			}else if(bg && layerNumber == 'layer2'){
				$('.p​olicy_layout').fadeOut();
			}else if(bg && layerNumber == 'layer3'){
				$('.consent_layout').fadeOut();
			}else{
				temp.fadeOut();
			}
			e.preventDefault();
		});
		
		$('.rules_layout .popup_bg, .p​olicy_layout .popup_bg, .consent_layout .popup_bg').click(function(e){  //배경을 클릭하면 레이어를 사라지게 하는 이벤트 핸들러
			$('.rules_layout, .p​olicy_layout, .consent_layout').fadeOut();
			e.preventDefault();
		});
	}
	
	//페이지 전환
	$('.intro_bg').click(function(){

		// 로그인 정보 확인
		setUserData("GetIsLogin", "", checkIsLogin);
	});
	
	function checkIsLogin (jo) {
		var val = jo.value;
		
		if (val == "Y") { // 로그인
			
			// 자녀 목록 요청
			setUserData('GetStudent',  '{"mdn":"${g_mdn}", "registrationId":""}', setStudentList);
		
		} else { // 비로그인
			$('#page01').css('display','none');
			$('#page02').css('display','block');
		}
	}
	
	// 자녀 목록
	function setStudentList (jo) {

		var val = jo.value;
		
		var pasteTxt = "";
		
		if (val == null || val.length == 0) {
			pasteTxt = '<div style="position:relative;overflow-y:auto;width:100%;height:220px;margin:10px 0 10px 0;"><table style="width:100%;"><tr><td style="cursor:pointer;padding:8px 4px;border-top:1px solid burlywood;border-bottom:1px solid burlywood;"><span style="padding-left:10px;vertical-align:super;">목록이 없습니다.</span></td></tr></div>';
			$('#studentList').html(pasteTxt);
		} else {

			var valLen = val.length;
			
			//DongQ
		    pasteTxt = '<ul class="list" id="children_list">';

			for (var i=0; i < valLen; i++) {
				pasteTxt += '<li onClick="JavaScript:gotoHealthCareMain(' + val[i].userId + ', ' + val[i].schoolGradeId + ');">';
				pasteTxt += '<img class="children_default_image" src="images/web/children_default_image.png">';
				pasteTxt += '<h1>' + val[i].name + '</h1>';
				pasteTxt += '<div></div>';
				pasteTxt += '<dl>';
				pasteTxt += '<dt style="display:none">나이</dt>';
				pasteTxt += '<dd style="color:gray;font-weight:bold">' + val[i].age + '세</dd>';
				pasteTxt += '<dt style="display:none">최근측정일</dt>';
				pasteTxt += '<dd style="color:#aaa;font-weight:bold;font-size:small">' + val[i].measureDate + '</dd>';
				pasteTxt += '</dl>';
				pasteTxt += '</li>';
			}
			
			pasteTxt += '</ul>';
			
			$('#studentList').html(pasteTxt);

			$('#page01').css('display','none');
			$('#page02').css('display','none');
			$('#page03').css('display','block');
		    
		}
	}

	//인증번호요청
	$('.requestConfirmBtn').click(function(){

		document.confirmForm.mobileNumber.value = removeSpace(document.confirmForm.mobileNumber.value);
		
		var tmpPhoneNo = document.confirmForm.mobileNumber.value;
		
		if (tmpPhoneNo == "") {
			alert("등록된 핸드폰번호를 입력 후\n인증번호를 요청하여 주십시오.");
			return;
		}
		
		if (!isInteger(tmpPhoneNo)) {
			alert("핸드폰번호는 숫자만 입력해 주십시오.");
			return;
		}

		console.log(" 템프 폰번호 ::: => "+tmpPhoneNo + " ::: certkey ::: "+ resCertKeyReq);
		setUserData('SMSCertKeyReq', '{"mdn":"' + tmpPhoneNo + '", "certificationNumber":""}', resCertKeyReq);
	});
	
	//게스트모드요청
	$('.guestBtn').click(function() {
		isGuestMode = true;
		//selUserId = "1775";
		selUserId = "1";
		setUserData('GetBodyMeasureSummary', '{"userId":"' + selUserId + '"}', getBodyMeasureSummary);
		$('#page02').css('display','none');                                               
		$('#page04').css('display','block');  
	});
	
	//로그인요청
	$('.loginBtn').click(function() {
		
		document.confirmForm.mobileNumber.value = removeSpace(document.confirmForm.mobileNumber.value);
		document.confirmForm.confirmNumber.value = removeSpace(document.confirmForm.confirmNumber.value);

		var tmpPhoneNo = document.confirmForm.mobileNumber.value;
		var tmpCertKey = document.confirmForm.confirmNumber.value;
		
		if (tmpPhoneNo == "" || tmpCertKey == "") {
			alert("등록된 핸드폰번호를 입력 후 인증번호를 요청하여\n전달받은 인증번호를 입력하여 주십시오.");
			return;
		}

		if (!isInteger(tmpPhoneNo)) {
			alert("핸드폰번호는 숫자만 입력해 주십시오.");
			return;
		}

		isGuestMode = false;
		setUserData('SMSCertReq', '{"mdn":"' + tmpPhoneNo + '", "certificationNumber":"' + tmpCertKey + '"}', resCertReq);
	});

	// 인증번호 요청 후처리
	function resCertKeyReq (jo) {

		if (jo.result == "0") {
			alert ("인증번호가 문자메시지로 발송되었습니다.");
		} else  {
			//alert(jo.errMsg);
			alert(" 등록되지 않은 전화번호입니다. \n\n 서비스를 이용하시려면 \n 고객센터 1544-1284로 연락바랍니다.");
		}
	}
	
	// 인증 요청 후처리
	function resCertReq (jo) {

		if (jo.result == "0") {

			// 자녀 목록 요청
			setUserData('GetStudent',  '{"mdn":"' + document.confirmForm.mobileNumber.value + '", "registrationId":""}', setStudentList);
			
		} else {
			alert(jo.errMsg);
		}
	}
	
	//건강지킴이 메인. 자녀목록에서 자녀 선택
	var selUserId = "";
	var selSchoolGradeId = "";
	
	function gotoHealthCareMain(pUserId, pSchoolGradeId) {
		selUserId = pUserId;
		selSchoolGradeId = pSchoolGradeId;
		
		setUserData('GetBodyMeasureSummary', '{"userId":"' + pUserId + '"}', getBodyMeasureSummary);
		$('#page03').css('display','none');
		$('#page04').css('display','block');
	}
	
	//스마트건강지킴이
	$('#smart').click(function(){
		document.location.href = "view_web";
	});
	
	//공지사항
	$('#notice').click(function(){
		document.location.href = "notice_web";
	});
	
	//Q&A
	$('#qAnda').click(function(){
		document.location.href = "question_web";
	});
	
	//menu
	function goto_Location(menuCode, url){
		
		//추가정보메뉴 수정2015.04.17 tharaud 
		
	//	if(menuCode != '0105' && menuCode != '0109'){
	//		$('.children_back').css('display','none');
	//		$('.default_back').css('display','block');	
	//	}
	//			
	
			
		if(menuCode != '0109'){
			$('.children_back').css('display','none');
			$('.default_back').css('display','block');	
		}
	//			
		if(menuCode == '0101'){
			$('#page04').css('display','none');
			$('#page05').css('display','block');
			$("#cm_result").html("<iframe scrolling='no' src=\"" + rootPath + url + selUserId + "\">신장</iframe>");
		}
		if(menuCode == '0102'){
			$('#page04').css('display','none');
			$('#page07').css('display','block');
			$("#kg_result").html("<iframe scrolling='no' src=\"" + rootPath + url + selUserId + "\">체중</iframe>");
		}
		if(menuCode == '0103'){
			$('#page04').css('display','none');
			$('#page09').css('display','block');
			$("#bmi_result").html("<iframe scrolling='no' src=\"" + rootPath + url + selUserId + "\">BMI</iframe>");
		}
		if(menuCode == '0104'){
			$('#page04').css('display','none');
			$('#page10').css('display','block');
			$("#smoking_result").html("<iframe scrolling='no' src=\"" + rootPath + url + selUserId + "\">흡연</iframe>");
		}
		if(menuCode == '0105'){
			//추가정보메뉴 수정2015.04.17 tharaud 
			alert("서비스 준비중 입니다.");
			(0 < selUserId) ? setUserData("addinfo/GetMenuList", '{"userId":"' + selUserId + '"}', getAddInfoMenuList) : alert('잘못된 요청입니다.');
			
			//$('#page04').css('display','none');
			//$('#page11').css('display','block');
		}
		if(menuCode == '0106'){
			$('#page04').css('display','none');
			$('#page12').css('display','block');
			$("#ranking_result").html("<iframe scrolling='no' src=\"" + rootPath + url + selUserId + "\">랭킹</iframe>");
		}
		if(menuCode == '0107'){
			$('#page04').css('display','none');
			$('#page15').css('display','block');
			$("#g_poing_result").html("<iframe scrolling='no' src=\"" + rootPath + url + selUserId + "\">성장점수</iframe>");
		}
		if(menuCode == '0108'){
			(0 < selUserId) ? setUserData("food/GetList", '{"userId":"' + selUserId + '"}', getFoodList) : alert('잘못된 요청입니다.');
			
			$('#page04').css('display','none');
			$('#page17').css('display','block');
		}
		if(menuCode == '0109'){
			setUserData('GetVideoList', '{"masterGradeId":"' + pBmiGradeId + '", "schoolGradeId":"' + selSchoolGradeId + '", "userId":"' + selUserId + '"}', getVideoList);
		}
	}
	
	var isGuestMode = false;
	//자녀선택 page03으로 가는 back버튼
	$('.children_back').click(function(){
		$('#page04').css('display','none');
		
		if (isGuestMode) {
			$('#page02').css('display','block');
		} else {
			setUserData('GetStudent',  '{"mdn":"${g_mdn}", "registrationId":""}', setStudentList);
			$('#page03').css('display','block');
		}
	});
	
	//back버튼 클릭시 메인메뉴로 이동
	$('.default_back').click(function(){
		if (isLocSubChart) {
			if (subChart_index == "0") {
				goto_Location('0101','front-views/view?p=height&userId=');
				$('#page07, #page09, #page10, #page12, #page15').css('display','none');
			} else if (subChart_index == "1"){
				goto_Location('0102','front-views/view?p=weight&userId=');
				$('#page05, #page09, #page10, #page12, #page15').css('display','none');
			} else if (subChart_index == "2"){
				goto_Location('0103','front-views/view?p=bmi&userId=');
				$('#page05, #page07, #page10, #page12, #page15').css('display','none');
			} else if (subChart_index == "3"){
				goto_Location('0104','front-views/view?p=smoke&userId=');
				$('#page05, #page07, #page09, #page12, #page15').css('display','none');
			}
		} else {
			$('.children_back').css('display','block');
			$('#page05_aside, #page07_aside, #page09_aside, #page10_aside, #page12_aside, #page15_aside').css('display','none');
			$('#page05').css('display','none');
			$('#page07').css('display','none');
			$('#page09').css('display','none');
			$('#page10').css('display','none');
			$('#page12').css('display','none');
			$('#page15').css('display','none');
			$('#page17').css('display','none');
			$('#page04').css('display','block');
		}
	});
	
	//aside를 변경 해 주기위한 함수
	function changeItemInfo (slideIdx) {
		if (slideIdx == "0") {
			$('#page05_aside').css('display','block');
			$('#page07_aside, #page09_aside, #page10_aside, #page12_aside, #page15_aside').css('display','none');
		} else if (slideIdx == "1") {
			$('#page07_aside').css('display','block');
			$('#page05_aside, #page09_aside, #page10_aside, #page12_aside, #page15_aside').css('display','none');
		} else if (slideIdx == "2") {
			$('#page09_aside').css('display','block');
			$('#page05_aside, #page07_aside, #page10_aside, #page12_aside, #page15_aside').css('display','none');
		} else if (slideIdx == "3") {
			$('#page10_aside').css('display','block');
			$('#page05_aside, #page07_aside, #page09_aside, #page12_aside, #page15_aside').css('display','none');
		} else if (slideIdx == "4") {
			$('#page12_aside').css('display','block');
			$('#page05_aside, #page07_aside, #page09_aside, #page10_aside, #page15_aside').css('display','none');
		} else if (slideIdx == "5") {
			$('#page15_aside').css('display','block');
			$('#page05_aside, #page07_aside, #page09_aside, #page10_aside, #page12_aside').css('display','none');
		}
	}
	 
	/*
		pUrl : 요청하는 컨트롤 함수 
		pars : 파라미터값(Json)
		func : 성공 시 수행할 함수
	*/	
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
	
	function getBodyMeasureSummary(jo){
		var val = jo.value;

		pUserName = val.name;
		pUpdateDate = val.measureDate;
		pBmiGradeId = val.bmiGradeId;
				
		if (val.sex == "M") {
			pUserSex = "군의";
		} else if (val.sex == "F") {
			pUserSex = "양의";
		} else {
			pUserSex = "님의";
		}		
		setPageHeader();
		
		$("#writeCm").html(val.height+'<BR>'+'<small style="font-size:small">'+val.heightStatus+'</small>');
		$("#writeKg").html(val.weight);
		$("#writeBmi").html(val.bmi+'<BR>'+val.bmiStatus);
		$("#writeBmi").html(val.bmi+'<BR>'+'<small style="font-size:small">'+val.bmiStatus+'</small>');
		if(val.smokeStatus == null || val.smokeStatus == "null"){
			$("#writeSmoking").html('미흡연');
		}else{
			$("#writeSmoking").html(val.ppm+' ppm<BR>'+val.cohd+' COHb<BR>'+val.smokeStatus);
		}
		$("#writeG_poing").html(val.growthGrade);
	}
	// 추가정보메뉴 리스트  // 2015.04.16 tharaud 추가 start
	function getAddInfoMenuList(jo){
		var val = jo.value;
		var valLen = val.length;
		var pasteTxt = '';
		var remain = 0;
		var idxSchoolSet = 1;
		var idxDateSet = 1;
		if(0 < valLen){
			pasteTxt = '<div class="school_layout" id="school' + idxSchoolSet + '">';
			pasteTxt += '<h1>추가정보</h1>';
			$('.slide_box').html(pasteTxt);
		}
	
	}
	// 추가정보메뉴 리스트  // 2015.04.16 tharaud 추가 end
	
	function getFoodList(jo){
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
					pasteTxt += '<div class="school_layout" id="school' + idxSchoolSet + '">';
				} else {
					pasteTxt += '<div class="school1_inner' + remain + '">';
				}
				if(val[i].use_yn == null || val[i].use_yn == 'N'){
					pasteTxt += '<h1>'+val[i].school_name+'</h1>';
					pasteTxt += '<h2>'+getFullDateString(val[i].qw)+'</h2>';
					pasteTxt += '<div class="image_box"><img class="image" src="../upload/noimage.gif" alt="식단사진"></div>';
					pasteTxt += '<table style="position:relative;margin:20px 0 0 15px;width:268px;height:170px;">';
					pasteTxt += '<tr>';
					pasteTxt += '<td style="border:1px gray solid;padding:15px;text-align:center;font-weight:bold;">급식 정보가 없습니다.</td>';
					pasteTxt += '</tr>';
					pasteTxt += '</table>';
				}else{
					pasteTxt += '<h1>'+val[i].school_name+'<h2>' + getFullDateString(val[i].qw) + '</h2></h1>';
					pasteTxt += '<div class="image_box"><img class="image" src="../'+val[i].img_url+'" alt="식단사진"></div>';
					pasteTxt += '<table style="position:relative;margin:10px 0 0 15px;width:268px;">';
					pasteTxt += '<tr>';
					pasteTxt += '<td rowspan="2" style="width:20%;border:1px gray solid;vertical-align:middle;text-align:center;padding:3px;font-weight:bold;background-color:#444444;color:#fff;">중식</td>';
					pasteTxt += '<td style="width:80%;border:1px gray solid;padding:3px;line-height:18px;vertical-align:middle;">'+val[i].lunch_main+'</td>';
					pasteTxt += '</tr>';
					pasteTxt += '<tr>';
					pasteTxt += '<td style="border:1px gray solid;padding:3px;line-height:18px;vertical-align:middle;">'+val[i].lunch_detl+'</td>';
					pasteTxt += '</tr>';
					pasteTxt += '</table>';
					pasteTxt += '<table style="position:relative;margin:10px 0 0 15px;width:268px;">';
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
					
					console.log("#####carbohydrate  : "+val[i].carbohydrate);
					console.log("#####protein  : "+val[i].protein);
					console.log("#####fat  : "+val[i].fat);
					console.log("#####calcium  : "+val[i].calcium);
					console.log("#####vitamin_A  : "+val[i].vitamin_A);
					console.log("#####vitamin_C  : "+val[i].vitamin_C);
					console.log("#####nutrient_etc  : "+val[i].nutrient_etc);
				}
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
			pasteTxt += '<h1>급식 정보가 없습니다.</h1>';
			pasteTxt += '</div>';
		}
		
		$('.slide_box').html(pasteTxt);
	}
	
	function getVideoList(jo){
		var val = jo.value;
		var valLen = val.length;
	    
		if(0 == valLen){
			var pasteTxt = '<div style="position:relative;overflow-y:auto;width:100%;height:220px;margin:10px 0 10px 0;"><table style="width:100%;"><tr><td style="cursor:pointer;padding:8px 4px;border-top:1px solid burlywood;border-bottom:1px solid burlywood;"><span style="padding-left:10px;vertical-align:super;">목록이 없습니다.</span></td></tr></div>';
			$('.videoListArea').html(pasteTxt);
		}else{
			//DongQ
		    var pasteTxt = '<div class="videoList_box">';
		    	pasteTxt += '<table class="videoTable">';

			for(var i=0; i<valLen; i+=2 ){
				 pasteTxt +='<tr class="thumbnail_layer">';
				 pasteTxt +='<td onclick="javascript:changeVideoContents(\''+val[i].videoUrl+'\');"><img src="'+val[i].thumbnailUrl+'"></td>';
				 if (i + 1 < valLen) {
					 pasteTxt +='<td onclick="javascript:changeVideoContents(\''+val[i].videoUrl+'\');"><img src="'+val[i+1].thumbnailUrl+'"></td>';
				 } else {
					 pasteTxt +='<td>&nbsp;</td>';
				 }
				 pasteTxt +='</tr>';
				 pasteTxt +='<tr class="thumbnail_name">';
				 pasteTxt +='<td id="odd">'+val[i].title+'</td>';
				 if (i + 1 < valLen) {
					 pasteTxt +='<td id="even">'+val[i+1].title+'</td>';
				 } else {
					 pasteTxt +='<td>&nbsp;</td>';
				 }
				 pasteTxt +='</tr>';
			}
			pasteTxt += '</table>';
			pasteTxt += '</div>';
			$('.videoListArea').html(pasteTxt);
		    
		    changeVideoContents(val[0].videoUrl);
		}
		$('.video_layout').fadeIn();
		var temp = $('#layer4');
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
	}
	
	//DongQ
	$('.video_layout .popup_bg').click(function(){
		$('.video_layout').fadeOut();
		$('.videoListArea').html('');
		myPlayer.pause();
		return false;
	});
	
	// DongQ	
	var myPlayer = videojs('my_video_1');
	function changeVideoContents(url) {
		myPlayer.pause();
		myPlayer.src([{ type: "video/mp4", src: url}]);
		myPlayer.play();
	}
	
	function preventSwipe(){
		flag = true;
	}
	
	/* swipeup */
	$('.slide_box').on('swipeup',function(){
		if(!flag) return;
		if($(".slide_box > div:first-child").css("top") != "0px"){
			$('.slide_box .school_layout div').parents('.school_layout').animate({"top":"-=503px"},"500");
		}else{
			return;
		}
		flag = false;
		setTimeout(preventSwipe,500);
	});
	
	/* swipedown */
	$('.slide_box').on('swipedown',function(){
		if(!flag) return;
		if($(".slide_box > div:first-child").css("top") != "2012px"){
			$('.slide_box .school_layout div').parents('.school_layout').animate({"top":"+=503px"},"500");
		}else{
			return;
		}
		flag = false;
		setTimeout(preventSwipe,500);
	});
	
	/* swipeleft */
	$('.slide_box').on('swipeleft',function(){
		if(!flag) return;
		if($(".slide_box > div:last-child").css("left") != "0px"){
			$('.slide_box .school_layout').animate({"left":"-=296px"},"500");
		}else{
			return;
		}
		flag = false;
		setTimeout(preventSwipe,500);
	});
	
	/* swiperight */
	$('.slide_box').on('swiperight',function(){
		if(!flag) return;
		if($(".slide_box > div:first-child").css("left") != "0px"){
			$('.slide_box .school_layout').animate({"left":"+=296px"},"500");
		}else{
			return;
		}
		flag = false;
		setTimeout(preventSwipe,500);
	});
	
	$('.arrow-Right').click(function(){
		if(!flag) return;
		if($(".slide_box > div:last-child").css("left") != "0px"){
			$('.slide_box .school_layout').animate({"left":"-=296px"},"500");
		}else{
			return;
		}
		flag = false;
		setTimeout(preventSwipe,500);
	});
	
	$('.arrow-Left').click(function(){
		if(!flag) return;
		if($(".slide_box > div:first-child").css("left") != "0px"){
			$('.slide_box .school_layout').animate({"left":"+=296px"},"500");
		}else{
			return;
		}
		flag = false;
		setTimeout(preventSwipe,500);
	});
	
	$('.arrow-Up').click(function(){
		if(!flag) return;
		if($(".slide_box > div:first-child").css("top") != "2012px"){
			$('.slide_box .school_layout div').parents('.school_layout').animate({"top":"+=503px"},"500");
		}else{
			return;
		}
		flag = false;
		setTimeout(preventSwipe,500);
	});

	$('.arrow-Down').click(function(){
		if(!flag) return;
		if($(".slide_box > div:first-child").css("top") != "0px"){
			$('.slide_box .school_layout div').parents('.school_layout').animate({"top":"-=503px"},"500");
		}else{
			return;
		}
		flag = false;
		setTimeout(preventSwipe,500);
	});
</script>
</html>