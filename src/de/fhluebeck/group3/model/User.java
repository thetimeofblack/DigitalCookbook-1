package de.fhluebeck.group3.model;

import java.util.List;

import de.fhluebeck.group3.util.EncryptUtil;

/**
 * The User is mapped with User table in DataBase.
 * 
 * @author Yichen.Hua on 2018/05/13.
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
	 * recipes owned by the user.
	 */
	private List<Recipe> ownRecipes;

	/**
	 * recipes favorite to the user.
	 */
	private List<Recipe> favoriteRecipes;

	/** ==============Constructors============== */
	/**
	 * Default constructor.
	 */
	public User() {
		super();
	}

	/**
	 * Constructor with necessary(create) attributes, with password automatically
	 * encrypted.
	 * 
	 * @param username
	 *            userName of the user.
	 * 
	 * @param password
	 *            password of the user.
	 * 
	 */
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = EncryptUtil.MD5(password);
		this.status = 1;
	}

	/**
	 * Constructor with all attributes, with password automatically encrypted.
	 * 
	 * @param userId
	 *            ID of the user.
	 * 
	 * @param username
	 *            userName of the user.
	 * 
	 * @param password
	 *            password of the user.
	 * 
	 * @param status
	 *            status of the user.
	 * 
	 * @param ownRecipes
	 *            ownRecipes of the user.
	 * 
	 * @param favoriteRecipes
	 *            favoriteRecipes of the user.
	 * 
	 */
	public User(Integer userId, String username, String password, Integer status, List<Recipe> ownRecipes,
			List<Recipe> favoriteRecipes) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.status = status;
		this.ownRecipes = ownRecipes;
		this.favoriteRecipes = favoriteRecipes;
	}

	/** ==============Getters and setters.============== */

	/**
	 * @return userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            id of the user.
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            name of the user.
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            password of the user.
	 */
	public void setPassword(String password) {
		this.password = EncryptUtil.MD5(password);
	}

	/**
	 * @return status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            status of the user.
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return ownRecipes
	 */
	public List<Recipe> getOwnRecipes() {
		return ownRecipes;
	}

	/**
	 * @param ownRecipes
	 *            ownRecipes of the user.
	 */
	public void setOwnRecipes(List<Recipe> ownRecipes) {
		this.ownRecipes = ownRecipes;
	}

	/**
	 * @return favoriteRecipes
	 */
	public List<Recipe> getFavoriteRecipes() {
		return favoriteRecipes;
	}

	/**
	 * @param favoriteRecipes
	 *            favoriteRecipes of the user.
	 */
	public void setFavoriteRecipes(List<Recipe> favoriteRecipes) {
		this.favoriteRecipes = favoriteRecipes;
	}

	/**
	 * Override toString method, print basic information of a user.
	 */
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", status=" + status
				+ ", ownRecipes=" + ownRecipes + ", favoriteRecipes=" + favoriteRecipes + "]";
	}

}
