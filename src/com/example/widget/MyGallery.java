package com.example.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Gallery;

/**
 * 自定义的Gallery
 * @author Dontouch
 *
 */
public class MyGallery extends Gallery{

	public MyGallery(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public MyGallery(Context context , AttributeSet attr)
	{
		super(context , attr);
	}

	/**
	 * 滑动的
	 */
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		return  false;
	}
	
	

}
