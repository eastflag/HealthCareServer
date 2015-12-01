package com.healthcare.common.upload.http;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletInputStream;
public class ParamPart extends Part {
	private final byte[] value;
/** 
* Constructs a parameter part; this is called by the parser.
* 
* @param name the name of the parameter.
* @param in the servlet input stream to read the parameter value from.
* @param boundary the MIME boundary that delimits the end of parameter value. 
*/
	ParamPart(String name, ServletInputStream in, String boundary) throws IOException {
		super(name);
		// Copy the part's contents into a byte array
		PartInputStream pis = new PartInputStream(in, boundary);
		ByteArrayOutputStream baos = new ByteArrayOutputStream(512);
		byte[] buf = new byte[128];
		int read;
		while ((read = pis.read(buf)) != -1) {
			baos.write(buf, 0, read);
		}
		pis.close();
		baos.close();
		// save it for later
		value = baos.toByteArray();
	}

/** 
* Returns the value of the parameter as an array of bytes or a zero length 
* array if the user entered no value for this parameter.
* 
* @return value of parameter as a ISO-8559-1 string.
*/
	public byte [] getValue() {
		return value;
	}

	/** 
	* Returns the value of the parameter in the default ISO-8859-1 encoding
	* or empty string if the user entered no value for this parameter.
	* 
	* @return value of parameter as a ISO-8559-1 encoded string.
	*/
	public String getStringValue(String locale) throws UnsupportedEncodingException {
    	String encodingType=null;
    	if(locale.equals("ko")){
        	//encodingType = "EUC-KR";
        	encodingType = "UTF-8"; 
        }else if(locale.equals("ja")){
        	encodingType = "Shift_JIS";
        }
        return new String(value, encodingType );
	}
	
	/** 
	* Returns the value of the parameter in the supplied encoding
	* or empty string if the user entered no value for this parameter.
	* 
	* @return value of parameter as a string.
	*/
	@Override
	public boolean isParam() {
		return true;
	}
}
