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

import com.tradeit.utility.database.PostOperate;

@WebServlet(name="searchpost",
	urlPatterns={"/searchpost.do"})
public class SearchPost extends HttpServlet {
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		int pageSize = 15;
		String keyword = request.getParameter("keyword");
				
		ArrayList<PostInfo> infoList = null;
		try {
			infoList = PostOperate.searchPost(keyword);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONArray list = new JSONArray();
		for(int i = 0; i < Integer.min(14, infoList.size()); i++) {
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
