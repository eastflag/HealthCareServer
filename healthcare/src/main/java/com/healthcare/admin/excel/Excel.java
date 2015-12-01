package com.healthcare.admin.excel;

import java.io.Serializable;

public class Excel implements Serializable {
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String excel_url;

    /**
	* Constructor
	*/
    public Excel(  	String excel_url)
    {
		this.excel_url = excel_url;
    }
    public Excel() {
    }
	public String getExcel_url() {
		return excel_url;
	}
	public void setExcel_url(String excel_url) {
		this.excel_url = excel_url;
	}

}