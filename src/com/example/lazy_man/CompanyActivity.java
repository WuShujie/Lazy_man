package com.example.lazy_man;

import java.util.ArrayList;
import java.util.List;

import com.example.adapter.CompanyAdapter;
import com.example.parser.CompanyParser;
import com.example.vo.CompanyCategory;
import com.example.vo.RequestVo;

import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;


public class CompanyActivity extends BaseWapperActivity{

	private List<CompanyCategory> list;
	private TextView textCompanyInfoNull;
	private TextView textTitle;
	private ExpandableListView expandableLV;
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		expandableLV =  (ExpandableListView) findViewById(R.id.listCompanyInfo);
		System.out.println(expandableLV==null);
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void loadViewLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.company_activity);
		list = new ArrayList<CompanyCategory>();
		setTitle("ÉÌ¼ÒÍÆ¼ö");
	}

	@Override
	protected void processLogic() {
		// TODO Auto-generated method stub
		RequestVo reqVo = new RequestVo();
		reqVo.requestUrl = R.string.url_company;
		reqVo.context = context;
		
		reqVo.jsonParser = new CompanyParser();
		
		super.getDataFromServer(reqVo, new DataCallback<List<CompanyCategory>>() {

			@Override
			public void processData(List<CompanyCategory> paramObject,
					boolean paramBoolean) {
				list = paramObject;
				CompanyAdapter companyAdapter = new CompanyAdapter(list, expandableLV, CompanyActivity.this);
				expandableLV.setAdapter(companyAdapter);
			}

			
		});
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		
	}

}
