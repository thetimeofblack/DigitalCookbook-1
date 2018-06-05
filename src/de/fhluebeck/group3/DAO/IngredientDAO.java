package de.fhluebeck.group3.DAO;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import de.fhluebeck.group3.model.Ingredient;
import de.fhluebeck.group3.model.Step;

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
		/*java.sql.Connection conn = null;
		java.sql.PreparedStatement ps = null;
		try {
			String preparedSql = "\"UPDATE `ingredient` VALUES (?, ?, ?, ?, ?, ?, ?) WHERE `recipeID` = ?\" ";
			Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
			conn = DriverManager.getConnection
					("jdbc:mysql://localhost:3306/cookbook_group3?characterEncoding=utf-8&useSSL=false", "root", "root");
			ps = conn.prepareStatement(preparedSql);
			Object[] parameters = {ingredients.get(0), ingredients.get(1), ingredients.get(2), ingredients.get(3), ingredients.get(4), ingredients.get(5), ingredients.get(6)};
			flag = BaseDAO.executeSql(preparedSql, parameters);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
		
		}*/
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
		java.sql.Connection conn = null;
		java.sql.PreparedStatement ps = null;
		try {
			String preparedSql = "INSERT INTO `ingredient` VALUES (?, ?, ?, ?, ?, ?,1) ";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection
					("jdbc:mysql://localhost:3306/cookbook_group3?characterEncoding=utf-8&useSSL=false", "root", "root");
			ps = conn.prepareStatement(preparedSql);
			for(int n=0; n < ingredients.size(); n++) {
			Object[] parameters = 
				{ingredients.get(n).getIngredientID(), ingredients.get(n).getIngredientName(), ingredients.get(n).getQuantity(), ingredients.get(n).getRecipeID(), ingredients.get(n).getUnit(), ingredients.get(n).getComment()};
			flag = BaseDAO.executeSql(preparedSql, parameters);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
		
		}
		
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
		try {
			java.sql.Connection conn = null;
			java.sql.PreparedStatement ps = null;
			ResultSet rs = null;
			String preparedSql = "SELECT * FROM ingredient WHERE recipeID = ? ";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection
					("jdbc:mysql://localhost:3306/cookbook_group3?characterEncoding=utf-8&useSSL=false", "root", "root");
			Object[] parameters = {recipeId};
			rs = BaseDAO.executeQuery(preparedSql, parameters);
			while(rs.next()) { 
				Ingredient ingredient = new Ingredient
						(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getInt(4),rs.getString(5),rs.getString(6));
				ingredients.add(ingredient);
			}	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
		
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
		java.sql.Connection conn = null;
		java.sql.PreparedStatement ps = null;
		try {
			String preparedSql = "DELETE FROM `ingredient` WHERE `id` = ?";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection
					("jdbc:mysql://localhost:3306/cookbook_group3?characterEncoding=utf-8&useSSL=false", "root", "root");
			ps = conn.prepareStatement(preparedSql);
			Object[] parameters = {ingredientId};
			flag = BaseDAO.executeSql(preparedSql, parameters);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
		
		}
		return flag;
	}
	
	/**
	 * Unit test for IngredientDAO.
	 * 
	 * @param args: string from console input.
	 */
	public static void main(String[] args) {	
		//test the function of addPatchIngredients()
		List<Ingredient> person=new ArrayList<>();
		int a = 66;
		String b = "2";
		double c = 2.3;
		int d = 4;
		String e = "5";
		String f = "6";
		Ingredient test1 = new Ingredient(a,b,c,d,e,f);
		person.add(test1);
		System.out.println(person);
        addPatchIngredients(person);
        System.out.println("Add patch ingredients successed.");
		
		//test the function of searchIngredientByRecipeId(Integer recipeId)
		List<Ingredient> abc2 = searchIngredientByRecipeId(1);
		System.out.println(abc2);
		
        //test the function of deleteIngredientById(Integer ingredientId)
		deleteIngredientById(66);
		System.out.println("Delete success");
	}

}
