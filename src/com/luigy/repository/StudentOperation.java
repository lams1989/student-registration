package com.luigy.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.luigy.controller.domain.Student;

public class StudentOperation {

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

	public static List<Student> getStudents() {
		List<Student> students = new ArrayList<Student>();

		try {
			Statement stmt = getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select * from student");
			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getInt("student_id"));
				student.setName(rs.getString("student_name"));
				student.setEmail(rs.getString("student_email"));
				student.setGender(rs.getString("student_gender"));
				student.setPassword(rs.getString("student_password"));
				student.setAddress(rs.getString("student_address"));

				students.add(student);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return students;
	}

	public static void createStudent(Student student) {
		try {
			Statement stmt = getConnection().createStatement();
			String sql = "INSERT INTO student (student_name, student_email, student_password, student_gender, student_address) "
					+ "VALUES ('" + student.getName() + "', '" + student.getEmail() + "', '" + student.getPassword() + "', '" + student.getGender() + "', '" + student.getAddress() + "')";
			stmt.execute(sql);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public static void updateStudent(Student student) {
		try {
			Statement stmt = getConnection().createStatement();
			String sql = "UPDATE student SET student_name = '" + student.getName() + "', student_email = '" + student.getEmail() + "', student_gender = '" + student.getGender() + "', student_address = '" + student.getAddress() + "' WHERE student_id = "+ student.getId();
			stmt.execute(sql);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public static void deleteStudent(int studentId) {
		try {
			Statement stmt = getConnection().createStatement();
			String sql = "DELETE FROM student WHERE student_Id = "+ studentId;
			stmt.execute(sql);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	
}
