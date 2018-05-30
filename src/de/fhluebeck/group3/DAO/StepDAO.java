package de.fhluebeck.group3.DAO;

import java.util.ArrayList;
import java.util.List;

import de.fhluebeck.group3.model.Step;

/**
 * StepDAO is major responsible for Data Access in Step table, functions like
 * findStepByRecipeID, updateStep and deleteStep is provided.
 * 
 * @author komgyu on 2018/5/27.
 */
public final class StepDAO {

	/**
	 * Patch update multiple steps.
	 * 
	 * @param ingredients:
	 *            the steps to be updated.
	 * 
	 * @return flag: whether the function is succeeded or not.
	 */
	public static boolean updatePatchSteps(List<Step> steps) {
		boolean flag = false;

		return flag;
	}

	/**
	 * Patch add multiple steps.
	 * 
	 * @param ingredients:
	 *            the steps to be added.
	 * 
	 * @return flag: whether the function is succeeded or not.
	 */
	public static boolean addPatchSteps(List<Step> steps) {
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
		List<Step> steps = new ArrayList<>();

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

}
