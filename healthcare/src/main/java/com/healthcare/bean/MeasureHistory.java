package com.healthcare.bean;

import java.util.ArrayList;

public class MeasureHistory {
	ArrayList<MeasureItem> list = new ArrayList<>();

	String standardMin = "0";
	String standardMax = "0";
	
	public ArrayList<MeasureItem> getList() {
		return list;
	}
	public void setList(ArrayList<MeasureItem> list) {
		this.list = list;
	}
	public String getStandardMin() {
		return standardMin;
	}
	public void setStandardMin(String standardMin) {
		this.standardMin = standardMin;
	}
	public String getStandardMax() {
		return standardMax;
	}
	public void setStandardMax(String standardMax) {
		this.standardMax = standardMax;
	}
	@Override
	public String toString() {
		return "MeasureHistory [list=" + list + ", standardMin=" + standardMin
				+ ", standardMax=" + standardMax + "]";
	}
}
