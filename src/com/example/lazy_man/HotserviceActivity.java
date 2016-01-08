package com.example.lazy_man;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.adapter.ServiceAdapter;
import com.example.parser.ServiceListParser;
import com.example.vo.RequestVo;
import com.example.vo.ServiceListVo;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;


/**
 * ���ŷ���
 * @author Dontouch
 *
 */
public class HotserviceActivity extends BaseWapperActivity {
	
	private List<ServiceListVo> List;
	private ListView listView;
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void findViewById() {
		listView = (ListView) findViewById(R.id.promBulldtinLv);
		System.out.println(listView==null);
	}
	
	//���ز����ļ�
	@Override
	protected void loadViewLayout() {
		setContentView(R.layout.prom_bulletin_activity);
		List = new ArrayList<ServiceListVo>();
		setTitle("��������");
		
	}
	//ִ���߼�
	@Override
	protected void processLogic() {
		RequestVo reqVo = new RequestVo();
		reqVo.requestUrl = R.string.url_hotservice;
		reqVo.context = context;
		HashMap<String, String> requestDataMap = new HashMap<String, String>();
		
		requestDataMap.put("page", "");
		requestDataMap.put("pageNum", "");
		reqVo.requestDataMap = requestDataMap;
		
		reqVo.jsonParser = new ServiceListParser();
		
		super.getDataFromServer(reqVo, new DataCallback<List<ServiceListVo>>() {

			@Override
			public void processData(List<ServiceListVo> paramObject,
					boolean paramBoolean) {
				List = paramObject;
				ServiceAdapter newserviceAdapter = new ServiceAdapter(context, listView ,List);
				listView.setAdapter(newserviceAdapter);
			}

			
		});
	}
	//���ü����¼�
	@Override
	protected void setListener() {
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ServiceListVo vo = (ServiceListVo) listView.getItemAtPosition(position);
				//String prodId = vo.getId()+"";
				Intent producutlistIntent = new Intent(HotserviceActivity.this,ServiceDetailActivity.class);
				//��ID���ݵ���Ʒ������ʾ�У���ʾ�������
				producutlistIntent.putExtra("id",  vo.getId());
				//��ת���µ�activity
				startActivity(producutlistIntent);
			}
		});
		
	}
}
