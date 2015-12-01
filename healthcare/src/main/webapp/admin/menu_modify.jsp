<%@page import="com.healthcare.admin.school.School"%>
<%@page import="com.healthcare.admin.school.Schools"%>
<%@ page import="java.util.Collection"%>
<%@ page import="java.util.Iterator"%>
<%@page import="com.healthcare.admin.menu.Menu"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%  
 request.setCharacterEncoding("UTF-8");
%>
<%
	SchoolMgrHelper schoolMgrHelper = new SchoolMgrHelper();
	School school = null;
	Schools schools = schoolMgrHelper.getListSchool(request);
	Iterator itSchool = schools.getSchools().iterator(); 
	
	MenuMgrHelper menuMgrHelper = new MenuMgrHelper();
	Menu menu = new Menu();
	
	menu = menuMgrHelper.getMenu(request);
%>
<!-- top include -->
<%@ include file="/admin/admin_top.jsp" %>
<script type="text/javascript">
$(document).ready(function(){

    $(".onlyNumber").keyup(function(event){
        if (!(event.keyCode >=37 && event.keyCode<=40)) {
            var inputVal = $(this).val();
            $(this).val(inputVal.replace(/[^0-9.0-9]/gi,''));
        }

    });
    $(".onlyNumber").blur(function(event){
        if (!(event.keyCode >=37 && event.keyCode<=40)) {
            var inputVal = $(this).val();
            $(this).val(inputVal.replace(/[^0-9.0-9]/gi,''));
        }

    });
});
	var i=0;
	
	function save(form, pAction) { 
		if (i==0) {
			
			if("" == form.school_id.value) {
	            alert("학교를 선택해 주십시오.\n");
	            form.school_id.focus();
	            return;
	        }
			if("" == form.school_year.value) {
	            alert("년도를 선택해 주십시오.\n");
	            form.school_year.focus();
	            return;
	        }
			if("" == form.school_month.value) {
	            alert("월을 선택해 주십시오.\n");
	            form.school_month.focus();
	            return;
	        }
			if("" == form.school_date.value) {
	            alert("일자를 선택해 주십시오.\n");
	            form.school_date.focus();
	            return;
	        }
			if("" == form.school_day.value) {
	            alert("요일을 선택해 주십시오.\n");
	            form.school_day.focus();
	            return;
	        }

			if("N" == form.use_yn.value) {
				i++;
				form.action= pAction;
				form.submit();
				
	        	ButtonProcessingSmall(form);  // Loading Image
	            return false;
	        }
			
// 			if("" == form.lunch_main.value) {
// 	            alert("중식 메뉴를 입력해 주십시오.\n");
// 	            form.lunch_main.focus();
// 	            return;
// 	        }
// 			if("" == form.lunch_detl.value) {
// 	            alert("중식 상세 메뉴를 입력해 주십시오.\n");
// 	            form.lunch_detl.focus();
// 	            return;
// 	        }
// 			if("" == form.lunch_calorie.value) {
// 	            alert("중식 칼로리를 입력해 주십시오.\n");
// 	            form.lunch_calorie.focus();
// 	            return;
// 	        }
// 			if("" == form.dinner_main.value) {
// 				alert("석식 메뉴를 입력해 주십시오.\n");
// 	            form.dinner_main.focus();
// 	            return;
// 	        }
// 			if("" == form.dinner_detl.value) {
// 				alert("석식 상세 메뉴를 입력해 주십시오.\n");
// 	            form.dinner_detl.focus();
// 	            return;
// 	        }
// 			if("" == form.dinner_calorie.value) {
// 				alert("석식 칼로리를 입력해 주십시오.\n");
// 	            form.dinner_calorie.focus();
// 	            return;
// 	        }

			if (!isInteger(form.lunch_calorie.value)) {
				alert("중식 메뉴 총열량은 숫자만 입력해 주십시오.");
				return;
			}
			/* 
			if (!isInteger(form.dinner_calorie.value)) {
				alert("석식 메뉴 총열량은 숫자만 입력해 주십시오.");
				return;
			}
			 */
			if(document.docForm.is_file[0].checked && "" == form.img_url.value){
				if(!confirm("등록된 사진이 없습니다. \n진행하시겠습니까?\n")){
					form.img_url.focus();
		            return;
				}
			}
			
			i++;
			form.action= pAction;
			form.submit();
			
        	ButtonProcessingSmall(form);  // Loading Image
				
		}else {
			alert("처리중입니다.");
			document.location.href="./menu.jsp";
		}
	}
	
	function chkIsFile()
	{
	    if (document.docForm.is_file[0].checked) // 파일선택
	    { 
	    	document.getElementById("doc_url_span").innerHTML = '<input name="img_url" type="file" class="darkgray12" style="HEIGHT: 19px;" >';
	    	return;
	    }
	    else                        // 경로입력
	    {
	    	document.getElementById("doc_url_span").innerHTML = '<a href="javascript:imgPopup(\'<%=menu.getImg_url()%>\');"><img src="./images/icon_file.gif"></a>';
	    	return;
	    }
	}

	
	function setUseYN(form, selVal) {
		
		if(selVal == "N") {
			form.lunch_main.disabled = true;
			form.lunch_detl.disabled = true;
			form.lunch_calorie.disabled = true;
			//form.dinner_main.disabled = true;
			//form.dinner_detl.disabled = true;
			//form.dinner_calorie.disabled = true;			
		} else {
			form.lunch_main.disabled = false;
			form.lunch_detl.disabled = false;
			form.lunch_calorie.disabled = false;
			//form.dinner_main.disabled = false;
			//form.dinner_detl.disabled = false;
			//form.dinner_calorie.disabled = false;
		} 
	}

	function imgPopup(url){
		var popOption = "width=400, height=300,top=100,left=100 status=no, directories=no, location=no, toolbar=no, menubar=no";
		if(1<url.length){
			window.open(rootPath + url,"",popOption);
		}else{
			alert("이미지가 없습니다.");
		}
	}
	
</script>
    <td valign="top" style="padding-left:5"> 
<!-- 컨텐츠영역 시작 -->	
	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="53" valign="bottom" class="darkgray16b"> 식단관리</td>
        </tr>
		<tr>
			<td height="3" bgcolor="#FF5500"></td>
		</tr>
        <tr>
          <td height="20"></td>
        </tr>
      </table>
      <form name="docForm" method="post" enctype="multipart/form-data">
      	  <input type="hidden" name="img_url_org" value="<%=menu.getImg_url()%>">
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
	          	<select class="darkgray12" id="school_id" name="school_id">
					<option value = "">선택</option>
					<%
						while(itSchool.hasNext()){
							school = (School)itSchool.next();
					%>
					<option value="<%=school.getSchool_id()%>" <%= (menu.getSchool_id().equals(school.getSchool_id())) ? "selected" : "" %>><%=school.getSchool_name()%>&nbsp;&nbsp;&nbsp;&nbsp;</option>
	               	<%
						}
	               	%>
				</select>
	          </td>
	        </tr>
	        <tr> 
	          <td height="1" bgcolor="#67AEB8"></td>
	          <td bgcolor="cccccc"></td>
	        </tr>
	        <tr> 
	          <td align="right" bgcolor="F8F6F6" style="padding-right:10">일자</td>
	          <td style="padding-left:10">
	          	<select class="darkgray12" id="school_year" name="school_year">
	          		<option value = "">선택</option>
	          		<option value = "2013">2013</option>
	          		<option value = "2014">2014</option>
	          		<option value = "2015">2015</option>
	          		<option value = "2016">2016</option>
	          		<option value = "2017">2017</option>
	          		<option value = "2018">2018</option>
	          		<option value = "2019">2019</option>
	          		<option value = "2020">2020</option>
	          	</select>
	          	년
	          	
	          	<select class="darkgray12" id="school_month" name="school_month">
	          		<option value = "">선택</option>
	          		<option value = "01">01</option>
	          		<option value = "02">02</option>
	          		<option value = "03">03</option>
	          		<option value = "04">04</option>
	          		<option value = "05">05</option>
	          		<option value = "06">06</option>
	          		<option value = "07">07</option>
	          		<option value = "08">08</option>
	          		<option value = "09">09</option>
	          		<option value = "10">10</option>
	          		<option value = "11">11</option>
	          		<option value = "12">12</option>
	          	</select>
	          	월
	          	
	          	<select class="darkgray12" id="school_date" name="school_date">
	          		<option value = "">선택</option>
	          		<option value = "01">01</option>
	          		<option value = "02">02</option>
	          		<option value = "03">03</option>
	          		<option value = "04">04</option>
	          		<option value = "05">05</option>
	          		<option value = "06">06</option>
	          		<option value = "07">07</option>
	          		<option value = "08">08</option>
	          		<option value = "09">09</option>
	          		<option value = "10">10</option>
	          		<option value = "11">11</option>
	          		<option value = "12">12</option>
	          		<option value = "13">13</option>
	          		<option value = "14">14</option>
	          		<option value = "15">15</option>
	          		<option value = "16">16</option>
	          		<option value = "17">17</option>
	          		<option value = "18">18</option>
	          		<option value = "19">19</option>
	          		<option value = "20">20</option>
	          		<option value = "21">21</option>
	          		<option value = "22">22</option>
	          		<option value = "23">23</option>
	          		<option value = "24">24</option>
	          		<option value = "25">25</option>
	          		<option value = "26">26</option>
	          		<option value = "27">27</option>
	          		<option value = "28">28</option>
	          		<option value = "29">29</option>
	          		<option value = "30">30</option>
	          		<option value = "31">31</option>
	          	</select>
	          	일
	          	<select class="darkgray12" id="school_day" name="school_day">
	          		<option value = "">선택</option>
	          		<option value = "1">월요일</option>
	          		<option value = "2">화요일</option>
	          		<option value = "3">수요일</option>
	          		<option value = "4">목요일</option>
	          		<option value = "5">금요일</option>
	          		<option value = "6">토요일</option>
	          		<option value = "7">일요일</option>
	          	</select>
	          </td>
	        </tr>
	        <tr> 
	          <td height="1" bgcolor="#67AEB8"></td>
	          <td bgcolor="cccccc"></td>
	        </tr>
	        <tr> 
	          <td align="right" bgcolor="F8F6F6" style="padding-right:10">급식 여부</td>
	          <td style="padding-left:10">
				<select class="darkgray12" id="use_yn" name="use_yn" onchange="setUseYN(document.docForm, this.options[this.selectedIndex].value);">
					<%
						if(menu.getUse_yn().equals("N")){
					%>
	          		<option value = "Y">예</option>
	          		<option value = "N" selected="selected">아니오</option>
	          		<%
						}else{
	          		%>
	          		<option value = "Y" selected="selected">예</option>
	          		<option value = "N">아니오</option>
	          		<%
						}
	          		%>
	          	</select>
	          	<br><font color="red">※ 평일 중 공휴일/개교기념일/자율휴업일 등 급식이 시행되지 않은 경우 "N" 선택 </font>
			  </td>
	        </tr>
	        <tr> 
	          <td height="1" bgcolor="#67AEB8"></td>
	          <td bgcolor="cccccc"></td>
	        </tr>
	        <tr> 
	          <td align="right" bgcolor="F8F6F6" style="padding-right:10">중식 메뉴</td>
	          <td style="padding-left:10">
	          	<input name="lunch_main" type="text" class="darkgray12" maxlength="25" style="WIDTH: 300px; HEIGHT: 19px; border:solid 1 #cccccc" value="<%=menu.getLunch_main()%>">
	          	(최대 25자)
	          	<br><font color="red">※ 주메뉴명 </font>
			  </td>
	        </tr>
	        <tr> 
	          <td height="1" bgcolor="#67AEB8"></td>
	          <td bgcolor="cccccc"></td>
	        </tr>
	        <tr> 
	          <td align="right" bgcolor="F8F6F6" style="padding-right:10">중식 메뉴 상세</td>
	          <td style="padding-left:10">
	          	<input name="lunch_detl" type="text" class="darkgray12" maxlength="60" style="WIDTH: 300px; HEIGHT: 19px; border:solid 1 #cccccc" value="<%=menu.getLunch_detl()%>">
	          	(최대 60자)
	          	<%-- <textarea name="lunch_detl" rows="5" class="darkgray12" style="WIDTH: 100%; border:solid 1 #cccccc" maxlength="60"><%=menu.getLunch_detl()%></textarea> --%>
			  	<br><font color="red">※ 반찬 / 부식 등 </font>
			  </td>
			 
	        </tr>
	        <tr> 
	          <td height="1" bgcolor="#67AEB8"></td>
	          <td bgcolor="cccccc"></td>
	        </tr>
	        <tr> 
	          <td align="right" bgcolor="F8F6F6" style="padding-right:10">중식 메뉴 총열량</td>
	          <td style="padding-left:10">
	          	<input name="lunch_calorie" type="text" class="darkgray12 onlyNumber" maxlength="4" onkeypress="return onlyNumber1(event)" style="WIDTH: 130px; HEIGHT: 19px; border:solid 1 #cccccc" value="<%=menu.getLunch_calorie()%>"> kcal
	          	(0~9999)
	          	<br><font color="red">※ 숫자만 입력 </font>
			  </td>
	        </tr>
	        <tr> 
	          <td height="1" bgcolor="#67AEB8"></td>
	          <td bgcolor="cccccc"></td>
	        </tr>
	        <!-- 2014.05.27 영양소 7개 추가 -->
	        <!-- 2015.06.02 메일 요청에 의해  칼슘 위치 비타민 A,C 밑으로 위치 변경   -->
	        <tr> 
	          <td align="right" bgcolor="F8F6F6" style="padding-right:10">탄수화물</td>
	          <td style="padding-left:10"><input name="carbohydrate" type="text" class="darkgray12 onlyNumber" onkeypress="return onlyNumber2(event)" maxlength="5" style="IME-MODE:disabled; WIDTH: 130px; HEIGHT: 19px; border:solid 1 #cccccc" value="<%=menu.getCarbohydrate()%>" >g
	          	 	<!--2015.06.02 메일  요청으로 <br/>(0.0 ~ 999.9까지 가능, 소수점 이하 1자리 숫자만 입력가능)-->
	          </td>
	        </tr>
	        <tr> 
	          <td height="1" bgcolor="#67AEB8"></td>
	          <td bgcolor="cccccc"></td>
	        </tr>
	        <tr> 
	          <td align="right" bgcolor="F8F6F6" style="padding-right:10">단백질</td>
	          <td style="padding-left:10"><input name="protein" type="text" class="darkgray12 onlyNumber" onkeypress="return onlyNumber2(event)" maxlength="5" style="IME-MODE:disabled; WIDTH: 130px; HEIGHT: 19px; border:solid 1 #cccccc" value="<%=menu.getProtein()%>" >g
	          	 	<!--2015.06.02 메일  요청으로 <br/>(0.0 ~ 999.9까지 가능, 소수점 이하 1자리 숫자만 입력가능)-->
	          </td>
	        </tr>
	        <tr> 
	          <td height="1" bgcolor="#67AEB8"></td>
	          <td bgcolor="cccccc"></td>
	        </tr>
	        <tr> 
	          <td align="right" bgcolor="F8F6F6" style="padding-right:10">지방</td>
	          <td style="padding-left:10"><input name="fat" type="text" class="darkgray12 onlyNumber" onkeypress="return onlyNumber2(event)" maxlength="5" style="IME-MODE:disabled; WIDTH: 130px; HEIGHT: 19px; border:solid 1 #cccccc" value="<%=menu.getFat()%>" >g
	          	 	<!--2015.06.02 메일  요청으로 <br/>(0.0 ~ 999.9까지 가능, 소수점 이하 1자리 숫자만 입력가능)-->
	          </td>
	        </tr>
	    
	        <tr> 
	          <td height="1" bgcolor="#67AEB8"></td>
	          <td bgcolor="cccccc"></td>
	        </tr>
	        <tr> 
	          <td align="right" bgcolor="F8F6F6" style="padding-right:10">비타민A</td>
	          <td style="padding-left:10"><input name="vitamin_A" type="text" class="darkgray12 onlyNumber" onkeypress="return onlyNumber2(event)" maxlength="5" style="IME-MODE:disabled; WIDTH: 130px; HEIGHT: 19px; border:solid 1 #cccccc" value="<%=menu.getVitamin_A()%>" >R.E
	          	 	<!--2015.06.02 메일  요청으로 <br/>(0.0 ~ 999.9까지 가능, 소수점 이하 1자리 숫자만 입력가능)-->
	          </td>
	        </tr>
	        <tr> 
	          <td height="1" bgcolor="#67AEB8"></td>
	          <td bgcolor="cccccc"></td>
	        </tr>
	        <tr> 
	          <td align="right" bgcolor="F8F6F6" style="padding-right:10">비타민C</td>
	          <td style="padding-left:10"><input name="vitamin_C" type="text" class="darkgray12 onlyNumber" onkeypress="return onlyNumber2(event)" maxlength="5" style="IME-MODE:disabled; WIDTH: 130px; HEIGHT: 19px; border:solid 1 #cccccc" value="<%=menu.getVitamin_C()%>" >mg
	          	 	<!--2015.06.02 메일  요청으로 <br/>(0.0 ~ 999.9까지 가능, 소수점 이하 1자리 숫자만 입력가능)-->
	          </td>
	        </tr>
	            <tr> 
	          <td height="1" bgcolor="#67AEB8"></td>
	          <td bgcolor="cccccc"></td>
	        </tr>
	      
	            <tr> 
	          <td align="right" bgcolor="F8F6F6" style="padding-right:10">칼슘</td>
	          <td style="padding-left:10"><input name="calcium" type="text" class="darkgray12 onlyNumber" onkeypress="return onlyNumber2(event)" maxlength="5" style="IME-MODE:disabled; WIDTH: 130px; HEIGHT: 19px; border:solid 1 #cccccc" value="<%=menu.getCalcium()%>" >mg
	          	 	<!--2015.06.02 메일  요청으로 <br/>(0.0 ~ 999.9까지 가능, 소수점 이하 1자리 숫자만 입력가능)-->
	          </td>
	        </tr>
	        <tr> 
	          <td height="1" bgcolor="#67AEB8"></td>
	          <td bgcolor="cccccc"></td>
	        </tr>
	        <tr> 
	          <td align="right" bgcolor="F8F6F6" style="padding-right:10">기타</td>
	          <td style="padding-left:10"><input name="nutrient_etc" type="text" class="darkgray12 onlyNumber" onkeypress="return onlyNumber2(event)" maxlength="5" style="IME-MODE:disabled; WIDTH: 130px; HEIGHT: 19px; border:solid 1 #cccccc" value="<%=menu.getNutrient_etc()%>" >
	          	<br/>(0.0 ~ 999.9까지 가능, 소수점 이하 1자리 숫자만 입력가능)
	          </td>
	        </tr>
	        <tr> 
	          <td height="1" bgcolor="#67AEB8"></td>
	          <td bgcolor="cccccc"></td>
	        </tr>
	        <%-- 
	        <tr> 
	          <td align="right" bgcolor="F8F6F6" style="padding-right:10">석식 메뉴</td>
	          <td style="padding-left:10"><input name="dinner_main" type="text" class="darkgray12" maxlength="60" style="WIDTH: 300px; HEIGHT: 19px; border:solid 1 #cccccc" value="<%=menu.getDinner_main()%>">
	          <br><font color="red">※ 주메뉴명 </font> </td>
	        </tr>
	        <tr> 
	          <td height="1" bgcolor="#67AEB8"></td>
	          <td bgcolor="cccccc"></td>
	        </tr>
	        <tr> 
	          <td align="right" bgcolor="F8F6F6" style="padding-right:10">석식 메뉴 상세</td>
	          <td style="padding-left:10">
	          	<input name="dinner_detl" type="text" class="darkgray12" maxlength="120" style="WIDTH: 130px; HEIGHT: 19px; border:solid 1 #cccccc" value="<%=menu.getDinner_detl()%>">
	          	<textarea name="dinner_detl" rows="5" class="darkgray12" style="WIDTH: 100%; border:solid 1 #cccccc" maxlength="60"><%=menu.getDinner_detl()%></textarea>
			   <br><font color="red">※ 반찬 / 부식 등 </font>
			  </td>
	        </tr>
	        <tr> 
	          <td height="1" bgcolor="#67AEB8"></td>
	          <td bgcolor="cccccc"></td>
	        </tr>
	        <tr> 
	          <td align="right" bgcolor="F8F6F6" style="padding-right:10">석식 메뉴 총열량</td>
	          <td style="padding-left:10"><input name="dinner_calorie" type="text" class="darkgray12" maxlength="14" style="WIDTH: 130px; HEIGHT: 19px; border:solid 1 #cccccc" value="<%=menu.getDinner_calorie()%>"> kcal
	          	<br><font color="red">※ 숫자만 입력 </font></td>
	        </tr>
	        <tr> 
	          <td height="1" bgcolor="#67AEB8"></td>
	          <td bgcolor="cccccc"></td>
	        </tr>
	         --%>
	        <tr> 
	          <td align="right" bgcolor="F8F6F6" style="padding-right:10">사진 등록</td>
	          <td style="padding-left:10">
	          	<input type="radio" name="is_file" value="Y" style='margin:0 0 -2 2' onClick="JavaScript:chkIsFile();">새 이미지 등록
	            <input type="radio" name="is_file" value="N" checked style='margin:0 0 -2 2' onClick="JavaScript:chkIsFile();">기존 이미지 유지
	            &nbsp;
	            <span id="doc_url_span"></span>
			  </td>
	        </tr>
	        <tr> 
	          <td height="1" bgcolor="#67AEB8"></td>
	          <td bgcolor="cccccc"></td>
	        </tr>
	      </table>
	      <input type="hidden" id="menu_id" name="menu_id" value="<%=menu.getMenu_id()%>">
	</form>	
<!-- 끝 : 테이블 -->		
<!-- 시작 : 버튼 -->
	<table width="700" border="0" cellspacing="0" cellpadding="0">
		<tr> 
			<td align="right" >
				<a href="JavaScript:save(document.docForm, 'menu_modify_action.jsp');"><img src="./images/btn_save.gif" border="0"></a>&nbsp;
				<%-- <a href="menu_delete_action.jsp"><img src="./images/btn_delete.gif" border="0"></a>&nbsp; --%>
				<a href="./menu.jsp"><img src="./images/btn_list.gif" border="0"></a>
			</td>
		</tr>
	</table>  
<!-- 끝 : 버튼 -->
	</td>
<!-- 컨텐츠영역 끝 -->
<td width="2%">&nbsp;</td>
<script type="text/javascript">
	
	window.onload = function(){
		var yearRowSel = document.docForm.school_year.options.length;
		var monthRowSel = document.docForm.school_month.options.length;
		var dateRowSel = document.docForm.school_date.options.length;
		var dayRowSel = document.docForm.school_day.options.length;

	    for(var i=1; i<yearRowSel+1;i++){
			if(document.docForm.school_year.options[i].value == '<%=menu.getSchool_year()%>'){
				document.docForm.school_year.options[i].selected = true;
				break;
			}
		}
	    for(var i=1; i<monthRowSel+1;i++){
			if(document.docForm.school_month.options[i].value == '<%=menu.getSchool_month()%>'){
				document.docForm.school_month.options[i].selected = true;
				break;
			}
		}
	    for(var i=1; i<dateRowSel+1;i++){
			if(document.docForm.school_date.options[i].value == '<%=menu.getSchool_date()%>'){
				document.docForm.school_date.options[i].selected = true;
				break;
			}
		}
	    for(var i=1; i<dayRowSel+1;i++){
			if(document.docForm.school_day.options[i].value == '<%=menu.getSchool_day()%>'){
				document.docForm.school_day.options[i].selected = true;
				break;
			}
		}
	    
	    chkIsFile();

		setUseYN(document.docForm, "<%= menu.getUse_yn()%>");
	};
</script>
<!-- bottom include -->
<%@ include file="/admin/admin_bottom.jsp" %>