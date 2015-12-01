package com.healthcare.common;

import java.io.ByteArrayOutputStream;
import java.text.BreakIterator;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 이클래스는 Web Tier 의 JSP 위한 유틸리티 클래스임
 */

public final class JSPUtil extends Object {

	/**
	 * request에서 헤더의 값을 구하되, null일 경우 NullPointerException을 catch하기 위한 클래스
	 * @param <code>req</code> HttpServletRequest
	 * @param <code>name</code> 찾고자하는 헤더의 키값
	 * @return <code>String</code> 찾고자하는 헤더의 값
	 */
    public static String getHeader(HttpServletRequest req, String name)
    {
        String value = "";
        try
        {
            value = req.getHeader(name);
        } catch (NullPointerException e) {}
        if(value == null) value = "";
        return value;
    }

    /**
	 * request 헤더의 값을 출력한다.
	 * @param <code>req</code> HttpServletRequest
	 * @return <code>Void</code> 없음.
	 */
    @SuppressWarnings("rawtypes")
	public static void viewHeader(HttpServletRequest req)
    {
        Enumeration en = req.getHeaderNames();
        while(en.hasMoreElements()){
            String name = (String)en.nextElement();
            String value = req.getHeader(name);
        }
    }

    /*
     * request 헤더에 포함된 Host정보를 반환한다.
     * 반환되는 host정보는 다음과 같은 모양이다.
     * <pre>
     *    www.where.com
     *    www.where.com:8080
     * </pre>
     * inserted by 임도형. 2001/01/17
     *
     * @param <code>req</code> request 객체
     * @return <code>String</code> String 타입의 host
     */
    public static String getHost(HttpServletRequest req)
    {
        String host = null;

        host = req.getHeader("Host");

        return host;
    }


    public static String getClient(HttpServletRequest req)
    {
        String client = null;

        client = req.getRemoteAddr();

        return client;
    }

    public static String getServerPort(HttpServletRequest req)
    {
        return req.getServerPort()+"";
    }

    /*
     * session 객체에 담긴 locale attribute에서 language를 반환한다.
     * session에 담긴 locale의 키는 WebKeys.LanguageKey이다.
     *
     * @param <code>session</code> 세션객체
     * @return <code>String</code> locale의 language. 'ko'|'jp'
     */
    public static String getLocaleLanguage(HttpServletRequest req)
    {
        String language = null;

        Locale locale = (Locale)req.getSession().getAttribute("language");
        if(locale!=null) {
            language = locale.getLanguage();
        }
        return language;
    }

    /**
     * Converts a String SJIS or JIS URL encoded hex encoding to a Unicode String
     * @param <code>target</code> 변환하고자하는 문자열
     * @return <code>String</code>
     */
    public static String convertJISEncoding(String target) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        if (target == null) return null;
        String paramString = target.trim();

        for (int loop =0; loop < paramString.length(); loop++) {
            int i = paramString.charAt(loop);
            bos.write(i);
        }
        String convertedString = null;
        try {
            convertedString =  new String(bos.toByteArray(), "JISAutoDetect");
        } catch (java.io.UnsupportedEncodingException uex) {}
        return convertedString;
    }

   /**
    * query string을 parsing한다.             <BR>
    *
    * @param    queryString queryString.
    * @param    indexParam  parsing하고자 하는 인덱스문자열.
    * @return   인덱스문자열에 의해 parsing된 value값.
    */
    public static String parseQueryString(String queryString, String indexParam)
    {
		int start;
		int howLong;

		queryString += "&"; 			    // 끝표시 추가
		indexParam += "=";			    // '='추가
		start = queryString.indexOf(indexParam, 0); // 원하는 indexParam의 value 첫 위치를 알아낸다.
		if(start == -1) return "";
		start += indexParam.length();
		howLong = queryString.indexOf("&", start ); 	// value의 길이를 알아낸다.
		if( howLong < (start + 1) )	return "";	// last value==NULL

		String temp = queryString.substring( start, howLong );
        int idx = temp.indexOf("%20");
        while (idx != -1)
        {
            temp = temp.substring(0,idx) +" "+ temp.substring(idx+3,temp.length());
            idx = temp.indexOf("%20");
        }
        return temp;

    }

   /**
    * post string을 parsing한다.              <BR>
    *
    * @param    queryString poststring.
    * @param    indexParam  parsing하고자 하는 인덱스문자열.
    * @return   인덱스문자열에 의해 parsing된 value값.
    */
	public static String parsePostString(String queryString, String indexParam)
	{
		int start;
		int howLong;

		queryString += "&"; 									// 끝표시 추가
		indexParam += "=";										// '='추가
		start = queryString.indexOf(indexParam, 0); 	// 원하는 indexParam의 value 첫 위치를 알아낸다.
		if(start == -1)
			return "";
		start += indexParam.length();
		howLong = queryString.indexOf("&", start ); 	// value의 길이를 알아낸다.
		if( howLong < (start + 1) )
			return "";											// last value==NULL
		return queryString.substring( start, howLong );	// value를 알아낸다.
	}

    public static Vector parseKeywords(String keywordString){
        if (keywordString != null){
            Vector keywords = new Vector();
            BreakIterator breakIt = BreakIterator.getWordInstance();
            int index=0;
            int previousIndex =0;
            breakIt.setText(keywordString);
            try{
                while(index < keywordString.length()){
                    previousIndex = index;
                    index = breakIt.next();
                    String word = keywordString.substring(previousIndex, index);
                    if (!word.trim().equals("")) keywords.addElement(word);
                }
                return keywords;
            } catch (Throwable e){
                System.out.println("Error while parsing search string");
            }

        }
        return null;
    }

    public static Vector parseKeywords(String keywordString, Locale locale){
        if (keywordString != null){
            Vector keywords = new Vector();
            BreakIterator breakIt = BreakIterator.getWordInstance(locale);
            int index=0;
            int previousIndex =0;
            breakIt.setText(keywordString);
            try{
                while(index < keywordString.length()){
                    previousIndex = index;
                    index = breakIt.next();
                    String word = keywordString.substring(previousIndex, index);
                    if (!word.trim().equals("")) keywords.addElement(word);
                }
                return keywords;
            } catch (Throwable e){
                System.out.println( "Error while parsing search string" );
            }

        }
        return null;
    }

    /**
     * Get the Locale specified in the session or return a default locale.
     */
    public static Locale getLocaleFromLanguage(String language) {
        Locale locale = Locale.KOREA;
        if (language.equals("Korean") || language.equals("ko"))
        	locale = Locale.KOREA;
        else if (language.equals("Japanese") || language.equals("ja"))
        	locale = Locale.JAPANESE;
        return locale;
    }
    /**
     * Get the Locale specified in the session or return a default locale.
     */
    public static Locale getLocale(HttpSession session) {
        Locale locale = (Locale)session.getAttribute("language");
        return locale;
    }

	/**
	* <pre>
	* 문자열을 받아서 Enter Key를 특정문자열(<BR>)로 변환하거나
	* 특정문자열을 Enter key로 변환함...
	* - Informix thin driver Bug 때문에 SQL문을 생성시 사용키 위함.
	* </pre>
	* @param	String		변환 대상
	* @param	nFlag		변환 방향
	* @return	'String'
	*/
	public static String convertBR(String str, int	nFlag)
	{
		StringBuffer fileStr = new StringBuffer();

		int i = 0;
		int lasti = 0;

		if(str == null) return "";
		else
		{
			if( nFlag > 0)	//양수이면 Enter Key를 `<BR>`로 변환
	    	{

				for(; i < str.length(); i++)
				{

					if( str.charAt(i) == '\r' )
					{
						fileStr.append("<BR>");
					}
					else if( str.charAt(i) == '\n' )
					{ ;}
					else
					{
						fileStr.append(str.charAt(i));
					}

				}//end for

				return fileStr.toString();
	    	}
	    	else			//음수이면 <BR>를 Enter Key로 변환
	    	{

				i = str.indexOf("<BR>");
				while( (i != -1) && (i < str.length()) )
				{
					fileStr.append(str.substring(lasti, i));
					fileStr.append("\r\n");

					i += 4;
					lasti = i;

					i = str.indexOf("<BR>", lasti);
				}
				if(i < str.length())
				{
					fileStr.append( str.substring( lasti, str.length() ) );
				}

	    	}//end sub if-else

	    }//end main if-else
		return  fileStr.toString();
	}//end convertRN method

}