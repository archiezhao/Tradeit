package com.tradeit.actions.bussiness;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.*;
import com.tradeit.utility.database.*;
import com.tradeit.utility.image.*;

@MultipartConfig
public class CreatePost extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//RequestDispatcher view = request.getRequestDispatcher("/hello.html");
		//view.forward(request, response);
		
		ServletContext context = getServletContext();
	
		try {
			/* get current session */
			HttpSession session = request.getSession();
			
			/* responds with CSRF error if csrf_token doesn't match */
			System.out.println("session csrf is: " + session.getAttribute("csrf_token"));
			System.out.println("request csrf is: " + request.getAttribute("csrf_token"));
			if(!session.getAttribute("csrf_token").equals(request.getParameter("csrf_token"))) {
				session.invalidate();
				RequestDispatcher csrfError = request.getRequestDispatcher("/csrfthreat.html");
				csrfError.forward(request, response);
      			return;
			}
			
			/* reset the current session's imagecount to zero, and clear 'imageref#', prepare for the next image upload */
			session.setAttribute("imagecount", new Integer(0));
			
			/* get the condition code for item */
			int condition = Integer.parseInt(request.getParameter("condition"));
			int price = Integer.parseInt(request.getParameter("postprice"));
			String title = request.getParameter("posttitle");
			System.out.println("title: " + title);
			System.out.println("condition: " + condition);
			System.out.println("price: " + price);
			
			String imageref1 = (null == session.getAttribute("imageref1")) ? "0" : session.getAttribute("imageref1").toString();
			String imageref2 = (null == session.getAttribute("imageref2")) ? "0" : session.getAttribute("imageref2").toString();
			String imageref3 = (null == session.getAttribute("imageref3")) ? "0" : session.getAttribute("imageref3").toString();
			
			/* Insert post information into database, insertPostInfo(int condition, String title, int price, String description, String userid) */
			PostOperate.insertPostInfo(
				condition, 
				title,	//VACHAR(100)
				price, 
				request.getParameter("description"), //VACHAR(5000)
				session.getAttribute("username").toString(), //VACHAR(20)
				imageref1, //VACHAR(15)
				imageref2, //VACHAR(15)
				imageref3); //VACHAR(15)
			
			session.removeAttribute("imageref1");
			session.removeAttribute("imageref2");
			session.removeAttribute("imageref3");	
			
			RequestDispatcher postCreateSuccess = request.getRequestDispatcher("/createsuccess.html");
			postCreateSuccess.forward(request, response);
		
			
		} catch (Exception e) {
			context.log(e.toString());
		}
		
		
	}
}
