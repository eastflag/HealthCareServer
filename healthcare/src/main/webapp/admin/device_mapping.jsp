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
<%
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

	SchoolMgrHelper schoolMgrHelper = new SchoolMgrHelper();
	School school = null;
	Schools schools = schoolMgrHelper.getListSchool(request);
	Iterator itSchool = schools.getSchools().iterator(); 
%>
<!-- top include --> 
<%@ include file="/admin/admin_top.jsp" %>
<script type="text/javascript">
var form = null;
var school_gradeID_selbox = new Array();
school_gradeID_selbox[0] = new Array("1", "2", "3", "4", "5", "6");
school_gradeID_selbox[1] = new Array("7", "8", "9");
school_gradeID_selbox[2] = new Array("10", "11", "12");

var school_grade_selbox = new Array();
school_grade_selbox[0] = new Array("1학년", "2학년", "3학년", "4학년", "5학년", "6학년");
school_grade_selbox[1] = new Array("1학년", "2학년", "3학년");
school_grade_selbox[2] = new Array("1학년", "2학년", "3학년");

window.onload = function(){
	form = document.form;		
};
function changeSchool (sel) {
	
	selData = sel.split(",");
	
	selIdx = -1;
	
	if (selData[1] == "E") {
		selIdx = 0;
	} else if (selData[1] == "M") {
		selIdx = 1;
	} else if (selData[1] == "H") {
		selIdx = 2;
	}

	var s_sel = form.grade_id;

	for(var i = s_sel.length; i >= 0; i--){
		s_sel.options[i] = null;
	}

	//s_sel.options[0] = new Option ("학년", "");

	if(selIdx != -1){
		for(var i=0; i < school_gradeID_selbox[selIdx].length; i++){
			s_sel.options[i] = new Option(school_grade_selbox[selIdx][i], school_gradeID_selbox[selIdx][i]);

			if(s_sel.options[i].value == '<%= SessionManager.getSearchKeys(request).get("grade_id") %>'){
				s_sel.options[i].selected = true;
			}
		}
		
		if (sel != ",") {
			 
			var optionObj = form.school_id.options;
			var selSchoolIdx = optionObj.selectedIndex;
			
			//form.school_id.value = selData[0];
			//form.school_name.value = optionObj[selSchoolIdx].text;
		}
	} else {
		//form.school_id.value = "";
		//form.school_name.value = "";
	}
	
	//form.stu_class.value = "";
	changeGrade();
}
function changeGrade() {	
	var selData = $("#school_id").val().split(",");	
	var schoolId = selData[0];
	var gradeId = $("#grade_id").val();
	
	var param = {"schoolId":schoolId, "gradeId":gradeId};
	
	$.ajax({
		type : "POST",
		url : rootPath+"/device/getClassList",
		async : false,
		data : param,
		dataType : "json",
		cache: false,
		success: function(data) {
			var resultList = "";
			if(data.length>0) {
				for(var i=0; i<data.length; i++) {
					resultList += "<option value=\""+data[i]+"\">"+data[i]+"반</option>";
				}
			}
			$("#classId").html(resultList);
			getStuList();
	    },
		error : function (data) {
			alert('죄송합니다. 잠시 후 다시 시도해주세요.');
			return false;
		}
	});	
}
function getStuList() {
	var selData = $("#school_id").val().split(",");	
	var schoolId = selData[0];
	var gradeId = $("#grade_id").val();
	var classId = $("#classId").val();
	
	var param = {"schoolId":schoolId, "gradeId":gradeId, "classId":classId};
	
	$.ajax({
		type : "POST",
		url : rootPath+"/device/getStudentList",
		async : false,
		data : param,
		dataType : "json",
		cache: false,
		success: function(data) {
			$("#studentList").html("");
			var resultList = "";
 			resultList += "<colgroup>";
			resultList += "<col style=\"width:10%\"/>";	
			resultList += "<col style=\"width:30%\"/>";	
			resultList += "<col style=\"width:30%\"/>";	
			resultList += "<col style=\"width:30%\"/>";	
			resultList += "</colgroup>"; 
			resultList += "<tr class=\"thTR\">";
			resultList += "<th class=\"noneBG\">NO</th>";
			resultList += "<th>이름</th>";
			resultList += "<th>MAC</th>";
			resultList += "<th>설정</th>";
			resultList += "</tr>		";	
			$("#studentList").append(resultList);

			if(data.studentList.length > 0) {
				for(var i=0; i<data.studentList.length; i++) {
					var resultList = "";		
					resultList += "<tr ";
					if(i%2==1) {
						resultList += " class=\"evenTR\"  ";
					}
					resultList += " >";

					resultList += "	<td>"+(i+1)+"</td>";						
					resultList += "	<td>"+data.studentList[i].studentName+"</td>";
					resultList += "	<td><select name='mac' id='mac'>";
					if(data.studentList[i].mac=="") {
						resultList += "<option value=''>---- 기기선택 ----</option>";	
					} else {
						resultList += "<option value='"+data.studentList[i].deviceName+"'>"+data.studentList[i].deviceName+"</option>";	
					}		
					resultList += "</select></td>";	
					resultList += "	<td>";
					resultList += "<a href=\"javascript:saveStudentDevice('"+i+"','"+data.studentList[i].studentId+"');\">저장</a> ";
					resultList += "<a href=\"javascript:deleteStudentDevice('"+data.studentList[i].deviceName+"','"+data.studentList[i].studentId+"');\">삭제</a>";
					resultList += "	</td>";
					resultList += "</tr>";
					
					$("#studentList").append(resultList);
				}
				makeMacSelect(data.restMacList);
			} else {
				var resultList = "";		
				resultList += "<tr><td colspan=\"4\">등록된 학생이 없습니다.</td></tr>";
				$("#studentList").append(resultList);
			}
	    },
		error : function (data) {
			alert('죄송합니다. 잠시 후 다시 시도해주세요.');
			return false;
		}
	});	
}
function makeMacSelect(restMacList) {
	$("select[name='mac']").each(function(index) {
		if(restMacList.length>0) {
			for(var i=0; i<restMacList.length; i++) {
				$(this).append("<option value='"+restMacList[i].deviceName+"'>"+restMacList[i].deviceName+"</option>");
			}
		}
	});
}
function saveStudentDevice(i,studentId) {
	var deviceName = $("select[name='mac']:eq("+i+")").val();
	var param = {"deviceName":deviceName, "studentId":studentId};

	if(deviceName=="") {
		alert("디바이스를 선택해주세요.");
		return;
	} else {
		$.ajax({
			type : "POST",
			url : rootPath+"/device/saveStudentDevice",
			async : false,
			data : param,
			dataType : "json",
			cache: false,
			success: function(data) {
				if(data==0) { // 실패
					alert('죄송합니다. 잠시 후 다시 시도해주세요.');
					return false;
				} else { // 성공
					alert("저장되었습니다.");
					getStuList();
				}
		    },
			error : function (data) {
				alert('죄송합니다. 잠시 후 다시 시도해주세요.');
				return false;
			}
		});
	}	
}
function deleteStudentDevice(deviceName, studentId) {
	//var deviceName = $("select[name='mac']:eq("+i+")").val();
	
	if(deviceName=="") {
		alert("삭제할 디바이스가 없습니다.");
		return;
	} else {
		var param = {"studentId":studentId};
		
		$.ajax({
			type : "POST",
			url : rootPath+"/device/deleteStudentDevice",
			async : false,
			data : param,
			dataType : "json",
			cache: false,
			success: function(data) {
				if(data==0) { // 실패
					alert('죄송합니다. 잠시 후 다시 시도해주세요.');
					return false;
				} else { // 성공
					alert("삭제되었습니다.");
					getStuList();
				}
		    },
			error : function (data) {
				alert('죄송합니다. 잠시 후 다시 시도해주세요.');
				return false;
			}
		});	
	}
}
</script>
<td width="98%" valign="top" style="padding-left:5">
	<form name="form" id="form" action="JavaScript:searchList();" method="post">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="53" valign="bottom" class="darkgray16b">디바이스맵핑</td>
			</tr>
			<tr>
				<td height="3" bgcolor="#FF5500"></td>
			</tr>
			<tr>
				<td height="20"></td>
			</tr>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					학교&nbsp;
					<select  class="darkgray12" id="school_id" name="school_id" onChange="JavaScript:changeSchool(this.value);" style="width:140px;">
						<option value = ",">전체</option>
						<%
							while(itSchool.hasNext()){
								school = (School)itSchool.next();
						%>
							<option value="<%=school.getSchool_id()%>,<%=school.getSection()%>"><%=school.getSchool_name()%></option>
	                	<%
							}
	                	%>
					</select> |
					학년&nbsp;
					<select  class="darkgray12" id="grade_id" name="grade_id" onchange="JavaScript:changeGrade();"  style="width:120px;">
					</select> |
					반&nbsp;
					<select  class="darkgray12" id="classId" name="classId" onchange="JavaScript:getStuList();"  style="width:120px;">
					</select> 
				</td>
			</tr>
		</table>
	</form>
	<table id="studentList" class="list" width="100%" border="0" cellspacing="2" cellpadding="0">
	</table>
</td>
<td width="2%">&nbsp;</td>
</tr>
</table>
<!-- bottom include -->
<%@ include file="/admin/admin_bottom.jsp" %>