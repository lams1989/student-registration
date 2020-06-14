package com.luigy.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.luigy.controller.domain.Student;
import com.luigy.repository.StudentOperation;
import java.io.Serializable;

@Named
@SessionScoped
public class StudentBean implements Serializable {

	List<Student> studentList = new ArrayList<Student>();
	
	private static final long serialVersionUID = 1L;

	public List<Student> studentsList() {
		
		studentList = StudentOperation.getStudents();
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
		StudentOperation.updateStudent(retorno);
		return "/studentsList.xhtml?faces-redirect=true";
	}

	public String deleteStudentRecord(int studentId) {
		StudentOperation.deleteStudent(studentId);
		return "/studentsList.xhtml?faces-redirect=true";
	}

	public String saveStudentDetails(Student editRecordObj) {
		StudentOperation.createStudent(editRecordObj);

		return "/studentsList.xhtml?faces-redirect=true";
	}

}
