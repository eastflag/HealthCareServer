/**
 * $Id: TypeConvert.java,v 1.3 2002/01/08 Ryu $
 * Copyright 2001 Dong Gyoo Lee. All rights reserved.
 **/

package com.healthcare.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

/**
 * @version   DongQ component 2.1
 */
 /**
 * @author "June Kwon Moon"
 * @date 2014. 2. 27.
 * 
 */
public class Util
{

	public static String GetActionUrl(HttpServletRequest request)
	{
		String projectName = "HealthCare";
		String url = request.getRequestURL().toString();
		return url.substring(0, url.indexOf(projectName) + projectName.length());
	}

	/**
     * source와 target을 비교하여 같을 경우 value1을 반환하고, 다를 경우
     * source를 반환한다.
     * @param source 문자열
     * @param target 비교할 문자열
     * @param value1 source와 target이 같을 경우 반환할 문자열
     * @return 문자열을 비교한 결과에 의한 문자열
     */
    public static String choice(String source, String target, String value1)
    {
        return choice(source, target, value1, source);
    }

    /**
     * source와 target을 비교하여 같을 경우 value1을 반환하고, 다를 경우
     * value2를 반환한다.
     * @param source 문자열
     * @param target 비교할 문자열
     * @param value1 source와 target이 같을 경우 반환할 문자열
     * @param value2 source와 target이 다를 경우 반환할 문자열
     * @return 문자열을 비교한 결과에 의한 문자열
     */
    public static String choice(String source, String target, String value1, String value2)
    {
        source = notNullValueOf(source, "");

        if (source.trim().equals(target) == true) return value1;
        return value2;
    }

    /**
     * value 값이 null이 아닌 경우 value를 반환하고
     * null일 경우 defautlValue를 반환한다.
     * @param value null 체크 대상
     * @param defaultValue null일 경우 반환할 문자열
     * @return 반환값
     */
    public static String notNullValueOf(String value, String defaultValue)
    {
        if (value == null) return defaultValue;
        return value;
    }

    /**
	* <pre>
	* 스트링의 내용을 파일에 쓰기 위한것
	* </pre>
	* @param	String		파일에 쓰기위한 내용
	* @param	String		파일의 절대 path
	* @return	boolean		파일의 내용
	*/
	public static boolean writeFile(String fileContent, String filePath)
		throws Exception
	{

		FileWriter 		writer = null;
		BufferedWriter 	buf_writer = null;

		try
		{
			writer = new FileWriter(filePath);
			buf_writer = new BufferedWriter(writer);
			buf_writer.write(fileContent);
			return true;
		}
		catch (IOException e)
		{
			System.out.println(e);
			throw e;
		}
		finally
		{
			try
			{
				buf_writer.close();
			}
			catch(Exception e)
			{;}
		}
	}//end writeFile

	/**
	* <pre>
	* 스트링의 내용을 파일에 쓰기 위한것
	* </pre>
	* @param	String		파일에 쓰기위한 내용
	* @param	String		파일의 절대 path
	* @return	boolean		성공여부
	*/
	public static synchronized boolean writeFileAppend(String fileContent,String filePath)
	{
        PrintWriter out = null;

		try	{
	        //create instance of File
	        File openFile = new File(filePath);
	        //if exist or not
	        if(openFile.exists())
	            //현재, 파일이 존재하면 append mode, autoflush 로 PrintWriter를 생성
	            out = new PrintWriter(new FileOutputStream(openFile.getAbsolutePath(), true), true);
	        //존재하지 않으면, 새로운 파일 생성
	        else
	            //autoflush
	            out = new PrintWriter(new FileOutputStream(openFile.getAbsolutePath()), true);
            out.println(fileContent);
			return true;
		}
		catch (IOException e)
		{
			System.out.println(e);
			return false;
		}
		finally
		{
			try	{
				out.close();
			}catch(Exception e)	{;}
		}

	}//end writeFile

	/**
     *문자열의 NULL CHECK
     *
     *@param strData	검색할 문자열
	 *@return String
	 */
	public static String stringCheck(String strData)
	{
		if(strData == null) strData = "";
		return strData;
	}// end stringData method

   /**
   	*문자열의 NULL or "" CHECK
   	*
   	*@param strData	검색할 문자열
	*@return String
	*/
	public static String spaceCheck(String strData)
	{
		strData = strData.trim();
		if(strData == null) strData = " ";
		else if(strData.equals("")) strData = " ";
		return strData;
	}// end stringData method

   /**
  	*문자열의 NULL CHECK 및 int형 default값 0셋팅.
   	*
   	*@param strData	검색할 문자열
   	* flag=0 이면 문자형비교하여 기본 공백을 넣는다.
   	* flag=1 이면 숫자형비교하여 기본 0을 넣는다.
	*@return String
	*/
	public static String insertCheck(int flag, String strData)
	{
		if(strData!=null) strData = strData.trim();

		if(flag==0) // 문자형
		{
			if(strData == null)	strData = " "; // 저장시 기본 공백을 넣는다.
			else if(strData.equals(""))	strData = " "; // 저장시 기본 공백을 넣는다.
		}
		else if(flag==1) // 숫자형
		{
			if(strData == null) strData = "0"; // 저장시 기본 0을 넣는다.
			else if(strData.equals(""))	strData = "0"; // 저장시 기본 0을 넣는다.
		}
		return strData;
	}// end stringData method

   /**
   	*문자열의 NULL CHECK 및 int형 default값 0셋팅.
   	*
   	*@param strData	검색할 문자열
   	* flag=0 이면 문자형비교하여 기본 공백을 넣는다.
   	* flag=1 이면 숫자형비교하여 기본 0을 넣는다.
   	* Update시 용이함.
	*@return String
	*/
	public static String insertCheck(int flag,String appendStr, String strData)
	{
		if(strData!=null) strData = strData.trim();

		if(flag==0) // 문자형
			 if(strData == null || strData.equals("")) return "";
			 else strData = " '" + strData + "' ";
		else if(flag==1) // 숫자형
 			 if(strData == null || strData.equals("")) return "";
		return appendStr + strData;
	}// end stringData method

   /**
   	*대체코드 단계별 변환
   	*@param string 8자리 대체코드 문자열
   	* ex) 01000000 -> 01
   	* ex) 01001000 -> 01001
   	* ex) 01001001 -> 01001001
   	*@return String
	*/
	public static String convertAlterCode(String alterCode)
	{
		String alterCode_tmpstr = alterCode;
		int alterCode_tmpint = 0;
		int remainder = 0 ;
		if(alterCode.length()==8){
			alterCode_tmpint = Integer.parseInt(alterCode);
			remainder = alterCode_tmpint%1000000;
			if(remainder>0){
				remainder = alterCode_tmpint%1000;
				if(remainder>0){
					alterCode_tmpstr = alterCode;
				}else{
					alterCode_tmpstr = alterCode.substring(0,5);
				}
			}else{
				alterCode_tmpstr = alterCode.substring(0,2);
			}
		}
		return alterCode_tmpstr;
	}

   /**
   	*공지사항이나 Q&A 내용 입력시 체크사항
	*/
	public static String quote(String str)
	{
		StringBuffer sqlStr = new StringBuffer();

		if(str == null)
		{
			return "";
		}else{
			for(int i = 0; i < str.length(); i++)
			{
				if(str.charAt(i) == '\'')
				{
					sqlStr.append("''");
				}
				else if(str.charAt(i) == '<')
				{
					sqlStr.append("&lt");
				}
				else if (str.charAt(i) == '>')
				{
					sqlStr.append("&gt");
				}
				else if (str.charAt(i) == (char)0x20 )
				{
					sqlStr.append("&nbsp;");
				}
				else if (str.charAt(i) == (char)0x09)
				{
					sqlStr.append("&nbsp&nbsp&nbsp&nbsp");
				}
				else
				{
					sqlStr.append(str.charAt(i));
				}

			}
			return sqlStr.toString();
		}
	}//end quote method
	public static String htmlQuote(String str)
	{
		StringBuffer sqlStr = new StringBuffer();

		if(str == null)
		{
			return "";
		}else{
			for(int i = 0; i < str.length(); i++)
			{
				if(str.charAt(i) == '\'')
				{
					sqlStr.append("''");
				}
				else
				{
					sqlStr.append(str.charAt(i));
				}

			}
			return sqlStr.toString();
		}
	}//end quote method

	/**
   	* 문장내 "<BR>" 체크
   	* @param string str
   	*@return String
	*/
	public static String toHtmlBR(String str){

		int length = str.length();

		StringBuffer buffer = new StringBuffer();

		for(int i=0; i<length; i++){

			String substr = str.substring(i, i+1);

			if("\r".compareTo(substr) == 0){

				substr = str.substring(++i, i+1);

				if("\n".compareTo(substr) == 0)
					buffer.append("<BR>\r");
				else
					buffer.append("\r");
			}

			buffer.append(substr);
		}

		return buffer.toString();
	}

   /**
   	* 문장 ">" 체크
   	* @param string str
   	*@return String
	*/
	public static String toReply(String str){

		int length = str.length();

		StringBuffer buffer = new StringBuffer();

		buffer.append(">");

		for(int i=0; i<length; i++){

			String substr = str.substring(i, i+1);

			if("\r".compareTo(substr) == 0){

				substr = str.substring(++i, i+1);
				if("\n".compareTo(substr) == 0)
					buffer.append("\n>");
			}else{

				buffer.append(substr);
			}
		}

		return buffer.toString();
	}

	/**
    *	Generates a Random Unique ID
    */
	public static String getUniqueID() {
		StringBuffer  sb = new StringBuffer();
		java.util.StringTokenizer st = new java.util.StringTokenizer (new java.rmi.server.UID().toString(), ":");

		while(st.hasMoreTokens()) {
			sb.append(st.nextToken());
		}
		return sb.toString();
	}

    /**
     * Delete File or Directory
     * 지정한 경로가 File일 경우 Delete 실행하고, Directory일 경우 delDirectory(File file) 실행
   	 * @param string 삭제할 File이나 Directory의 경로
   	 * @return 없음
	 */

    public static void delete(String path)
    {
        File file = new File(path);

        if( !file.exists() )
        {
            System.out.println(path + " : NOT FOUND DIRECTORY OR FILE");
        }
        else if(file.isFile())
        {
            boolean ttt = file.delete();
            System.out.println(path + " : REMOVE FILE SUCCESS!!");
        }
        else if(file.isDirectory())
        {

            if(!file.delete())
            {
                delDirectory(file);
            }
            file.delete();
            System.out.println(path + " : REMOVE DIRECTORY SUCCESS!!");
        }
    }//end quote method

    /**
     * Delete Directory
   	 * @param string 삭제할 Directory의 경로
   	 * @return 없음
	 */

    public static void delDirectory(File file)
    {
        String filelist[] = file.list();

        for(int i = 0; i < filelist.length; i++)
        {
            File newfile = new File(file, filelist[i]);

            if(newfile.isFile())
            {
                System.out.println(filelist[i] + " : DELETED!!");
                newfile.delete();
            }
            else if(newfile.isDirectory())
            {
                System.out.println(filelist[i] + " : DIRECTORY IS DELETED!!");
                delDirectory(newfile);
                newfile.delete();
            }
        }
    }//end quote method

    public static String getCharSet(String localeMode)
    {
		String charSet = null;

		if (localeMode.equalsIgnoreCase("ko"))
		{
			charSet = "EUC-KR";
		}
		else if (localeMode.equalsIgnoreCase("ja"))
		{
			charSet = "Shift_JIS";
		}
		else
		{
			charSet = "EUC-KR";
		}

		return charSet;
   }
    
    /**
     * \r\n의 존재 여부를 찾는다면 \n만 입력
     * @param str
     * @param speChar
     * @return 존재할경우 true
     */
    public static boolean existSpecialCharToHtml(String str, char speChar){
    	boolean isExist = false;
    	int strLeng = str.length();
    	
    	for(int i=0;i<strLeng;i++){
    		if(str.charAt(i) == speChar){
    			isExist = true;
    			break;
    		}
    	}
    	return isExist;
    }
    
    /**
     * 해당 특수문자를 원하는 문자열로 변환시킨다.
     * \r\n일경우 \r은 무조건 반환하는 문자열에서 skip한다. 
     * @param str
     * @param speChar
     * @param convertStr
     * @return
     */
    public static String convertSpecialCharToHtml(String str,char speChar, String convertStr){
    	String finishStr = "";
    	int strLeng = str.length();
    	StringBuffer convertBuff = new StringBuffer(strLeng+50);
    	
    	for(int i=0;i<strLeng;i++){
    		if(str.charAt(i) == '\r'){
    			continue;
    		}else if(str.charAt(i) == speChar){
    			convertBuff.append(convertStr);
    		}else{
    			convertBuff.append(str.charAt(i));
    		}
    	}
    	
    	finishStr = convertBuff.toString();
    	
    	return finishStr;
    }

	/**
	 * request를 받아 UserAgent 에서 Android / iOS 등을 구분한다.
	 * 
	 * @ahthor Lee DongQ
	 * @since 2012. 8. 28.
	 *
	 * @param target
	 * @param request
	 * @return
	 */
    
	public static boolean doFindMobileDevice(String target, HttpServletRequest request) {
		Pattern p = Pattern.compile(target.toLowerCase());
		Matcher m = p.matcher(request.getHeader("User-Agent").toLowerCase());

		return m.find();
	}

	/**
	 * src 를 size 만큼 채운다 예) 1 -> 01
	 * @param src
	 * @param size
	 * @return
	 */
	public static String fulfillSize(String src, int size) {
		
		if (src == null || src.length() == 0) {
			return "";
		} else {
			while(src.length() < size) {
		        src="0"+src;
		    }
		    return src;
		}
	}
	
	/**
	 * 숫자형식 핸드폰 번호 -> XXX-YYYY-ZZZZ 형식으로 변형
	 * @param phoneNo
	 * @return
	 */
	public static String makePhoneNumber (String phoneNo) {
		
		String regEx = "(\\d{3})(\\d{3,4})(\\d{4})";
		
		if(!Pattern.matches(regEx, phoneNo)) 
			return null;
		
		return phoneNo.replaceAll(regEx, "$1-$2-$3");
	}
	
	/**
	 * 현재일로부터 -5일 주말제외한 값 리턴
	 * @return Map
	 */
	public static Map<String,Object> before5Day() {
		Map<String,Object> rmap = new HashMap<String, Object>();
		int dayOfWeek = 0;
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 1);
		
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		for(int i = 0; i <= 4; i++){
            dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            
            if (i == 0) {
            	if (dayOfWeek == Calendar.SATURDAY) {
	            	c.add(Calendar.DATE, +2);
	            }else if(dayOfWeek == Calendar.SUNDAY){
	            	c.add(Calendar.DATE, +1);
	            }
            } else {
	            if (dayOfWeek == Calendar.MONDAY) {
	            	c.add(Calendar.DATE, -3);
	            }else if(dayOfWeek == Calendar.SUNDAY){
	            	c.add(Calendar.DATE, -2);
	            }else{
	            	c.add(Calendar.DATE, -1);
	            }
            }
            
            rmap.put("date-"+i, df.format(c.getTime()));
		}
		return rmap;
	}
}