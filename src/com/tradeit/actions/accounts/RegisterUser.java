package com.tradeit.actions.accounts;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import com.tradeit.utility.database.*;

public class RegisterUser extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//RequestDispatcher view = request.getRequestDispatcher("/hello.html");
		//view.forward(request, response);
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		ServletContext context = getServletContext();
		/* Validate password strength, and password retyped identically */
		String pass1 = request.getParameter("password");
		String pass2 = request.getParameter("password2");
		if(!pass1.equals(pass2)) {
			PrintWriter out = response.getWriter();
      			out.println(
        		"<html>\n" +
        		"<body bgcolor=\"#f0f0f0\">\n" +
        		"<p>Current Message is: " + "password not equivalent" + "</p>\n");
			return;
		}
		
		try {
			boolean isExist = UserAuth.checkUserExist(request.getParameter("username"));
			if(!isExist) {
				UserAuth.insertUserPass(request.getParameter("username"), request.getParameter("password"));
				PrintWriter out = response.getWriter();
      				out.println(
        			"<html>\n" +
        			"<body bgcolor=\"#f0f0f0\">\n" +
        			"<p>Current Message is: " + "User created" + "</p>\n");
			}	
			else {

				PrintWriter out = response.getWriter();
      				out.println(
        			"<html>\n" +
        			"<body bgcolor=\"#f0f0f0\">\n" +
        			"<p>Current Message is: " + "Username exist" + "</p>\n");

			}
		} catch (Exception e) {
			context.log(e.toString());
		}
	}
}
