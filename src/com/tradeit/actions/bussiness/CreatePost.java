package com.tradeit.actions.bussiness;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import com.tradeit.utility.database.*;
import com.tradeit.utility.image.*;

public class CreatePost extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//RequestDispatcher view = request.getRequestDispatcher("/hello.html");
		//view.forward(request, response);
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		ServletContext context = getServletContext();
	
		try {		
			if(request.isRequestedSessionIdValid()) {
				HttpSession session = request.getSession();
				/* Insert post information into database */
				PostOperate.insertPostInfo(Integer.parseInt(request.getParameter("condition")), request.getParameter("description"), session.getAttribute("username").toString());
				
				/* upload post image */
				InputStream filecontent = null;
				final Part filePart = request.getPart("itemimage");
				filecontent = filePart.getInputStream();
				ImageUpload.processRequest(filecontent, "/tmp/file.upload1");
			

				String message = "You have created a post";
			
				PrintWriter out = response.getWriter();
      				out.println(
        			"<html>\n" +
        			"<body bgcolor=\"#f0f0f0\">\n" +
        			"<p>Current Message is: " + message + "</p>\n");
			}
			else {
				String message = "Session expired";
			
				PrintWriter out = response.getWriter();
      				out.println(
        			"<html>\n" +
        			"<body bgcolor=\"#f0f0f0\">\n" +
        			"<p>Current Message is: " + message + "</p>\n");
			}
			
		} catch (Exception e) {
			context.log(e.toString());
		}
		
		
	}
}
