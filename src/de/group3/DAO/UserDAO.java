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

	private static Connection conn = null;
	private static PreparedStatement pstmt = null;
	private static ResultSet rs = null;

	/**
	 * Validate the input credentials and return user object if exists.
	 * 
	 * @param username user name
	 * @param password password
	 * 
	 * @return User: entity object if user exists.
	 * */
	public static User validatePassword(String username, String password) {
		User user = null;
		try {
			String preparedSql = "SELECT * from user where username = ? and password = ?";
			Object[] parameters = { username, password };
			UserDAO.rs = BaseDAO.executeSQL(preparedSql, parameters);
			if (UserDAO.rs != null) {
				while (UserDAO.rs.next()) {
					user = new User();
					user.setUserId((Integer.getInteger(UserDAO.rs.getString("user_id"))));
					user.setUsername(UserDAO.rs.getString("username"));
					user.setPassword(UserDAO.rs.getString("password"));
					user.setUserId(Integer.getInteger(UserDAO.rs.getString("status")));
				}
			}
			//TODO also we have to fill its favorite recipes and ownRecipes Lists to make a full user.
			// which will involve code in RecipeDAO.
		} catch (Exception e) {
			e.printStackTrace();
		}finally {	//finally close and release resources.
            try {
            	BaseDAO.closeAll(UserDAO.conn, UserDAO.pstmt, UserDAO.rs);
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
		User user = UserDAO.validatePassword("admin", "8106417f482b5b3a30a433f4a31704bf");
		System.out.println(user);
	}

}
