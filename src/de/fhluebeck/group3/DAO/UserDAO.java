package de.fhluebeck.group3.DAO;

import java.sql.*;

import de.fhluebeck.group3.model.User;
import de.fhluebeck.group3.util.EncryptUtil;

/**
 * UserDAO is major responsible for Data Access in User table, functions like
 * validate user, findUserById are provided.
 * 
 * @author Yichen.Hua on 2018/05/22.
 */
public final class UserDAO {

	// not necessary at the moment.
	// private static Connection conn = null;
	// private static PreparedStatement pstmt = null;
	// private static ResultSet rs = null;

	/**
	 * Validate the input credentials and return user object if exists.
	 * 
	 * @param username
	 *            user name
	 * @param password
	 *            password
	 * 
	 * @return User: entity object if user exists or null if user not exists.
	 */
	public static User validatePassword(String username, String password) {
		User user = null;
		ResultSet resultSet = null;

		if (username.equals("") || password.equals("")) {
			return user;
		}

		try {
			password = EncryptUtil.MD5(password);
			String preparedSql = "SELECT * FROM user WHERE username = ? AND password = ? AND status = 1";
			Object[] parameters = { username, password };
			resultSet = BaseDAO.executeQuery(preparedSql, parameters);
			if (resultSet != null && resultSet.isBeforeFirst()) { // ensure that there are some data in result set.
				while (resultSet.next()) {
					user = new User();
					user.setUserId((Integer.valueOf(resultSet.getString("user_id"))));
					user.setUsername(resultSet.getString("username"));
					user.setPassword(resultSet.getString("password"));
					user.setStatus(Integer.valueOf(resultSet.getString("status")));
				}
				// also we have to fill its favorite recipes and ownRecipes Lists to make a full
				// user.
				// user.setOwnRecipes(RecipeDAO.getRecipesByUser(user));
				// user.setFavoriteRecipes(RecipeDAO.getFavoritedRecipes(user));
			} else {
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally { // finally close and release resources.
			try {
				BaseDAO.closeAll(BaseDAO.getConn(), BaseDAO.getPstmt(), BaseDAO.getRs());
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
	 * @return whether the function is succeeded or not.
	 */
	public static boolean deleteUser(Integer userId) {
		boolean flag = false;
		try {
			String preparedSql = "UPDATE `user` SET `status` = 0 WHERE `user_id` = ?";
			Object[] parameters = { userId };
			flag = BaseDAO.executeSql(preparedSql, parameters);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * Validate the input credentials and return user object if exists.
	 * 
	 * @param user
	 *            the user information we want to insert.
	 * 
	 * @return User: whether the function is succeeded or not.
	 */
	public static boolean addUser(User user) {
		boolean flag = false;

		// If user has already existed.
		if (user.getUserId() != null) {
			return false;
		}
		try {
			String preparedSql = "INSERT INTO `user` (`username`, `password`, `status`) VALUES (?, ?, ?)";
			Object[] parameters = { user.getUsername(), user.getPassword(), user.getStatus() };
			flag = BaseDAO.executeSql(preparedSql, parameters);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * Unit test for UserDAO
	 * 
	 * @param args:
	 *            string from console input.
	 */
	public static void main(String[] args) {

		// User user = UserDAO.validatePassword("admin", "1234");
		// user = UserDAO.validatePassword("admin", "123");
		// System.out.println(user == null);
		// System.out.println(user); //success

		// deleteUser(2); //success

		// addUser(user);
		addUser(new User("test5", "456"));
	}

}
