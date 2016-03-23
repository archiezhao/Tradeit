package com.tradeit.utility.database;

import java.sql.*;

public class UserAuth {

	/* Database query strings: Insert a username and hashed password into database */
	private static String InsertUserPass = "insert into user_authentication (user_id, pass_hash) values (?, ?)";

	public static void insertUserPass(String username, String hashpass) throws Exception{
		Connection conn = DatabaseConnector.getConn();
	
		PreparedStatement insertUserPassStmt = conn.prepareStatement(InsertUserPass);
		insertUserPassStmt.setString(1, username);
		insertUserPassStmt.setString(2, hashpass); 

		DatabaseConnector.executeQuery(conn, insertUserPassStmt);
		
	}


}
