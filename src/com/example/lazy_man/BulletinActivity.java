package com.example.lazy_man;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.adapter.BulletinLvAdapter;
import com.example.parser.BulletinParser;
import com.example.util.Logger;
import com.example.vo.BulletinVo;
import com.example.vo.RequestVo;


import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;


public class BulletinActivity  extends BaseWapperActivity{

	protected static final String TAG = "BulletinActivity";
	
	private ListView ll_bulletin_prom;
	private List<BulletinVo> bulletinInfos;
	
	@Override
	public void onClick(View v) {

	}

	@Override
	protected void findViewById() {
		ll_bulletin_prom = (ListView) findViewById(R.id.promBulldtinLv);
	}

	@Override
	protected void loadViewLayout() {
		setContentView(R.layout.prom_bulletin_activity);
		bulletinInfos = new ArrayList<BulletinVo>();
		setTitle("�Ź��");
	}

	@Override
	protected void processLogic() {
		
		RequestVo bulletinReqVo = new RequestVo();
		bulletinReqVo.requestUrl =R.string.url_topic; //�ر�ע��
		bulletinReqVo.context= context;
		HashMap<String,String> paramMap = new HashMap<String, String>();
		paramMap.put("page", "");
		paramMap.put("pageNum", "");
		bulletinReqVo.requestDataMap = paramMap;
		
		bulletinReqVo.jsonParser = new BulletinParser();
		
		super.getDataFromServer(bulletinReqVo, new DataCallback<List<BulletinVo>>(){

			
			@Override
			public void processData(List<BulletinVo> paramObject,
					boolean paramBoolean) {
				//������ݺ����listView���ݵ����
				bulletinInfos = paramObject;
				BulletinLvAdapter bulletinAdaper = new BulletinLvAdapter(context, ll_bulletin_prom, bulletinInfos);
				ll_bulletin_prom.setAdapter(bulletinAdaper);
			}
			
		});
		
		
		
	}

	@Override
	protected void setListener() {
		ll_bulletin_prom.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
 				BulletinVo vo = (BulletinVo) ll_bulletin_prom.getItemAtPosition(position);
				String prodId = vo.getId();
				Intent producutlistIntent = new Intent(BulletinActivity.this,TopicServiceListActivity.class);
				//��ID���ݵ���Ʒ������ʾ�У���ʾ�������
				producutlistIntent.putExtra("cId", prodId);
				//��ת���µ�activity
				startActivity(producutlistIntent);
				Logger.i(TAG, prodId);
			//	finish();//�Ƿ���Ҫ������ֱ�������������ã�ò�Ʋ���Ҫ���д����
			}
		});
	}
}
