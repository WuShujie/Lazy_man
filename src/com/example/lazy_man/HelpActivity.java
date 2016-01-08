package com.example.lazy_man;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.adapter.HelpListViewAdapter;
import com.example.parser.HelpParser;
import com.example.util.Constant;
import com.example.vo.HelpVo;
import com.example.vo.RequestVo;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;


public class HelpActivity extends BaseWapperActivity {
 	private ListView lv_help_info;
	Map<String,Object> helpMap;
	@Override
	public void onClick(View v) {
		 
	}

	@Override
	protected void findViewById() {
 		lv_help_info = (ListView) findViewById(R.id.listHelpInfo);
	}

	@Override
	protected void loadViewLayout() {
		this.setContentView(R.layout.help_activity);
		setTitle("°ïÖúÖÐÐÄ");
		selectedBottomTab(Constant.MORE);
	}

	@Override
	protected void processLogic() {
		RequestVo requestVo = new RequestVo();
		requestVo.requestUrl = R.string.help;
		requestVo.context = context;
		requestVo.jsonParser = new HelpParser();
		super.getDataFromServer(requestVo, new DataCallback<Map<String,Object>>() {

			@Override
			public void processData(Map<String,Object> paramObject,
					boolean paramBoolean) {
				if(paramObject!=null){
					HelpListViewAdapter adapter = new HelpListViewAdapter(context, paramObject, lv_help_info);
					lv_help_info.setAdapter(adapter);
					helpMap = paramObject;
				}
			}
		});

	}

	@Override
	protected void setListener() {
 		lv_help_info.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				List helpList = (ArrayList) helpMap.get("help");
				HelpVo helpVo = (HelpVo)helpList.get(position);
				String detail_url = helpVo.getDetail_url();
				Intent intent = new Intent(context,HelpDetailActivity.class);
				intent.putExtra("detail_url", detail_url);
				startActivity(intent);
				
				
			}
		});
		
	}

}