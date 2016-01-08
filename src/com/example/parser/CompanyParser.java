package com.example.parser;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.example.vo.CompanyCategory;

public class CompanyParser extends BaseParser<List<CompanyCategory>> {

	@Override
	public List<CompanyCategory> parseJSON(String paramString)
			throws JSONException {
		// TODO Auto-generated method stub
		if(super.checkResponse(paramString)!=null){
			JSONObject jsonObject = new JSONObject(paramString);
			String productlist = jsonObject.getString("brand");
			return JSON.parseArray(productlist, CompanyCategory.class);
		}else{
		return null;
		}
	}

}
