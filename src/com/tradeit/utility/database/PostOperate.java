package com.tradeit.utility.database;

import java.sql.*;
import java.security.*;
import java.util.Arrays;

public class PostOperate {

	/* Database query strings: Insert a post into database */
	private static String SQLInsertPostInfo = "insert into post_info (cond, descrip, userid) values (?, ?, ?)";

	public static void insertPostInfo(int condition, String description, String userid) throws Exception{
		Connection conn = DatabaseConnector.getConn();
	
		PreparedStatement StmtInsertPostInfo = conn.prepareStatement(SQLInsertPostInfo);
	
		StmtInsertPostInfo.setInt(1, condition);
		StmtInsertPostInfo.setString(2, description); 
		StmtInsertPostInfo.setString(3, userid);

		DatabaseConnector.executeQuery(conn, StmtInsertPostInfo);
		
	}

	
}
