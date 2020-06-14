package com.luigy.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.luigy.controller.domain.Recipe;


public class RecipeOperation {

	private static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:33060/luigy", "root", "root");
		} catch (Exception e) {
			System.out.println(e);
		}
		return connection;
	}

	public static List<Recipe> getRecipes() {
		List<Recipe> recipes = new ArrayList<Recipe>();
		
		try {
			Statement stmt = getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select * from recipe");
			while (rs.next()) {
				Recipe recipe =new Recipe();
				recipe.setCod(rs.getInt("recipe_cod"));
				recipe.setRecipename(rs.getString("recipe_recipename"));
				recipe.setIngredients(rs.getString("recipe_ingredients"));
				recipe.setProcess(rs.getString("recipe_process"));
				recipe.setPlatedtype(rs.getString("recipe_platedtype"));
				
				recipes.add(recipe);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return recipes;
	}
	
	public static void createRecipe(Recipe recipe) {
		try {
			Statement stmt = getConnection().createStatement();
			String sql = "INSERT INTO recipe (recipe_recipename, recipe_ingredients, recipe_process, recipe_platedtype) "
					+ "VALUES ('" + recipe.getRecipename() + "', '" + recipe.getIngredients() + "', '" + recipe.getProcess() + "', '" + recipe.getPlatedtype() + "')";
			stmt.execute(sql);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public static void updateRecipe(Recipe recipe) {
		try {
			Statement stmt = getConnection().createStatement();
			String sql = "UPDATE recipe SET recipe_recipename = '" + recipe.getRecipename() + "', recipe_ingredients = '" + recipe.getIngredients() + "', recipe_process = '" + recipe.getProcess() + "', recipe_platedtype = '" + recipe.getPlatedtype() + "' WHERE recipe_cod = "+ recipe.getCod();
			stmt.execute(sql);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public static void deleteRecipe(int recipeCod) {
		try {
			Statement stmt = getConnection().createStatement();
			String sql = "DELETE FROM recipe WHERE recipe_Cod = "+ recipeCod;
			stmt.execute(sql);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}




















