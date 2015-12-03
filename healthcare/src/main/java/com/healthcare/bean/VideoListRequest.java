package com.healthcare.bean;

public class VideoListRequest {

	// 비디오 master Grade Id
	String masterGradeId;
	
	//  학생 학년 ID
	String schoolGradeId;
	
	// 학생 ID
	String user_id;

	public String getMasterGradeId() {
		return masterGradeId;
	}

	public void setMasterGradeId(String masterGradeId) {
		this.masterGradeId = masterGradeId;
	}

	public String getSchoolGradeId() {
		return schoolGradeId;
	}

	public void setSchoolGradeId(String schoolGradeId) {
		this.schoolGradeId = schoolGradeId;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "VideoListRequest [masterGradeId=" + masterGradeId
				+ ", schoolGradeId=" + schoolGradeId + ", user_id=" + user_id + "]";
	}

}
