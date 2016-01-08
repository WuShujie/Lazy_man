package com.example.vo;

public class HomeCategory {
	private int imageid;
	private String title;
	
	
	public HomeCategory() {
	}

	public HomeCategory(int imgresid, String title) {
		super();
		this.imageid = imgresid;
		this.title = title;
	}

	public int getImgresid() {
		return imageid;
	}

	public void setImgresid(int imgresid) {
		this.imageid = imgresid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	

}
