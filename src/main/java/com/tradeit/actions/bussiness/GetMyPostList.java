package com.tradeit.actions.bussiness;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.apache.commons.lang3.StringEscapeUtils;

import com.tradeit.utility.database.PostOperate;

@WebServlet(name="getmypostlist",
	urlPatterns={"/getmypostlist.do"})
public class GetMyPostList extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		doGet(request, response);
	}
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		int pageSize = 15;
		int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
		
		/* get current session */
		HttpSession session = request.getSession();
		
		/* get user id */
		String username = (String)session.getAttribute("username");
				
		ArrayList<PostInfo> infoList = null;
		try {
			infoList = PostOperate.getMyPostList(pageSize, pageIndex, username);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONArray list = new JSONArray();
		for(int i = 0; i < infoList.size(); i++) {
			JSONObject item = new JSONObject();
			item.put("postid", new Integer(infoList.get(i).postid));
			item.put("condition", new Integer(infoList.get(i).condition));
			item.put("title", StringEscapeUtils.escapeHtml4(infoList.get(i).title));
			item.put("price", new Integer(infoList.get(i).price));
			item.put("descrip", StringEscapeUtils.escapeHtml4(infoList.get(i).description));
			item.put("userid", StringEscapeUtils.escapeHtml4(infoList.get(i).userid));
			item.put("imageid1", infoList.get(i).imageid1);
			item.put("imageid2", infoList.get(i).imageid2);
			item.put("imageid3", infoList.get(i).imageid3);
			list.add(item);
		}
		PrintWriter out = response.getWriter();
		out.println(list.toJSONString());
	}
}
