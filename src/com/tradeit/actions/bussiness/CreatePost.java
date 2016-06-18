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
			HttpSession session = request.getSession();
			/* get current time in millisecond, will be used as image name */
			final String curTimeMillis = String.valueOf(System.currentTimeMillis());
			/* get the condition code for item */
			int condition = Integer.parseInt(request.getParameter("condition"));
			int price = Integer.parseInt(request.getParameter("postprice"));
			String title = request.getParameter("posttitle");
			System.out.println("title: " + title);
			System.out.println("condition: " + condition);
			System.out.println("price: " + price);
			/* Insert post information into database, insertPostInfo(int condition, String title, int price, String description, String userid) */
			PostOperate.insertPostInfo(
				condition, 
				title,	//VACHAR(100)
				price, 
				request.getParameter("description"), //VACHAR(5000)
				session.getAttribute("username").toString(), //VACHAR(20)
				curTimeMillis); //VACHAR(20)
			
			/* upload post image */
			InputStream filecontent = null;
			final Part filePart = request.getPart("itemimage");
			if(filePart != null && filePart.getSize() != 0) {	
				filecontent = filePart.getInputStream();
				ImageUpload.processRequest(filecontent, "/tmp/" + curTimeMillis);
			}
			else {
			}
			RequestDispatcher postCreateSuccess = request.getRequestDispatcher("/createsuccess.html");
			postCreateSuccess.forward(request, response);
		
		} catch (Exception e) {
			context.log(e.toString());
		}
		
		
	}
}
