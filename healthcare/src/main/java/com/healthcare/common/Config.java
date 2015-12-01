/**
  * $Id: Config.java,v 1.3 2001/12/07 00:06:41 Park $
  * Copyright 2001 Dong Gyoo Lee All rights reserved.
  **/

package com.healthcare.common;

//파일 업로드 경로 파일 확인 

public interface Config
{
	public static final int    NUM_OF_LINE50 = 50 ;
    public static final int    NUM_OF_LINE15 = 15 ;
    public static final int    NUM_OF_LINE20 = 20 ;
    public static final int    NUM_OF_LINE10 = 10 ;
    public static final int    NUM_OF_LINE5 = 5 ;
    public static final int    NUM_OF_LINE3 = 3 ;

    public static final String DEFAULT_ENCODING = "KSC5601";
	public static final String DELIMETER = ";" ;
    public static final int    MAXSIZE_OF_INFOSERVICEIMG = 1024*1024*1024;

    //식단 관리 upload 경로 변경
  public static final String SCHOOL_FOOD_IMG_UPLOAD_DIR = "C:/Program Files/Apache Software Foundation/Tomcat 7.0/webapps/HealthCare/upload/"; //실서버

    public static final String SCHOOL_FOOD_IMG_ALIAS_DIR = "upload/";
    public static final String SCHOOL_INFO_EXCEL_UPLOAD_DIR = "C:/Program Files/Apache Software Foundation/Tomcat 7.0/webapps/HealthCare/upload/excel/";
    public static final String SCHOOL_INFO_EXCEL_ALIAS_DIR = "upload/excel/";

}