package com.example.parser;

import org.json.JSONException;

import android.text.TextUtils;

/**
 * �����Ƿ�ɹ�   
 * @author Dontouch
 *
 */
public class SuccessParser extends BaseParser<Boolean> {

	@Override
	public Boolean parseJSON(String paramString) throws JSONException {
		// TODO Auto-generated method stub
		if(!TextUtils.isEmpty(checkResponse(paramString))){
			return true;   //�������ɹ��Ļ�    �ɹ����Ƿ��ص�true
		}
		return false;
	}

}
