package com.example.lazy_man;

import com.example.util.Constant;

import android.os.Bundle;
import android.view.View;


public class HelpDetailActivity extends BaseWapperActivity {
 	@Override
	public void onClick(View v) {
	 
	}

	@Override
	protected void findViewById() {
		 
	}

	@Override
	protected void loadViewLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.help_detail_activity);
		setTitle("°ïÖúÖÐÐÄ");
		selectedBottomTab(Constant.MORE);
		Bundle bundle = getIntent().getExtras();
		String detail_url = bundle.getString("detail_url");
		System.out.println(detail_url);

	}

	@Override
	protected void processLogic() {
 
	}

	@Override
	protected void setListener() {
  	}

}