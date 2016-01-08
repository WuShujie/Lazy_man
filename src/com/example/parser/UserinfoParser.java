package com.example.parser;

import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.example.util.Logger;
import com.example.vo.User;

/**
 * ��Userinfo���ݵĽ���
 * @author Dontouch
 *
 */
public class UserinfoParser extends BaseParser<User> {

	private static final String TAG = "UserinfoParser";
	
	@Override
	public User parseJSON(String paramString) throws JSONException {
		// TODO Auto-generated method stub
		if(super.checkResponse(paramString)!=null)
		{
			//��־
			Logger.d(TAG, "����Userinfo����");
			JSONObject json = new JSONObject(paramString);
			String useinfoObj = json.getString("userinfo");
			User userInfoList = JSON.parseObject(useinfoObj, User.class);
			return userInfoList;
		}
		return null;
	}

}
