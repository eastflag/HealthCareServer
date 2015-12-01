package com.healthcare.common.upload.http;

import java.io.*;
import java.text.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class UploadParser {
	public static final int MEGA = 1024* 1024;
	private final ServletInputStream in;
	private final String boundary;

	/** reference to the last file part we returned */
	private FilePart lastFilePart;

	//Test
	int fileTotLength = 0;

	/** buffer for readLine method */
	private final byte[] buf = new byte[8 * MEGA];

	public UploadParser(HttpServletRequest req, int maxSize) throws IOException {
		String contentType = resolveContentType(req);
		if (contentType == null || !contentType.toLowerCase().startsWith("multipart/form-data")) {
		throw new IOException("Posted content type isn't multipart/form-data");
		}
		int length = req.getContentLength();
		//�׽�Ʈ
		fileTotLength = req.getContentLength();

		if (length > maxSize) {
			throw new IOException("Posted content length of " + length + " exceeds limit of " + maxSize);
		}
		// Get the boundary string included in the content type.
		// Should look something like "------------------------12012133613061"
		String boundary = extractBoundary(contentType);
		if (boundary == null) {
			throw new IOException("Boundary marker was not specified");
		}
		ServletInputStream in = req.getInputStream();
		// use bufferedInputStream(ServletInput) to boost performance and stability

		in = new BufferedServletInputStream( in );
		in = new LimitedServletInputStream(in, length);

		this.in = in;
		this.boundary = boundary;

		// Read the first line, should be the first boundary
		String line = readLine();
		if (line == null) {
			throw new IOException("Corrupt form data: premature ending");
		}

		// Verify that the line is the boundary
		if (!line.startsWith(boundary)) {
			throw new IOException("Corrupt form data: no leading boundary: " + line + " != " + boundary);
		}
	}

	//test
	public UploadParser(HttpServletRequest req, int maxSize, String fileId,String className) throws IOException {
		String contentType = resolveContentType(req);
		if (contentType == null || !contentType.toLowerCase().startsWith("multipart/form-data")) {
		throw new IOException("Posted content type isn't multipart/form-data");
		}
		int length = req.getContentLength();
		//�׽�Ʈ
		fileTotLength = req.getContentLength();
		if (length > maxSize) {
			throw new IOException("Posted content length of " + length + " exceeds limit of " + maxSize);
		}
		// Get the boundary string included in the content type.
		// Should look something like "------------------------12012133613061"
		String boundary = extractBoundary(contentType);
		if (boundary == null) {
			throw new IOException("Boundary marker was not specified");
		}
		ServletInputStream in = req.getInputStream();
		// use bufferedInputStream(ServletInput) to boost performance and stability
		in = new BufferedServletInputStream( in );
		in = new LimitedServletInputStream(in, length, fileId, className);

		this.in = in;
		this.boundary = boundary;

		// Read the first line, should be the first boundary
		String line = readLine();
		if (line == null) {
			throw new IOException("Corrupt form data: premature ending");
		}

		// Verify that the line is the boundary
		if (!line.startsWith(boundary)) {
			throw new IOException("Corrupt form data: no leading boundary: " + line + " != " + boundary);
		}
	}

/**
*	This first checks if non-null "Content-Type" Header exists and selects  that one ,  <BR>
*	If both values are non-null, it selects larger value<BR>
*	@param	cType1	Content-Type Header value <B>
*	(this can be extracted from one of HttpServletRequest.getHeader("Content-Type") or HttpServletRequest.getContentType() )
*	@param	cType2	Content-Type Header value
*	(this can be extracted from one of HttpServletRequest.getHeader("Content-Type") or HttpServletRequest.getContentType() )
*/
	private String resolveContentType(HttpServletRequest req) {
		String contentType = null;
		String cType1 = req.getHeader("Content-Type");
		String cType2 = req.getContentType();
		if (cType1 == null && cType2 != null) {
			contentType = cType2;
		} else if (cType2 == null && cType1 != null) {
			contentType = cType1;
		} else if (cType1 != null && cType2 != null) {// If neither value is null, choose the longer value
			contentType = (cType1.length() > cType2.length() ? cType1 : cType2);
		}
		return contentType;
	}
	 int yy			= 0;
	private String readLine() throws IOException {
		StringBuffer sbuf = new StringBuffer();
		int result;

		do {
			result = in.readLine(buf, 0, buf.length);  // does +=
			if (result != -1) {
				sbuf.append(new String(buf, 0, result, "ISO-8859-1"));
			}
		} while (result == buf.length);  // loop only if the buffer was filled

		if (sbuf.length() == 0) {
			return null;  // nothing read, must be at the end of stream
		}
		// Cut off the trailing \n or \r\n
		// It should always be \r\n but IE5 sometimes does just \n
		int len = sbuf.length();
		if (sbuf.charAt(len - 2) == '\r') {
			sbuf.setLength(len - 2);  // cut \r\n
		} else {
			sbuf.setLength(len - 1);  // cut \n
		}
		return sbuf.toString();
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Part readNextPart() throws IOException {
		// Make sure the last file was entirely read from the input
		if (lastFilePart != null) {
			lastFilePart.getInputStream().close();
			lastFilePart = null;
		}

		/* Read the headers; they look like this (not all may be present):
		 Content-Disposition: form-data; name="field1"; filename="file1.txt"
		 Content-Type: type/subtype
		 Content-Transfer-Encoding: binary
		*/
		Vector headers = new Vector();
		String line = readLine();

		// IE4 on Mac sends an empty line at the end; treat that as the end.
		// Thanks to Daniel Lemire and Henri Tourigny for this fix.
		if (line == null || line.length() == 0) {
			return null;
		}
		headers.addElement(line);
		// Read the following header lines we hit an empty line
		while ((line = readLine()) != null && (line.length() > 0)) {
			headers.addElement(line);
		}
		// If we got a null above, it's the end
		if (line == null) { // what about the time when the above line's length negative...
			return null;
		}

		String name = null;
		String filename = null;
		String origname = null;
		String contentType = "text/plain";  // rfc1867 says this is the default

		Enumeration enumr = headers.elements();
		while (enumr.hasMoreElements()) {
			String headerline = (String) enumr.nextElement();
			if (headerline.toLowerCase().startsWith("content-disposition:")) {
				// Parse the content-disposition line
				String[] dispInfo = extractDispositionInfo(headerline);
				// String disposition = dispInfo[0];  // not currently used
				name = dispInfo[1];
				filename = dispInfo[2];
				origname = dispInfo[3];
			} else if (headerline.toLowerCase().startsWith("content-type:")) {
				// Get the content type, or null if none specified
				String type = extractContentType(headerline);
				if (type != null) {
					contentType = type;
				}
			}
		}

		// Now, finally, we read the content (end after reading the boundary)
		if (filename == null) {
			// This is a parameter, add it to the vector of values
			return new ParamPart(name, in, boundary);
		} else {
			// This is a file
			if (filename.equals("")) {
				filename = null; // empty filename, probably an "empty" file param
			}
			lastFilePart = new FilePart(name, in, boundary, contentType, filename, origname);
			return lastFilePart;
		}
	}

	private String extractBoundary(String line) {
		// Use lastIndexOf() because IE 4.01 on Win98 has been known to send the
		// "boundary=" string multiple times.  Thanks to David Wall for this fix.
		int index = line.lastIndexOf("boundary=");
		if (index == -1) {
			return null;
		}
		String boundary = line.substring(index + 9);  // 9 for "boundary="
		if (boundary.charAt(0) == '"') {
			// The boundary is enclosed in quotes, strip them
			index = boundary.lastIndexOf('"');
			boundary = boundary.substring(1, index);
		}
		// The real boundary is always preceeded by an extra "--"
		boundary = "--" + boundary;
		return boundary;
	}

	private String[] extractDispositionInfo(String line) throws IOException {
		// Return the line's data as an array: disposition, name, filename
		String[] retval = new String[4];

		// Convert the line to a lowercase string without the ending \r\n
		// Keep the original line for error messages and for variable names.
		String origline = line;
		line = origline.toLowerCase();

		// Get the content disposition, should be "form-data"
		//Content-Disposition: form-data; name="field1"; filename="file1.txt"
		int start = line.indexOf("content-disposition: ");
		int end = line.indexOf(";");
		if (start == -1 || end == -1) {
			throw new IOException("Content disposition corrupt: " + origline);
		}
		String disposition = line.substring(start + 21, end);
		if (!disposition.equals("form-data")) {
			throw new IOException("Invalid content disposition: " + disposition);
		}

		// Get the field name
		start = line.indexOf("name=\"", end);  // start at last semicolon
		end = line.indexOf("\"", start + 7);   // skip name=\"
		if (start == -1 || end == -1) {
			throw new IOException("Content disposition corrupt: " + origline);
		}
		String name = origline.substring(start + 6, end);

		// Get the filename, if given
		String filename = null;
		String origname = null;
		start = line.indexOf("filename=\"", end + 2);  // start after name
		end = line.indexOf("\"", start + 10);          // skip filename=\"
		if (start != -1 && end != -1) {                // note the !=
			filename = origline.substring(start + 10, end);
			origname = filename;
			// The filename may contain a full path.  Cut to just the filename.
			int slash = Math.max(filename.lastIndexOf('/'), filename.lastIndexOf('\\'));
			if (slash > -1) {
				filename = filename.substring(slash + 1);  // past last slash
			}
		}
		retval[0] = disposition;
		retval[1] = name;
		retval[2] = filename;
		retval[3] = origname;
		return retval;
	}
	private String extractContentType(String line) throws IOException {
		String contentType = null;
		String origline = line;
		line = origline.toLowerCase();
		// Content-Type: type/subType
		if (line.startsWith("content-type")) {
			int start = line.indexOf(" ");
			if (start == -1) {
				throw new IOException("Content type corrupt: " + origline);
			}
			contentType = line.substring(start + 1);
		} else if (line.length() != 0) {  // no content type, so should be empty
			throw new IOException("Malformed line after disposition: " + origline);
		}
		return contentType;
	}

	public static String formatNumber(int number , String format){
    	DecimalFormat formatter = new DecimalFormat(format);
    	return formatter.format(number);
   	}
}
