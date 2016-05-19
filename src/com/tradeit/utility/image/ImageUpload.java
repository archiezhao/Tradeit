package com.tradeit.utility.image;

import java.io.*;

public class ImageUpload {
	
	static String basePath = "/webapps/tradeit";
	//filecontent: request.getPart("filename").getgetInputStream
	public static void processRequest(InputStream filecontent, String filepath) throws IOException{
				
		OutputStream out = null;
		try {		
			out = new FileOutputStream(new File(basePath+filepath));
			int read = 0;
			final byte[] bytes = new byte[1024];

			while((read = filecontent.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
		} catch(FileNotFoundException fne) {
			
		} finally {
			if(out != null) {out.close();}
			if(filecontent != null) {filecontent.close();}
		}
	}
}
