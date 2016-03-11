package com.sovate.activity.common;

public class SchoolUtil {

	public static String getShoolGradeName(String gradeId)
	{
		String gradeName = "1";
		
		// 초등학교
		if(gradeId.equals("1")){
			gradeName = "1";
		} else if(gradeId.equals("2")){
			gradeName = "2";
		} else if(gradeId.equals("3")){
			gradeName = "3";
		} else if(gradeId.equals("4")){
			gradeName = "4";
		} else if(gradeId.equals("5")){
			gradeName = "5";
		} else if(gradeId.equals("6")){
			gradeName = "6";
		} 
		// 중학교
		else if(gradeId.equals("7")){
			gradeName = "1";
		} else if(gradeId.equals("8")){
			gradeName = "2";
		} else if(gradeId.equals("9")){
			gradeName = "3";
		} 
		//  고등학교
		else if(gradeId.equals("10")){
			gradeName = "1";
		} else if(gradeId.equals("11")){
			gradeName = "2";
		} else if(gradeId.equals("12")){
			gradeName = "3";
		}
		
		return gradeName;
	}
}
