package com.healthcare.admin.menu;

import java.io.Serializable;
import java.util.Collection;

public class Menus implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("rawtypes")
	private Collection documents = null;
	private int totRowCnt = 0;
	
	@SuppressWarnings("rawtypes")
	public Menus(Collection documents,int totRowCnt)
    {
		this.documents   = documents;
		this.totRowCnt = totRowCnt;
    }
	
	@SuppressWarnings("rawtypes")
	public Menus(Collection documents)
    {
		this(documents,0);
    }

	@SuppressWarnings("rawtypes")
	public Collection getMenus() {
	   return documents;
    }
	
	public int getTotRowCnt() {
	   return totRowCnt;
    }
    public void setTotRowCnt(int totRowCnt) {
	   this.totRowCnt = totRowCnt;
    }
}
