package de.fhluebeck.group3.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Cookbook Model as an Application.
 * 
 * @author Eason.Hua on 2018-05-25.
 */
public class CookBook {

	private List<Recipe> recipes;

	private String name;

	/**
	 * Default Constructor.
	 */
	public CookBook() {
		super();
	}

	/**
	 * Constructor.
	 * 
	 * @param name
	 *            the name of the cook book.
	 */
	public CookBook(String name) {
		this.name = name;
		this.recipes = new ArrayList<>();
	}

	/**
	 * Constructor.
	 * 
	 * @param name
	 *            the name of the cook book.
	 * 
	 * @param recipes
	 *            all the recipes that the application contains.
	 */
	public CookBook(List<Recipe> recipes, String name) {
		super();
		this.recipes = recipes;
		this.name = name;
	}

	/**
	 * Get recipe from the application.
	 * 
	 * @param name
	 *            the name of the recipe
	 * 
	 * @return the recipe we want to get.
	 * 
	 */
	public Recipe getRecipe(String name) {
		Recipe recipe = null;

		for (Recipe index_recipe : this.recipes) {
			if (name.equals(index_recipe.getRecipeName())) {
				recipe = index_recipe;
				break;
			}
		}

		return recipe;
	}

	/**
	 * Add new recipe to the application.
	 * 
	 * @param recipe
	 *            a new recipe to be add to the application.
	 */
	public void add(Recipe recipe) {
		this.recipes.add(recipe);
	}

	/**
	 * @return the recipes
	 */
	public List<Recipe> getRecipes() {
		return recipes;
	}

	/**
	 * @param recipes
	 *            the recipes to set
	 */
	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
