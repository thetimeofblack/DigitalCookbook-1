package de.fhluebeck.group3.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sun.xml.internal.bind.v2.model.core.ID;

import de.fhluebeck.group3.model.Recipe;
import de.fhluebeck.group3.model.User;

/**
 * RecipeDAO is major responsible for Data Access in Recipe table, functions
 * like addRecipe, getRecipeByName, updateRecipe are provided.
 * 
 * @author Shan Jiaxiang on 2018/5/27.
 */
public final class RecipeDAO {

	/**
	 * Search for the recipes that owned by specific user.
	 * 
	 * @param user:
	 *            the current user that owns the recipes
	 * 
	 * @return recipes: a list of recipes owned by the user.
	 */
	public static List<Recipe> getRecipesByUser(User user) {
		List<Recipe> recipes = new ArrayList<Recipe>();

		return recipes;
	}

	/**
	 * This method is called after a new recipe is inserted into the DB to retrive
	 * the ID of the recipe;
	 */
	public static Integer getRecipeID(Recipe recipe) {
		Integer recipeID = 0;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = null;

		try {
			connection = BaseDAO.getConnection();
			// SHOW RECIPE
			String preparedSql = "SELECT id FROM `recipe` WHERE `status` = 1 AND `ownerUserid` = ? "
					+ "AND `recipeName` = ? AND `description` = ? AND `preparationTime` = ? AND `cookingTime` = ? "
					+ "AND `peopleAvailable` = ? AND `imagePath` = ?";
			pstmt = connection.prepareStatement(preparedSql);
			Object[] parameters = { recipe.getOwnerId(), recipe.getRecipeName(), recipe.getDescription(),
					recipe.getPreparationTime(), recipe.getCookingTime(), recipe.getAvailablePeople(),
					recipe.getImagePath() };
			resultSet = BaseDAO.executeQuery(pstmt, parameters);
			if (resultSet != null && resultSet.isBeforeFirst()) { // ensure that there are some data in result set.
				while (resultSet.next()) {

					recipeID = Integer.valueOf(resultSet.getString("id"));

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally { // finally close and release resources.
			try {
				BaseDAO.closeAll(null, pstmt, resultSet);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return recipeID;
	}

	/**
	 * 
	 * */
	public static boolean removeRecipeFromFavoriteList(Integer recipeId, Integer userId) {
		boolean flag = false;

		try {
			String preparedSql = "DELETE from `user-recipe-table` where userID = ? AND recipeID = ?";
			Object[] parameters = { userId, recipeId };
			flag = BaseDAO.executeSql(preparedSql, parameters);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// do not need to close resources here.

		return flag;
	}

	/**
	 * 
	 * */
	public static boolean addRecipeToFavoriteList(Integer recipeId, Integer userId) {
		boolean flag = false;

		try {
			String preparedSql = "INSERT INTO `user-recipe-table`(userID, recipeID, status) VALUES(?,?,1);";
			Object[] parameters = { userId, recipeId };
			flag = BaseDAO.executeSql(preparedSql, parameters);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// do not need to close resources here.

		return flag;
	}

	/**
	 * Search for all recipes.
	 * 
	 * @return recipes: a list of recipes.
	 */
	public static List<Recipe> getAllRecipes() {
		List<Recipe> recipes = new ArrayList<Recipe>();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = null;

		try {
			connection = BaseDAO.getConnection();
			String preparedSql = "SELECT * FROM recipe WHERE status = 1";
			pstmt = connection.prepareStatement(preparedSql);
			// resultSet = pstmt.executeQuery();
			resultSet = BaseDAO.executeQuery(pstmt, null);
			if (resultSet != null && resultSet.isBeforeFirst()) { // ensure that there are some data in result set.
				while (resultSet.next()) {
					Recipe recipe = new Recipe();
					recipe.setRecipeID(Integer.valueOf(resultSet.getString("id")));
					recipe.setRecipeName(resultSet.getString("recipeName"));
					recipe.setImagePath(resultSet.getString("imagePath"));
					recipe.setPreparationTime(Integer.valueOf(resultSet.getString("preparationTime")));
					recipe.setCookingTime(Integer.valueOf(resultSet.getString("cookingTime")));
					recipe.setAvailablePeople(Integer.valueOf(resultSet.getString("peopleAvailable")));
					recipe.setStatus(Integer.valueOf(resultSet.getString("status")));
					recipe.setDescription(resultSet.getString("description"));
					recipe.setOwnerId(Integer.valueOf(resultSet.getString("ownerUserid")));

					// fill the ingredients and steps..
					recipe.setSteps(StepDAO.searchStepByRecipeId(recipe.getRecipeID()));
					recipe.setIngredients(IngredientDAO.searchIngredientByRecipeId(recipe.getRecipeID()));

					recipes.add(recipe);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally { // finally close and release resources.
			try {
				BaseDAO.closeAll(null, pstmt, resultSet);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return recipes;
	}

	/**
	 * Search for the recipes that match the recipeName, which is regular
	 * expression.
	 * 
	 * @param recipeName:
	 *            the recipe name used to search for satisfied recipes.
	 * 
	 * @return recipes: a list of recipes that satisfied the regular expression
	 *         "*recipeName*"
	 * 
	 */
	public static List<Recipe> getRecipesByName(String recipeName) {
		List<Recipe> recipes = new ArrayList<Recipe>();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = null;

		try {
			connection = BaseDAO.getConnection();
			// GET KEYWORD FROM USER INPUT
			String searchName = "'%" + recipeName + "%'";
			// SHOW RECIPE
			String preparedSql = "SELECT * FROM recipe WHERE status = 1 AND recipeName LIKE " + searchName;
			pstmt = connection.prepareStatement(preparedSql);
			resultSet = BaseDAO.executeQuery(pstmt, null);
			if (resultSet != null && resultSet.isBeforeFirst()) { // ensure that there are some data in result set.
				while (resultSet.next()) {
					Recipe recipe = new Recipe();
					recipe.setRecipeID(Integer.valueOf(resultSet.getString("id")));
					recipe.setRecipeName(resultSet.getString("recipeName"));
					recipe.setImagePath(resultSet.getString("imagePath"));
					recipe.setPreparationTime(Integer.valueOf(resultSet.getString("preparationTime")));
					recipe.setCookingTime(Integer.valueOf(resultSet.getString("cookingTime")));
					recipe.setAvailablePeople(Integer.valueOf(resultSet.getString("peopleAvailable")));
					recipe.setStatus(Integer.valueOf(resultSet.getString("status")));
					recipe.setDescription(resultSet.getString("description"));
					recipe.setOwnerId(Integer.valueOf(resultSet.getString("ownerUserid")));

					// Fill the ingredients and steps..
					recipe.setSteps(StepDAO.searchStepByRecipeId(recipe.getRecipeID()));
					recipe.setIngredients(IngredientDAO.searchIngredientByRecipeId(recipe.getRecipeID()));

					recipes.add(recipe);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally { // finally close and release resources.
			try {
				BaseDAO.closeAll(null, pstmt, resultSet);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return recipes;
	}

	/**
	 * Search for the recipes with the ingredient, which is regular expression.
	 * 
	 * @param recipeName:
	 *            the recipe name used to search for satisfied recipes.
	 * 
	 * @return recipes: a list of recipes that satisfied the regular expression
	 *         "*recipeName*"
	 * 
	 */
	public static List<Recipe> getRecipesByIngredient(String ingredientName) {
		List<Recipe> recipes = new ArrayList<Recipe>();
		List<Integer> resultList = null;

		try {
			// First you get all the ingredients name by calling functions in the
			// ingredientDAO.

			resultList = IngredientDAO.searchRecipeIdByIngredientsName(ingredientName);

			// Second after you get all the List<Integer> recipeId from ingredientDAO, just
			// call function getRecipesByIds

			recipes = RecipeDAO.getRecipesByIds(resultList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return recipes;
	}

	/**
	 * Search for the recipes with the recipe IDs, which is a list. This method
	 * involves with batch search, which I get the reference
	 * from:https://blog.csdn.net/suifeng3051/article/details/24033237
	 * 
	 * @param recipeIds:
	 *            list of recipe IDs provided by the user.
	 * 
	 * @return recipes: a list of recipes that with the IDs.
	 * 
	 */
	public static List<Recipe> getRecipesByIds(List<Integer> recipeIds) {
		List<Recipe> recipes = new ArrayList<Recipe>();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = null;

		try {
			if (recipeIds != null && recipeIds.size() > 0) {
				connection = BaseDAO.getConnection();
				StringBuilder writer = new StringBuilder("SELECT * FROM recipe WHERE status = 1 AND id IN (");
				for (int i = 0; i < recipeIds.size(); i++) {
					if (i == recipeIds.size() - 1) {
						writer.append(recipeIds.get(i));
					} else {
						writer.append(recipeIds.get(i)).append(",");
					}
				}

				writer.append(")");
				pstmt = connection.prepareStatement(writer.toString());
				resultSet = BaseDAO.executeQuery(pstmt, null); // or whatever values you are

				if (resultSet != null && resultSet.isBeforeFirst()) { // ensure that there are some data in result set.
					while (resultSet.next()) {
						Recipe recipe = new Recipe();
						recipe.setRecipeID(Integer.valueOf(resultSet.getString("id")));
						recipe.setRecipeName(resultSet.getString("recipeName"));
						recipe.setImagePath(resultSet.getString("imagePath"));
						recipe.setPreparationTime(Integer.valueOf(resultSet.getString("preparationTime")));
						recipe.setCookingTime(Integer.valueOf(resultSet.getString("cookingTime")));
						recipe.setAvailablePeople(Integer.valueOf(resultSet.getString("peopleAvailable")));
						recipe.setStatus(Integer.valueOf(resultSet.getString("status")));
						recipe.setDescription(resultSet.getString("description"));
						recipe.setOwnerId(Integer.valueOf(resultSet.getString("ownerUserid")));

						// Fill the ingredients and steps..
						recipe.setSteps(StepDAO.searchStepByRecipeId(recipe.getRecipeID()));
						recipe.setIngredients(IngredientDAO.searchIngredientByRecipeId(recipe.getRecipeID()));

						recipes.add(recipe);
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // finally close and release resources.
			try {
				BaseDAO.closeAll(null, pstmt, resultSet);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return recipes;
	}

	/**
	 * Delete the recipe according to its ID, here we do not have to delete
	 * ingredients and steps owned by the recipe.
	 * 
	 * @param recipeIds:
	 *            recipe ID which the user wants to delete from DB.
	 * 
	 * @return flag: whether the function is succeeded or not.
	 * 
	 */
	public static boolean deleteRecipe(Integer recipeID) {
		boolean flag = false;

		try {
			String preparedSql = "UPDATE `recipe` SET `status` = 0 WHERE `id` = ?";
			Object[] parameters = { recipeID };
			flag = BaseDAO.executeSql(preparedSql, parameters);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// do not need to close resources here.
		return flag;
	}

	/**
	 * Update the recipe according to its ID, here we do have to do "batch-update"
	 * which means while update the information of recipe, we call the function from
	 * ingredientDAO and stepDAO to update corresponding ingredients and steps.
	 * 
	 * @param recipe:
	 *            new version of recipe which we want to update in the DB.
	 * 
	 * @return flag: whether the function is succeeded or not.
	 * 
	 */
	public static boolean updateRecipe(Recipe recipe) {
		boolean flag = false;
		try {
				String preparedSql = "UPDATE `recipe` SET" + " `ownerUserid`= ?, " + "`recipeName` = ?, " + "`description` = ?," + "`preparationTime` = ?," 
									+ "`cookingTime` = ?, " + "`peopleAvailable` = ?, " + "`imagePath` = ? " + "WHERE `id` = ?";
				Object[] parameters = {recipe.getOwnerId(), recipe.getRecipeName(), recipe.getDescription(),
						recipe.getPreparationTime(), recipe.getCookingTime(), recipe.getAvailablePeople(),
						recipe.getImagePath(),recipe.getRecipeID()};
				flag = BaseDAO.executeSql(preparedSql, parameters);
			} catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		return flag;
	}

	/**
	 * Add the recipe to the DB, here we do have to do "batch-insertion" which means
	 * while insert the basic information of recipe, we call the function from
	 * ingredientDAO and stepDAO to insert corresponding ingredients and steps.
	 * 
	 * @param recipe:
	 *            new version of recipe which we want to insert into the DB.
	 * 
	 * @return flag: whether the function is succeeded or not.
	 * 
	 */
	public static boolean addRecipe(Recipe recipe) {
		boolean flag = false;

		// Also calls the functions from IngredientDAO and StepDAO.
		// IngredientDAO.addBatchIngredients(recipe.getIngredients());
		// StepDAO.addBatchSteps(recipe.getSteps());
		// If user has already existed.
		if (recipe.getRecipeID() != null) {
			return false;
		}
		try {
			String preparedSql = "INSERT INTO `recipe` (`ownerUserid`, `recipeName`, `description`, `preparationTime`,"
					+ "`cookingTime`,`peopleAvailable`,`imagePath`,`status`) VALUES (?, ?, ?, ?, ?, ?, ?, 1)";
			Object[] parameters = { recipe.getOwnerId(), recipe.getRecipeName(), recipe.getDescription(),
					recipe.getPreparationTime(), recipe.getCookingTime(), recipe.getAvailablePeople(),
					recipe.getImagePath() };
			flag = BaseDAO.executeSql(preparedSql, parameters);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * Search for the recipes which the current user likes.
	 * 
	 * @param user:
	 *            specific user.
	 * 
	 * @return recipes: a list of recipes that the user favorites.
	 * 
	 */
	public static List<Recipe> getFavoritedRecipes(Integer userid) {
		List<Recipe> recipes = new ArrayList<Recipe>();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = null;

		try {
			connection = BaseDAO.getConnection();
			// SHOW RECIPE
			String preparedSql = "SELECT * FROM `recipe` WHERE `status` = 1 AND id IN("
					+ "SELECT DISTINCT(recipeID) FROM `user-recipe-table` WHERE userID = ? AND status = 1)";
			pstmt = connection.prepareStatement(preparedSql);
			Object[] parameters = { userid };
			resultSet = BaseDAO.executeQuery(pstmt, parameters);
			if (resultSet != null && resultSet.isBeforeFirst()) { // ensure that there are some data in result set.
				while (resultSet.next()) {
					Recipe recipe = new Recipe();
					recipe.setRecipeID(Integer.valueOf(resultSet.getString("id")));
					recipe.setRecipeName(resultSet.getString("recipeName"));
					recipe.setImagePath(resultSet.getString("imagePath"));
					recipe.setPreparationTime(Integer.valueOf(resultSet.getString("preparationTime")));
					recipe.setCookingTime(Integer.valueOf(resultSet.getString("cookingTime")));
					recipe.setAvailablePeople(Integer.valueOf(resultSet.getString("peopleAvailable")));
					recipe.setStatus(Integer.valueOf(resultSet.getString("status")));
					recipe.setDescription(resultSet.getString("description"));
					recipe.setOwnerId(Integer.valueOf(resultSet.getString("ownerUserid")));

					// Fill the ingredients and steps..
					recipe.setSteps(StepDAO.searchStepByRecipeId(recipe.getRecipeID()));
					recipe.setIngredients(IngredientDAO.searchIngredientByRecipeId(recipe.getRecipeID()));

					recipes.add(recipe);

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally { // finally close and release resources.
			try {
				BaseDAO.closeAll(null, pstmt, resultSet);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return recipes;
	}

	/**
	 * 
	 * */
	public static List<Recipe> getFavRecipeByIngredients(String ingredientName, Integer userId) {
		List<Recipe> recipes = new ArrayList<Recipe>();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = null;

		try {
			connection = BaseDAO.getConnection();
			// SHOW RECIPE
			String preparedSql = "SELECT * FROM `recipe` WHERE `status` = 1 AND id IN(SELECT DISTINCT(recipeID) FROM `user-recipe-table` WHERE userID = ? AND status = 1) "
					+ "AND id IN("
					+ "SELECT DISTINCT(recipeID) FROM ingredient WHERE ingredientName LIKE ? AND status = 1)";
			String wildCardIngredientName = "%" + ingredientName + "%";
			pstmt = connection.prepareStatement(preparedSql);
			Object[] parameters = { userId, wildCardIngredientName };
			resultSet = BaseDAO.executeQuery(pstmt, parameters);
			if (resultSet != null && resultSet.isBeforeFirst()) { // ensure that there are some data in result set.
				while (resultSet.next()) {
					Recipe recipe = new Recipe();
					recipe.setRecipeID(Integer.valueOf(resultSet.getString("id")));
					recipe.setRecipeName(resultSet.getString("recipeName"));
					recipe.setImagePath(resultSet.getString("imagePath"));
					recipe.setPreparationTime(Integer.valueOf(resultSet.getString("preparationTime")));
					recipe.setCookingTime(Integer.valueOf(resultSet.getString("cookingTime")));
					recipe.setAvailablePeople(Integer.valueOf(resultSet.getString("peopleAvailable")));
					recipe.setStatus(Integer.valueOf(resultSet.getString("status")));
					recipe.setDescription(resultSet.getString("description"));
					recipe.setOwnerId(Integer.valueOf(resultSet.getString("ownerUserid")));

					// Fill the ingredients and steps..
					recipe.setSteps(StepDAO.searchStepByRecipeId(recipe.getRecipeID()));
					recipe.setIngredients(IngredientDAO.searchIngredientByRecipeId(recipe.getRecipeID()));

					recipes.add(recipe);

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // finally close and release resources.
			try {
				BaseDAO.closeAll(null, pstmt, resultSet);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return recipes;
	}

	/**
	 * 
	 * */
	public static List<Recipe> getFavRecipeByName(String recipeName, Integer userId) {
		List<Recipe> recipes = new ArrayList<Recipe>();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = null;

		try {
			connection = BaseDAO.getConnection();
			// SHOW RECIPE
			String preparedSql = "SELECT * FROM `recipe` WHERE `status` = 1 AND recipeName LIKE ? AND id IN("
					+ "SELECT DISTINCT(recipeID) FROM `user-recipe-table` WHERE userID = ? AND status = 1)";
			String wildCardRecipeName = "%" + recipeName + "%";
			pstmt = connection.prepareStatement(preparedSql);
			Object[] parameters = { wildCardRecipeName, userId };
			resultSet = BaseDAO.executeQuery(pstmt, parameters);
			if (resultSet != null && resultSet.isBeforeFirst()) { // ensure that there are some data in result set.
				while (resultSet.next()) {
					Recipe recipe = new Recipe();
					recipe.setRecipeID(Integer.valueOf(resultSet.getString("id")));
					recipe.setRecipeName(resultSet.getString("recipeName"));
					recipe.setImagePath(resultSet.getString("imagePath"));
					recipe.setPreparationTime(Integer.valueOf(resultSet.getString("preparationTime")));
					recipe.setCookingTime(Integer.valueOf(resultSet.getString("cookingTime")));
					recipe.setAvailablePeople(Integer.valueOf(resultSet.getString("peopleAvailable")));
					recipe.setStatus(Integer.valueOf(resultSet.getString("status")));
					recipe.setDescription(resultSet.getString("description"));
					recipe.setOwnerId(Integer.valueOf(resultSet.getString("ownerUserid")));

					// Fill the ingredients and steps..
					recipe.setSteps(StepDAO.searchStepByRecipeId(recipe.getRecipeID()));
					recipe.setIngredients(IngredientDAO.searchIngredientByRecipeId(recipe.getRecipeID()));

					recipes.add(recipe);

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally { // finally close and release resources.
			try {
				BaseDAO.closeAll(null, pstmt, resultSet);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return recipes;
	}

	/**
	 * Unit test for RecipeDAO.
	 * 
	 * @param args:
	 *            string from console input.
	 */
	public static void main(String[] args) {

		// List<Recipe> recipes = getAllRecipes();

		// List<Recipe> recipes = getRecipesByName("su");

		// deleteRecipe(1);

		// List<Recipe> recipeFavs = getFavoritedRecipes(1);

		// List<Integer> list = new ArrayList<>();
		// list.add(1);
		// list.add(2);
		// List<Recipe> recipes = getRecipesByIds(list);

		// List<Recipe> recipes = getRecipesByIngredient("shao");

		// List<Recipe> recipes = getFavRecipeByName("hong", 1);

		// addUser(user);
		// addUser(new User("test5", "456"));
		Recipe recipe = new Recipe();
		recipe.setRecipeID(5);
		System.out.println(recipe.getRecipeID());
		recipe.setOwnerId(1);
		recipe.setRecipeName("asdasdartyutyrt");
		recipe.setDescription("asdasda");
		recipe.setPreparationTime(67);
		recipe.setCookingTime(34);
		recipe.setImagePath("steamedEgg.jpg");
		recipe.setAvailablePeople(12);

//		System.out.println(RecipeDAO.getRecipeID(recipe));

		
//		 System.out.println(RecipeDAO.addRecipe(recipe));
		System.out.println(RecipeDAO.updateRecipe(recipe));

		/**
		 * print basic information of step, you can set, in the database, some step's
		 * status as 0, to test if they will be printed out.
		 */
		// for (Recipe recipe : recipes) {
		// System.out.println(recipe);
		// }

		// System.out.println(recipeIDsIntegers);

	}

}
