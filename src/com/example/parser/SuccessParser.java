package com.example.parser;

import org.json.JSONException;

import android.text.TextUtils;

/**
 * 请求是否成功   
 * @author Dontouch
 *
 */
public class SuccessParser extends BaseParser<Boolean> {

	@Override
	public Boolean parseJSON(String paramString) throws JSONException {
		// TODO Auto-generated method stub
		if(!TextUtils.isEmpty(checkResponse(paramString))){
			return true;   //如果请求成功的话    成功的是返回的true
		}
		return false;
	}

}
