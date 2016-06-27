package com.tradeit.actions.bussiness;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.json.simple.*;

import com.tradeit.utility.database.PostOperate;

import java.io.*;
import java.util.ArrayList;

@WebServlet(name="getpostlist",
	urlPatterns={"/getpostlist.do"})
public class GetPostList extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		doGet(request, response);
		
	}
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		int pageSize = 15;
		int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
		ArrayList<PostInfo> infoList = null;
		try {
			infoList = PostOperate.getPostList(pageSize, pageIndex);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONArray list = new JSONArray();
		for(int i = 0; i < infoList.size(); i++) {
			JSONObject item = new JSONObject();
			item.put("postid", new Integer(infoList.get(i).postid));
			item.put("condition", new Integer(infoList.get(i).condition));
			item.put("title", infoList.get(i).title);
			item.put("price", new Integer(infoList.get(i).price));
			item.put("descrip", infoList.get(i).description);
			item.put("userid", infoList.get(i).userid);
			item.put("imageid1", infoList.get(i).imageid1);
			item.put("imageid2", infoList.get(i).imageid2);
			item.put("imageid3", infoList.get(i).imageid3);
			list.add(item);
		}
		PrintWriter out = response.getWriter();
		out.println(list.toJSONString());
	}
}
