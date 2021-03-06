package com.tradeit.actions.accounts;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import com.tradeit.utility.database.*;
import java.util.UUID;


public class LoginUser extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//RequestDispatcher view = request.getRequestDispatcher("/hello.html");
		//view.forward(request, response);
		ServletContext context = getServletContext();
	
		try {		
			boolean isCorrect = UserAuth.checkPassCorrect(request.getParameter("username"), request.getParameter("password"));
			String message = "";
			if(isCorrect) {
				message = "Correct! You have logged in";
				
				HttpSession session = request.getSession();
				session.setAttribute("username", request.getParameter("username"));
				
				/* generate CSRF token and store in session */
				String csrf_token = UUID.randomUUID().toString();
				session.setAttribute("csrf_token", csrf_token);
				
				response.sendRedirect("about.html");
				//message = "Hi " + session.getAttribute("username");
			}
			else
				message = "Username or password incorrect";
				
			PrintWriter out = response.getWriter();
      			out.println(
        		"<html>\n" +
        		"<body bgcolor=\"#f0f0f0\">\n" +
        		"<p>Current Message is: " + message + "</p>\n");
		} catch (Exception e) {
			context.log(e.toString());
		}
		
		
	}
}
