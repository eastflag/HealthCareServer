package com.healthcare.common;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;


/**
 * request에서 파라매터의 값을 구하기 위한 클래스
 */
public class HttpParameter {

    /**
     * 0
     */
    public static final int DEFAULT_INT_VALUE = 0;
    /**
     * 0
     */
    public static final int DEFAULT_INTEGER_VALUE = 0;
    /**
     * 0l
     */
    public static final long DEFAULT_LONG_VALUE = 0l;
    /**
     * 0d
     */
    public static final double DEFAULT_DOUBLE_VALUE = 0.0d;
    /**
     * 0f
     */
    public static final float DEFAULT_FLOAT_VALUE = 0f;
    /**
     * ""
     */
    public static final String DEFAULT_STRING_VALUE = "";
    /**
     * ""
     */
    public static final String DEFAULT_STRING_BUFFER_VALUE = "";
    /**
     * false
     */
    public static final boolean DEFAULT_BOOLEAN_VALUE = false;
    /**
     * null
     */
    public static final Date DEFAULT_DATE_VALUE = null;
    /**
     * 'yyyyMMddhhmmss'
     */
    public static final String DEFAULT_DATE_FORMAT = "yyyyMMddhhmmss";

    /**
     * http로 전달된 parameter의 값을 int 타입으로 반환한다.
     * 파라매터의 값이 적절하지 않거나 해당 파라매터가 존재하지 않으면
     * defaultValue를 반환한다.
     * @param request 파라매터가 포함된 request 객체
     * @param name 파라매터의 이름
     * @param defaultValue 파라매터가 존재하지 않을 경우에 반환될 default 값
     * @return int 타입의 파라매터 값
     */
    public static int getInt(HttpServletRequest request, String name,int defaultValue) {

        int value = defaultValue;

        try {
           value = Integer.parseInt(request.getParameter(name).trim());
        } catch (NumberFormatException e) {
        } catch (NullPointerException e) {
        }

        return value;

    }

    /**
     * http로 전달된 parameter의 값을 int 타입으로 반환한다.
     * 파라매터의 값이 적절하지 않거나 해당 파라매터가 존재하지 않으면
     * DEFAULT_INT_VALUE 값을 반환한다.
     * @param request 파라매터가 포함된 request 객체
     * @param name 파라매터의 이름
     * @return int 타입의 파라매터 값
     */
    public static int getInt(HttpServletRequest request, String name)
    {
        return getInt(request, name, DEFAULT_INT_VALUE);
    }

    /**
     * http로 전달된 parameter의 값을 Integer 타입으로 반환한다.
     * 파라매터의 값이 적절하지 않거나 해당 파라매터가 존재하지 않으면
     * defaultValue를 반환한다.
     * @param request 파라매터가 포함된 request 객체
     * @param name 파라매터의 이름
     * @param defaultValue 파라매터가 존재하지 않을 경우에 반환될 default 값
     * @return Integer 타입의 파라매터 값
     */
    public static Integer getInteger(HttpServletRequest request, String name,int defaultValue) {

        int value = defaultValue;

        try {
           value = Integer.parseInt(request.getParameter(name).trim());
        } catch (NumberFormatException e) {
        } catch (NullPointerException e) {
        }

        return new Integer(value);

    }

    /**
     * http로 전달된 parameter의 값을 Integer 타입으로 반환한다.
     * 파라매터의 값이 적절하지 않거나 해당 파라매터가 존재하지 않으면
     * DEFAULT_INT_VALUE 값을 반환한다.
     * @param request 파라매터가 포함된 request 객체
     * @param name 파라매터의 이름
     * @return Integer 타입의 파라매터 값
     */
    public static Integer getInteger(HttpServletRequest request, String name)
    {
        return getInteger(request, name, DEFAULT_INTEGER_VALUE);
    }

    /**
     * http로 전달된 parameter의 값을 long 타입으로 반환한다.
     * 파라매터의 값이 적절하지 않거나 해당 파라매터가 존재하지 않으면
     * defaultValue를 반환한다.
     * @param request 파라매터가 포함된 request 객체
     * @param name 파라매터의 이름
     * @param defaultValue 파라매터가 존재하지 않을 경우에 반환될 default 값
     * @return long 타입의 파라매터 값
     */
    public static long getLong(HttpServletRequest request, String name, long defaultValue) {

        long value = defaultValue;

        try {
            value = Long.parseLong(request.getParameter(name).trim());
        } catch (NumberFormatException e) {
        } catch (NullPointerException e) {
        }

        return value;

    }

    /**
     * http로 전달된 parameter의 값을 long 타입으로 반환한다.
     * 파라매터의 값이 적절하지 않거나 해당 파라매터가 존재하지 않으면
     * DEFAULT_LONG_VALUE 값을 반환한다.
     * @param request 파라매터가 포함된 request 객체
     * @param name 파라매터의 이름
     * @return long 타입의 파라매터 값
     */
    public static long getLong(HttpServletRequest request, String name)
    {
        return getLong(request, name, DEFAULT_LONG_VALUE);
    }

    /**
     * http로 전달된 parameter의 값을 float 타입으로 반환한다.
     * 파라매터의 값이 적절하지 않거나 해당 파라매터가 존재하지 않으면
     * defaultValue를 반환한다.
     * @param request 파라매터가 포함된 request 객체
     * @param name 파라매터의 이름
     * @param defaultValue 파라매터가 존재하지 않을 경우에 반환될 default 값
     * @return float 타입의 파라매터 값
     */
    public static float getFloat(HttpServletRequest request, String name, float defaultValue) {

        float value = defaultValue;

        try {
            value = Float.parseFloat(request.getParameter(name).trim());
        } catch (NumberFormatException e) {
        } catch (NullPointerException e) {
        }

        return value;

    }

    /**
     * http로 전달된 parameter의 값을 float 타입으로 반환한다.
     * 파라매터의 값이 적절하지 않거나 해당 파라매터가 존재하지 않으면
     * DEFAULT_FLOAT_VALUE 값을 반환한다.
     * @param request 파라매터가 포함된 request 객체
     * @param name 파라매터의 이름
     * @return float 타입의 파라매터 값
     */
    public static float getFloat(HttpServletRequest request, String name)
    {
        return getFloat(request, name, DEFAULT_FLOAT_VALUE);
    }

    /**
     * http로 전달된 parameter의 값을 double 타입으로 반환한다.
     * 파라매터의 값이 적절하지 않거나 해당 파라매터가 존재하지 않으면
     * defaultValue를 반환한다.
     * @param request 파라매터가 포함된 request 객체
     * @param name 파라매터의 이름
     * @param defaultValue 파라매터가 존재하지 않을 경우에 반환될 default 값
     * @return double 타입의 파라매터 값
     */
    public static double getDouble(HttpServletRequest request, String name, double defaultValue) {

        double value = defaultValue;

        try {
            value = Double.parseDouble(request.getParameter(name).trim());
        } catch (NumberFormatException e) {
        } catch (NullPointerException e) {
        }

        return value;

    }

    /**
     * http로 전달된 parameter의 값을 double 타입으로 반환한다.
     * 파라매터의 값이 적절하지 않거나 해당 파라매터가 존재하지 않으면
     * DEFAULT_DOUBLE_VALUE 값을 반환한다.
     * @param request 파라매터가 포함된 request 객체
     * @param name 파라매터의 이름
     * @return double 타입의 파라매터 값
     */
    public static double getDouble(HttpServletRequest request, String name)
    {
        return getDouble(request, name, DEFAULT_DOUBLE_VALUE);
    }

   /**
     * http로 전달된 parameter의 값을 String 타입으로 반환한다.
     * 파라매터의 값이 적절하지 않거나 해당 파라매터가 존재하지 않으면
     * defaultValue를 반환한다.
     * @param request 파라매터가 포함된 request 객체
     * @param name 파라매터의 이름
     * @param defaultValue 파라매터가 존재하지 않을 경우에 반환될 default 값
     * @return String 타입의 파라매터 값
     */
    public static String getString(HttpServletRequest request, String name, String defaultValue)
    {

        String value = defaultValue;
		String locale = JSPUtil.getLocaleLanguage(request);
        try {
            //com.innosns.common.util.Debug.println("HttpParameter:before"+request.getParameter(name));
            //value = uni2Ksc(new String(request.getParameter(name).trim()),locale);
            value = request.getParameter(name).trim();
            //com.innosns.common.util.Debug.println("HttpParameter:after"+value);

        } catch (NumberFormatException e) {
        } catch (NullPointerException e) {
        }

        return value;

    }

    /**
     * http로 전달된 parameter의 값을 String 타입으로 반환한다.
     * 파라매터의 값이 적절하지 않거나 해당 파라매터가 존재하지 않으면
     * DEFAULT_STRING_VALUE 값을 반환한다.
     * @param request 파라매터가 포함된 request 객체
     * @param name 파라매터의 이름
     * @return String 타입의 파라매터 값
     */
    public static String getString(HttpServletRequest request, String name)
    {
        return getString(request, name,DEFAULT_STRING_VALUE);
    }

    /**
     * http로 전달된 parameter의 값을 StringBuffer 타입으로 반환한다.
     * 파라매터의 값이 적절하지 않거나 해당 파라매터가 존재하지 않으면
     * defaultValue를 반환한다.
     * @param request 파라매터가 포함된 request 객체
     * @param name 파라매터의 이름
     * @param defaultValue 파라매터가 존재하지 않을 경우에 반환될 default 값
     * @return StringBuffer 타입의 파라매터 값
     */
    public static StringBuffer getStringBuffer(HttpServletRequest request, String name, String defaultValue) {

        StringBuffer value  = new StringBuffer(defaultValue);

        try {
            value.append(request.getParameter(name).toString().trim());
        } catch (NumberFormatException e) {
        } catch (NullPointerException e) {
        }

        return value;

    }

    /**
     * http로 전달된 parameter의 값을 StringBuffer 타입으로 반환한다.
     * 파라매터의 값이 적절하지 않거나 해당 파라매터가 존재하지 않으면
     * DEFAULT_STRING_BUFFER_VALUE 값을 반환한다.
     * @param request 파라매터가 포함된 request 객체
     * @param name 파라매터의 이름
     * @return StringBuffer 타입의 파라매터 값
     */
    public static StringBuffer getStringBuffer(HttpServletRequest request, String name)
    {
        return getStringBuffer(request, name, DEFAULT_STRING_BUFFER_VALUE);
    }

    /**
     * http로 전달된 parameter의 값을 Date 타입으로 반환한다.
     * 파라매터의 값이 적절하지 않거나 해당 파라매터가 존재하지 않으면
     * defaultValue를 반환한다.
     * @param request 파라매터가 포함된 request 객체
     * @param name 파라매터의 이름
     * @param defaultValue 파라매터가 존재하지 않을 경우에 반환될 default 값
     * @param format 날짜를 해석할 포맷.
     * @return Date 타입의 파라매터 값
     */
    public static java.sql.Date getDate(HttpServletRequest request, String name, Date defaultValue, String format)
    {
        Date value = defaultValue;
        java.sql.Date sqlDate = null;
        try {
            value = CalendarUtil.String2Date(request.getParameter(name).trim(),format);
            sqlDate = new java.sql.Date(value.getTime());
        } catch (NumberFormatException e) {
        } catch (NullPointerException e) {
        }

        return sqlDate;

    }
    /**
     * http로 전달된 parameter의 값을 Date 타입으로 반환한다.
     * 날짜를 해석하기 위한 포맷으로 DEFAULT_DATE_FORMAT을 사용하며,
     * 파라매터의 값이 적절하지 않거나 해당 파라매터가 존재하지 않으면
     * DEFAULT_DATE_VALUE 값을 반환한다.
     * @param request 파라매터가 포함된 request 객체
     * @param name 파라매터의 이름
     * @return Date 타입의 파라매터 값
     */
    public static java.sql.Date getDate(HttpServletRequest request, String name)
    {

        java.sql.Date value = null;

        try {

            value = CalendarUtil.String2Date(request.getParameter(name).trim());
        } catch (NumberFormatException e) {
        } catch (NullPointerException e) {
        }

        return value;

    }
    /**
     * http로 전달된 parameter의 값을 Date 타입으로 반환한다.
     * 파라매터의 값이 적절하지 않거나 해당 파라매터가 존재하지 않으면
     * DEFAULT_DATE_VALUE 값을 반환한다.
     * @param request 파라매터가 포함된 request 객체
     * @param name 파라매터의 이름
     * @param format 날짜를 해석할 포맷. java.util.SimpleDateFormat의 포맷과 같다.
     * @return Date 타입의 파라매터 값
     */
    public static java.sql.Date getDate(HttpServletRequest request, String name, String format)
    {
        return getDate(request, name, DEFAULT_DATE_VALUE, format);
    }

    /**
     * http로 전달된 parameter의 값을 Timestamp 타입으로 반환한다.
     * 파라매터의 값이 적절하지 않거나 해당 파라매터가 존재하지 않으면
     * defaultValue를 반환한다.
     * @param request 파라매터가 포함된 request 객체
     * @param name 파라매터의 이름
     * @param defaultValue 파라매터가 존재하지 않을 경우에 반환될 default 값
     * @param format 날짜를 해석할 포맷.
     * @return Timestamp 타입의 파라매터 값
     */
    public static java.sql.Timestamp getTimestamp(HttpServletRequest request, String name, Date defaultValue, String format)
    {
        Date value = defaultValue;
        java.sql.Timestamp sqlTimestamp = null;
        try {
            value = CalendarUtil.String2Timestamp(request.getParameter(name).trim(),format);
            sqlTimestamp = new java.sql.Timestamp(value.getTime());
        } catch (NumberFormatException e) {
        } catch (NullPointerException e) {
        }

        return sqlTimestamp;

    }
    /**
     * http로 전달된 parameter의 값을 Timestamp 타입으로 반환한다.
     * 날짜를 해석하기 위한 포맷으로 DEFAULT_DATE_FORMAT을 사용하며,
     * 파라매터의 값이 적절하지 않거나 해당 파라매터가 존재하지 않으면
     * DEFAULT_DATE_VALUE 값을 반환한다.
     * @param request 파라매터가 포함된 request 객체
     * @param name 파라매터의 이름
     * @return Timestamp 타입의 파라매터 값
     */
    public static java.sql.Timestamp getTimestamp(HttpServletRequest request, String name)
    {

        java.sql.Timestamp value = null;

        try {

            value = CalendarUtil.String2Timestamp(request.getParameter(name).trim());
        } catch (NumberFormatException e) {
        } catch (NullPointerException e) {
        }

        return value;

    }
   /**
     * http로 전달된 parameter의 값을 Timestamp 타입으로 반환한다.
     * 파라매터의 값이 적절하지 않거나 해당 파라매터가 존재하지 않으면
     * DEFAULT_DATE_VALUE 값을 반환한다.
     * @param request 파라매터가 포함된 request 객체
     * @param name 파라매터의 이름
     * @param format 날짜를 해석할 포맷. java.util.SimpleDateFormat의 포맷과 같다.
     * @return Timestamp 타입의 파라매터 값
     */
    public static java.sql.Timestamp getTimestamp(HttpServletRequest request, String name, String format)
    {
        return getTimestamp(request, name, DEFAULT_DATE_VALUE, format);
    }

    /**
     * http로 전달된 parameter에 name의 이름을 갖는 파라매터가 존재하는가를 확인한다.
     * @param request 파라매터가 포함된 request 객체
     * @param name 파라매터의 이름
     * @return 존재여부
     */
    public static boolean isParameterExist(HttpServletRequest request, String name)
    {
        boolean isParameterExist = false;
        try
        {
            if(request.getParameter(name)!=null)
            {
                isParameterExist = true;
            }
        } catch (NumberFormatException e) {
        } catch (NullPointerException e) {
        }

        return isParameterExist;

    }

   	public static String uni2Ksc(String str,String locale)  {
        String retstr=null;
        if(str != null)
        {
            try {
            	if(locale==null) {
        			retstr = new String(str.getBytes("8859_1"));
            	}else if(locale.equals("ko")){
	            	retstr = new String(str.getBytes("8859_1"), "EUC-KR"); // EUC-KR
	            }else if(locale.equals("ja")){
	            	retstr = new String(str.getBytes("8859_1"), "Shift_JIS"); // Shift_JIS
	            }

             }
            catch(Exception e){}
            return retstr;
        }
        else
            return "";
    }

}