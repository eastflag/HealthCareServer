package com.healthcare.common;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;

/**
 * This class represents a calender
 */
public class CalendarUtil extends Object implements java.io.Serializable
{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private static String defaultDateFormat = "yyyyMMddhhmmss";

    public static final int MONTH = Calendar.MONTH;
    public static final int DATE = Calendar.DATE;
    public static final int YEAR = Calendar.YEAR;

    private int month;
    private int day;
    private int year;

    private CalendarUtil(int year, int month, int day){
        this.month = month;
        this.day= day;
        this.year = year;
    }

    public static CalendarUtil getInstance(){
        Calendar c = Calendar.getInstance();
        int m = c.get(MONTH);
        int d = c.get(DATE);
        int y = c.get(YEAR);
        return new CalendarUtil(y,m,d);
    }

    public int getMonth(){
        return month;
    }

    public int getDay(){
        return day;
    }

    public int getYear(){
        return year;
    }

    public void set(int year, int month, int day){
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public void set(int target, int value){
        switch (target){
            case MONTH :
                this.month = value;
                break;
            case YEAR :
                this.year = value;
                break;
            case DATE :
                this.day = value;
                break;
        }
    }

    public int get(int target){
        switch (target){
            case MONTH : return this.month;
            case YEAR : return this.year;
            case DATE : return this.day;
            default: return -1;
        }

    }

    public void setTime(java.util.Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        this.day = c.get(DATE);
        this.month = c.get(MONTH);
        this.year = c.get(YEAR);
    }

    public void clear(){
        this.day = -1;
        this.month = -1;
        this.year = -1;
    }

   /**
    * @return the date encoded in the format  mm/yyyy
    */
    public String getExpiryDateString(){
        return ((month > 10)? "0" : "") + month + "/" + ((year > 10)? "0" : "") + year;
    }

   /**
    * @return the date encoded in the format  based on locale
    */
    public String getFullDateString(Locale locale){
        Calendar c = Calendar.getInstance(locale);
        c.set(year,month,day);
        Date d = c.getTime();
        DateFormat df = DateFormat.getDateInstance(DateFormat.DEFAULT,locale);
        return df.format(d);
    }

   /**
    * @return the date encoded in the format  mm/dd/yyyy
    */
    public String getFullDateString(){
        return ((month + 1 < 10)? "0" : "") + (month + 1) + "/" + ((day < 10)? "0" : "") + day + "/" + ((year < 10)? "0" : "") + year;
    }

    /**
     * @return the date encoded in the JDBC format, {d 'yyyy-mm-dd'}
     */

    public String getJDBCDateString() {
        return "{d '" + ((year<10) ? "0" : "") + year + "-"
            + ((month+1 < 10) ? "0" : "") + (month+1) + "-"
            + ((day<10) ? "0" : "") + day + "'}";
    }
   public static boolean isValidDate(int yyyy, int MM, int dd)
    {
        return isValidDate(yyyy,MM,dd,0,0,0);
    }

    /**
     * 입력된 날짜값이 유효한지 체크한다.
     */
    public static boolean isValidDate(int yyyy, int MM, int dd, int hh, int mm, int ss)
    {

        boolean isValid = false;

        MM--;

        Calendar calendar = Calendar.getInstance();
        calendar.set(yyyy,MM,dd,hh,mm,ss);

        if(  (yyyy == calendar.get(YEAR))
           &&(MM == calendar.get(MONTH))
           &&(dd == calendar.get(Calendar.DAY_OF_MONTH))
           &&(hh == calendar.get(Calendar.HOUR))
           &&(mm == calendar.get(Calendar.MINUTE))
           &&(ss == calendar.get(Calendar.SECOND)) )
        {
            isValid = true;
        }

        return isValid;
    }
     /**
    * 현재(한국기준) 시간정보를 얻는다.                     <BR>
    * (예) 입력파리미터인 format string에 "yyyyMMddhh"를 셋팅하면 1998121011과 같이 Return.  <BR>
    * (예) format string에 "yyyyMMddHHmmss"를 셋팅하면 19990114232121과 같이
    *      0~23시간 타입으로 Return. <BR>
    * @param <code>format</code> 얻고자하는 현재시간의 Type
    * @return <code>String</code> 입력 패러메터로 포멧된 현재 한국 시간.
    */
    public static String getKST(String format)
    {
	  	//1hour(ms) = 60s * 60m * 1000ms
        int millisPerHour = 60 * 60 * 1000;
        SimpleDateFormat fmt= new SimpleDateFormat(format);

        SimpleTimeZone timeZone = new SimpleTimeZone(9*millisPerHour,"KST");
        fmt.setTimeZone(timeZone);

        long time=System.currentTimeMillis();
        String str=fmt.format(new Date(time));
        return str;
   }

   /**
     * 현재(한국기준) 시간을 기준으로 입력 day값을 더한 DATE형 시간을 반환함.
     * 시간의 형태는 (yyyy-mm-dd)
     * @param <code>day</code> 더하려는 날 수
     * @return <code>String</code> 현재 시간에 입력 day값을 더한 DATE형 시간
     */
   	public static String getDatewithSpan(long day)
   	{
        int millisPerHour = 60 * 60 * 1000;
        SimpleDateFormat fmt= new SimpleDateFormat("yyyy-MM-dd");
        SimpleTimeZone timeZone = new SimpleTimeZone(9*millisPerHour,"KST");
        fmt.setTimeZone(timeZone);

        long time = System.currentTimeMillis();
        long span = ( 60 * 60 * 1000 * 24 ) * day;    //하루에 대한 millisecond...
        long time2 = time + span;
        String str=fmt.format(new Date(time2));

        return str;
   	}

	/**
     * dateString을 파싱해서 Date를 반환한다.
     * 파싱하는 포맷은 getDateFormat() 매소드를 사용해서 구한다.
     * 만약 적합한 포맷을 찾지 못한 경우 defaultDateFormat을 사용한다. <=== ?? 아닌것 같은걸..
     * @param <code>dateString</code> String 타입의 날짜
     * @return <code>Date</code> 입력패러메터에 해당하는 date format으로 파싱된 Date
     */
    public static java.sql.Date String2Date(String dateString)
    {

        String format = getDateFormat(dateString);
        if(format==null)
	    	return null;
        return String2Date(dateString, format);

    }

    /**
     * SimpleDateFormat의 format으로 dateString을 파싱해서 Date를 반환한다.
     * format은 SimpleDateFormat에서 정의한 바를 따른다.
     * @param <code>dateString</code> String 타입의 날짜
     * @param <code>format</code> SimpleDateFormat에서 정의된 format을 사용한 포맷
     * @return <code>Date</code> 입력 패러메터의 format으로 파싱된 Date
     */
    public static java.sql.Date String2Date(String dateString, String format) {

        Date date = null;
		java.sql.Date sqlDate = null;
        try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
			date = simpleDateFormat.parse(dateString);
			sqlDate = new java.sql.Date(date.getTime());
        } catch(ParseException e) {
        	System.out.println("ParseException : parse '"+dateString+"' to '"+format+"'");
        }

        return sqlDate;

    }
    /**
     * 입력된 시간을 defaultDateFormat 형태의 String 으로 반환한다.
     * @params <code>date</code> 변환하고자 하는 Date
     * @return <code>String</code> 포맷된 시간
     */
    public static String Date2String(java.sql.Date date) {
        return Date2String(date, "");
    }

    /**
     * 입력된 Date 타입의 시간을 입력된 format 형태의 String 으로 반환한다.
     * @param <code>date</code> 변환하고자 하는 Date
     * @param <code>format</code> java.util.SimpleDateFormat에서 사용하는 포맷
     * @return <code>String</code> 입력된 format 형태로 변환된 String
     */
    public static String Date2String(java.sql.Date date, String format) {
        if(date==null) { return ""; }
        if((format==null)||(format.equals("")) ) { format = "yyyyMMddHHmmss"; }
        return (new SimpleDateFormat(format)).format(date);
    }

	/**
     * timeStampString을 파싱해서 Timestamp를 반환한다.
     * 파싱하는 포맷은 getDateFormat() 매소드를 사용해서 구한다.
     * 만약 적합한 포맷을 찾지 못한 경우 defaultDateFormat을 사용한다. <=== ?? 아닌것 같은걸..
     * @param <code>timeStampString</code> String 타입의 날짜
     * @return <code>Timestamp</code> 입력패러메터에 해당하는 date format으로 파싱된 Timestamp
     */
    public static java.sql.Timestamp String2Timestamp(String timeStampString)
    {

        String format = getDateFormat(timeStampString);
        if(format==null)
	    	return null;
        return String2Timestamp(timeStampString, format);

    }
    /**
     * SimpleDateFormat의 format으로 timeStrampString을 파싱해서 Timestamp를 반환한다.
     * format은 SimpleDateFormat에서 정의한 바를 따른다.
     * @param <code>timeStampString</code> String 타입의 날짜
     * @param <code>format</code> SimpleDateFormat에서 정의된 format을 사용한 포맷
     * @return <code>Timestamp</code> 입력 패러메터의 format으로 파싱된 Timestamp
     */
    public static java.sql.Timestamp String2Timestamp(String timeStampString, String format) {

        Date timeStamp = null;
		java.sql.Timestamp sqlTimestamp = null;
        try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
			timeStamp = simpleDateFormat.parse(timeStampString);
			sqlTimestamp = new java.sql.Timestamp(timeStamp.getTime());
        } catch(ParseException e) {
        	System.out.println("ParseException : parse '"+timeStampString+"' to '"+format+"'");
        }

        return sqlTimestamp;

    }

    /**
     * 입력된 시간을 defaultDateFormat 형태의 String 으로 반환한다.
     * @params <code>date</code> 변환하고자 하는 Date
     * @return <code>String</code> 포맷된 시간
     */
    public static String Timestamp2String(Timestamp timeStamp) {
        return Timestamp2String(timeStamp, "");
    }

    /**
     * 입력된 Date 타입의 시간을 입력된 format 형태의 String 으로 반환한다.
     * @param <code>date</code> 변환하고자 하는 Date
     * @param <code>format</code> java.util.SimpleDateFormat에서 사용하는 포맷
     * @return <code>String</code> 입력된 format 형태로 변환된 String
     */
    public static String Timestamp2String(Timestamp timeStamp, String format) {
        if(timeStamp==null) { return ""; }
        if((format==null)||(format.equals("")) ) { format = "yyyy/MM/dd HH:mm:ss"; }
        return (new SimpleDateFormat(format)).format(timeStamp);
    }

	/**
     * String 타입으로 입력된 date의 값의 포맷을 구한다.
     * 구할수 있는 포맷은 다음 요소의 조합으로 이루어진다.
     * <pre>
     *    년   : yyyy, yy
     *    월   : MM, M
     *    일   : dd, d
     *    시   : HH, h
     *    분   : mm, m
     *    초   : ss, s
     *    날짜안의 구분자        : ""(없음), "/", "-", " ", "."
     *    날자와 시간간의 구분자 : ""(없음), " ", ","
     *    시간안의 구분자        : ""(없음), ":"
     *
     * 날짜를 구성하는 년월일은 3개가 모두 존재하거나 혹은 전부 없어야 한다.
     * 그리고 시간의 경우 시와분은 항상 같이 다니며, 초의 경우 있거나 없을 수 있다.
     * 날짜안의 구분자는 모두 같아야 하면, 시간안의 구분자 역시 모두 같아야 한다.
     * 따라서 다음과 같은 포맷등은 불가하다.
     *    MMdd(년 없음), yyyyMM(일 없음), yyyyMMddHH(분 없음)
     *    yy-MM/dd(구분자 다름),
     * </pre>
     * @param 시간날짜 정보를 갖고 있는 String
     * @return 포맷
     */
    public static String getDateFormat(String dateString)
    {
        String dateFormat[]
        = {
            "", "yyyyMMdd",
            "yyyy/MM/dd", "yyyy/M/dd", "yyyy/MM/d", "yyyy/M/d", "yy/MM/dd", "yy/M/dd", "yy/MM/d", "yy/m/d",
            "yyyy-MM-dd", "yyyy-M-dd", "yyyy-MM-d", "yyyy-M-d", "yy-MM-dd", "yy-M-dd", "yy-MM-d", "yy-m-d",
            "yyyy MM dd", "yyyy M dd", "yyyy MM d", "yyyy M d", "yy MM dd", "yy M dd", "yy MM d", "yy m d",
            "yyyy.MM.dd", "yyyy.M.dd", "yyyy.MM.d", "yyyy.M.d", "yy.MM.dd", "yy.M.dd", "yy.MM.d", "yy.m.d"
        };


        String intervalFormat[] = {"", " ", "," };
        String timeFormat[]
        = {
            "", "HHmmss",
            "HH:mm:ss", "HH:m:ss", "HH:mm:s", "HH:m:s",
            "H:mm:ss", "H:m:ss", "H:mm:s", "H:m:s",
            "HH:mm", "HH:m", "H:mm", "H:m"
        };


        for(int i=0;i<dateFormat.length;i++)
        {
            for(int j=0;j<intervalFormat.length;j++)
            {
                for(int k=0;k<timeFormat.length;k++)
                {
                    String format = dateFormat[i]+intervalFormat[j]+timeFormat[k];
                    if(format.length()==dateString.length())
                    {
                        Date date = null;
                        try {
                           SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
                           date = simpleDateFormat.parse(dateString);
                        } catch(ParseException e) {
                        }
                        if(date!=null) {
                            String newDateString = (new SimpleDateFormat(format)).format(date);
                            if(newDateString.equals(dateString)) return format;
                        }
                    }
                }
            }
        }

        return null;

    }

    public static String getDefaultDateFormat()
    {
        return defaultDateFormat;
    }
    @Override
	public String toString(){
        return "[Year=" + year + ", Month=" + month + ", Day=" + day + "]";
    }

    public String getCloudscapeDateString(){
        return year + "-" + ((month + 1 < 10)? "0" : "") + (month + 1) + "-" + ((day < 10)? "0" : "") + day;
    }


	//정한 숫자만큼 날짜를 증가시킨다.
	public static java.sql.Date plusDate(java.sql.Date firstDate,int dateNum){
		int millisPerHour = 60 * 60 * 1000;
		long longFirstDate = firstDate.getTime();
		return new java.sql.Date(longFirstDate+dateNum*millisPerHour*24);
	}

	//다음 날짜를 구한다.
	public static java.sql.Date nextDate(java.sql.Date today){
		return plusDate(today,1);
	}

	public static int minusDate(java.sql.Date firstDate,java.sql.Date secondDate){
		return (int)((firstDate.getTime()-secondDate.getTime())/(24*60*60*1000));
	}

    /**
     * Timestamp to Code - 예)  2003-01-13 16:55:17.39 -> 031D
     * @param <code>indate</code> 변환하고자 하는 Timestamp
     * @return <code>String</code> 변환된Code. error - "0000"
     */
    public static String timestamp2DateCode(Timestamp indate) {
        return string2DateCode(Timestamp2String(indate));
    }
    /**
     * Date to Code - 예)  2003-01-13 -> 031D
     * @param <code>indate</code> 변환하고자 하는 Date
     * @return <code>String</code> 변환된Code. error - "0000"
     */
    public static String date2DateCode(java.sql.Date indate) {
		return string2DateCode(Date2String(indate));
    }
    /**
     * String to Code - 예)  2003-01-13 or 2003/01/13 -> 031D
     * @param <code>indate</code> 변환하고자 하는 String
     * @return <code>String</code> 변환된Code. error - "0000"
     */
    public static String string2DateCode(String indate) {
    	int nYear,nMonth,nDay;
    	String onechar;
    	String dateStr = "";

		for(int i=0;dateStr.length()<8;i++){
			onechar = String.valueOf(indate.charAt(i));
			try{
				Integer.parseInt(onechar);
				dateStr += onechar;
			}catch(NumberFormatException nfe){return "0000";}
		}
		if( dateStr.equals(Date2String(String2Date(dateStr,"yyyyMMdd"),"yyyyMMdd")) && dateStr.length()>=8){
			nYear  = Integer.parseInt(dateStr.substring(0,4));
			nMonth = Integer.parseInt(dateStr.substring(4,6));
			nDay   = Integer.parseInt(dateStr.substring(6,8));
			return commonToDateCode(nYear,nMonth,nDay);
		}else{
			return "0000";
		}

    }
    /**
     * 일자코드 대체표 계산
     * @param <code>nYear</code> 년
     * @param <code>nMonth</code> 월
     * @param <code>nDay</code> 일
     * @return <code>String</code> 변환된Code
     */
    public static String commonToDateCode(int nYear,int nMonth,int nDay) {
		int YEAR_START = 2000;	// 유효한 최저년도
		int YEAR_LIMIT = 3296;	// 유효한 최고년도(3295년)
		int CODE_ARRAY = 36;	// 36진법
    	String cYear1 = null;
    	String cYear2 = null;
    	String cMonth = null;
    	String cDay	  = null;
        String codeGroup = null;
        if(nYear>=YEAR_START && nYear<YEAR_LIMIT ){
	        nYear -=YEAR_START;
	        cYear1 = toDateCode(nYear/CODE_ARRAY);
	        cYear2 = toDateCode(nYear%CODE_ARRAY);
	        cMonth = toDateCode(nMonth);
	        cDay   = toDateCode(nDay);
	        codeGroup = cYear1+cYear2+cMonth+cDay;
        }else{
        	codeGroup = "0000";
    	}

        return codeGroup;
	}
    /**
     * 일자코드 대체 - 예)  0 -> 0, 10 -> A, 30 -> a
     * @param <code>in</code> 계산된 값
     * @return <code>String</code> 변환된Code
     */
    public static String toDateCode(int in) {
		int CODE_ARRAY = 36;	// 36진법
		int CHAR_DEC   = 55;	//dec 65->A, 10->A로 만들어어야 하므로 들어온 값에 55를 더해준다.
		char largeChar;
		String  alternativeCode="";
        int dec = in ;

        if( dec>=0 && dec<=9){
        	alternativeCode = Integer.toString(dec);
        }else if( dec>=10 && dec<CODE_ARRAY){
        	largeChar=(char)(dec+CHAR_DEC); // decimal을 char로 변환 ex)65->A
        	alternativeCode =  largeChar+"";
        }

        return alternativeCode;
    }
}