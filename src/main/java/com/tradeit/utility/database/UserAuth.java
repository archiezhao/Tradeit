package com.tradeit.utility.database;

import java.sql.*;
import java.security.*;
import java.util.Arrays;

public class UserAuth {

	/* Database query strings: Insert a username and hashed password into database */
	private static String SQLInsertUserPass = "insert into user_authentication (user_id, pass_hash) values (?, ?)";
	private static String SQLCheckUserExist = "select * from user_authentication where user_id = ?";
	private static String SQLCheckPassCorrect = "select * from user_authentication where user_id = ?";

	public static void insertUserPass(String username, String pass) throws Exception{
		Connection conn = DatabaseConnector.getConn();
	
		PreparedStatement StmtInsertUserPass = conn.prepareStatement(SQLInsertUserPass);

		byte[] bytesOfPass = pass.getBytes("UTF-8");
		MessageDigest sha = MessageDigest.getInstance("SHA-256");
		byte[] passDigest = sha.digest(bytesOfPass);	
	
		StmtInsertUserPass.setString(1, username);
		StmtInsertUserPass.setBytes(2, passDigest); 

		DatabaseConnector.executeQuery(conn, StmtInsertUserPass);
		
	}

	public static boolean checkUserExist(String username) throws Exception {
		boolean isExist = true;
		Connection conn = DatabaseConnector.getConn();
		
		PreparedStatement StmtCheckUserExist = conn.prepareStatement(SQLCheckUserExist);
		StmtCheckUserExist.setString(1, username);
	
		ResultSet rs = StmtCheckUserExist.executeQuery();

		/* check if result set is empty or not */
		if(rs.next())
			isExist = true;
		else
			isExist = false;

		/* close connection and statement */
		try {
			StmtCheckUserExist.close();
			StmtCheckUserExist = null;
			
			conn.close();
			conn = null;
		} finally {
			if(StmtCheckUserExist != null) {
				try {StmtCheckUserExist.close();
				} catch(SQLException sqlex) {}
				StmtCheckUserExist = null;
			}
			if(conn != null) {
				try {conn.close();
				} catch(SQLException sqlex) {}
				conn = null;
			}			
		}
	
		return isExist;		
	}

	public static boolean checkPassCorrect(String username, String pass) throws Exception {
		boolean isCorrect = false;
		Connection conn = DatabaseConnector.getConn();
		
		PreparedStatement StmtCheckPassCorrect = conn.prepareStatement(SQLCheckPassCorrect);
		StmtCheckPassCorrect.setString(1, username);
	
		ResultSet rs = StmtCheckPassCorrect.executeQuery();

		/* check if the pass match */
		if(rs.next()) {
			byte[] storedPassDigest = rs.getBytes("pass_hash");
			
			byte[] bytesOfPass = pass.getBytes("UTF-8");
			MessageDigest sha = MessageDigest.getInstance("SHA-256");
			byte[] inputPassDigest = sha.digest(bytesOfPass);

			isCorrect = Arrays.equals(storedPassDigest, inputPassDigest);
		
		}
		else
			isCorrect = false;

		/* close connection and statement */
		try {
			StmtCheckPassCorrect.close();
			StmtCheckPassCorrect = null;
			
			conn.close();
			conn = null;
		} finally {
			if(StmtCheckPassCorrect != null) {
				try {StmtCheckPassCorrect.close();
				} catch(SQLException sqlex) {}
				StmtCheckPassCorrect = null;
			}
			if(conn != null) {
				try {conn.close();
				} catch(SQLException sqlex) {}
				conn = null;
			}			
		}
	
		return isCorrect;		
	}
	
}
