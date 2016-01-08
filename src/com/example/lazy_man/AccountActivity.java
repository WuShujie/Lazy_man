package com.example.lazy_man;

import com.example.parser.SuccessParser;
import com.example.parser.UserinfoParser;
import com.example.util.Constant;
import com.example.util.Logger;
import com.example.vo.RequestVo;
import com.example.vo.User;


import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AccountActivity extends BaseWapperActivity {

	private static final String TAG = "AccountActivity";
	private TextView my_name_text; // �û���
	private TextView my_bonus_text; // ����
	private TextView my_level_text; // �ȼ�
	private LinearLayout ll_account_myorder; // �ҵĶ���
	private LinearLayout ll_account_address_manage; // ��ַ����
	private LinearLayout ll_account_conservation; // �ղؼ�
	private SharedPreferences sp;

	private User info;
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.ll_account_myorder:
			Logger.d(TAG, "��ת�ҵĶ���activity");
			Intent orderIntent = new Intent(this, OrderListActivity.class);
			orderIntent.putExtra("totoalOrderCount", info.getOrdercount());
			startActivity(orderIntent);
			break;
		case R.id.ll_account_address_manage:
			Logger.d(TAG, "��ת��ַ����activity");
			Intent addressManagerIntent = new Intent(this, AddressManageActivity.class);
			startActivity(addressManagerIntent);
			break;
		case R.id.ll_account_conservation:
			Logger.d(TAG, "��ת�ղؼ�activity");
			Intent myFavoriteIntent = new Intent(this, MyFavoriteActivity.class);
			myFavoriteIntent.putExtra("totalFavoriteCount", info.getFavoritescount());
			startActivity(myFavoriteIntent);
			break;
		}
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		my_name_text = (TextView) this.findViewById(R.id.my_name_text);
		my_bonus_text = (TextView) this.findViewById(R.id.my_bonus_text);
		my_level_text = (TextView) this.findViewById(R.id.my_level_text);
		ll_account_myorder = (LinearLayout) this.findViewById(R.id.ll_account_myorder);
		ll_account_address_manage = (LinearLayout) this.findViewById(R.id.ll_account_address_manage);
		ll_account_conservation = (LinearLayout) this.findViewById(R.id.ll_account_conservation);
	}

	@Override
	protected void loadViewLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.my_account_activity);
		setHeadRightVisibility(View.VISIBLE);
		setHeadRightText("�˳�");
		selectedBottomTab(Constant.MORE);
		setTitle(R.string.my_account_title);
		sp = getSharedPreferences("userinfo", MODE_PRIVATE);
	}

	@Override
	protected void processLogic() {
		// TODO Auto-generated method stub
		RequestVo vo = new RequestVo();
		vo.context = context;
		vo.requestUrl = R.string.userinfo;
		vo.jsonParser = new UserinfoParser();
		super.getDataFromServer(vo, new DataCallback<User>(){

			@Override
			public void processData(User paramObject, boolean paramBoolean) {
				// TODO Auto-generated method stub
				if(paramObject != null){
					info = paramObject;
					
					my_bonus_text.setText(info.getBonus() + "");
					my_level_text.setText(info.getLevel() + "");
					String username = sp.getString("userName", "");
					Logger.d(TAG, "userName:"+username);
					my_name_text.setText(username);
				}
			}
			
		});
		
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		//��������
		ll_account_myorder.setOnClickListener(this);
		ll_account_address_manage.setOnClickListener(this);
		ll_account_conservation.setOnClickListener(this);
	}

	/***
	 * �Ա���ͷ�����ݽ���ˢ��ʱ ��������������
	 */
	@Override
	protected void onHeadRightButton(View v) {
		// TODO Auto-generated method stub
		RequestVo vo = new RequestVo(R.string.url_logout, context, null, new SuccessParser());
		super.getDataFromServer(vo, new DataCallback<Boolean>(){

			@Override
			public void processData(Boolean paramObject, boolean paramBoolean) {
				// TODO Auto-generated method stub
				startActivity(new Intent(AccountActivity.this, HomeActivity.class));   //���ص�����������
			}
			
		});
	}

	
	
}
