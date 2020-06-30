package com.luigy.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.luigy.controller.domain.Categori;

public class CategoriOperation {

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

	public static List<Categori> getCategoris() {
		List<Categori> categoris = new ArrayList<Categori>();

		try {
			Statement stmt = getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select * from categori");
			while (rs.next()) {
				Categori categori = new Categori();
				categori.setId(rs.getInt("id_categori"));
				categori.setCategoriname(rs.getString("categori_name"));

				categoris.add(categori);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return categoris;
	}

	public static void createCategori(Categori categori) {
		try {
			String sql = "INSERT INTO recipe (categori_name) " + "VALUES (?)";
			PreparedStatement pps = getConnection().prepareStatement(sql);
			pps.setString(1, categori.getCategoriname());

			pps.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void updateCategori(Categori categori) {
		try {

			String sql = "UPDATE categori SET categori_name = ? WHERE id_categori = ? ";
			PreparedStatement pps = getConnection().prepareStatement(sql);
			pps.setString(1, categori.getCategoriname());
			pps.setInt(2, categori.getId());

			pps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void deleteCategori(int id) {
		try {
			Statement stmt = getConnection().createStatement();
			String sql = "DELETE FROM categori WHERE id_categori = " + id;
			stmt.execute(sql);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
