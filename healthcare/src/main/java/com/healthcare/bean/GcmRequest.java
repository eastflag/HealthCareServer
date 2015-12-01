package com.healthcare.bean;

public class GcmRequest {
	
	String schoolId;
	String pushType;
	String year;

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	
	public String getPushType() {
		return pushType;
	}

	public void setPushType(String pushType) {
		this.pushType = pushType;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "GcmRequest [schoolId=" + schoolId + ", pushType="+ pushType + ", year="+ year + "]";
	}
	
	
	
	
}
