package com.example.adapter;

import java.util.List;

import com.example.lazy_man.R;
import com.example.vo.ServiceListVo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;



public class ServiceAdapter extends ImageAsyncLoaderAdpter<ServiceListVo>{
	
	public ServiceAdapter(Context context, AbsListView absListView) {
		super(context, absListView);
	}

	public ServiceAdapter(Context context, AbsListView absListView, List<ServiceListVo> list) {
		super(context, absListView, list);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		ServiceViewHolder holder;
		if (convertView == null) {
			view = inflate(R.layout.service_list_items, null);
			holder = new ServiceViewHolder();
			holder.serviceIv = (ImageView) view.findViewById(R.id.servicesIconIv);
			holder.tvName = (TextView) view.findViewById(R.id.textServiceName);
			holder.tvPrice = (TextView) view.findViewById(R.id.textServicePrice);
			holder.tvMkPrice = (TextView) view.findViewById(R.id.textMarketPrice);
			holder.commNum = (TextView) view.findViewById(R.id.textServiceCommentNum);
			view.setTag(R.layout.service_list_items, holder);
		} else {
			view = convertView;
			holder = (ServiceViewHolder) view.getTag(R.layout.service_list_items);
		}
		
		ServiceListVo item = getItem(position);
		holder.serviceIv.setBackgroundResource(R.drawable.service_loading);
		holder.tvName.setText(item.getName());
		holder.tvPrice.setText("会员价:" + item.getPrice() + "");
		holder.tvMkPrice.setText("市场价:" + item.getMarketprice() + "");
		holder.commNum.setText(item.getComment_count() + "");
		view.setTag(position);
		loadImage(position, item.getPic());
		return view;
	}
	
	@Override
	public void onImageLoadFinish(Integer position, Drawable drawable) {
		View view = mListView.findViewWithTag(position);
 		if (view != null) {
			ImageView iv = (ImageView) view.findViewById(R.id.servicesIconIv);
			iv.setBackgroundDrawable(drawable);
		}
	}
	

	public class ServiceViewHolder {
		ImageView serviceIv;
		TextView tvName;
		TextView tvPrice;
		TextView tvMkPrice;
		TextView commNum;
	}

}
