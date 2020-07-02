package com.luigy.controller.domain;

public class Plate {
	private int cod;
	private String recipename;
	private String ingredients;
	private String process;
	private String platedtype;
	
	public Plate() {
		super();
	}
	
	public Plate(int cod, String recipename, String ingredients, String process, String platedtype) {
		super();
		this.cod = cod;
		this.recipename = recipename;
		this.ingredients = ingredients;
		this.process = process;
		this.platedtype = platedtype;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getRecipename() {
		return recipename;
	}

	public void setRecipename(String recipename) {
		this.recipename = recipename;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getPlatedtype() {
		return platedtype;
	}

	public void setPlatedtype(String platedtype) {
		this.platedtype = platedtype;
	}
	
}

