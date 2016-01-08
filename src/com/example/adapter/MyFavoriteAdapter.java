package com.example.adapter;

import java.util.List;

import com.example.lazy_man.R;
import com.example.vo.Service;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;

public class MyFavoriteAdapter extends ImageAsyncLoaderAdpter<Service> {

	public MyFavoriteAdapter(Context context, AbsListView absListView) {
		super(context, absListView);
	}

	public MyFavoriteAdapter(Context context, AbsListView absListView, List<Service> list) {
		super(context, absListView, list);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		ServiceViewHolder holder;
		if (convertView == null) {
			view = inflate(R.layout.my_favorite_listitem, null);
			holder = new ServiceViewHolder();
			holder.serviceIv = (ImageView) view.findViewById(R.id.myfavorite_product_img);
			holder.tvName = (TextView) view.findViewById(R.id.myfavorite_title_text);
			holder.tvPrice = (TextView) view.findViewById(R.id.myfavorite_price_text);
			holder.tvMkPrice = (TextView) view.findViewById(R.id.myfavorite_nostock_text);
			view.setTag(R.layout.my_favorite_listitem, holder);
		} else {
			view = convertView;
			holder = (ServiceViewHolder) view.getTag(R.layout.my_favorite_listitem);
		}
		
		Service item = getItem(position);
		holder.serviceIv.setBackgroundResource(R.drawable.service_loading);
		holder.tvName.setText(item.getName());
		holder.tvPrice.setText(item.getPrice() + "");
		holder.tvMkPrice.setText(item.getMarketprice() + "");
		view.setTag(position);
		loadImage(position, item.getPic());
		return view;
	}
	@Override
	public void onImageLoadFinish(Integer position, Drawable drawable) {
		View view = mListView.findViewWithTag(position);
 		if (view != null) {
			ImageView iv = (ImageView) view.findViewById(R.id.myfavorite_product_img);
			iv.setBackgroundDrawable(drawable);
		}
	}
	

	public class ServiceViewHolder {
		ImageView serviceIv;
		TextView tvName;
		TextView tvPrice;
		TextView tvMkPrice;
	}
}
