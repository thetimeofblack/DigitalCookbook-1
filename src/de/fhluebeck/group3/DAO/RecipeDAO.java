package de.fhluebeck.group3.DAO;

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

		return flag;
	}

	/**
	 * Update the recipe according to its ID, here we do have to do "patch-update"
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
	 * Add the recipe to the DB, here we do have to do "patch-insertion" which means
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

}
