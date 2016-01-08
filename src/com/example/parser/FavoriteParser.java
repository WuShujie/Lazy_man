package com.example.parser;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.example.util.Logger;
import com.example.vo.Service;

public class FavoriteParser extends BaseParser<List<Service>> {
	private static final String TAG = "FavoriteParser";
	@Override
	public List<Service> parseJSON(String paramString) throws JSONException {
		Logger.d(TAG, "解析收藏夹中的内容");
		JSONObject json = new JSONObject(paramString);
		String productlist = json.getString("productlist");
		List<Service> products = JSON.parseArray(productlist, Service.class);
		return products;
	}

}
