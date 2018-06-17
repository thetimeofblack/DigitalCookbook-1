package de.fhluebeck.group3.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	 * Batch update multiple ingredients.
	 * 
	 * @param ingredients:
	 *            the ingredients to be updated.
	 * 
	 * @return flag: whether the function is succeeded or not.
	 */
	public static boolean updateBatchIngredients(List<Ingredient> ingredients) {
		boolean flag = false;
		String preparedSql = "UPDATE `ingredient` SET  " + "ingredientName=?, " + "recipeID=?, " + "quantity=?, "
				+ "unit=?, " + "comments=?, " + "status=? " + "WHERE `id` = ?";
		try {
			for (int n = 0; n < ingredients.size(); n++) {
				Object[] parameters = { ingredients.get(n).getIngredientName(), ingredients.get(n).getRecipeID(),
						ingredients.get(n).getQuantity(), ingredients.get(n).getUnit(), ingredients.get(n).getComment(),
						ingredients.get(n).getStatus(), ingredients.get(n).getIngredientID() };
				flag = BaseDAO.executeSql(preparedSql, parameters);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return flag;
	}

	/**
	 * Batch add multiple ingredients.
	 * 
	 * @param ingredients:
	 *            the ingredients to be added.
	 * 
	 * @return flag: whether the function is succeeded or not.
	 */
	public static boolean addBatchIngredients(List<Ingredient> ingredients) {
		boolean flag = false;
		String preparedSql = "INSERT INTO `ingredient` (id, ingredientName, recipeID, quantity, unit, comments, status) VALUES(?,?,?,?,?,?,?)";
		try {
			for (int n = 0; n < ingredients.size(); n++) {
				Object[] parameters = { ingredients.get(n).getIngredientID(), ingredients.get(n).getIngredientName(),
						ingredients.get(n).getRecipeID(), ingredients.get(n).getQuantity(),
						ingredients.get(n).getUnit(), ingredients.get(n).getComment(), ingredients.get(n).getStatus() };
				flag = BaseDAO.executeSql(preparedSql, parameters);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return flag;
	}

	/**
	 * Search for the recipes(only retrieve their id) who has the ingredient that
	 * matches the ingredientsName, which is regular expression.
	 * 
	 * @param ingredientName:
	 *            the ingredient name used to search for satisfied ingredients.
	 * 
	 * @return recipeIds: a list of ingredients that satisfied the regular
	 *         expression "*ingredientName*"
	 * 
	 */
	public static List<Integer> searchRecipeIdByIngredientsName(String ingredientName) {
		List<Integer> recipeIds = new ArrayList<Integer>();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection connection = null;

		try {
			connection = BaseDAO.getConnection();
			String preparedSql = "SELECT DISTINCT(recipeID) FROM ingredient WHERE ingredientName like ? AND status = 1";
			ingredientName = "%" + ingredientName + "%";
			Object[] parameters = { ingredientName };
			pstmt = connection.prepareStatement(preparedSql);
			resultSet = BaseDAO.executeQuery(pstmt, parameters);
			if (resultSet != null && resultSet.isBeforeFirst()) {
				while (resultSet.next()) {
					int recipeID;
					recipeID = resultSet.getInt("recipeID");
					recipeIds.add(recipeID);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				BaseDAO.closeAll(null, pstmt, resultSet);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return recipeIds;
		// SQL likes: select recipeId from ingredients where status = 1 and
		// ingredientName like "*ingredientName*"
	}

	/**
	 * Batch search multiple ingredients by recipe id.
	 * 
	 * @param recipeId:
	 *            recipe id of required ingredients.
	 * 
	 * @return flag: whether the function is succeeded or not.
	 */
	public static List<Ingredient> searchIngredientByRecipeId(Integer recipeId) {
		List<Ingredient> ingredients = new ArrayList<>();
		Ingredient ingredient = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection connection = null;
		try {
			connection = BaseDAO.getConnection();
			String preparedSql = "SELECT * FROM ingredient WHERE recipeID = ? AND status = 1";
			Object[] parameters = { recipeId };
			pstmt = connection.prepareStatement(preparedSql);
			rs = BaseDAO.executeQuery(pstmt, parameters);
			if (rs != null && rs.isBeforeFirst()) {
				while (rs.next()) {
					ingredient = new Ingredient();
					ingredient.setRecipeID(rs.getInt("recipeID"));
					ingredient.setIngredientName(rs.getString("ingredientName"));
					ingredient.setIngredientID(rs.getInt("id"));
					ingredient.setQuantity(rs.getDouble("quantity"));
					ingredient.setUnit(rs.getString("unit"));
					ingredient.setComment(rs.getString("comments"));

					ingredients.add(ingredient);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				BaseDAO.closeAll(null, pstmt, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
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
		try {
			String preparedSql = "UPDATE `ingredient` SET `status` = 0 WHERE `id` = ?";
			Object[] parameters = { ingredientId };
			flag = BaseDAO.executeSql(preparedSql, parameters);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return flag;
	}

	/**
	 * Unit test for IngredientDAO.
	 * 
	 * @param args:
	 *            string from console input.
	 */

	public static void main(String[] args) {

		/*
		 * List<Ingredient> ingredients = searchIngredientByRecipeId(1);
		 * 
		 * for (Ingredient ingredient : ingredients) { System.out.println(ingredient);
		 */

		// test the function of searchRecipeIdByIngredientsName(String ingredientName)
		// List<Integer> ids = searchRecipeIdByIngredientsName("Shaoxin rice wine");
		List<Ingredient> exampleIds = new ArrayList<Ingredient>();
		Ingredient ChickenEssence = new Ingredient(36, "Chicken essence", (double) 1, 4, "teaspoon",
				"Chinese material!");
		Ingredient DriedShrimp = new Ingredient(37, "Dried Shrimp", (double) 1, 4, "teaspoon", "seafoodd");
		exampleIds.add(0, ChickenEssence);
		exampleIds.add(1, DriedShrimp);
		System.out.println(updateBatchIngredients(exampleIds));
		// updateBatchIngredients(exampleIds);
		/*
		 * for (int id : ids) { System.out.println(id); }
		 */
	}
}
