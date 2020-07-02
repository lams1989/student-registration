package com.luigy.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.luigy.controller.domain.Plate;

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

	public static List<Plate> getRecipes() {
		List<Plate> recipes = new ArrayList<Plate>();

		try {
			Statement stmt = getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select * from recipe");
			while (rs.next()) {
				Plate recipe = new Plate();
				recipe.setCod(rs.getInt("recipe_cod"));
				recipe.setRecipename(rs.getString("recipe_name"));
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

	public static void createRecipe(Plate recipe) {
		try {
			String sql = "INSERT INTO recipe (recipe_name, recipe_ingredients, recipe_process, recipe_platedtype) "
					+ "VALUES (?, ? , ?, ?)";
			PreparedStatement pps = getConnection().prepareStatement(sql);
			pps.setString(1, recipe.getRecipename());
			pps.setString(2, recipe.getIngredients());
			pps.setString(3, recipe.getProcess());
			pps.setString(4, recipe.getPlatedtype());

			pps.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void updateRecipe(Plate recipe) {
		try {

			String sql = "UPDATE recipe SET recipe_name = ?, recipe_ingredients = ?, recipe_process = ?, recipe_platedtype = ? WHERE recipe_cod = ? ";
			PreparedStatement pps = getConnection().prepareStatement(sql);
			pps.setString(1, recipe.getRecipename());
			pps.setString(2, recipe.getIngredients());
			pps.setString(3, recipe.getProcess());
			pps.setString(4, recipe.getPlatedtype());
			pps.setInt(5, recipe.getCod());

			pps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void deleteRecipe(int recipeCod) {
		try {
			Statement stmt = getConnection().createStatement();
			String sql = "DELETE FROM recipe WHERE recipe_Cod = " + recipeCod;
			stmt.execute(sql);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
