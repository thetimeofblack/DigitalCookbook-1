package de.fhluebeck.group3.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Eason.Hua on 2018-05-25.
 * */
public class CookBook {

	private List<Recipe> recipes;

	private String name;

	public CookBook() {
		super();
	}

	public CookBook(String name) {
		this.name = name;
		this.recipes = new ArrayList<>();
	}

	public CookBook(List<Recipe> recipes, String name) {
		super();
		this.recipes = recipes;
		this.name = name;
	}

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
