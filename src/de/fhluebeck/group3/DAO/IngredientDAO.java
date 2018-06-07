package de.fhluebeck.group3.DAO;

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
	 * Patch update multiple ingredients.
	 * 
	 * @param ingredients:
	 *            the ingredients to be updated.
	 * 
	 * @return flag: whether the function is succeeded or not.
	 */
	public static boolean updatePatchIngredients(List<Ingredient> ingredients) {
		boolean flag = false;
		/*
		 * java.sql.Connection conn = null; java.sql.PreparedStatement ps = null; try {
		 * String preparedSql =
		 * "\"UPDATE `ingredient` VALUES (?, ?, ?, ?, ?, ?, ?) WHERE `recipeID` = ?\" ";
		 * Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver"); conn =
		 * DriverManager.getConnection
		 * ("jdbc:mysql://localhost:3306/cookbook_group3?characterEncoding=utf-8&useSSL=false",
		 * "root", "root"); ps = conn.prepareStatement(preparedSql); Object[] parameters
		 * = {ingredients.get(0), ingredients.get(1), ingredients.get(2),
		 * ingredients.get(3), ingredients.get(4), ingredients.get(5),
		 * ingredients.get(6)}; flag = BaseDAO.executeSql(preparedSql, parameters);
		 * }catch(Exception e) { e.printStackTrace(); }finally {
		 * 
		 * }
		 */
		return flag;
	}

	/**
	 * Patch add multiple ingredients.
	 * 
	 * @param ingredients:
	 *            the ingredients to be added.
	 * 
	 * @return flag: whether the function is succeeded or not.
	 */
	public static boolean addPatchIngredients(List<Ingredient> ingredients) {
		boolean flag = false;


		return flag;
	}

	/**
	 * Patch search multiple ingredients by recipe id.
	 * 
	 * @param recipeId:
	 *            recipe id of required ingredients.
	 * 
	 * @return flag: whether the function is succeeded or not.
	 */
	public static List<Ingredient> searchIngredientByRecipeId(Integer recipeId) {
		List<Ingredient> ingredients = new ArrayList<>();
		Ingredient ingredient = null;
		try {
			// java.sql.Connection conn = null;
			// java.sql.PreparedStatement ps = null;
			ResultSet rs = null;
			String preparedSql = "SELECT * FROM ingredient WHERE recipeID = ? AND status = 1";
			// conn = BaseDAO.getConnection();
			Object[] parameters = { recipeId };
			rs = BaseDAO.executeQuery(preparedSql, parameters);
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
				BaseDAO.closeAll(BaseDAO.getConn(), BaseDAO.getPstmt(), BaseDAO.getRs());
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
			String preparedSql = "DELETE FROM `ingredient` WHERE `id` = ?";
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

		List<Ingredient> ingredients = searchIngredientByRecipeId(1);

		for (Ingredient ingredient : ingredients) {
			System.out.println(ingredient);
		}

	}
}
