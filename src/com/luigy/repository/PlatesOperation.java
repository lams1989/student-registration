package com.luigy.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.luigy.controller.domain.Plates;

public class PlatesOperation {

	private static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:33060/luis", "root", "root");
		} catch (Exception e) {
			System.out.println(e);
		}
		return connection;
	}

	public static List<Plates> getPlates() {
		List<Plates> totalplates = new ArrayList<Plates>();

		try {
			Statement stmt = getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select * from plates");
			while (rs.next()) {
				Plates plates = new Plates();
				plates.setId(rs.getInt("id_plate"));
				plates.setPlatename(rs.getString("plate_name"));
				plates.setPublicprice(rs.getDouble("public_price"));
				plates.setAvailable(rs.getInt("available"));
				plates.setCategori(rs.getInt("id_categoria"));

				totalplates.add(plates);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return totalplates;
	}

	public static void createPlates(Plates plates) {
		try {
			String sql = "INSERT INTO plates (plate_name, public_price, available, id_categoria) "
					+ "VALUES (?, ? , ?, ?)";
			PreparedStatement pps = getConnection().prepareStatement(sql);
			pps.setString(1, plates.getPlatename());
			pps.setDouble(2, plates.getPublicprice());
			pps.setInt(3, plates.getAvailable());
			pps.setInt(4, plates.getCategori());

			pps.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void updatePlates(Plates plates) {
		try {

			String sql = "UPDATE plates SET plate_name = ?, public_price = ?, available = ?, id_categoria = ? WHERE id_plate = ? ";
			PreparedStatement pps = getConnection().prepareStatement(sql);
			pps.setString(1, plates.getPlatename());
			pps.setDouble(2, plates.getPublicprice());
			pps.setInt(3, plates.getAvailable());
			pps.setInt(4, plates.getCategori());
			pps.setInt(5, plates.getId());

			pps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void deletePlates(int idplate) {
		try {
			Statement stmt = getConnection().createStatement();
			String sql = "DELETE FROM plates WHERE id_plate = " + idplate;
			stmt.execute(sql);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}


