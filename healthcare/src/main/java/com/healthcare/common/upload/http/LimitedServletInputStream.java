package com.healthcare.common.upload.http;
import java.io.IOException;
import javax.servlet.ServletInputStream;
import java.text.*;

public class LimitedServletInputStream extends ServletInputStream {
	public ServletInputStream in;
	public int totalExpected;
	public int totalRead = 0;
	public int oldRead   = 0;
	public String fileId  = "";
	public String className = "";
	String per_str	= "";
    

	public LimitedServletInputStream(ServletInputStream in, int totalExpected_ori, String fileId_ori, String className ) {
		this.in = in;
		totalExpected = totalExpected_ori;
		fileId = fileId_ori;
		this.className = className;

	}

	public LimitedServletInputStream(ServletInputStream in, int totalExpected) {
		this.in = in;
		this.totalExpected = totalExpected;

	}




	public int readLine(byte b[], int off, int len) throws IOException {
		int result, left = totalExpected - totalRead;

		int per = 0;



		if (left <= 0) {
			return -1;
		} else {
			result = ((ServletInputStream)in).readLine(b, off, Math.min(left, len));
		}
		if (result > 0) {
			totalRead += result;
		}


		return result;
	} 

    public int read() throws IOException {
		if (totalRead >= totalExpected) {
			return -1;
		}
		return in.read();
	}

	public int read( byte b[], int off, int len ) throws IOException {
		int result, left = totalExpected - totalRead;
		if (left <= 0) {
			return -1;
		} else {
			result = in.read(b, off, Math.min(left, len));
		}
		if (result > 0) {
			totalRead += result;
		}
		return result;
	}

	public static String formatNumber(int number , String format){
    	DecimalFormat formatter = new DecimalFormat(format);
    	return formatter.format(number);
   	}
   	
 }
