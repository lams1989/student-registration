package com.luigy.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
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

	public String editStudentRecord(int studentId) {
		Map<String, Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

		for (int i = 0; i < studentList.size(); i++) {
			if (studentList.get(i).getId() == studentId) {
				sessionMapObj.put("editRecordObj", studentList.get(i));
			}
		}

		return "/editStudent.xhtml?faces-redirect=true";
	}
	
	public String createStudentRecord() {
		Map<String, Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		
		Student createNew = new Student();
		createNew.setId(studentList.size() + 1);
		
		sessionMapObj.put("editRecordObj", createNew);
		
		return "/createStudent.xhtml?faces-redirect=true";
	}

	public String updateStudentDetails(Student retorno) {
		for (int i = 0 ; i < studentList.size(); i++) {
			if(studentList.get(i).getId() == retorno.getId()) {
				studentList.get(i).setName(retorno.getName());
				studentList.get(i).setAddress(retorno.getAddress());
				studentList.get(i).setEmail(retorno.getEmail());
				studentList.get(i).setGender(retorno.getGender());
			}
		}
		return "/studentsList.xhtml?faces-redirect=true";
	}
	
	public String deleteStudentRecord(int studentId) {
		
		for (int i = 0; i < studentList.size(); i++) {
			if (studentList.get(i).getId() == studentId) {
				studentList.remove(i);
			}
		}
		return "/studentsList.xhtml?faces-redirect=true";
	}
	
	public String saveStudentDetails(Student editRecordObj) {
		studentList.add(editRecordObj);
		
		return "/studentsList.xhtml?faces-redirect=true";
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
