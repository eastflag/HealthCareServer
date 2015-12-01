package com.healthcare.admin.statistics;

import java.io.Serializable;
import java.util.Collection;

public class Statisticss implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("rawtypes")
	private Collection documents = null;
	private int totRowCnt = 0;

	private String allMaleAvgH = "0.0";
	private String allFemaleAvgH = "0.0";
	private String allMaleAvgW = "0.0";
	private String allFemaleAvgW = "0.0";
	
	@SuppressWarnings("rawtypes")
	public Statisticss(Collection documents,int totRowCnt)
    {
		this.documents   = documents;
		this.totRowCnt = totRowCnt;
    }
	
	@SuppressWarnings("rawtypes")
	public Statisticss(Collection documents)
    {
		this(documents,0);
    }
	
	@SuppressWarnings("rawtypes")
	public Collection getStatisticss() {
	   return documents;
    }
	
	public int getTotRowCnt() {
	   return totRowCnt;
    }
    public void setTotRowCnt(int totRowCnt) {
	   this.totRowCnt = totRowCnt;
    }

	public String getAllMaleAvgH() {
		return allMaleAvgH;
	}

	public void setAllMaleAvgH(String allMaleAvgH) {
		this.allMaleAvgH = allMaleAvgH;
	}

	public String getAllFemaleAvgH() {
		return allFemaleAvgH;
	}

	public void setAllFemaleAvgH(String allFemaleAvgH) {
		this.allFemaleAvgH = allFemaleAvgH;
	}

	public String getAllMaleAvgW() {
		return allMaleAvgW;
	}

	public void setAllMaleAvgW(String allMaleAvgW) {
		this.allMaleAvgW = allMaleAvgW;
	}

	public String getAllFemaleAvgW() {
		return allFemaleAvgW;
	}

	public void setAllFemaleAvgW(String allFemaleAvgW) {
		this.allFemaleAvgW = allFemaleAvgW;
	}
	
}
