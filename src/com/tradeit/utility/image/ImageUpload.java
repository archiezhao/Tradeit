package com.tradeit.utility.image;

import java.io.*;
import org.apache.log4j.*;

public class ImageUpload {
	
	static Logger log = Logger.getLogger(ImageUpload.class.getName());
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
			log.debug("came here");
		} catch(FileNotFoundException fne) {
			log.debug(fne.getMessage());
		} finally {
			if(out != null) {out.close();}
			if(filecontent != null) {filecontent.close();}
		}
	}
}
