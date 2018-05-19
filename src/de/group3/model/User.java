package de.group3.model;

import java.util.List;

import de.group3.util.EncryptUtil;

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
	 * recipes owned by the user.
	 * */
	private List<Recipe> ownRecipes;
	
	/**
	 * recipes favorite to the user.
	 * */
	private List<Recipe> favoriteRecipes;
	
	/** ==============Constructors============== */
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

	/** ==============Getters and setters.============== */
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
		this.password = EncryptUtil.MD5(password);
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<Recipe> getOwnRecipes() {
		return ownRecipes;
	}

	public void setOwnRecipes(List<Recipe> ownRecipes) {
		this.ownRecipes = ownRecipes;
	}

	public List<Recipe> getFavoriteRecipes() {
		return favoriteRecipes;
	}

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
