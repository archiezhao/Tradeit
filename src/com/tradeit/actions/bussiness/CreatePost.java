package com.tradeit.actions.bussiness;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import com.tradeit.utility.database.*;
import com.tradeit.utility.image.*;
import org.apache.log4j.*;

public class CreatePost extends HttpServlet {
	static Logger log = Logger.getLogger(ImageUpload.class.getName());

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//RequestDispatcher view = request.getRequestDispatcher("/hello.html");
		//view.forward(request, response);
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		ServletContext context = getServletContext();
	
		try {		
			if(request.isRequestedSessionIdValid()) {
				HttpSession session = request.getSession();
				/* get current time in millisecond, will be used as image name */
				final String curTimeMillis = String.valueOf(System.currentTimeMillis());
				/* Insert post information into database, insertPostInfo(int condition, String title, int price, String description, String userid) */
				PostOperate.insertPostInfo(
					Integer.parseInt(request.getParameter("condition")), 
					request.getParameter("posttitle"),
					Integer.parseInt(request.getParameter("postprice")), 
					request.getParameter("description"), 
					session.getAttribute("username").toString(),
					curTimeMillis);
				
				/* upload post image */
				InputStream filecontent = null;
				final Part filePart = request.getPart("itemimage");
				final String fileName = filePart.getName();
				if(filePart.getSize() != 0) {
					filecontent = filePart.getInputStream();
					ImageUpload.processRequest(filecontent, "/tmp/" + curTimeMillis);
				}
				else {
					log.debug("There's no image in the form");
				}
				
			
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
