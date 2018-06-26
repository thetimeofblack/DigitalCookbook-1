package de.fhluebeck.group3.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import de.fhluebeck.group3.DAO.IngredientDAO;
import de.fhluebeck.group3.model.Ingredient;

/**
 * Test Class for IngredientDAO.
 * 
 * @author Wang Jungang on 2018/6/23.
 */
public class IngredientDAOTest 
{
	@Before
	public void setUptest()
	{
	List<Ingredient> testIngredientList = new ArrayList<Ingredient>();
	Ingredient testIngredient1 = new Ingredient(809, "testIngredient1", (double) 1, 314, "teaspoon", "testStyle");
	Ingredient testIngredient2 = new Ingredient(996, "testIngredient2", (double) 1, 314, "teaspoon", "testStyle");
	Ingredient testIngredient3 = new Ingredient(997, "testIngredient3", (double) 1, 123, "teaspoon", "testStyle");
	testIngredientList.add(testIngredient1);
	testIngredientList.add(testIngredient2);
	testIngredientList.add(testIngredient3);
	IngredientDAO.addBatchIngredients(testIngredientList);
	}
	
	@Test
	public void testsearchIngredientByRecipeId() throws Exception
	{
		assertEquals((int)IngredientDAO.searchIngredientByRecipeId(1).get(0).getIngredientID(), 1);
	}
	
	@Test
	public void testaddBatchIngredients() throws Exception
	{
		assertNotNull(IngredientDAO.searchIngredientByRecipeId(314));
	}
	
	@Test
	public void testSearchRecipeIdbyIngredientName() throws Exception
	{
		assertEquals((int)IngredientDAO.searchRecipeIdByIngredientsName("testIngredient1").get(0), (int)314);
	}
	
	@Test
	public void testupdateBatchIngredients()
	{
		List<Ingredient> updatetestIngredientList = new ArrayList<Ingredient>();
		Ingredient updatetestIngredient1 = new Ingredient(809, "testIngredient1updated", 
				(double) 2, 314, "teaspooon", "testStyleupdated");
		Ingredient updatetestIngredient2 = new Ingredient(996, "testIngredient2updated", 
				(double) 3, 314, "teaspooon", "testStyleupdated!");
		updatetestIngredientList.add(updatetestIngredient1);
		updatetestIngredientList.add(updatetestIngredient2);
		IngredientDAO.updateBatchIngredients(updatetestIngredientList);
		assertEquals((int)IngredientDAO.searchIngredientByRecipeId(314).get(0).getIngredientID(), 809);
		assertEquals(IngredientDAO.searchIngredientByRecipeId(314).get(1).getIngredientName(), "testIngredient2updated");
	}
	
	@Test
	public void testDeleteIngredientById()
	{
		assertTrue(IngredientDAO.deleteIngredientById(997));
		assertEquals(IngredientDAO.searchIngredientByRecipeId(123).size(), 0);
	}
	
	@Test
	public void testDeleteBatchIngredients()
	{
		List<Ingredient> testDeleteIngredientList = new ArrayList<Ingredient>();
		Ingredient testIngredient1 = new Ingredient(809, "testIngredient1updated", 
				(double) 2, 314, "teaspooon", "testStyleupdated");
		Ingredient testIngredient2 = new Ingredient(996, "testIngredient2updated", 
				(double) 3, 314, "teaspooon", "testStyleupdated!");
		testDeleteIngredientList.add(testIngredient1);
		testDeleteIngredientList.add(testIngredient2);
		assertTrue(IngredientDAO.batchDeleteIngredients(testDeleteIngredientList));
		assertEquals(IngredientDAO.searchIngredientByRecipeId(314).size(), 0);
	}
}
