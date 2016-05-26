package com.tradeit.utility.database;

import java.sql.*;
import java.util.*;

import com.tradeit.actions.bussiness.PostInfo;

public class PostOperate {

	/* Database query strings: Insert a post into database */
	private static String SQLInsertPostInfo = "INSERT INTO post_info (title, price, cond, descrip, userid, imageid) VALUES (?, ?, ?, ?, ?, ?)";
	private static String SQLGetPostList = "SELECT * FROM post_info ORDER BY postid LIMIT ? offset ?";
	
	public static void insertPostInfo(int condition, String title, int price, String description, String userid, String curTimeMillis) throws Exception{
		Connection conn = DatabaseConnector.getConn();
		try {
			PreparedStatement StmtInsertPostInfo = conn.prepareStatement(SQLInsertPostInfo);
		
			StmtInsertPostInfo.setString(1, title);
			StmtInsertPostInfo.setInt(2, price);
			StmtInsertPostInfo.setInt(3, condition);
			StmtInsertPostInfo.setString(4, description); 
			StmtInsertPostInfo.setString(5, userid);
			StmtInsertPostInfo.setString(6, curTimeMillis);

			DatabaseConnector.executeQuery(conn, StmtInsertPostInfo);
		} finally {
			if(conn != null) {
				try {conn.close();
				} catch(SQLException sqlex) {}
				conn = null;
			}
		}
	}
	
	public static ArrayList<PostInfo> getPostList(int pageSize, int pageIndex) throws Exception {
		Connection conn = DatabaseConnector.getConn();
		PreparedStatement StmtGetPostList = null;
		ArrayList<PostInfo> result = new ArrayList<PostInfo>();
		try {
			StmtGetPostList = conn.prepareStatement(SQLGetPostList);
			
			StmtGetPostList.setInt(1, pageSize);
			StmtGetPostList.setInt(2, pageSize*(pageIndex-1));
			
			ResultSet rs = StmtGetPostList.executeQuery();
			while(rs.next()) {
				PostInfo item = new PostInfo();
				item.postid = rs.getInt("postid");
				item.condition = rs.getInt("cond");
				item.description = rs.getString("descrip");
				item.title = rs.getString("title");
				item.price = rs.getInt("price");
				item.imageid = rs.getString("imageid");
				result.add(item);
			}
			StmtGetPostList.close();
			conn.close();
			return result;
		} finally {
			if(StmtGetPostList != null) {
				StmtGetPostList.close();	
			}
			if(conn != null) {
				conn.close();
			}
		}
	}

}
