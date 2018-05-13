package model;

import util.EncryptUtil;

/**
 * The User is mapped with User table in DB.
 * 
 * @author Eason.Hua on 2018/05/13.
 */
public final class User {

	/**
	 * user ID as primary key in DB.
	 */
	private Integer userId;

	/**
	 * userName -> unique in database
	 */
	private String username;

	/**
	 * encrypted password.
	 */
	private String password;

	/**
	 * Status of user: 1 for valid 0 for deleted.
	 */
	private Integer status;

	/**
	 * Default constructor.
	 */
	public User() {
		super();
	}

	/**
	 * constructor with all attributes, with password automatically encrypted.
	 */
	public User(Integer userId, String username, String password, Integer status) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = EncryptUtil.MD5(password);
		this.status = status;
	}

	/**
	 * Getters and Setters
	 * 
	 */
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * Override toString method, print basic information of a user.
	 */
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", status=" + status
				+ "]";
	}

}
