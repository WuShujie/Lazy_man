package com.example.parser;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.example.vo.HomeGallery;

public class HomeParser extends BaseParser<List<HomeGallery>> {

	@Override
	public List<HomeGallery> parseJSON(String paramString) throws JSONException {
		
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject(paramString);
		String response = json.getString("response");
		if(response != null && !response.equals("error")){
			String banner = json.getString("home_banner");
			List<HomeGallery> bannerList = JSON.parseArray(banner,HomeGallery.class);
			return bannerList;
		}
		return null;
	}

}