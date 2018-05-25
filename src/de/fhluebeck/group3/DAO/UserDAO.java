package de.fhluebeck.group3.DAO;

import java.sql.*;

import de.fhluebeck.group3.model.User;

/**
 * UserDAO is major responsible for Data Access in User table, functions like
 * validate user, findUserById are provided.
 * 
 * @author Yichen.Hua on 2018/05/22.
 */
public final class UserDAO {

//	private static Connection conn = null;
//	private static PreparedStatement pstmt = null;
//	private static ResultSet rs = null;

	/**
	 * Validate the input credentials and return user object if exists.
	 * 
	 * @param username
	 *            user name
	 * @param password
	 *            password
	 * 
	 * @return User: entity object if user exists.
	 */
	public static User validatePassword(String username, String password) {
		User user = null;
		ResultSet resultSet = null;
		try {
			String preparedSql = "SELECT * FROM user WHERE username = ? and password = ?";
			Object[] parameters = { username, password };
			resultSet = BaseDAO.executeQuery(preparedSql, parameters);
			if (resultSet != null) {
				while (resultSet.next()) {
					user = new User();
					user.setUserId((Integer.getInteger(resultSet.getString("user_id"))));
					user.setUsername(resultSet.getString("username"));
					user.setPassword(resultSet.getString("password"));
					user.setUserId(Integer.getInteger(resultSet.getString("status")));
				}
			}
			// TODO also we have to fill its favorite recipes and ownRecipes Lists to make a full user.
			// which will involve code in RecipeDAO.
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // finally close and release resources.
			try {
				BaseDAO.closeAll(BaseDAO.getConn(),BaseDAO.getPstmt(),BaseDAO.getRs());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	/**
	 * Validate the input credentials and return user object if exists.
	 * 
	 * @param username
	 *            user name
	 * @param password
	 *            password
	 * 
	 * @return User: entity object if user exists.
	 */
	public static void deleteUser(Integer userId) {
		try {
			String preparedSql = "UPDATE `user` SET `status` = 0 WHERE `user_id` = ?";
			Object[] parameters = { userId };
			BaseDAO.executeSql(preparedSql, parameters);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
	}

	/*
	 * Unit test
	 */
	public static void main(String[] args) {

//		 User user = UserDAO.validatePassword("admin","8106417f482b5b3a30a433f4a31704bf");
//		 System.out.println(user);	//success

//		deleteUser(2); //success
	}

}
