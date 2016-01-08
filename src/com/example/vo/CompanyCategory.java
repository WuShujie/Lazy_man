package com.example.vo;

import java.util.ArrayList;
import java.util.List;

public class CompanyCategory {
	
	//�������� 
	private String key;
	
	//��Ŀ�����е� Company
	private List<Company> value = new ArrayList<Company>();

	public CompanyCategory() {

	}
	public CompanyCategory(String key, List<Company> value) {
		super();
		this.key = key;
		this.value = value;
	}


	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<Company> getValue() {
		return value;
	}

	public void setValue(List<Company> value) {
		this.value = value;
	}
}
