package com.example.parser;

import java.util.List;

import org.json.JSONException;

import android.text.TextUtils;

import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.example.vo.HomeBanner;


/**
 * ºá·ù
 * @author Dontouch
 *
 */
public class HomeBannerParser extends BaseParser<List<HomeBanner>> {

	@Override
	public List<HomeBanner> parseJSON(String paramString) throws JSONException {
		// TODO Auto-generated method stub
		if(!TextUtils.isEmpty(checkResponse(paramString))){
			JSONObject j = new JSONObject();
			return JSON.parseArray(j.getString("home_banner"), HomeBanner.class);
			
		}
		return null;
	}

}
