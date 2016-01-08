package com.example.parser;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.example.vo.AddressDetail;



/**
 * 地址的详细信息
 * @author Dontouch
 *
 */
public class AddressManageParser extends BaseParser<List<AddressDetail>> {

	@Override
	public List<AddressDetail> parseJSON(String paramString)
			throws JSONException {
		// TODO Auto-generated method stub
		if (!TextUtils.isEmpty(checkResponse(paramString))) {
			JSONObject j = new JSONObject(paramString);
			String addresslist = j.getString("addresslist");
			return JSON.parseArray(addresslist, AddressDetail.class);
		}
		return null;
	}

}
