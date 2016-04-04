package com.tradeit.actions.bussiness;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import com.tradeit.utility.database.*;

public class DisplayHome extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//RequestDispatcher view = request.getRequestDispatcher("/hello.html");
		//view.forward(request, response);
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		ServletContext context = getServletContext();
	
		try {		
			if(request.isRequestedSessionIdValid()) {
				String message = "welcome to home page";
			
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
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doPost(request, response);
	}
}
