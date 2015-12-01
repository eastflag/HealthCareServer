package com.healthcare.bean;

public class VideoListRequest {

	// 비디오 master Grade Id
	String masterGradeId;
	
	//  학생 학년 ID
	String schoolGradeId;

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

	@Override
	public String toString() {
		return "VideoListRequest [masterGradeId=" + masterGradeId
				+ ", schoolGradeId=" + schoolGradeId + "]";
	}

}
