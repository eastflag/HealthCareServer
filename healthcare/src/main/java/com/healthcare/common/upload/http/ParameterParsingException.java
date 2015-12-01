package com.healthcare.common.upload.http;
public class ParameterParsingException extends Exception {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public ParameterParsingException() {
		this("Parameter parsing exception ...");
	}
	public ParameterParsingException(String msg) {
		super(msg);
	}
}
