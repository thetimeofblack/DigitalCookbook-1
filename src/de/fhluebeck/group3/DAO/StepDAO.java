package de.fhluebeck.group3.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.fhluebeck.group3.model.Step;

/**
 * StepDAO is major responsible for Data Access in Step table, functions like
 * findStepByRecipeID, updateStep and deleteStep is provided.
 * 
 * @author kong Yu on 2018/6/3.
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
		int i = 0;
		if (steps.get(i).getStepID() != null) {
			return false;
		}
		try {
			while (i < steps.size()) {
				String preparedSql = "INSERT INTO `step` (`stepOrder`, `content`,`recipeID`,`status`) VALUES (?, ?, ?, ?)";
				Object[] parameters = { steps.get(i).getStepOrder(), steps.get(i).getContent(),
						steps.get(i).getRecipeID(), steps.get(i).getStatus() };
				i++;
				flag = BaseDAO.executeSql(preparedSql, parameters);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

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
		Step step = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		List<Step> steps = new ArrayList<Step>();

		if (recipeId == null) { // if null, return null;
			return null;
		}

		try {
			connection = BaseDAO.getConnection();
			String preparedSql = "SELECT * FROM step WHERE recipeID = ? AND status = 1 ORDER BY stepOrder ASC";
			pstmt = connection.prepareStatement(preparedSql);
			Object[] parameters = { recipeId };
			if (parameters != null && parameters.length > 0) {
				for (int i = 0; i < parameters.length; i++) {
					pstmt.setObject(i + 1, parameters[i]); // set parameters for prepared statement
				}
			}
			resultSet = pstmt.executeQuery(); // execute the SQL expression

			if (resultSet != null && resultSet.isBeforeFirst()) { // ensure that there are some data in result set.
				while (resultSet.next()) {
					step = new Step();
					step.setStepOrder(resultSet.getInt("stepOrder"));
					step.setContent(resultSet.getString("content"));
					step.setStepID(resultSet.getInt("id"));
					step.setStatus(resultSet.getInt("status"));
					step.setRecipeID(resultSet.getInt("recipeID"));
					steps.add(step);
				}
			} else {
				System.out.println("sorry, step not found");
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally { // finally close and release resources.
			try {
				BaseDAO.closeAll(connection, pstmt, resultSet);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

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
		try {
			String preparedSql = "UPDATE `step` SET `status` = 0 WHERE `id` = ?";
			Object[] parameters = { stepId };
			flag = BaseDAO.executeSql(preparedSql, parameters);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * Unit test for StepDAO.
	 * 
	 * @param args:
	 *            string from console input.
	 */
	public static void main(String[] args) {

		List<Step> steptest1 = searchStepByRecipeId(1);

		// should print "sorry step not found"
		// List<Step> steptest2 = searchStepByRecipeId(100);

		/**
		 * print basic information of step, you can set, in the database, some step's
		 * status as 0, to test if they will be printed out.
		 */

		for (Step step : steptest1) {
			System.out.println(step);
		}

		List<Step> steptest2 = new ArrayList<Step>();
		Step step1 = new Step("Add a small pinch of salt and sesame oil to minced beef. Mix well and set aside.", 1, 7);
		Step step2 = new Step(
				"Mix 1 tablespoon of cornstarch with 2 and ½ tablespoons of water in a small bowl to make water starch.",
				2, 7);
		Step step3 = new Step(
				"Cut tofu into square cubes (around 2cms). Bring a large amount of water to a boil and then add a pinch of salt. Slide the tofu in and cook for 1 minute. Move out and drain.",
				3, 7);
		steptest2.add(step1);
		steptest2.add(step2);
		steptest2.add(step3);
		addBatchSteps(steptest2);

	}

}
