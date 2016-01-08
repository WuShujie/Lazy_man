package com.example.parser;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.example.util.Logger;
import com.example.vo.OrderList;

public class OrderListParser extends BaseParser<List<OrderList>> {

	private static final String TAG = "OrderListParser";

	@Override
	public List<OrderList> parseJSON(String paramString) throws JSONException {
		Logger.d(TAG, "����OrderList�����б�����");
		if(paramString == null){
			return null;
		}else{
			JSONObject json = new JSONObject(paramString);
			String result = json.getString("response");
			if(result!=null && !result.equals("error")){
				String orderlist = json.getString("orderlist");
				List<OrderList> list = JSON.parseArray(orderlist, OrderList.class);
				return list;
			}else{
				return null;
			}
		}
	}
}
