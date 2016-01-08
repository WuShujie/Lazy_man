package com.example.parser;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.vo.UserInfo;

public class LoginParser extends BaseParser<UserInfo> {

	@Override
	public UserInfo parseJSON(String paramString) throws JSONException {
		// TODO Auto-generated method stub
		if (super.checkResponse(paramString) != null) {
			UserInfo localUserInfo = new UserInfo();
			JSONObject jsonObject = new JSONObject(paramString)
					.getJSONObject("userinfo");
			String userid = jsonObject.getString("userId");
			String usersession = jsonObject.getString("usersession");
			localUserInfo.userId = userid;
			localUserInfo.usersession = usersession;
			return localUserInfo;
		}
		return new UserInfo();
	}

}
