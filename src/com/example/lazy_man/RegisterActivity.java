package com.example.lazy_man;

import java.util.HashMap;

import com.example.parser.LoginParser;
import com.example.util.CommonUtil;
import com.example.vo.RequestVo;
import com.example.vo.UserInfo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends BaseWapperActivity {
	private EditText login_name_edit;
	private EditText login_pwd_edit;
	private EditText login_pwd2_edit;
	private TextView register_text;
	private DataCallback<UserInfo> callback;
	private SharedPreferences sp;

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.register_text:
			String name = login_name_edit.getText().toString().trim();
			String pwd = login_pwd_edit.getText().toString().trim();
			String pwd2 = login_pwd2_edit.getText().toString().trim();
			if (name == null || "".equals(name)) {
				CommonUtil.showInfoDialog(this, "�û�������Ϊ��");
				return;
			}
			if (pwd == null || "".equals(pwd)) {
				CommonUtil.showInfoDialog(this, "���벻��Ϊ��");
				return;
			}
			if (pwd2 == null || "".equals(pwd2)) {
				CommonUtil.showInfoDialog(this, "���벻��Ϊ��");
				return;
			}
			if (!(CommonUtil.isValidMobiNumber(name) || CommonUtil
					.isValidEmail(name))) {
				CommonUtil.showInfoDialog(this, "�û�����������������ֻ���");
				return;
			}
			if (!pwd.equals(pwd2)) {
				CommonUtil.showInfoDialog(this, "�������벻ͬ");
				return;
			}

			RequestVo vo = new RequestVo();
			HashMap map = new HashMap();
			map.put("username", name);
			map.put("password", pwd);
			vo.requestDataMap = map;
			vo.requestUrl = R.string.register;
			vo.jsonParser = new LoginParser();
			vo.context = context;
			getDataFromServer(vo, callback);
			Editor ed = sp.edit();
			ed.putString("userName", name);
			ed.putString("userPwd", pwd);
			ed.commit();
			showProgressDialog();
			break;

		}
	}

	@Override
	protected void findViewById() {
		login_name_edit = (EditText) findViewById(R.id.login_name_edit);
		login_pwd_edit = (EditText) findViewById(R.id.login_pwd_edit);
		login_pwd2_edit = (EditText) findViewById(R.id.login_pwd2_edit);
		register_text = (TextView) findViewById(R.id.register_text);
	}

	@Override
	protected void loadViewLayout() {
		setContentView(R.layout.register_activity);
		sp = getSharedPreferences("userinfo", MODE_PRIVATE);
		setTitle("ע��");
	}

	@Override
	protected void processLogic() {
		sp = getSharedPreferences("userinfo", MODE_PRIVATE);
		DataCallback<UserInfo> callback = new DataCallback<UserInfo>() {

			@Override
			public void processData(UserInfo paramObject, boolean paramBoolean) {
				String userId = paramObject.userId;
				String usersession = paramObject.usersession;
				Editor ed = sp.edit();
				ed.putString("userId", userId);
				ed.putString("usersession", usersession);
				ed.commit();
				Intent intent = new Intent(RegisterActivity.this,
						AccountActivity.class);
				startActivity(intent);
				closeProgressDialog();

			}
		};

		this.callback = callback;
	}

	@Override
	protected void setListener() {
		register_text.setOnClickListener(this);

	}

}
