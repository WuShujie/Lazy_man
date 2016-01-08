package com.example.lazy_man;

import java.util.ArrayList;
import java.util.List;

import com.example.adapter.HomeAdapter;
import com.example.adapter.HomeBannerAdapter;
import com.example.parser.HomeBannerParser;
import com.example.util.Constant;
import com.example.util.Logger;
import com.example.vo.HomeBanner;
import com.example.vo.HomeCategory;
import com.example.vo.HomeGallery;
import com.example.vo.RequestVo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

/***
 * 主页面   主页面   重要的页面
 * @author Dontouch
 *
 */
public class HomeActivity extends BaseWapperActivity implements OnItemClickListener, OnItemSelectedListener{

	//标志
	private static final String TAG = "HomeActivity";
	
	private ListView mCategoryListView;
	private Gallery mGallery;
	private List<ImageView> mSlideViews;
	private TextView searchEdit;
	private boolean isPlay;
	
	private Runnable runnable = new Runnable()
	{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			if(! isPlay)
				return;
			
			//图片的自动切换
			mGallery.setSelection(mGallery.getSelectedItemPosition() + 1);
			handler.postDelayed(this, 4000);
 			Logger.d(TAG, "下一张");  //标记 方便调试
			
		}
		
	};
	
	private Handler handler = new Handler();
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.home_searchok: //搜索确定按钮
			String words = searchEdit.getText().toString();
			if (TextUtils.isEmpty(words)) {
				Toast.makeText(this, "请输入关键词", Toast.LENGTH_LONG).show();
				return ;
			}
			Intent Intent = new Intent(this, SearchServiceListActivity.class);
			Intent.putExtra("keyWord", words);
			startActivity(Intent);
			break;
		}
	
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		//gallery = (Gallery) findViewById(R.id.gallery);
		mCategoryListView = (ListView) findViewById(R.id.custonInfoListView);
		mGallery = (Gallery) findViewById(R.id.gallery);
		mSlideViews = new ArrayList<ImageView>();
		mSlideViews.add((ImageView) findViewById(R.id.imgPoint0));
		mSlideViews.add((ImageView) findViewById(R.id.imgPoint1));
		mSlideViews.add((ImageView) findViewById(R.id.imgPoint2));
		mSlideViews.add((ImageView) findViewById(R.id.imgPoint3));
		mSlideViews.add((ImageView) findViewById(R.id.imgPoint4));
		searchEdit = (TextView) findViewById(R.id.editSearchInfo);
		findViewById(R.id.home_searchok).setOnClickListener(this);
	}

	@Override
	protected void loadViewLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.home_activity);
		//设置页面的头
		setHeadLeftVisibility(View.INVISIBLE);
		setHeadBackgroundResource(R.drawable.head_bg_home);
		selectedBottomTab(Constant.HOME);
	}

	@Override
	protected void processLogic() {
		// TODO Auto-generated method stub
		List<HomeCategory> categroy = new ArrayList<HomeCategory>();
		categroy.add(new HomeCategory(R.drawable.home_classify_01, "限时抢购"));
		categroy.add(new HomeCategory(R.drawable.home_classify_02, "团购活动"));
		categroy.add(new HomeCategory(R.drawable.home_classify_03, "新品上架"));
		categroy.add(new HomeCategory(R.drawable.home_classify_04, "服务热卖"));
		categroy.add(new HomeCategory(R.drawable.home_classify_05, "商家推荐"));
		mCategoryListView.setAdapter(new HomeAdapter(this, categroy));
		RequestVo reqVo = new RequestVo(R.string.url_home, this, null, new HomeBannerParser());
		getDataFromServer(reqVo, new DataCallback<List<HomeBanner>>() {
			@Override
			public void processData(List<HomeBanner> paramObject, boolean paramBoolean) {
				HomeBannerAdapter adapter = new HomeBannerAdapter(HomeActivity.this, paramObject);
				mGallery.setAdapter(adapter);

			}
		});
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		mGallery.setOnItemSelectedListener(this);
		mCategoryListView.setOnItemClickListener(this);
	}
	
	/**
	 * 首页栏点击
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		switch (position) {
		case 0://限时抢购
			startActivity(new Intent(this,LimitbuyActivity.class));
			break;
		case 1://团购活动
			startActivity(new Intent(this, BulletinActivity.class));
			break;
		case 2://新品上架
			startActivity(new Intent(this,NewserviceActivity.class));
			break;
		case 3://服务热卖
			startActivity(new Intent(this,HotserviceActivity.class));
			break;
		case 4://商家推荐
			startActivity(new Intent(this,CompanyActivity.class));
			break;
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
 		int size = mSlideViews.size();
		for (int i = 0; i < size; i++) {
			int j = position % size;
			ImageView imageView = mSlideViews.get(i);
			if (j == i)
				imageView.setBackgroundResource(R.drawable.slide_adv_selected);
			else
				imageView.setBackgroundResource(R.drawable.slide_adv_normal);
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		isPlay = true;
		runnable.run();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		isPlay = false;
	}

    
}
