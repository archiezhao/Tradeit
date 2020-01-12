package com.tradeit.utility.database;

import java.sql.*;
import java.lang.Exception;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DatabaseConnector {
	
	private static InitialContext ctx;
	private static DataSource ds;

	static{
		try {
			/* Create a JNDI Initial context to be able to lookup the DataSource, expensive operation */		
			ctx = new InitialContext();
			/* Lookup the DataSource, which will be backed by a pool that the application server provides.*/
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/tradeit");
			Class.forName("com.mysql.jdbc.Driver");	
		} catch(Exception e) {
			
		}
		
	}

	public static Connection getConn() throws Exception {
		Connection conn = null;
		try {
			conn = ds.getConnection();
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
		return conn;
	}
	
	public static void executeQuery (Connection conn, PreparedStatement stmt) throws Exception{
		
		try {
			stmt.executeUpdate();

			stmt.close();
			stmt = null;
			
			conn.close();
			conn = null;
		} finally {
			if(stmt != null) {
				try {stmt.close();
				} catch(SQLException sqlex) {}
				stmt = null;
			}
			if(conn != null) {
				try {conn.close();
				} catch(SQLException sqlex) {}
				conn = null;
			}			
		}
	}
}
