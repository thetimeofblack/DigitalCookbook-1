package de.fhluebeck.group3.DAO;

import java.util.ArrayList;
import java.util.List;

import de.fhluebeck.group3.model.Ingredient;

/**
 * IngredientDAO is major responsible for Data Access in Ingredient table,
 * functions like addRecipe, getRecipeByName, updateRecipe are provided.
 * 
 * @author Wang Jungang on 2018/5/27.
 */
public final class IngredientDAO {

	/**
	 * Patch update multiple ingredients.
	 * 
	 * @param ingredients:
	 *            the ingredients to be updated.
	 * 
	 * @return flag: whether the function is succeeded or not.
	 */
	public static boolean updatePatchIngredients(List<Ingredient> ingredients) {
		boolean flag = false;

		return flag;
	}

	/**
	 * Patch add multiple ingredients.
	 * 
	 * @param ingredients:
	 *            the ingredients to be added.
	 * 
	 * @return flag: whether the function is succeeded or not.
	 */
	public static boolean addPatchIngredients(List<Ingredient> ingredients) {
		boolean flag = false;

		return flag;
	}

	/**
	 * Patch search multiple ingredients by recipe id.
	 * 
	 * @param recipeId:
	 *            recipe id of required ingredients.
	 * 
	 * @return flag: whether the function is succeeded or not.
	 */
	public static List<Ingredient> searchIngredientByRecipeId(Integer recipeId) {
		List<Ingredient> ingredients = new ArrayList<>();

		return ingredients;
	}

	/**
	 * Delete the ingredient according to its id.
	 * 
	 * @param ingredientId:
	 *            ingredient id of specific ingredients.
	 * 
	 * @return flag: whether the function is succeeded or not.
	 */
	public static boolean deleteIngredientById(Integer ingredientId) {
		boolean flag = false;

		return flag;
	}

	/**
	 * Unit test for IngredientDAO.
	 * 
	 * @param args:
	 *            string from console input.
	 */
	public static void main(String[] args) {

	}

}
