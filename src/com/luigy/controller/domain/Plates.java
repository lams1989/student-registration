package com.luigy.controller.domain;

public class Plates {
	private int id;
	private String platename;
	private double publicprice;
	private int available;
	private int categori;
	
	public Plates() {
		super();
	}
	
	public Plates(int id, String platename, double publicprice, int available, int categori) {
		super();
		this.id = id;
		this.platename = platename;
		this.publicprice = publicprice;
		this.available = available;
		this.categori = categori;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlatename() {
		return platename;
	}

	public void setPlatename(String platename) {
		this.platename = platename;
	}

	public double getPublicprice() {
		return publicprice;
	}

	public void setPublicprice(double publicprice) {
		this.publicprice = publicprice;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public int getCategori() {
		return categori;
	}

	public void setCategori(int categori) {
		this.categori = categori;
	}

	
}
