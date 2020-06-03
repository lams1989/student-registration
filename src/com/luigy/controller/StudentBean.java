package com.luigy.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class StudentBean implements Serializable {
	
	List<Student> studentList = new ArrayList<Student>();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<Student> studentsList() {
		
		return studentList;
	}

	@PostConstruct
	public void init() {
		Student person1 = new Student(1, "Luis Muñoz", "LuisMunoz@email.com", "12345", "M", "corrientes 6477");
		Student person2 = new Student(2, "Jose Muñoz", "JoseMunoz@email.com", "12346", "M", "colombia 1234");
		Student person3 = new Student(3, "Adrian Muñoz", "AdrianMunoz@email.com", "12375", "M", "colombia 234");

		studentList.add(person1);
		studentList.add(person2);
		studentList.add(person3);
	}
}
