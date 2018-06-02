package de.fhluebeck.group3.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.fhluebeck.group3.model.Recipe;
import de.fhluebeck.group3.model.Step;
import de.fhluebeck.group3.model.User;
import de.fhluebeck.group3.util.EncryptUtil;

/**
 * StepDAO is major responsible for Data Access in Step table, functions like
 * findStepByRecipeID, updateStep and deleteStep is provided.
 * 
 * @author kong Yu on 2018/5/27.
 */
public final class StepDAO {

	/**
	 * Batch update multiple steps.
	 * 
	 * @param ingredients:
	 *            the steps to be updated.
	 * 
	 * @return flag: whether the function is succeeded or not.
	 */
	public static boolean updateBatchSteps(List<Step> steps) {
		boolean flag = false;

		return flag;
	}

	/**
	 * Batch add multiple steps.
	 * 
	 * @param ingredients:
	 *            the steps to be added.
	 * 
	 * @return flag: whether the function is succeeded or not.
	 */
	public static boolean addBatchSteps(List<Step> steps) {
		boolean flag = false;

		return flag;
	}

	/**
	 * Patch search multiple steps by recipe id.
	 * 
	 * @param recipeId:
	 *            recipe id of required steps.
	 * 
	 * @return flag: whether the function is succeeded or not.
	 */
	public static List<Step> searchStepByRecipeId(Integer recipeId) {
		List<Step> steps = null;
		Step step = null;
		ResultSet resultSet = null;
		
		//TODO Here if-clause, to check whether recipeId is null, if so, return null;
		
		try {
			//TODO create String preparedSql and parameters. see UserDAO.

			//TODO call the function in BaseDAO, createConnections;
			if (resultSet != null && resultSet.isBeforeFirst()) { // ensure that there are some data in result set.
				steps = new ArrayList<>();
				
			} else {
				//TODO if not the result set is empty, print "sorry step not found", and return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		//TODO Finally, close all the resources, see BaseDAO and UserDAO.
		
		return steps;
	}

	/**
	 * Delete the step according to its id.
	 * 
	 * @param ingredientId:
	 *            step id of specific steps.
	 * 
	 * @return flag: whether the function is succeeded or not.
	 */
	public static boolean deleteStepById(Integer stepId) {
		boolean flag = false;

		return flag;
	}
	
	/**
	 * Unit test for StepDAO.
	 * 
	 * @param args: string from console input.
	 */
	public static void main(String[] args) {
		
		List<Step> steps = searchStepByRecipeId(1);
		
		//should print "sorry step not found"
		List<Step> steps1 = searchStepByRecipeId(100);
		
		/**
		 * print basic information of step, you can set, in the database, some step's status as 0, 
		 * to test if they will be printed out.
		 * */
		for(Step step : steps) {
			System.out.println(step);
		}
		
		//true
		System.out.println(steps1 == null);
		
	}

}
