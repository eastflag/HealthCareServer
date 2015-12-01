package com.healthcare.common.upload.http;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.healthcare.common.Util;

public class Uploader  {

	/*
	*  setUploadDir메소드 내에서 파일까지 주어진 이름으로 생성할 껀지
	*  아니면 주어진 이름으로 디렉토리만 생성할 건지를 옵션을 준다.
	*  CREATE_FILE : 주어진 이름으로 파일까지 생성
	*/
	public static int CREATE_FILE = 1;
	/*
	*  setUploadDir메소드 내에서 파일까지 주어진 이름으로 생성할 껀지
	*  아니면 주어진 이름으로 디렉토리만 생성할 건지를 옵션을 준다.
	*  CREATE_DIR : 주어진 이름으로 디렉토리만 생성
	*/
	public static int CREATE_DIR = 2;

	private ReqParam parameters;
	private int max_limit = 1000;
	private File dir = null;
	private final String locale = "ko";
	UploadedInfo info;

	public static final int MEGA = 1024* 1024;
	public  boolean useUniqueID = true;		//화일이름을 UniqueID로 바꿔서 화일을 저장하겠는가?
	private String orginalFileName = "";
	public  String lastFileName = "";
	private Vector<String> vFileNames = null;
	private Vector<String> reNameFileNames = null;
	private Vector<String> reNamesDup = null;   //같은이름의 파일이 이미 존재하는 경우 파일이름뒤에 [1], [2] 등이 붙었을때 그 이름을 저장
	@SuppressWarnings("rawtypes")
	private Vector fileData = null;		//메일발송용 첨부파일을 byte[]로 변경 저장한 내용


	@SuppressWarnings("rawtypes")
	public Uploader() {
		vFileNames      = new Vector<String>();
		reNameFileNames = new Vector<String>();
		reNamesDup      = new Vector<String>();   //같은이름의 파일이 이미 존재하는 경우 파일이름뒤에 [1], [2] 등이 붙었을때 그 이름을 저장
		fileData        = new Vector();
	}
	public void setMaxLimit(int limit) {
		if( limit >= 0) max_limit = limit;
	}

	@SuppressWarnings("rawtypes")
	public Vector getFileData(){
		return fileData;
	}

	public void setUploadDir (String d, int option) {
		if(d != null) {
			this.dir = new File(d);
			if(option==CREATE_FILE) {
				int index = d.lastIndexOf(dir.getName());
				File fdir = new File(d.substring(0, index));
				if(!fdir.exists()) {
					//print("Created New Directory : " + fdir.getPath());
					fdir.mkdirs();
				}
			} else if (option==CREATE_DIR) {
				if(!dir.exists()) {
					dir.mkdirs();
					System.out.print("Created New Directory : " + d);
				}
			}
		}

	}

	public boolean  doUpload(HttpServletRequest request)
	throws IOException {
		boolean successful = true;
		parameters = new ReqParam();

		try {
			UploadParser mp = new UploadParser(request, max_limit * MEGA); // 10MB
			Part part;
			while ((part = mp.readNextPart()) != null) {
				String name = part.getName();
				if (part.isParam()) {
					// it's a parameter part
					ParamPart paramPart = (ParamPart) part;
					String value = paramPart.getStringValue(locale);
					parameters.setParameter(name, value);
				} else if (part.isFile()) {
					// it's a file part
					FilePart filePart = (FilePart) part;

					if(!(filePart.getFileName() == null || filePart.getFileName().equals(""))){
					    String fileName = filePart.getFileName();
    					orginalFileName = asc2euc(fileName,locale);
    					if(useUniqueID == true) {
    						//String strUniqueID = Util.getUniqueID();
    						//String strUniqueID = this.getUniqueID(orginalFileName);

                			int    point 	  = fileName.lastIndexOf(".");
                        	String strUniqueID = fileName.substring(0, point);

    						String strFileExtName = extendDivision( fileName, ".");
    						fileName = strUniqueID + "." + strFileExtName;
    						lastFileName = fileName;
    						//vFileNames.addElement(filePart.getFileName());
    						vFileNames.addElement(orginalFileName);

    						System.out.println("Uploader vFileNames : "+orginalFileName);

    						reNameFileNames.addElement(fileName);
    					}
    					if (fileName != null) {
    						// the part actually contained a file
    						if(dir == null) throw new IOException ("I do not know where to write uploaded file..");
    						filePart.setFileName(fileName);
    						info = filePart.writeTo(dir,locale);
    						reNamesDup.addElement(info.renames);
    					}
				    }
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			//print("Uploading failed due to IOException ", e);
			successful = false;
			throw new IOException(e.toString());
		}
		return successful;
	}
	
	public boolean  doUpload(HttpServletRequest request, String fileRename)
			throws IOException {
				boolean successful = false;
				parameters = new ReqParam();

				try {
					UploadParser mp = new UploadParser(request, max_limit * MEGA); // 10MB
					Part part;
					while ((part = mp.readNextPart()) != null) {
						String name = part.getName();
						if (part.isParam()) {
							// it's a parameter part
							ParamPart paramPart = (ParamPart) part;
							String value = paramPart.getStringValue(locale);
							parameters.setParameter(name, value);
						} else if (part.isFile()) {
							// it's a file part
							FilePart filePart = (FilePart) part;

							if(!(filePart.getFileName() == null || filePart.getFileName().equals(""))){
							    String fileName = filePart.getFileName();
		    					orginalFileName = asc2utf8(fileName,locale);
		    					if(useUniqueID == true) {
		    						//String strUniqueID = Util.getUniqueID();
		    						//String strUniqueID = this.getUniqueID(orginalFileName);

		                			int    point 	  = fileName.lastIndexOf(".");
		                        	String strUniqueID = fileName.substring(0, point);

		    						String strFileExtName = extendDivision( fileName, ".");
		    						fileName = strUniqueID + "." + strFileExtName;
		    						fileRename = fileRename + "." + strFileExtName;
		    						
		    						vFileNames.addElement(orginalFileName);

		    						System.out.println("Uploader vFileNames : "+orginalFileName);

		    						reNameFileNames.addElement(fileRename);
		    						
		    						System.out.println("Uploader reNameFileNames : "+fileRename);
		    					}
		    					if (fileName != null) {
		    						// the part actually contained a file
		    						if(dir == null) throw new IOException ("I do not know where to write uploaded file..");
		    						filePart.setFileName(fileRename);
		    						info = filePart.writeTo(dir,locale);
		    						reNamesDup.addElement(info.renames);
		    						successful = true;
		    					}
						    }
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
					//print("Uploading failed due to IOException ", e);
					successful = false;
					throw new IOException(e.toString());
				}
				return successful;
			}


	public boolean  doUploadMulti(HttpServletRequest request)
	throws IOException {
		boolean successful = true;
		parameters = new ReqParam();

		try {
			UploadParser mp = new UploadParser(request, max_limit * MEGA); // 10MB
			Part part;
			while ((part = mp.readNextPart()) != null) {
				String name = part.getName();
				if (part.isParam()) {
					// it's a parameter part
					ParamPart paramPart = (ParamPart) part;
					String value = paramPart.getStringValue(locale);
					parameters.setParameter(name, value);
				} else if (part.isFile()) {
					// it's a file part
					FilePart filePart = (FilePart) part;

					if(!(filePart.getFileName() == null || filePart.getFileName().equals(""))){
					    String fileName = filePart.getFileName();
    					orginalFileName = asc2euc(fileName,locale);
    					if(useUniqueID == true) {
    						String strUniqueID = Util.getUniqueID();
    						//String strUniqueID = this.getUniqueID(orginalFileName);

                			//int    point 	  = fileName.lastIndexOf(".");
                        	//String strUniqueID = fileName.substring(0, point);

    						String strFileExtName = extendDivision( fileName, ".");
    						fileName = strUniqueID + "." + strFileExtName;
    						lastFileName = fileName;
    						//vFileNames.addElement(filePart.getFileName());
    						vFileNames.addElement(name + "|" + orginalFileName);

    						System.out.println("Uploader vFileNames : "+name + "|" + fileName);

    						reNameFileNames.addElement(name + "|" + fileName);
    					}
    					if (fileName != null) {
    						// the part actually contained a file
    						if(dir == null) throw new IOException ("I do not know where to write uploaded file..");
    						filePart.setFileName(fileName);
    						info = filePart.writeTo(dir,locale);
    						reNamesDup.addElement(name + "|" + info.renames);
    					}
				    }
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			//print("Uploading failed due to IOException ", e);
			successful = false;
			throw new IOException(e.toString());
		}
		return successful;
	}


	//UploadStatus view
	public boolean  doUpload(HttpServletRequest request,String fileId,String className)
	throws IOException {
		boolean successful = true;
		parameters = new ReqParam();
		try {
			UploadParser mp = new UploadParser(request, max_limit * MEGA, fileId,className); // 10MB
			Part part;
			while ((part = mp.readNextPart()) != null) {
				String name = part.getName();
				if (part.isParam()) {
					// it's a parameter part
					ParamPart paramPart = (ParamPart) part;
					String value = paramPart.getStringValue(locale);
					parameters.setParameter(name, value);
				} else if (part.isFile()) {
					// it's a file part
					FilePart filePart = (FilePart) part;

					if(!(filePart.getFileName() == null || filePart.getFileName().equals(""))){

					    String fileName = filePart.getFileName();
    					orginalFileName = asc2euc(fileName,locale);
    					if(!(fileId.equals("") || fileId == null)) {
    						String strUniqueID = fileId;
    						//db에 저장.. id
    						String strFileExtName = extendDivision( fileName, ".");
    						fileName = strUniqueID + "." + strFileExtName;
    						lastFileName = fileName;
    					}
    					if (fileName != null) {
    						// the part actually contained a file
    						if(dir == null) throw new IOException ("I do not know where to write uploaded file..");
    						filePart.setFileName(fileName);
    						info = filePart.writeTo(dir,locale);
    					} /*else {
    						// the field did not contain a file
    						print("file; name=" + name + "; EMPTY");
    					}*/
				    }
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			//print("Uploading failed due to IOException ", e);
			successful = false;
			throw new IOException(e.toString());
		}
		return successful;
	}


	public boolean  doUpload(HttpServletRequest request,String[] fileNames)
	throws IOException {
		boolean successful = true;
		parameters = new ReqParam();
		try {
			UploadParser mp = new UploadParser(request, max_limit * MEGA); // 10MB
			Part part;
			int fileCount = 0;
			while ((part = mp.readNextPart()) != null) {
				String name = part.getName();
				if (part.isParam()) {
					// it's a parameter part
					ParamPart paramPart = (ParamPart) part;
					String value = paramPart.getStringValue(locale);
					parameters.setParameter(name, value);
				} else if (part.isFile()) {
					// it's a file part
					FilePart filePart = (FilePart) part;
					String fileName = fileNames[fileCount];
					vFileNames.addElement(filePart.getFileName());
					if (fileName != null) {
						// the part actually contained a file
						if(dir == null) throw new IOException ("please!. use the Uploader class's setUploadDir(String d, int option)");
						filePart.setFileName(fileName);
						info = filePart.writeTo(dir,locale);
					} /*else {
						// the field did not contain a file
						print("file; name=" + name + "; EMPTY");
					}*/
					fileCount++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			//print("Uploading failed due to IOException ", e);
			successful = false;
			throw new IOException(e.toString());
		}
		return successful;
	}
	public ReqParam getRequest() throws ParameterParsingException {
		if(parameters == null) throw new ParameterParsingException("Parameters passed from multi-part/form.... was not successfully extracted.. make sure doUpload invoked first...");
		return parameters;
	}
	//Add method 2002.01.21
	public String getParameter(String name) throws ParameterParsingException {
		if(name == null) throw new ParameterParsingException("Parameters passed from multi-part/form.... was not successfully extracted.. make sure doUpload invoked first...");
		return parameters.getParameter(name);
	}
	public String getOriginal() {
		if(info == null) return null;
		return info.original;
	}
	public String getOriginalFName() {
		return orginalFileName;
	}

	public String getLastFileName(){
	    return lastFileName;
	}

	public String getRenames() {
		if(info == null) return null;
		return info.renames;
	}
	public long getSize() {
		if(info == null) return -1;
		return info.size;
	}
	public String[] getOrignalFileNames(){
		String fNames[]=new String[vFileNames.size()];
		vFileNames.copyInto(fNames);
		return fNames;
	}
	public String[] getLastFileNames(){
		String fNames[]=new String[reNameFileNames.size()];
		reNameFileNames.copyInto(fNames);
		return fNames;
	}
	public String[] getReNamesDup(){
		String fNames[]=new String[reNamesDup.size()];
		reNamesDup.copyInto(fNames);
		return fNames;
	}

	private String extendDivision( String strFileName, String findChar ) {
		String strExtend="";
		try {
			int intFileLen = strFileName.length();
			int intdot = strFileName.lastIndexOf(findChar);
			strExtend = strFileName.substring( intdot + 1, intFileLen);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return strExtend;
	}

	private String asc2euc(String asc,String locale ) throws java.io.UnsupportedEncodingException {
		String encodingType=null;
		if(asc == null) {
			return "";
		}
    	if(locale.equals("ko")){
        	encodingType = "EUC-KR";
        	//encodingType = "UFT-8";
        }else if(locale.equals("ja")){
        	encodingType = "Shift_JIS";
        }
        return new String (asc.getBytes("8859_1"), encodingType);
	}
	
	private String asc2utf8(String asc,String locale ) throws java.io.UnsupportedEncodingException {
		String encodingType=null;
		if(asc == null) {
			return "";
		}
    	if(locale.equals("ko")){
        	encodingType = "UTF-8";
        }else if(locale.equals("ja")){
        	encodingType = "Shift_JIS";
        }
        return new String (asc.getBytes("8859_1"), encodingType);
	}
}