package com.tradeit.actions.bussiness;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.tradeit.utility.image.ImageUpload;

@MultipartConfig
public class UploadImage extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//RequestDispatcher view = request.getRequestDispatcher("/hello.html");
		//view.forward(request, response);
		
		ServletContext context = getServletContext();
	
		try {		
			HttpSession session = request.getSession();
			/* get current time in millisecond, will be used as image name */
			final String curTimeMillis = String.valueOf(System.currentTimeMillis());
			
			/* responds with CSRF error if csrf_token doesn't match */
			if(!session.getAttribute("csrf_token").equals(request.getParameter("csrf_token"))) {
				session.invalidate();
				//response.sendRedirect("/Tradeit/csrfthreat.html");
      			return;
			}
			
			/* upload post image */
			InputStream filecontent = null;
			final Part filePart = request.getPart("itemimage");
			if(filePart == null) {
				System.out.println("no file");
			}
			if(filePart != null && filePart.getSize() != 0) {	
			
				filecontent = filePart.getInputStream();
				/* Upload the image to a temporary directory */
				ImageUpload.processRequest(filecontent, "/tmp/" + curTimeMillis);
				
				/* Update 'imagecount' parameter to the number of images, initialize it to one if null */
				Integer imagecount = (Integer)session.getAttribute("imagecount");
				if(imagecount != null) {
					session.setAttribute("imagecount", new Integer(imagecount.intValue()+1));
				}
				else {
					session.setAttribute("imagecount", new Integer(1));
				}
				Integer curimagecount = (Integer)session.getAttribute("imagecount");
				session.setAttribute("imageref"+curimagecount, curTimeMillis);
				
			}
			else {
			}
			//RequestDispatcher postCreateSuccess = request.getRequestDispatcher("/createsuccess.html");
			//postCreateSuccess.forward(request, response);
		
		} catch (Exception e) {
			context.log(e.toString());
		}
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
	}
}
