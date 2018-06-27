package de.fhluebeck.group3.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.validator.PublicClassValidator;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Receiver;

import de.fhluebeck.group3.DAO.RecipeDAO;
import de.fhluebeck.group3.model.Recipe;
import junit.framework.Assert;

/**
 * Test Class for RecipeDAO.
 * 
 * @author Shan Jiaxiang on 2018/6/23.
 */
public class RecipeDAOTest {

	private Recipe newRecipe1 = null;
	private Recipe newRecipe2 = null;
		
	/**
	 * Before testing recipe, we create two recipe objects
	 */
	@Before
	public void setUp() throws Exception{
		
//		//create new Recipe for insertion
//		newRecipe1 = new Recipe();
//		newRecipe1.setOwnerId(1);
//		newRecipe1.setRecipeName("testrecipe1");
//		newRecipe1.setDescription("this recipe is only for testing");
//		newRecipe1.setPreparationTime(60);
//		newRecipe1.setCookingTime(30);
//		newRecipe1.setImagePath("noimage.jpg");
//		newRecipe1.setAvailablePeople(4);
//		newRecipe1.setStatus(1);
//		
//		newRecipe2 = new Recipe();
//		newRecipe2.setOwnerId(1);
//		newRecipe2.setRecipeName("testrecipe2");
//		newRecipe2.setDescription("this recipe is only for testing");
//		newRecipe2.setPreparationTime(30);
//		newRecipe2.setCookingTime(20);
//		newRecipe2.setImagePath("noimage.jpg");
//		newRecipe2.setAvailablePeople(6);
//		newRecipe2.setStatus(1);
//		
//		//Add recipe to Database
//		RecipeDAO.addRecipe(newRecipe1);
//		RecipeDAO.addRecipe(newRecipe2);
	}
	
	/**
	 * Test whether addRecipe function is valid
	 */
	@Test
	public void testAddRecipeFuction() throws Exception{
		
		assertNotNull(RecipeDAO.getRecipesByName("testrecipe"));
		// If it works, this cannot be null
	}
	
	/**
	 * Test whether deleteRecipe function is valid 
	 */
	@Test
	public void testDeleteRecipeFunction() throws Exception{
		
		int testRecipeId = 4;
		//test whether the function worked
		assertTrue(RecipeDAO.deleteRecipe(testRecipeId));
		//test the result
		List<Integer> testList = new ArrayList<>();
		testList.add(testRecipeId);
		//assertNull(RecipeDAO.getRecipesByIds(testList));
		assertEquals(RecipeDAO.getRecipesByIds(testList).size(), 0);
	}
	
	/**
	 * Test whether updateRecipe function is valid
	 */
	@Test
	public void testUpdateRecipeFunction() throws Exception{
	
		int recipeId = 6;
		List<Integer> recipeIds = new ArrayList<>();
		recipeIds.add(recipeId);
		List<Recipe> testRecipe = RecipeDAO.getRecipesByIds(recipeIds);
		if(testRecipe != null && testRecipe.size() > 0) {
			newRecipe2 = testRecipe.get(0);
		}
		newRecipe2.setCookingTime(800);
		newRecipe2.setDescription("description changed");
		assertTrue(RecipeDAO.updateRecipe(newRecipe2));
		//retrive again.
		testRecipe = RecipeDAO.getRecipesByIds(recipeIds);
		if(testRecipe != null && testRecipe.size() > 0) {
			Recipe recipe = testRecipe.get(0);
			assertEquals(newRecipe2.getCookingTime(), recipe.getCookingTime());
			assertEquals(newRecipe2.getDescription(), recipe.getDescription());
		}
		
	}
	
	/**
	 * Test whether getAllRecipes function is valid
	 */
	@Test
	public void testGetAllRecipeFunction() throws Exception{
		assertNotNull(RecipeDAO.getAllRecipes());
	}
	
	/**
	 * Test whether getRecipeID function is valid
	 */
	@Test
	public void testGetRecipeIDFunction() throws Exception{
		Recipe testRecipe = new Recipe();
		
		assertNotNull(RecipeDAO.getRecipeID(testRecipe));
		
	}
	
	/**
	 * Test addRecipetoFavouriteList function is valid
	 */
	@Test
	public void testAddRecipeToFavouriteListFunction() throws Exception{
		int testId = 7;
		int testUserId = 1;
		assertTrue(RecipeDAO.addRecipeToFavoriteList(testId, testUserId));
		
	}
	
	/**
	 * Test removeRecipeFromFavouriteList function is valid
	 */
	@Test
	public void testRemoveRecipeFromFavouriteFunction() throws Exception{
		int testId = 9;
		int testUserId = 1;
		assertTrue(RecipeDAO.removeRecipeFromFavoriteList(testId, testUserId));
	}
	
	/**
	 * Test getRecipesByName function is valid
	 */
	@Test
	public void testGetRecipesByNameFunction() throws Exception{
		String testName = "test";
		assertNotNull(RecipeDAO.getRecipesByName(testName));
	}
	
	/**
	 * Test getFavRecipesByName function is valid
	 */
	@Test
	public void testGetFavRecipeByNameFunction() throws Exception{
		String testName = "test";
		int userId = 1;
		assertNotNull(RecipeDAO.getFavRecipeByName(testName, userId));
	}
	
	/**
	 * Test getRecipesByIngredient function is valid
	 */
	@Test
	public void testGetRecipesByIngredient() throws Exception{
		String testIngredient = "pork";
		assertNotNull(RecipeDAO.getRecipesByIngredient(testIngredient));
	}
	
	/**
	 * Test getFavRecipeByIngredients function is valid
	 */
	@Test
	public void testGetFavRecipeByIngredientsFunction() throws Exception{
		String testIngredient = "sugar";
		int userId = 1;
		assertNotNull(RecipeDAO.getFavRecipeByIngredients(testIngredient, userId));
	}
	
	/**
	 * Test getRecipesByIds function is valid
	 */
	@Test
	public void testGetRecipesByIdsFunction() throws Exception{
		List<Integer> recipeIds = new ArrayList<>();
		int testId1 = 2;
		int testId2 = 3;
		assertNotNull(RecipeDAO.getRecipesByIds(recipeIds));
	}
	
	/**
	 * Test getFavouritedRecipes function is valid
	 */
	@Test
	public void testGetFavouritedRecipesFunction() {
		int userId = 1;
		assertNotNull(RecipeDAO.getFavoritedRecipes(userId));
	}
}