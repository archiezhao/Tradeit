package com.tradeit.utility.database;

import java.sql.*;

public class PostOperate {

	/* Database query strings: Insert a post into database */
	private static String SQLInsertPostInfo = "insert into post_info (title, price, cond, descrip, userid, imagenum) values (?, ?, ?, ?, ?, ?)";

	public static void insertPostInfo(int condition, String title, int price, String description, String userid, String curTimeMillis) throws Exception{
		Connection conn = DatabaseConnector.getConn();
	
		PreparedStatement StmtInsertPostInfo = conn.prepareStatement(SQLInsertPostInfo);
	
		StmtInsertPostInfo.setString(1, title);
		StmtInsertPostInfo.setInt(2, price);
		StmtInsertPostInfo.setInt(3, condition);
		StmtInsertPostInfo.setString(4, description); 
		StmtInsertPostInfo.setString(5, userid);
		StmtInsertPostInfo.setString(6, curTimeMillis);

		DatabaseConnector.executeQuery(conn, StmtInsertPostInfo);
		
	}

	
}
