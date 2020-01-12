package com.tradeit.actions.bussiness;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DisplayPostImage extends HttpServlet {
	static String TmpPathAWS = "/webapps/tmp/tradeit/";
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		/* get servlet context */
		ServletContext cntx= getServletContext();
		String imageName = request.getParameter("id");
		String filePath = TmpPathAWS + imageName;
		
		response.setContentType("image/xyz");
		
		File file = new File(filePath);
		response.setContentLength((int)file.length());
		
		FileInputStream in = new FileInputStream(file);
		OutputStream out = response.getOutputStream();
		
		byte[] buf = new byte[1024];
		int count = 0;
		while((count = in.read(buf)) >= 0) {
			out.write(buf, 0, count);
		}
		out.close();
		in.close();
	}

}
