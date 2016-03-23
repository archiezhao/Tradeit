package com.tradeit.actions.accounts;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import com.tradeit.utility.database.*;

public class LoginUser extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//RequestDispatcher view = request.getRequestDispatcher("/hello.html");
		//view.forward(request, response);
		doGet(request, response);
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		ServletContext context = getServletContext();
		//String que = "insert into user_authentication (user_id, pass_hash) values ('tset_id123', 'test_pass123');";
		try {
			UserAuth.insertUserPass(request.getParameter("username"), request.getParameter("password"));
		} catch (Exception e) {
			context.log(e.toString());
		}
		
		
	} 
}
