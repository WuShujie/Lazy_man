package com.example.parser;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.example.vo.ServiceListVo;


public class ServiceListParser extends BaseParser<List<ServiceListVo>> {

	@Override
	public List<ServiceListVo> parseJSON(String paramString) throws JSONException {
		if(super.checkResponse(paramString)!=null){
			JSONObject jsonObject = new JSONObject(paramString);
			String productlist = jsonObject.getString("productlist");
 			return JSON.parseArray(productlist, ServiceListVo.class);
		}else{
		return null;
		}
	}

}
