package com.example.adapter;

import java.util.List;

import com.example.lazy_man.R;
import com.example.vo.HomeCategory;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 主页栏目的适配器
 * @author Dontouch
 *
 */
public class HomeAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private List<HomeCategory> categroy;
	
	
	/**
	 * 构造函数
	 * @param context
	 * @param categroy
	 */
	public HomeAdapter(Context context, List<HomeCategory> categroy) {
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.categroy = categroy;
	}
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return categroy.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return categroy.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	/**
	 * 
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view;
		HomeCategory item = categroy.get(position);
		if(convertView == null)
			view = inflater.inflate(R.layout.home_item, null);
		else
			view = convertView;
		
		((TextView) view.findViewById(R.id.textContent)).setText(item.getTitle());
		((ImageView)view.findViewById(R.id.imgIcon)).setBackgroundResource(item.getImgresid());
		return view;
	}

	
}
