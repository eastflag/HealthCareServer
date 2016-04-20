<%@page import="com.healthcare.common.Config"%>
<%@page import="com.healthcare.common.SessionManager"%>
<%@page import="com.healthcare.admin.school.Schools"%>
<%@page import="com.healthcare.admin.school.School"%>
<%@page import="com.healthcare.common.JSPUtil"%>
<%@page import="java.util.Iterator"%>
<%@ page import="java.net.*" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%  
	request.setCharacterEncoding("UTF-8");
%>
<!-- top include --> 
<%@ include file="/admin/admin_top.jsp" %>
<script type="text/javascript">
$(document).ready(function() {
	if($("#curPageNum").val()=="") {
		$("#curPageNum").val("1");
	}
	if($("#listScale").val()=="") {
		$("#listScale").val("10");
	}
	getListTotalRowCnt(); // 게시물 수 가져오기
	getList($("#curPageNum").val());
	
	/* $("input[name='mac']").blur(function() {
		checkMac(this);
	}); */
});
function getListTotalRowCnt() {
	var param = $("#frm").serialize();
	$.ajax({
		type : "POST",
		url : rootPath+"/device/getDeviceListCnt",
		async : false,
		data : param,
		dataType : "json",
		cache: false,
		success: function(data) {
				$("#totalRowCnt").val(data);
	    },
		error : function (data) {
			alert('죄송합니다. 잠시 후 다시 시도해주세요.');
			return false;
		}
	});	
}
function getList(num) {
	$("#curPageNum").val(num);
	var curPageNum = num;
	var listScale = $("#listScale").val();
	
	var param = $("#frm").serialize();
	
	$.ajax({
		type : "POST",
		url : rootPath+"/device/getDeviceList",
		async : false,
		data : param,
		dataType : "json",
		cache: false,
		success: function(data) {
			var rowNo = $("#totalRowCnt").val() - (curPageNum-1)*listScale;
			$("#deviceList").html("");
			var resultList = "";
 			resultList += "<colgroup>";
			resultList += "<col style=\"width:10%\"/>";	
			resultList += "<col style=\"width:30%\"/>";	
			resultList += "<col style=\"width:30%\"/>";	
			resultList += "<col style=\"width:30%\"/>";	
			resultList += "</colgroup>"; 
			resultList += "<tr class=\"thTR\">";
			resultList += "<th class=\"noneBG\">NO</th>";
			resultList += "<th>MAC</th>";
			resultList += "<th>디바이스 이름</th>";
			resultList += "<th>설정</th>";
			resultList += "</tr>		";	
			$("#deviceList").append(resultList);
console.log(data);
			if(data.length > 0) {
				for(var i=0; i<data.length; i++) {
					var resultList = "";		
					resultList += "<tr ";
					if(i%2==1) {
						resultList += " class=\"evenTR\"  ";
					}
					resultList += " >";

					resultList += "	<td>"+rowNo+"</td>";						
					resultList += "	<td><input type=\"text\" name=\"mac\" id=\"mac\" value=\""+data[i].mac+"\" maxlength=\"17\"/></td>";				
					resultList += "	<td><input type=\"hidden\" name=\"deviceNameOld\" value=\""+data[i].deviceName+"\" />";
					resultList += "<input type=\"text\" name=\"deviceName\" value=\""+data[i].deviceName+"\"/></td>";
					resultList += "	<td>";
					resultList += "<a href=\"javascript:saveDevice('"+i+"');\">수정</a> ";
					resultList += "<a href=\"javascript:deleteDevice('"+data[i].deviceName+"');\">삭제</a>";
					resultList += "	</td>";
					resultList += "</tr>";
					
					$("#deviceList").append(resultList);
					rowNo = rowNo-1;
				}
			} else {
				var resultList = "";		
				resultList += "<tr><td colspan=\"4\">등록된 기기가 없습니다.</td></tr>";
				$("#deviceList").append(resultList);
			}
			paging();
	    },
		error : function (data) {
			alert('죄송합니다. 잠시 후 다시 시도해주세요.');
			return false;
		}
	});	
}
function saveDevice(i) {
	var mac = $("input[name='mac']:eq("+i+")").val();
	var deviceNameOld = $("input[name='deviceNameOld']:eq("+i+")").val();
	var deviceName = $("input[name='deviceName']:eq("+i+")").val();
	
	var param = {"mac":mac, "deviceNameOld":deviceNameOld, "deviceName":deviceName};
	console.log(param);
	$.ajax({
		type : "POST",
		url : rootPath+"/device/saveDevice",
		async : false,
		data : param,
		dataType : "json",
		cache: false,
		success: function(data) {
			if(data>0) {
				alert("수정되었습니다.");
				getList($("#curPageNum").val());
			} else {
				alert("수정할수 없습니다.");
				return;
			}
	    },
		error : function (data) {
			alert('죄송합니다. 잠시 후 다시 시도해주세요.');
			return false;
		}
	});	
}
function deleteDevice(deviceName) {
	var param = {"deviceName":deviceName};
	console.log(param);
	$.ajax({
		type : "POST",
		url : rootPath+"/device/deleteDevice",
		async : false,
		data : param,
		dataType : "json",
		cache: false,
		success: function(data) {
			if(data>0) {
				alert("삭제되었습니다.");
				getList($("#curPageNum").val());
			} else {
				alert("삭제할수 없습니다.");
				return;
			}
	    },
		error : function (data) {
			alert('죄송합니다. 잠시 후 다시 시도해주세요.');
			return false;
		}
	});	
}
function checkMac(obj) {
	var param = {"mac":obj.value};
	$.ajax({
		type : "POST",
		url : rootPath+"/device/checkMac",
		async : false,
		data : param,
		dataType : "json",
		cache: false,
		success: function(data) {
			console.log(data);
			if(data>0) {
				alert("중복된 mac이 있습니다.");
				//obj.value = "";
				obj.focus();
			}
	    },
		error : function (data) {
			alert('죄송합니다. 잠시 후 다시 시도해주세요.');
			return false;
		}
	});	
}
function paging() {
	$("#paging").html("");
	var curPageNum = $("#curPageNum").val();
	var listScale = $("#listScale").val();

	var curPageNumScale = 10; // 보여줄 리스트 수
	var totalRowCnt = $("#totalRowCnt").val(); // 불러온 row 수

	if(Number(totalRowCnt) > 0) {	
		
		var totalpage = parseInt(totalRowCnt / listScale + (totalRowCnt % listScale > 0 ? 1:0)); // 전체 페이지
		if(curPageNum>totalpage) {
			curPageNum = totalpage;
		}	
		$("#pageCntInfo").html(curPageNum+"/"+totalpage);
		
		var groupno = parseInt(curPageNum / curPageNumScale + (curPageNum % curPageNumScale > 0 ? 1:0)); // 현재 그룹 번호
		var endpage = groupno * curPageNumScale; // 현재 그룹 끝 번호
		var startpage = endpage - (curPageNumScale - 1); // 현재 그룹 시작 번호
		if(endpage > totalpage) { // 현재 그룹 끝 번호가 전체 페이지 수 보다 클 경우
			endpage = totalpage; // 현재 그룹 끝번호와 전체 페이지 수를 같게
		}

		//int prevpageno = startpage - curPageNumScale; // 이전 페이지 번호
		//int nextpageno = startpage + curPageNumScale; // 다음 페이지 번호
		var prevpageno = Number(curPageNum) - Number(curPageNumScale); // 이전 페이지 번호
		var nextpageno = Number(curPageNum) + Number(curPageNumScale); // 다음 페이지 번호

		if(prevpageno < 1) {
			prevpageno = 1;
		}
		
		if(nextpageno>totalpage) {
			if(nextpageno+1 < totalpage) {
				nextpageno = curPageNum+1;
			} else {
				nextpageno = totalpage;//totalpage / curPageNumScale * curPageNumScale ;
			}
		}
		
		var pagingList = "";
		
		if(curPageNum!=1) {
			pagingList += "<img src=\"./images/prev2.gif\" alt=\"<<\" onclick=\"getList(1);\" style=\"cursor:pointer\" />&nbsp;&nbsp;";
		}
		if(prevpageno>1) {
			pagingList += "<img src=\"./images/prev1.gif\" alt=\"<\" onclick=\"getList("+prevpageno+");\" style=\"cursor:pointer\"/>&nbsp;&nbsp;";
		}
		
		for(var i=startpage; i<=endpage; i++) {
			if(curPageNum==i) {
				pagingList += " <span>"+i+"</span> ";
			} else {
				pagingList += "&nbsp;<a href='javascript:getList("+i+");'>["+i+"]</a>&nbsp;";	
			}
			
			if(i<endpage) {
				//pagingList += "<img src=\"/resources/images/paging_dot.gif\" alt=\".\"/>";	
			}
		}
		if(nextpageno<totalpage) {
			pagingList += "&nbsp;&nbsp;<img src=\"./images/next1.gif\" alt=\">\" onclick=\"getList("+nextpageno+");\" style=\"cursor:pointer\"/>";
		}
		if(curPageNum<totalpage) {
			pagingList += "&nbsp;&nbsp;<img src=\"./images/next2.gif\" alt=\">>\" onclick=\"getList("+totalpage+");\" style=\"cursor:pointer\"/>";
		}
		$("#paging").append(pagingList);
	}
}
function addDevice() {
	var mac = $("#macNew").val();
	var deviceName = $("#deviceNameNew").val();
	
	var param = {"mac":mac, "deviceName":deviceName};
	console.log(param);
	$.ajax({
		type : "POST",
		url : rootPath+"/device/addDevice",
		async : false,
		data : param,
		dataType : "json",
		cache: false,
		success: function(data) {
			if(data>0) {
				$("#macNew").val("");
				$("#deviceNameNew").val("");
				alert("추가되었습니다.");
				getList(1);
			} else {
				alert("추가할수 없습니다.");
				return;
			}
	    },
		error : function (data) {
			alert('죄송합니다. 잠시 후 다시 시도해주세요.');
			return false;
		}
	});	
}
</script>
<td width="98%" valign="top" style="padding-left:5">
	<form name="frm" id="frm" method="post">
		<input type="hidden" name="curPageNum" id="curPageNum" value="${request.curPageNum }" />
		<input type="hidden" name="listScale" id="listScale" value="${request.listScale }" />
		<input type="hidden" name="totalRowCnt" id="totalRowCnt" />
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="53" valign="bottom" class="darkgray16b">디바이스관리</td>
			</tr>
			<tr>
				<td height="3" bgcolor="#FF5500"></td>
			</tr>
			<tr>
				<td height="20"></td>
			</tr>
		</table>
	</form>
	MAC: <input type="text" name="macNew" id="macNew" maxlength="17"/>
	디바이스이름: <input type="text" name="deviceNameNew" id="deviceNameNew" />
	<a href="javascript:addDevice();">추가</a>
	<table id="deviceList" class="list" width="100%" border="0" cellspacing="2" cellpadding="0">
	</table>
	<div class="paging" id="paging">
	</div>
</td>
<td width="2%">&nbsp;</td>
</tr>
</table>
<!-- bottom include -->
<%@ include file="/admin/admin_bottom.jsp" %>