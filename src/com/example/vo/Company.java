package com.example.vo;

public class Company {
	private int id;
	private String name;
	private String pic;

	public Company() {
	}

	public Company(int id, String name, String pic) {
		super();
		this.id = id;
		this.name = name;
		this.pic = pic;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}
}
