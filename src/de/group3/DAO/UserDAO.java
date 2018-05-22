package de.group3.DAO;

import java.sql.*;
import de.group3.model.User;

/**
 * UserDAO is major responsible for Data Access in User table, functions like
 * validate user, findUserById is provided.
 * 
 * @author Yichen.Hua on 2018/05/22.
 */
public final class UserDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	/**
	 * Validate the input credentials and return user object if exists.
	 * 
	 * @param username user name
	 * @param password password
	 * 
	 * @return User: entity object if user exists.
	 * */
	public User validatePassword(String username, String password) {
		User user = null;
		try {
			String preparedSql = "SELECT * from user where username = ? and password = ?";
			Object[] parameters = { username, password };
			this.rs = BaseDAO.executeSQL(preparedSql, parameters);
			if (this.rs != null) {
				while (this.rs.next()) {
					user = new User();
					user.setUserId((Integer.getInteger(this.rs.getString("user_id"))));
					user.setUsername(this.rs.getString("username"));
					user.setPassword(this.rs.getString("password"));
					user.setUserId(Integer.getInteger(this.rs.getString("status")));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {	//finally close and release resources.
            try {
            	BaseDAO.closeAll(conn, pstmt, null);
            } catch (SQLException e) {    
                e.printStackTrace();
            }
        }
		return user;
	}

	/**
	 * Unit test
	 * */
	public static void main(String[] args) {
		UserDAO userDAO = new UserDAO();
		User user = userDAO.validatePassword("admin", "8106417f482b5b3a30a433f4a31704bf");
		System.out.println(user);
	}

}
