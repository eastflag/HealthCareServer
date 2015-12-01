package com.healthcare.admin.board;

import java.io.Serializable;
import java.util.Collection;

public class Boards implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("rawtypes")
	private Collection documents = null;
	private int totRowCnt = 0;
	
	@SuppressWarnings("rawtypes")
	public Boards(Collection documents,int totRowCnt)
    {
		this.documents   = documents;
		this.totRowCnt = totRowCnt;
    }
	
	@SuppressWarnings("rawtypes")
	public Boards(Collection documents)
    {
		this(documents,0);
    }
	
	@SuppressWarnings("rawtypes")
	public Collection getBoards() {
	   return documents;
    }
	
	public int getTotRowCnt() {
	   return totRowCnt;
    }
    public void setTotRowCnt(int totRowCnt) {
	   this.totRowCnt = totRowCnt;
    }
	
}
