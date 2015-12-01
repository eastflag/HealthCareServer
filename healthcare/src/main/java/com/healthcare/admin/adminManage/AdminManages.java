package com.healthcare.admin.adminManage;

import java.io.Serializable;
import java.util.Collection;

public class AdminManages implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	private Collection documents = null;
	private int totRowCnt = 0;
	
	@SuppressWarnings("rawtypes")
	public AdminManages(Collection documents,int totRowCnt)
    {
		this.documents   = documents;
		this.totRowCnt = totRowCnt;
    }
	
	@SuppressWarnings("rawtypes")
	public AdminManages(Collection documents)
    {
		this(documents,0);
    }

	@SuppressWarnings("rawtypes")
	public Collection getAdminManages() {
	   return documents;
    }
	
	public int getTotRowCnt() {
	   return totRowCnt;
    }
    public void setTotRowCnt(int totRowCnt) {
	   this.totRowCnt = totRowCnt;
    }
}
