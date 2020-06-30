package com.luigy.controller.domain;

public class Categori {
	private int id;
	private String categoriname;
	
	public Categori() {
		super();
	}
	
	public Categori(int id, String categoriname) {
		super();
		this.id = id;
		this.categoriname = categoriname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoriname() {
		return categoriname;
	}

	public void setCategoriname(String categoriname) {
		this.categoriname = categoriname;
	}
	
	
}
