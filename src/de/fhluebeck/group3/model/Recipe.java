package de.fhluebeck.group3.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Recipe is mapped with Recipe table in DB.
 * 
 * @author Yichen.Hua on 2018/05/13.
 */
public final class Recipe {

	/**
	 * ID of the recipe.
	 */
	private Integer recipeID;

	/**
	 * Name of the recipe.
	 */
	private String recipeName;

	/**
	 * Description of the recipe.
	 */
	private String description;

	/**
	 * Preparation time of the recipe.
	 */
	private Integer preparationTime;

	/**
	 * Cooking time of the recipe.
	 */
	private Integer cookingTime;

	/**
	 * Image path of a recipe.
	 */
	private String imagePath;

	/**
	 * Owner id of the recipe
	 */
	private Integer ownerId;

	/**
	 * people available for one dish
	 */
	private Integer availablePeople;

	/**
	 * Steps of the recipe.
	 */
	private List<Step> steps;

	/**
	 * Ingredients of the recipe.
	 */
	private List<Ingredient> ingredients;

	/**
	 * Status of recipe: 1 for valid 0 for deleted.
	 */
	private Integer status;

	/** ==============Operational Functions============== */
	/**
	 * Add Ingredients to the Recipe.
	 */
	public void addIngredient(Ingredient newIngredient) {
		this.ingredients.add(newIngredient);
	}

	/**
	 * Add Preparation Steps to the Recipe.
	 */
	public void addPreparationStep(String stepContent) {
		Step step = new Step(stepContent);
		this.steps.add(step);
	}

	/** ==============Constructors============== */
	/**
	 * default constructor.
	 */
	public Recipe() {
		super();
	}

	/**
	 * constructor with attributes except recipeID and status -> for insertions;
	 */
	public Recipe(String recipeName, String description, Integer preparationTime, Integer cookingTime,
			Integer availeblPeople) {
		super();
		this.recipeName = recipeName;
		this.description = description;
		this.preparationTime = preparationTime;
		this.cookingTime = cookingTime;
		this.status = 1;
		this.steps = new ArrayList<>();
		this.ingredients = new ArrayList<>();
		this.availablePeople = availeblPeople;
	}

	/**
	 * constructor with attributes except recipeID and status -> for insertions;
	 */
	public Recipe(String recipeName, String description, Integer preparationTime, Integer cookingTime, String imagePath,
			Integer ownerId, List<Step> steps, List<Ingredient> ingredients, Integer availeblPeople) {
		this(null, recipeName, description, preparationTime, cookingTime, imagePath, ownerId, availeblPeople, steps,
				ingredients, 1);
	}

	/**
	 * constructor of all attributes. -> Do not set recipeID and status yourself, it
	 * is auto-generated by database.
	 */
	public Recipe(Integer recipeID, String recipeName, String description, Integer preparationTime, Integer cookingTime,
			String imagePath, Integer ownerId, Integer availablePeople, List<Step> steps, List<Ingredient> ingredients,
			Integer status) {
		super();
		this.recipeID = recipeID;
		this.recipeName = recipeName;
		this.description = description;
		this.preparationTime = preparationTime;
		this.cookingTime = cookingTime;
		this.imagePath = imagePath;
		this.ownerId = ownerId;
		this.availablePeople = availablePeople;
		this.steps = steps;
		this.ingredients = ingredients;
		this.status = status;
	}

	/** ==============Getters and setters.============== */
	/**
	 * @return the recipeID
	 */
	public Integer getRecipeID() {
		return recipeID;
	}

	/**
	 * @param recipeID
	 *            the recipeID to set
	 */
	public void setRecipeID(Integer recipeID) {
		this.recipeID = recipeID;
	}

	/**
	 * @return the recipeName
	 */
	public String getRecipeName() {
		return recipeName;
	}

	/**
	 * @param recipeName
	 *            the recipeName to set
	 */
	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the preparationTime
	 */
	public Integer getPreparationTime() {
		return preparationTime;
	}

	/**
	 * @param preparationTime
	 *            the preparationTime to set
	 */
	public void setPreparationTime(Integer preparationTime) {
		this.preparationTime = preparationTime;
	}

	/**
	 * @return the cookingTime
	 */
	public Integer getCookingTime() {
		return cookingTime;
	}

	/**
	 * @param cookingTime
	 *            the cookingTime to set
	 */
	public void setCookingTime(Integer cookingTime) {
		this.cookingTime = cookingTime;
	}

	/**
	 * @return the imagePath
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * @param imagePath
	 *            the imagePath to set
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	/**
	 * @return the steps
	 */
	public List<Step> getSteps() {
		return steps;
	}

	/**
	 * @param steps
	 *            the steps to set
	 */
	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}

	/**
	 * @return the ingredients
	 */
	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	/**
	 * @param ingredients
	 *            the ingredients to set
	 */
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public Integer getAvailablePeople() {
		return availablePeople;
	}

	public void setAvailablePeople(Integer availablePeople) {
		this.availablePeople = availablePeople;
	}

	/**
	 * Override toString method, print basic information of an recipe.
	 */
	@Override
	public String toString() {
		return "Recipe [recipeID=" + recipeID + ", recipeName=" + recipeName + ", description=" + description
				+ ", preparationTime=" + preparationTime + ", cookingTime=" + cookingTime + ", imagePath=" + imagePath
				+ ", ownerId=" + ownerId + ", availablePeople=" + availablePeople + ", steps=" + steps
				+ ", ingredients=" + ingredients + ", status=" + status + "]";
	}

	/** 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof Recipe)) {
			return false;
		}else {
			Recipe other = (Recipe) obj;
			return (this.getRecipeID().equals(other.getRecipeID()));
		}
	}
	
	

}