package com.tradeit.utility.database;

import java.sql.*;
import java.util.*;

import com.tradeit.actions.bussiness.PostInfo;
import com.tradeit.utility.image.ImageDelete;

public class PostOperate {

	/* Database query strings: Insert a post into database */
	private static String SQLInsertPostInfo = "INSERT INTO post_info (title, price, cond, descrip, userid, imageref1, imageref2, imageref3) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static String SQLGetPostList = "SELECT * FROM post_info ORDER BY postid DESC LIMIT ? offset ? ";
	private static String SQLGetMyPostList = "SELECT * FROM post_info WHERE userid = ? ORDER BY postid DESC LIMIT ? offset ?";
	private static String SQLSearchPost = "SELECT * FROM post_info WHERE title LIKE ?";
	private static String SQLDeletePost = "DELETE FROM post_info WHERE postid = ? AND userid = ?";
	private static String SQLGetPostInfo = "SELECT * FROM post_info WHERE postid = ?";
	
	public static void insertPostInfo(int condition, String title, int price, String description, String userid, String imageref1, String imageref2, String imageref3) throws Exception{
		Connection conn = DatabaseConnector.getConn();
		try {
			PreparedStatement StmtInsertPostInfo = conn.prepareStatement(SQLInsertPostInfo);
		
			StmtInsertPostInfo.setString(1, title);
			StmtInsertPostInfo.setInt(2, price);
			StmtInsertPostInfo.setInt(3, condition);
			StmtInsertPostInfo.setString(4, description); 
			StmtInsertPostInfo.setString(5, userid);
			StmtInsertPostInfo.setString(6, imageref1);
			StmtInsertPostInfo.setString(7, imageref2);
			StmtInsertPostInfo.setString(8, imageref3);

			DatabaseConnector.executeQuery(conn, StmtInsertPostInfo);
		} finally {
			if(conn != null) {
				try {conn.close();
				} catch(SQLException sqlex) {}
				conn = null;
			}
		}
	}
	
	public static PostInfo getPostInfo(int postid) throws Exception {
		Connection conn = DatabaseConnector.getConn();
		PreparedStatement StmtGetPostInfo = null;
		PostInfo result = new PostInfo();
		try {
			StmtGetPostInfo = conn.prepareStatement(SQLGetPostInfo);
			
			StmtGetPostInfo.setInt(1, postid);
			
			ResultSet rs = StmtGetPostInfo.executeQuery();
			while(rs.next()) {
				result.postid = rs.getInt("postid");
				result.condition = rs.getInt("cond");
				result.description = rs.getString("descrip");
				result.title = rs.getString("title");
				result.price = rs.getInt("price");
				result.imageid1 = rs.getString("imageref1");
				result.imageid2 = rs.getString("imageref2");
				result.imageid3 = rs.getString("imageref3");
			}
			StmtGetPostInfo.close();
			conn.close();
			return result;
		} finally {
			if(StmtGetPostInfo != null) {
				StmtGetPostInfo.close();	
			}
			if(conn != null) {
				conn.close();
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
				item.imageid1 = rs.getString("imageref1");
				item.imageid2 = rs.getString("imageref2");
				item.imageid3 = rs.getString("imageref3");
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
	
	public static ArrayList<PostInfo> getMyPostList(int pageSize, int pageIndex, String username) throws Exception {
		Connection conn = DatabaseConnector.getConn();
		PreparedStatement StmtGetPostList = null;
		ArrayList<PostInfo> result = new ArrayList<PostInfo>();
		try {
			StmtGetPostList = conn.prepareStatement(SQLGetMyPostList);
			
			StmtGetPostList.setString(1, username);
			StmtGetPostList.setInt(2, pageSize);
			StmtGetPostList.setInt(3, pageSize*(pageIndex-1));
			
			ResultSet rs = StmtGetPostList.executeQuery();
			while(rs.next()) {
				PostInfo item = new PostInfo();
				item.postid = rs.getInt("postid");
				item.condition = rs.getInt("cond");
				item.description = rs.getString("descrip");
				item.title = rs.getString("title");
				item.price = rs.getInt("price");
				item.imageid1 = rs.getString("imageref1");
				item.imageid2 = rs.getString("imageref2");
				item.imageid3 = rs.getString("imageref3");
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
	public static ArrayList<PostInfo> searchPost(String keyword) throws Exception {
		String searchPattern = "%" + keyword + "%";
		Connection conn = DatabaseConnector.getConn();
		PreparedStatement StmtSearchPost = null;
		ArrayList<PostInfo> result = new ArrayList<PostInfo>();
		try {
			StmtSearchPost = conn.prepareStatement(SQLSearchPost);
			
			StmtSearchPost.setString(1, searchPattern);
			
			ResultSet rs = StmtSearchPost.executeQuery();
			while(rs.next()) {
				PostInfo item = new PostInfo();
				item.postid = rs.getInt("postid");
				item.condition = rs.getInt("cond");
				item.description = rs.getString("descrip");
				item.title = rs.getString("title");
				item.price = rs.getInt("price");
				item.imageid1 = rs.getString("imageref1");
				item.imageid2 = rs.getString("imageref2");
				item.imageid3 = rs.getString("imageref3");
				result.add(item);
			}
			StmtSearchPost.close();
			conn.close();
			return result;
		} finally {
			if(StmtSearchPost != null) {
				StmtSearchPost.close();	
			}
			if(conn != null) {
				conn.close();
			}
		}
	}
	
	public static int deletePost(int postid, String userid) throws Exception{
		PostInfo targetPost = PostOperate.getPostInfo(postid);
		Connection conn = DatabaseConnector.getConn();
		try {	
			PreparedStatement StmtDeletePost = conn.prepareStatement(SQLDeletePost);
			StmtDeletePost.setInt(1, postid);
			StmtDeletePost.setString(2, userid);
			
			int result = StmtDeletePost.executeUpdate();
			if(result > 0)
				ImageDelete.processRequest(targetPost);
			return result;
		} finally {
			if(conn != null) {
				try {conn.close();
				} catch(SQLException sqlex) {}
				conn = null;
			}
		}
	}

}
