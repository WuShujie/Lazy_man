package com.example.lazy_man;

import com.example.adapter.SearchAdapter;
import com.example.parser.SearchRecommondParser;
import com.example.util.CommonUtil;
import com.example.util.Constant;
import com.example.vo.RequestVo;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class SearchActivity extends BaseWapperActivity {
	private EditText keyWordEdit;
	private ListView hotWordsLv;
	private String[] search;

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.searchTv:

			break;
		}
	}

	@Override
	protected void onHeadRightButton(View v) {
		String keyWord = keyWordEdit.getText().toString();
		if (keyWord == null || "".equals(keyWord)) {
			CommonUtil.showInfoDialog(SearchActivity.this, "ÇëÊäÈë¹Ø¼ü×Ö");
			return;
		}
		Intent intent = new Intent(SearchActivity.this,
				SearchServiceListActivity.class);
		intent.putExtra("keyword", keyWord);
		startActivity(intent);
	}

	@Override
	protected void findViewById() {
		hotWordsLv = (ListView) findViewById(R.id.hotWordsLv);
		keyWordEdit = (EditText) findViewById(R.id.keyWordEdit);
		Intent intent = new Intent();

	}

	@Override
	protected void loadViewLayout() {
		setContentView(R.layout.search_activity);
		setHeadLeftVisibility(View.INVISIBLE);
		setTitle("ËÑË÷");
		setHeadRightText("ËÑË÷");
		setHeadRightVisibility(View.VISIBLE);
		selectedBottomTab(Constant.SEARCH);
	}

	@Override
	protected void processLogic() {
		showProgressDialog();
		RequestVo vo = new RequestVo();
		vo.context = SearchActivity.this;
		vo.jsonParser = new SearchRecommondParser();
		vo.requestUrl = R.string.searchRecommend;
		super.getDataFromServer(vo, new DataCallback<String[]>() {

			@Override
			public void processData(String[] paramObject, boolean paramBoolean) {
				if (paramObject != null) {
					search = paramObject;
					SearchAdapter adapter = new SearchAdapter(context,
							paramObject);
					hotWordsLv.setAdapter(adapter);
					closeProgressDialog();
				}
			}
		});
	}

	@Override
	protected void setListener() {
		hotWordsLv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			}
		});
	}

}
