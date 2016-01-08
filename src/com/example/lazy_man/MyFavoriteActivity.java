package com.example.lazy_man;

import java.util.HashMap;
import java.util.List;

import com.example.adapter.MyFavoriteAdapter;
import com.example.parser.FavoriteParser;
import com.example.util.Constant;
import com.example.util.Logger;
import com.example.vo.PageVo;
import com.example.vo.RequestVo;
import com.example.vo.Service;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;


public class MyFavoriteActivity extends BaseWapperActivity {
	private static final String TAG = "MyFavoriteActivity";
	
	private ListView myfavorite_product_list;
	private PageVo pageVo;
	private View pageView;
	TextView previousPage;
	TextView nextPage;
	TextView textPage;
	MyFavoriteAdapter adapter;
	private List<Service> data;
	
	@Override
	protected void onHeadRightButton(View v) {
		data.clear();
		// 分页处理
		if (pageView != null) {
			myfavorite_product_list.removeFooterView(pageView);
		}
		adapter.notifyDataSetChanged();
		setHeadRightVisibility(View.INVISIBLE);
		setContentView(R.layout.empty);
		TextView tv = (TextView) this.findViewById(R.id.empty_text);
		tv.setText("您的收藏夹还没有商品哦！亲");
	};
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.my_page_previous:
			Logger.d(TAG, "显示前一页------1");
			Logger.d(TAG,pageVo.wantedPageNum+"");
			if(pageVo.wantedPageNum<=1){
				previousPage.setClickable(false);
				return;
			}
			processLogic();
			pageVo.wantedPageNum -= 1;
			Logger.d(TAG, "显示前一页------2");
			Logger.d(TAG,pageVo.wantedPageNum+"");
			textPage.setText(pageVo.wantedPageNum+"/"+pageVo.totalPageNum);
			break;
		case R.id.my_page_next:
			Logger.d(TAG, "显示下一页-------1");
			Logger.d(TAG,pageVo.wantedPageNum+"");
			if(pageVo.wantedPageNum>=pageVo.totalPageNum){
				nextPage.setClickable(false);
				return;
			}
			processLogic();
			pageVo.wantedPageNum += 1;
			Logger.d(TAG, "显示下一页-------2");
			Logger.d(TAG,pageVo.wantedPageNum+"");
			textPage.setText(pageVo.wantedPageNum+"/"+pageVo.totalPageNum);
			break;
		}
	}

	@Override
	protected void findViewById() {
		myfavorite_product_list = (ListView) this
				.findViewById(R.id.myfavorite_product_list);

	}

	@Override
	protected void loadViewLayout() {
		setContentView(R.layout.my_favorite_activity);
		setHeadLeftVisibility(View.VISIBLE);
		setHeadRightVisibility(View.VISIBLE);
		setHeadRightText("清空");
		setTitle(R.string.my_favorite_title);
		setHeadBackgroundResource(R.drawable.head_bg);
		selectedBottomTab(Constant.HOME);
		int totalFavoriteCount = getIntent().getIntExtra("totalFavoriteCount",
				0);
		Logger.d(TAG, totalFavoriteCount + "------------");
		pageVo = new PageVo(0, totalFavoriteCount, 1);
	}

	@Override
	protected void processLogic() {
		RequestVo favoriteRequeset = new RequestVo();
		favoriteRequeset.context = this;
		favoriteRequeset.jsonParser = new FavoriteParser();
		favoriteRequeset.requestUrl = R.string.favorites;
		favoriteRequeset.requestDataMap = new HashMap<String, String>();
		favoriteRequeset.requestDataMap.put("page", pageVo.wantedPageNum + "");
		favoriteRequeset.requestDataMap.put("pageNum", pageVo.pageLenth + "");
		getDataFromServer(favoriteRequeset, new DataCallback<List<Service>>() {
			@Override
			public void processData(List<Service> paramObject,
					boolean paramBoolean) {
				data = paramObject;
				// 分页处理
				if (pageView != null) {
					myfavorite_product_list.removeFooterView(pageView);
				}
				if (paramObject.size() >= pageVo.pageLenth) {
					showPageBar();
				}
				adapter  = new MyFavoriteAdapter(MyFavoriteActivity.this,myfavorite_product_list,data);
				myfavorite_product_list.setAdapter(adapter);
			}
		});
	}

	private void showPageBar() {
		pageView = View.inflate(MyFavoriteActivity.this, R.layout.page, null);
		previousPage = (TextView) pageView.findViewById(R.id.my_page_previous);
		nextPage = (TextView) pageView.findViewById(R.id.my_page_next);
		textPage = (TextView) pageView.findViewById(R.id.my_page_text);
		previousPage.setOnClickListener(this);
		nextPage.setOnClickListener(this);
		textPage.setText(pageVo.wantedPageNum + "/" + pageVo.totalPageNum);
		myfavorite_product_list.addFooterView(pageView);
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub

	}

}
