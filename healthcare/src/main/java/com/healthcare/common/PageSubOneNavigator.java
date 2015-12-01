/**
  * $Id: PageNavigator.java,v 1.3 2001/12/07 00:06:41 Park $
  * Copyright 2001 DongGyoo Lee. All rights reserved.
  **/
package	com.healthcare.common;

/**
 * Jsp에서 page	Navigator 기능을 효율적으로 지원하기 위한 클래스
 * @author InnoSNS
 */
	public class PageSubOneNavigator
	{
	private int	 m_nCurrPos;	//현재 위치
	private int	 m_nMaxCount;	//커리한 최대 갯수
	private int	 m_nMax;	//표시할 수 있는 최대 갯수
	private int	 m_initMultiple;
	private String m_szUrl;
	private String leftFolderArrow = null;
	private String rightFoldertArrow = null;

	public PageSubOneNavigator(int nPos, int nMaxCount,int numOfLine,String szUrl)
	{
		int	 m_nDefaultNavigationNum=5;	//디폴트 라인 수
		inStruct(nPos, nMaxCount, m_nDefaultNavigationNum,numOfLine, szUrl);
	}
	public PageSubOneNavigator(int nPos, int nMaxCount,int navigationNum,int numOfLine,String szUrl)
	{
		inStruct(nPos, nMaxCount, navigationNum,numOfLine, szUrl);
	}
	public void inStruct(int nPos, int nMaxCount,int navigationNum,int numOfLine,String szUrl)
	{
		m_nCurrPos  = nPos;
		m_nMaxCount = (--nMaxCount/numOfLine)+1;
		m_nMax	    = navigationNum;
		m_szUrl	    = szUrl;

		m_initMultiple = ((m_nCurrPos-1)/m_nMax)*m_nMax;
	}
	public void setCurrPos (int nPos)
	{
		m_nCurrPos = nPos;
	}
	public int getTotPages()
	{
		return m_nMaxCount;
	}

	public String getHtml()
	{
		leftFolderArrow 	= "<img src='./images/icon_prev.gif' border='0' align='absmiddle'>";
		rightFoldertArrow 	= "<img src='./images/icon_next.gif' border='0' align='absmiddle'>";

		StringBuffer szHtml = new StringBuffer();
		if (m_nMaxCount	< 1)
		{
			szHtml.append("<font  color='#5E668D'><strong>[1]</strong></font>");
			return szHtml.toString();
		}
		szHtml.append(insertIsPrev());
		int startindex = m_initMultiple+1;
		int endindex = m_initMultiple+m_nMax;
		if(endindex>m_nMaxCount)endindex=m_nMaxCount;
		for (int i = startindex; i <= endindex; i++)
		{
			szHtml.append(insertNumber(i));
		}
		szHtml.append(insertIsNext());
		return szHtml.toString();
	}

	private	String insertIsPrev ()
	{
		String szUrl;
		String szTemp;
		if (m_nCurrPos > m_nMax) {
			int beforePages = m_initMultiple-m_nMax+1;
			szUrl = m_szUrl + "subOnePageNo="+beforePages;
			szTemp = "<a href='" +	szUrl +	"' >" + leftFolderArrow+"</a>&nbsp;";
		} else {
			szTemp = "";
		}
		return szTemp;
	}

	private	String insertNumber(int	nNum)
	{
		String szTemp;
		String szUrl;
		szUrl =	m_szUrl	+ "subOnePageNo="+nNum;
		if (nNum == m_nCurrPos)	{
			szTemp ="<font color='#5E668D'><strong>[" + nNum + "]</strong></font>&nbsp;";
		} else {
			szTemp = "<a href='"+ szUrl +"'>["+ nNum +"]</a>&nbsp;";
		}
		return szTemp;
	}

	private	String insertIsNext ()
	{
		String szTemp;
		String szUrl;
		if( (m_nMax < m_nMaxCount)&&((m_initMultiple+m_nMax)< m_nMaxCount) ) {
			int nextPages = m_initMultiple+m_nMax+1;
			szUrl = m_szUrl + "subOnePageNo="+nextPages;
			szTemp	= "<a href='"+ szUrl +"'>" + rightFoldertArrow + "</a>";
		} else {
			szTemp = "";
		}
		return szTemp;
	}
}

