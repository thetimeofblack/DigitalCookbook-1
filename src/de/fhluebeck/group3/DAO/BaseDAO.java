package de.fhluebeck.group3.DAO;

import java.sql.*;

/**
 * BaseDAO as the Factory Model. This class is mainly responsible for MYSQL
 * database connection, which sets the connection parameters, load the driver at
 * the beginning of the system and provides functions for acquiring and closing
 * DB connections.
 * 
 * @author Yichen.Hua on 2018/05/22.
 */
public final class BaseDAO {

	private static Connection conn = null;
	private static PreparedStatement pstmt = null;
	private static ResultSet rs = null;

	/**
	 * Basic attributes for database.
	 */
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/cookbook_group3?characterEncoding=utf-8&useSSL=false";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";

	/**
	 * Load the driver for database connection at the beginning of the system.
	 */
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
	 * @throws SQLException
	 *             when connection is not available.
	 */
	public static Connection getConnection() throws SQLException {
//		if(conn != null && !conn.isClosed()) {
//			return conn;
//		}else {
			return DriverManager.getConnection(URL, USERNAME, PASSWORD);
//		}
	}

	/**
	 * Provider the external classes with the close function of DB connections
	 * resources.
	 * 
	 * @param conn
	 *            DB Connection.
	 * @param stmt
	 *            Statement.
	 * @param rs
	 *            ResultSet.
	 * 
	 * @throws SQLException
	 *             when SQLException occurs.
	 */
	public static void closeAll(Connection conn, Statement stmt, ResultSet rs) throws SQLException {
		if (rs != null && !rs.isClosed()) {
			rs.close();
		}
		if (stmt != null && !stmt.isClosed()) {
			stmt.close();
		}
		if (conn != null && !conn.isClosed()) {
			conn.close();
		}
	}

	/**
	 * Fill in the wild-cards with attribute in the Object[] and Execute SQL query,
	 * returns the result set. Note that you do have to close the resources by
	 * calling our closeAll function.
	 * 
	 * @param preparedSql:
	 *            raw SQL statements with wild-cards.
	 * @param param:
	 *            parameters to fill in the wild-cards.
	 * 
	 * @return ResultSet: SQL querying results.
	 * 
	 * @throws ClassNotFoundException:
	 *             when the driver class is not found.
	 */
	public static ResultSet executeQuery(String preparedSql, Object[] param) throws ClassNotFoundException {
		/* deal with and execute SQL */
		try {
			conn = getConnection(); // get database link from this class
			pstmt = conn.prepareStatement(preparedSql); // get PreparedStatement object
			if (param != null && param.length > 0) {
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

	/**
	 * Fill in the wild-cards with attribute in the Object[] and Execute SQL query,
	 * returns a boolean attribute. Note that you do not have to close the resources
	 * since they are automatically closed by us.
	 * 
	 * @param preparedSql:
	 *            raw SQL statements with wild-cards.
	 * @param param:
	 *            parameters to fill in the wild-cards.
	 * 
	 * @return boolean - flag that shows whether sql is executed successfully.
	 * 
	 * @throws ClassNotFoundException:
	 *             when the driver class is not found.
	 */
	public static boolean executeSql(String preparedSql, Object[] param) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		/* deal with and execute SQL */
		try {
			conn = getConnection(); // get database link from this class
			pstmt = conn.prepareStatement(preparedSql); // get PreparedStatement object
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setObject(i + 1, param[i]); // set parameters for prepared statement
				}
			}
			pstmt.execute(); // execute the SQL expression
			flag = true;
		} catch (SQLException e) {
			flag = false;
			e.printStackTrace(); // handle SQLException
		} finally { // finally close and release resources.
			try {
				BaseDAO.closeAll(conn, pstmt, null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return flag;
	}

	/**
	 * @return the conn
	 */
	public static Connection getConn() {
		return conn;
	}

	/**
	 * @param conn
	 *            the conn to set
	 */
	public static void setConn(Connection conn) {
		BaseDAO.conn = conn;
	}

	/**
	 * @return the pstmt
	 */
	public static PreparedStatement getPstmt() {
		return pstmt;
	}

	/**
	 * @param pstmt
	 *            the pstmt to set
	 */
	public static void setPstmt(PreparedStatement pstmt) {
		BaseDAO.pstmt = pstmt;
	}

	/**
	 * @return the rs
	 */
	public static ResultSet getRs() {
		return rs;
	}

	/**
	 * @param rs
	 *            the rs to set
	 */
	public static void setRs(ResultSet rs) {
		BaseDAO.rs = rs;
	}
	

}
