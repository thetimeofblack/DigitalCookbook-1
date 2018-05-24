package de.fhluebeck.group3.DAO;

import java.sql.*;

/**
 * BaseDAO as the Factory Model. This class is mainly responsible for MYSQL database connection,
 * which sets the connection parameters, load the driver at the beginning of the system and provides 
 * functions for acquiring and closing DB connections.  
 * 
 * @author Yichen.Hua on 2018/05/22.
 */
public final class BaseDAO {

	/**
	 * Basic attributes for database.
	 * */
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/cookbook_group3?characterEncoding=utf-8";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";

	/**
	 * Load the driver for database connection at the beginning of the system.
	 * */
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Provider the external classes with the valid DB connections.
	 * 
	 * @return connection valid database connection.
	 * @throws SQLException when connection is not available.
	 * */
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}

	/**
	 * Provider the external classes with the close function of DB connections resources.
	 * 
	 * @param conn DB Connection.
	 * @param stmt Statement.
	 * @param rs ResultSet. 
	 * 
	 * @throws SQLException when SQLException occurs.
	 * */
	public static void closeAll(Connection conn, Statement stmt, ResultSet rs) throws SQLException {
		if (rs != null) {
			rs.close();
		}
		if (stmt != null) {
			stmt.close();
		}
		if (conn != null) {
			conn.close();
		}
	}
	
	/**
	 * Fill in the wild-cards with attribute in the Object[] and Execute SQL query, returns the result set.
	 * 
	 * @param preparedSql: raw SQL statements with wild-cards.
	 * @param param: parameters to fill in the wild-cards.
	 * 
	 * @return ResultSet: SQL querying results.
	 * 
	 * @throws ClassNotFoundException: when the driver class is not found.
	 * */
	public static ResultSet executeSQL(String preparedSql, Object[] param) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        /* deal with and execute SQL */
        try {
            conn = getConnection(); // get database link from this class
            pstmt = conn.prepareStatement(preparedSql); // get PreparedStatement object
            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    pstmt.setObject(i + 1, param[i]); // set parameters for prepared statement
                }
            }
        rs = pstmt.executeQuery(); // execute the SQL expression
        } catch (SQLException e) {
            e.printStackTrace(); // handle SQLException
        } 
        
        return rs;
    }
	

}
