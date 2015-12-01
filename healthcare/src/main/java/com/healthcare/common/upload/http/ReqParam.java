package com.healthcare.common.upload.http;
import java.util.*;
public class ReqParam {
	private Properties props = new Properties();
	public void setParameter(String name, String value ) {
		props.setProperty(name, value);
	}
	public String getParameter(String name) { 
		return props.getProperty(name, "");
	}	
	public String toString() {
		return props.toString();
	}
}