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
		Step step = null ;
		ResultSet resultSet = null;
		int stepOrder = 0;
		String content = null;
		List<Step> steps = new ArrayList<Step>();
		
		if(recipeId!=null) {
			return null;
		}
		
		try {
			//TODO create String preparedSql and parameters. see UserDAO.
			String preparedSql="SELECT * FROM recipe WHERE recipeId = ? AND status = 1";
			Object[]parameters={stepOrder, content}
;			resultSet = BaseDAO.executeQuery(preparedSql,parameters);

			//TODO call the function in BaseDAO, executeQuery; see how I did it in UserDAO.
			if (resultSet!= null && resultSet.isBeforeFirst()) { // ensure that there are some data in result set.
				while(resultSet.next()){
				step = new Step();
				step.setStepOrder(resultSet.getInt("stepOrder"));
				step.setContent(resultSet.getString("content"));
				steps.add(step);
				System.out.println(resultSet.getString("content"));
				
				}
			} else {
				//TODO if not the result set is empty, print "sorry step not found", and return null;
				System.out.println("sorry, step not found");
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		// TODO Finally, close all the resources, see BaseDAO and UserDAO.
		finally { // finally close and release resources.
			try {
				BaseDAO.closeAll(BaseDAO.getConn(), BaseDAO.getPstmt(), BaseDAO.getRs());
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

		return flag;
	}
	
	/**
	 * Unit test for StepDAO.
	 * 
	 * @param args: string from console input.
	 */
	public static void main(String[] args) {
		
		List<Step> steptest1 =searchStepByRecipeId(1);
		
		//should print "sorry step not found"
	    List<Step> steptest2= searchStepByRecipeId(100);
	
		
		/**
		 * print basic information of step, you can set, in the database, some step's status as 0, 
		 * to test if they will be printed out.
		 * */
		
	    for(Step step:steptest1) {
			System.out.println(step);
			 
		}
	}

}
