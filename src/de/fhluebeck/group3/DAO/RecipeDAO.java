package de.fhluebeck.group3.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	 * Search for all recipes.
	 * 
	 * @return recipes: a list of recipes.
	 */
	public static List<Recipe> getAllRecipes() {
		List<Recipe> recipes = new ArrayList<Recipe>();
		ResultSet resultSet = null;

		try {
			String preparedSql = "SELECT * FROM recipe WHERE status = 1";
			resultSet = BaseDAO.executeQuery(preparedSql, null);
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

			} else {
				System.out.println("sorry, recipe not found");
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
		ResultSet resultSet = null;

		try {
			// GET KEYWORD FROM USER INPUT
			String searchName = "'%" + recipeName + "%'";
			// SHOW RECIPE
			String preparedSql = "SELECT * FROM recipe WHERE status = 1 AND recipeName LIKE " + searchName;
			resultSet = BaseDAO.executeQuery(preparedSql, null);
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

					// TODO fill the ingredients and steps..
					recipe.setSteps(StepDAO.searchStepByRecipeId(recipe.getRecipeID()));
					recipe.setIngredients(IngredientDAO.searchIngredientByRecipeId(recipe.getRecipeID()));

					recipes.add(recipe);
				}

			} else {
				System.out.println("sorry, recipe not found");
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

		return recipes;
	}

	/**
	 * Search for the recipes with the recipe IDs, which is a list. This method
	 * involves with patch search, which I get the reference
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
	public static List<Recipe> getFavoritedRecipes(User user) {
		List<Recipe> recipes = new ArrayList<Recipe>();

		return recipes;
	}

	/**
	 * Unit test for RecipeDAO.
	 * 
	 * @param args:
	 *            string from console input.
	 */
	public static void main(String[] args) {
//		List<Recipe> recipes = getAllRecipes();
		
		List<Recipe> recipes = getRecipesByName("su");

		/**
		 * print basic information of step, you can set, in the database, some step's
		 * status as 0, to test if they will be printed out.
		 */
		for (Recipe recipe : recipes) {
			System.out.println(recipe.getRecipeName());
		}

	}

}
