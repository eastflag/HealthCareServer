package com.healthcare.common.upload.http;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.servlet.ServletInputStream;

public class FilePart extends Part {

	private String fileName;
	private String filePath;
	private String contentType;
	private PartInputStream partInput;

	FilePart(String name, ServletInputStream in, String boundary,
	String contentType, String fileName, String filePath)
	throws IOException {
		super(name);
		this.fileName = fileName;
		this.filePath = filePath;
		this.contentType = contentType;
		partInput = new PartInputStream(in, boundary);
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public String getContentType() {
		return contentType;
	}

	public InputStream getInputStream() {
		return partInput;
	}

	public UploadedInfo writeTo(File fileOrDirectory,String locale) throws IOException {
		long written = 0;
		OutputStream fileOut = null;
		UploadedInfo info = new UploadedInfo();
		try {
			if (fileName != null) {
				info.original = asc2euc(fileName,locale);
				File file;
				if (fileOrDirectory.isDirectory()) {
					fileName = checkAndGetFileName(fileOrDirectory, asc2euc(fileName,locale));
					info.renames = fileName;
					file = new File(fileOrDirectory, fileName);
				} else {
					file = fileOrDirectory;
				}
				fileOut = new BufferedOutputStream(new FileOutputStream(file));
				written = write(fileOut);
				info.size = written;
			}
		} finally {
			if (fileOut != null) fileOut.close();
		}

		return info;
	}

//	public com.innosns.util.ByteArrayDataSource getDataSource() throws IOException{
//		 com.innosns.util.ByteArrayDataSource dataSource = new com.innosns.util.ByteArrayDataSource(partInput,contentType,fileName);
//		return dataSource;
//	}

	public long writeTo(OutputStream out,String locale) throws IOException {
		long size=0;
		if (fileName != null) {
			size = write( out );
		}
		return size;
	}

	//file write
	long write(OutputStream out) throws IOException {
		if (contentType.equals("application/x-macbinary")) {
			out = new MacBinaryDecoderOutputStream(out);
		}
		long size=0;
		int read;
		byte[] buf = new byte[8 * 1024];
		while((read = partInput.read(buf)) != -1) {
			out.write(buf, 0, read);
			size += read;
		}
		return size;
	}

	public boolean isFile() {
		return true;
	}
	private String asc2euc(String asc,String locale ) throws java.io.UnsupportedEncodingException {
		String encodingType=null;
		if(asc == null) {
			return "";
		}
    	if(locale.equals("ko")){
        	encodingType = "EUC-KR";
        }else if(locale.equals("ja")){
        	encodingType = "Shift_JIS";
        }
        return new String (asc.getBytes("8859_1"), encodingType);
        //return new String (asc.getBytes(encodingType), encodingType);
        //return new String (asc.getBytes(), encodingType);
	}

	private String checkAndGetFileName(File dir, String name) {

		int idx = name.lastIndexOf(".");
		String pref = name.substring(0, idx);
		String sur = name.substring(idx);
		File file = new File(dir, name);
		int count = 0;
		while(file.exists()) {
			name = pref+"[" + ++count +"]"+ sur;
			file = new File(dir , name);
		}
		return name;
	}

}
