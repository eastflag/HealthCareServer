 /**
  * $Id: SessionManager.java,v 1.3 2002/03/12 00:06:41 Ryu $
  * Copyright 2001 Dong Gyoo Lee. All rights reserved.
  **/

package com.healthcare.common;

import java.util.*;

import javax.servlet.http.*;

/**
 * 이클래스는 세션 관리를 위한 유틸리티 클래스임
 */

public final class SessionManager extends Object {

	/**
    * PageNavigator에서 사용되는 현재 페이지를 세션객체에서 가져온다.
    * @param HttpServletRequest
	* @return <code>int</code> Current Page
    */
    public static int getPageNo(HttpServletRequest req)
    {
		int pageNo = 1;
		HashMap searchKeys = getSearchKeys(req);
		if(	HttpParameter.isParameterExist(req,"INIT") )
		{
			searchKeys.put("pageNo",new Integer(pageNo));
		}else{
			if(HttpParameter.isParameterExist(req,"pageNo")) searchKeys.put("pageNo",HttpParameter.getInteger(req,"pageNo"));
			if(searchKeys.get("pageNo")!=null) pageNo = ((Integer)searchKeys.get("pageNo")).intValue();
		}
		req.getSession().setAttribute(WebKeys.searchKey,searchKeys);

		return pageNo;
    }
	/**
    * "pageNo"를 초기화한다.
    * @param HttpServletRequest
	* @return <code>없음</code>
    */
    public static void initPageNo(HttpServletRequest req)
    {
		int pageNo = 1;
		HashMap searchKeys = getSearchKeys(req);
		searchKeys.put("pageNo",new Integer(pageNo));
		req.getSession().setAttribute(WebKeys.searchKey,searchKeys);
    }
    /**
    * webKey를 외부에서 받아 해당 세션에서 정보를 찾는다.request,webKey
    */
	/**
    * PageNavigator에서 사용되는 현재 페이지를 세션객체에서 가져온다.
    * 단,세션에 생성할 Key를  외부에서 받는다.
    * @param HttpServletRequest
	* @param String webkey
	* @return <code>int</code>
    */
    public static int getPageNo(HttpServletRequest req,String webKey)
    {
		int pageNo = 1;
		HashMap searchKeys = getSearchKeys(req,webKey);

		if(	HttpParameter.isParameterExist(req,"INIT") )
		{
			searchKeys.put("pageNo",new Integer(pageNo));
		}else{
			if(HttpParameter.isParameterExist(req,"pageNo")) searchKeys.put("pageNo",HttpParameter.getInteger(req,"pageNo"));
			if(searchKeys.get("pageNo")!=null) pageNo = ((Integer)searchKeys.get("pageNo")).intValue();
		}
		req.getSession().setAttribute(webKey,searchKeys);

		return pageNo;
    }
	/**
    * SubOnePageNavigator에서 사용되는 현재 페이지를 세션객체에서 가져온다.
    * @param HttpServletRequest
    * @return <code>int</code> Current Page
    */
    public static int getSubOnePageNo(HttpServletRequest req)
    {
		int subOnePageNo = 1;
		HashMap searchKeys = getSearchKeys(req);

		if(	HttpParameter.isParameterExist(req,"INIT") )
		{
			searchKeys.put("subOnePageNo",new Integer(subOnePageNo));
		}else{
			if(HttpParameter.isParameterExist(req,"subOnePageNo")) searchKeys.put("subOnePageNo",HttpParameter.getInteger(req,"subOnePageNo"));
			if(searchKeys.get("subOnePageNo")!=null) subOnePageNo = ((Integer)searchKeys.get("subOnePageNo")).intValue();
		}

		req.getSession().setAttribute(WebKeys.searchKey,searchKeys);
		return subOnePageNo;
    }
    public static void initSubOnePageNo(HttpServletRequest req)
    {
		int subOnePageNo = 1;
		HashMap searchKeys = getSearchKeys(req);
		searchKeys.put("subOnePageNo",new Integer(subOnePageNo));
		req.getSession().setAttribute(WebKeys.searchKey,searchKeys);
    }
	/**
    * SubTwoPageNavigator에서 사용되는 현재 페이지를 세션객체에서 가져온다.
    * @param HttpServletRequest
    * @return <code>int</code> Current Page
    */
    public static int getSubTwoPageNo(HttpServletRequest req)
    {
		int subTwoPageNo = 1;
		HashMap searchKeys = getSearchKeys(req);

		if(	HttpParameter.isParameterExist(req,"INIT") )
		{
			searchKeys.put("subTwoPageNo",new Integer(subTwoPageNo));
		}else{
			if(HttpParameter.isParameterExist(req,"subTwoPageNo")) searchKeys.put("subTwoPageNo",HttpParameter.getInteger(req,"subTwoPageNo"));
			if(searchKeys.get("subTwoPageNo")!=null) subTwoPageNo = ((Integer)searchKeys.get("subTwoPageNo")).intValue();
		}
		req.getSession().setAttribute(WebKeys.searchKey,searchKeys);

		return subTwoPageNo;
    }
    public static void initSubTwoPageNo(HttpServletRequest req)
    {
		int subTwoPageNo = 1;
		HashMap searchKeys = getSearchKeys(req);
		searchKeys.put("subTwoPageNo",new Integer(subTwoPageNo));
		req.getSession().setAttribute(WebKeys.searchKey,searchKeys);
    }
	/**
    * SubThreePageNavigator에서 사용되는 현재 페이지를 세션객체에서 가져온다.
    * @param HttpServletRequest
    * @return <code>int</code> Current Page
    */
    public static int getSubThreePageNo(HttpServletRequest req)
    {
		int subThreePageNo = 1;
		HashMap searchKeys = getSearchKeys(req);

		if(	HttpParameter.isParameterExist(req,"INIT") )
		{
			searchKeys.put("subThreePageNo",new Integer(subThreePageNo));
		}else{
			if(HttpParameter.isParameterExist(req,"subThreePageNo")) searchKeys.put("subThreePageNo",HttpParameter.getInteger(req,"subThreePageNo"));
			if(searchKeys.get("subThreePageNo")!=null) subThreePageNo = ((Integer)searchKeys.get("subThreePageNo")).intValue();
		}

		req.getSession().setAttribute(WebKeys.searchKey,searchKeys);
		return subThreePageNo;
    }
    public static void initSubThreePageNo(HttpServletRequest req)
    {
		int subThreePageNo = 1;
		HashMap searchKeys = getSearchKeys(req);
		searchKeys.put("subThreePageNo",new Integer(subThreePageNo));
		req.getSession().setAttribute(WebKeys.searchKey,searchKeys);
    }
	/**
    * SubFourPageNavigator에서 사용되는 현재 페이지를 세션객체에서 가져온다.
    * @param HttpServletRequest
    * @return <code>int</code> Current Page
    */
    public static int getSubFourPageNo(HttpServletRequest req)
    {
		int subFourPageNo = 1;
		HashMap searchKeys = getSearchKeys(req);

		if(	HttpParameter.isParameterExist(req,"INIT") )
		{
			searchKeys.put("subFourPageNo",new Integer(subFourPageNo));
		}else{
			if(HttpParameter.isParameterExist(req,"subFourPageNo")) searchKeys.put("subFourPageNo",HttpParameter.getInteger(req,"subFourPageNo"));
			if(searchKeys.get("subFourPageNo")!=null) subFourPageNo = ((Integer)searchKeys.get("subFourPageNo")).intValue();
		}
		req.getSession().setAttribute(WebKeys.searchKey,searchKeys);

		return subFourPageNo;
    }
    public static void initSubFourPageNo(HttpServletRequest req)
    {
		int subFourPageNo = 1;
		HashMap searchKeys = getSearchKeys(req);
		searchKeys.put("subFourPageNo",new Integer(subFourPageNo));
		req.getSession().setAttribute(WebKeys.searchKey,searchKeys);
    }

   /**
    * 검색어를 request 또는 세션객체에서 가져와 세션객체에 저장한다.
    * @param HttpServletRequest 검색어 정보를 저장하고 있는 request 객체
    * @param String[] 	세선에 저장할 HashMap의 key
    * @return <code>void</code>
    */
    public static void setSearchKeys(HttpServletRequest req, String[] pSearchKeys)
    {
		HashMap searchKeys = new HashMap();
		// 파라메터가 INIT가 있는경우나 verifysignin,topindex,side_notify,side_progress 클릭한경우
		if(	HttpParameter.isParameterExist(req,"INIT")
			||compareReferer(req,"topIndex")
			||compareReferer(req,"sideIndex"))
		{
			for (int i=0;i<pSearchKeys.length;i++)
				searchKeys.put(pSearchKeys[i],HttpParameter.getString(req,pSearchKeys[i],""));
		}else if( HttpParameter.isParameterExist(req,pSearchKeys[0]) ){
			if(req.getSession().getAttribute(WebKeys.searchKey)!=null)
				searchKeys = (HashMap)req.getSession().getAttribute(WebKeys.searchKey);
			for (int i=0;i<pSearchKeys.length;i++){
				searchKeys.put(pSearchKeys[i],HttpParameter.getString(req,pSearchKeys[i],""));
			}
		}else if( req.getSession().getAttribute(WebKeys.searchKey)!=null  ) {
			searchKeys = (HashMap)req.getSession().getAttribute(WebKeys.searchKey);
			if(!searchKeys.containsKey(pSearchKeys[0])){
				for(int i=0;i<pSearchKeys.length;i++)
					searchKeys.put(pSearchKeys[i],HttpParameter.getString(req,pSearchKeys[i],""));
			}
		}else if(!searchKeys.containsKey(pSearchKeys[0])){
			for(int i=0;i<pSearchKeys.length;i++)
				searchKeys.put(pSearchKeys[i],HttpParameter.getString(req,pSearchKeys[i],""));
		}

		req.getSession().setAttribute(WebKeys.searchKey,searchKeys);
    }

    /**
    * webKey를 외부에서 받아 webKey별로 해당 세션에 정보를 생성한다. request,webkey,searchkey
    */
   /**
    * 검색어를 request 또는 세션객체에서 가져와 세션객체에 저장한다.
    * 단,세션에 생성할 Key를  외부에서 받는다.
    *    webKey를 외부에서 받아 webKey별로 해당 세션에 정보를 생성한다.
    * @param HttpServletRequest 검색어 정보를 저장하고 있는 request 객체
    * @param String 	세션key
    * @param String[] 	세선에 저장할 HashMap의 key
    * @return <code>void</code>
    */
    public static void setSearchKeys(HttpServletRequest req,String webKey,String[] pSearchKeys)
    {
		HashMap searchKeys = new HashMap();
		// 파라메터가 INIT가 있는경우나 verifysignin,topindex,side_notify,side_progress 클릭한경우
		if(	HttpParameter.isParameterExist(req,"INIT")
			||compareReferer(req,"topIndex")
			||compareReferer(req,"sideIndex"))
		{
			for (int i=0;i<pSearchKeys.length;i++)
				searchKeys.put(pSearchKeys[i],HttpParameter.getString(req,pSearchKeys[i],""));
		}else if( HttpParameter.isParameterExist(req,pSearchKeys[0]) ){
			if(req.getSession().getAttribute(webKey)!=null)
				searchKeys = (HashMap)req.getSession().getAttribute(webKey);
			for (int i=0;i<pSearchKeys.length;i++){
				searchKeys.put(pSearchKeys[i],HttpParameter.getString(req,pSearchKeys[i],""));
			}
		}else if( req.getSession().getAttribute(webKey)!=null  ) {
			searchKeys = (HashMap)req.getSession().getAttribute(webKey);
			if(!searchKeys.containsKey(pSearchKeys[0])){
				for(int i=0;i<pSearchKeys.length;i++)
					searchKeys.put(pSearchKeys[i],HttpParameter.getString(req,pSearchKeys[i],""));
			}
		}else if(!searchKeys.containsKey(pSearchKeys[0])){
			for(int i=0;i<pSearchKeys.length;i++)
				searchKeys.put(pSearchKeys[i],HttpParameter.getString(req,pSearchKeys[i],""));
		}

		req.getSession().setAttribute(webKey,searchKeys);
    }

    public static void setSearchKeys(HttpServletRequest req, HashMap pSearchKeys)
    {
		req.getSession().setAttribute(WebKeys.searchKey, pSearchKeys);
    }

    /**
    * 검색어를 request 또는 세션객체에서 가져온다.
    * @param 검색어 정보를 저장하고 있는 request 객체
    * @return <code>searchKeys</code> 저장된 대분류, 코드, 코드명 검색어를 포함한 HashMap
    */
    public static HashMap getSearchKeys(HttpServletRequest req)
    {
		HashMap searchKeys = new HashMap();
		if(req.getSession().getAttribute(WebKeys.searchKey)!=null)
			searchKeys = (HashMap)req.getSession().getAttribute(WebKeys.searchKey);

		return searchKeys;
    }
    /**
    * 검색어를 request 또는 세션객체에서 가져온다.
    * 단,세션에 생성할 Key를  외부에서 받는다.
    * @param HttpServletRequest 검색어 정보를 저장하고 있는 request 객체
    * @param String 세션key
    * @return <code>HashMap</code> 저장된 대분류, 코드, 코드명 검색어를 포함한 HashMap
    */
    public static HashMap getSearchKeys(HttpServletRequest req,String webKey)
    {
		HashMap searchKeys = new HashMap();
		if(req.getSession().getAttribute(webKey)!=null)
			searchKeys = (HashMap)req.getSession().getAttribute(webKey);

		return searchKeys;
    }

   /**
   	* 현재 검색어를 검색창에 보여주기 위해 검색어를 리턴한다.
   	* @param 검색어 정보를 저장하고 있는 request 객체
   	* @param 리턴받고자하는 searchKey
   	* @return <code>String</code> 검색어
    */
    public static String getSearchKey(HttpServletRequest req, String searchKey)
    {
		HashMap searchKeys = getSearchKeys(req);
		searchKey = (String)searchKeys.get(searchKey);
		return searchKey;
    }
   /**
   	* 현재 검색어를 검색창에 보여주기 위해 검색어를 리턴한다.
   	* @param HttpServletRequest	검색어 정보를 저장하고 있는 request 객체
    * @param String 세션key
   	* @param String	리턴받고자하는 searchKey
   	* @return <code>String</code> 검색어
    */
    public static String getSearchKey(HttpServletRequest req, String webKey, String searchKey)
    {
		HashMap searchKeys = getSearchKeys(req,webKey);
		searchKey = (String)searchKeys.get(searchKey);
		return searchKey;
    }
    /**
    * webkey에 해당되는 값을 세션에서 제거
   	* @param HttpServletRequest	검색어 정보를 저장하고 있는 request 객체
    * @param String 세션key
    */
    public static void removeSearchKey(HttpServletRequest req, String webKey)
    {
		if(req.getSession().getAttribute(webKey)!=null)
			req.getSession().removeAttribute(webKey);
    }

  /**
    * 광고 신청 정보를 세션객체에 저장한다.
    * @param <code>pRequestKeys</code> 광고 신청 정보를 저장하고 있는 HashMap
    * @return <code>void</code>
    */
    public static void setRequestKeys(HttpServletRequest req, HashMap pRequestKeys)
    {
		req.getSession().setAttribute(WebKeys.requestKey, pRequestKeys);
    }

    /**
    * 광고 신청 정보를 세션객체에서 제거한다.
    * @return <code>void</code>
    */
    public static void removeRequestKeys(HttpServletRequest req)
    {
		req.getSession().removeAttribute(WebKeys.requestKey);
    }

    /**
    * 광고 신청 정보를 세션객체에서 가져온다.
    * @param <code>req</code> 광고 신청 정보를 저장하고 있는 request 객체
    * @return <code>requestKeys</code> 광고 신청 정보를 포함한 HashMap
    */
    public static HashMap getRequestKeys(HttpServletRequest req)
    {
		HashMap requestKeys = new HashMap();
		requestKeys = (HashMap)req.getSession().getAttribute(WebKeys.requestKey);
		return requestKeys;
    }

    /**
    * 광고 신청 정보를 세션객체에 저장한다.
    * @param <code>pRequestKeys</code> 광고 신청 정보를 저장하고 있는 HashMap
    * @return <code>void</code>
    */
    public static void setRequest2Keys(HttpServletRequest req, HashMap pRequestKeys)
    {
		req.getSession().setAttribute(WebKeys.request2Key, pRequestKeys);
    }

    /**
    * 광고 신청 정보를 세션객체에서 제거한다.
    * @return <code>void</code>
    */
    public static void removeRequest2Keys(HttpServletRequest req)
    {
		req.getSession().removeAttribute(WebKeys.request2Key);
    }

    /**
    * 광고 신청 정보를 세션객체에서 가져온다.
    * @param <code>req</code> 광고 신청 정보를 저장하고 있는 request 객체
    * @return <code>requestKeys</code> 광고 신청 정보를 포함한 HashMap
    */
    public static HashMap getRequest2Keys(HttpServletRequest req)
    {
		HashMap requestKeys = new HashMap();
		requestKeys = (HashMap)req.getSession().getAttribute(WebKeys.request2Key);
		return requestKeys;
    }

    /**
    * 로그인정보를 세션객체에서 제거한다.
    * @return <code>void</code>
    */
    public static void removeUserKey(HttpServletRequest req)
    {
		req.getSession().invalidate();
    }

    /**
    * 로그인 정보를 세션객체에서 가져온다.
    * @param <code>req</code> 로그인 정보를 저장하고 있는 request 객체
    * @return <code>requestKeys</code> 로그인 정보를 포함한 HashMap
    */
    /*
    public static User getUserKey(HttpServletRequest req)
    {
		User user = null;
		HashMap roleIds = null;
		user = (User)req.getSession().getAttribute(WebKeys.UserKey);
		roleIds =  user.getRoles();
		if(roleIds != null){
			user.setRoleId((String)roleIds.get(user.getUserId()+"roleId"));
			user.setRoleCodeName((String)roleIds.get(user.getUserId()+"codeName"));
		}
		return user;
    }
    */
	/**
    * 광고 신청 정보를 세션객체에 저장한다.
    * @param <code>pRequestKeys</code> 광고 신청 정보를 저장하고 있는 HashMap
    * @return <code>void</code>
    */
    public static void setRequestModifyKeys(HttpServletRequest req, HashMap pRequestKeys)
    {
		req.getSession().setAttribute(WebKeys.requestModifyKey, pRequestKeys);
    }

    /**
    * 광고 신청 정보를 세션객체에서 제거한다.
    * @return <code>void</code>
    */
    public static void removeRequestModifyKeys(HttpServletRequest req)
    {
		req.getSession().removeAttribute(WebKeys.requestModifyKey);
    }

    /**
    * 광고 신청 정보를 세션객체에서 가져온다.
    * @param <code>req</code> 광고 신청 정보를 저장하고 있는 request 객체
    * @return <code>requestKeys</code> 광고 신청 정보를 포함한 HashMap
    */
    public static HashMap getRequestModifyKeys(HttpServletRequest req)
    {
		HashMap requestKeys = new HashMap();
		requestKeys = (HashMap)req.getSession().getAttribute(WebKeys.requestModifyKey);
		return requestKeys;
    }
    public static void setIndexPointKey(HttpServletRequest req)
    {
		String indexPoint = "0";
		if( HttpParameter.isParameterExist(req,"indexPoint") ){
			indexPoint = HttpParameter.getString(req,"indexPoint","");
			req.getSession().setAttribute(WebKeys.indexPointKey, indexPoint);
		}else if(getIndexPointKey(req)==null || getIndexPointKey(req).length()<1){
			req.getSession().setAttribute(WebKeys.indexPointKey, indexPoint);
		}
    }
    public static String getIndexPointKey(HttpServletRequest req)
    {
		String  indexPoint = null;
		indexPoint = (String)req.getSession().getAttribute(WebKeys.indexPointKey);
		return indexPoint;
    }

    /**
    * referer URL과 current URL 의 비교
    *
    * @return boolean if referer URL==current URL then return true,else return false
    */
	public static boolean compareRefererToCurrentURL(HttpServletRequest req)
	{
		boolean compareValue = false;

		String refererUrl = JSPUtil.getHeader(req,"Referer");
		String currentUrl = req.getSession().getAttribute(WebKeys.SelectedURL).toString();

		// referer url
		String referer_final_value = "";
		if(refererUrl!=null && (refererUrl.trim()).length()>0){
			StringTokenizer referer_parse = new StringTokenizer(refererUrl,"?");
			int referer_parseCnt = referer_parse.countTokens();
			String referer_parse_value = referer_parse.nextToken();
			StringTokenizer referer_first_parse = new StringTokenizer(referer_parse_value,"/");
			int referer_firstCnt = referer_first_parse.countTokens();
			for(int i=0;i<referer_firstCnt;i++){
				referer_final_value = referer_first_parse.nextToken();
			}
		}
		// current url
		String current_final_value = "";
		if(currentUrl!=null && (currentUrl.trim()).length()>0){
			StringTokenizer current_parse = new StringTokenizer(currentUrl,"/");
			int current_parseCnt = current_parse.countTokens();
			for(int i=0;i<current_parseCnt;i++){
				current_final_value=current_parse.nextToken();
			}
		}

		if(referer_final_value.equals(current_final_value)){
			compareValue = true;
		}
		return compareValue;
	}

	public static boolean compareReferer(HttpServletRequest req,String pageName)
	{
		boolean compareValue = false;

		String refererUrl = JSPUtil.getHeader(req,"Referer");

		// referer url
		if(refererUrl!=null && (refererUrl.trim()).length()>0){
			StringTokenizer referer_parse = new StringTokenizer(refererUrl,"?");
			int referer_parseCnt = referer_parse.countTokens();
			String referer_parse_value = referer_parse.nextToken();
			StringTokenizer referer_first_parse = new StringTokenizer(referer_parse_value,"/");
			int referer_firstCnt = referer_first_parse.countTokens();
			String referer_final_value = null;
			for(int i=0;i<referer_firstCnt;i++){
				referer_final_value = referer_first_parse.nextToken();
			}

			if(referer_final_value.equals(pageName)){
				compareValue = true;
			}
		}
		return compareValue;
	}

}