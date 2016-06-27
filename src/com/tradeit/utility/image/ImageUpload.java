package com.tradeit.utility.image;

import java.io.*;

public class ImageUpload {
	
	static String basePathAWS = "/webapps";
	//filecontent: request.getPart("filename").getgetInputStream
	public static void processRequest(InputStream filecontent, String filepath) throws IOException{
				
		OutputStream out = null;
		try {
			System.out.println(basePathAWS+filepath);
			out = new FileOutputStream(new File(basePathAWS+filepath));
			
			int read = 0;
			final byte[] bytes = new byte[1024];

			while((read = filecontent.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
		} catch(IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			if(out != null) {out.close();}
			if(filecontent != null) {filecontent.close();}
		}
	}
}
