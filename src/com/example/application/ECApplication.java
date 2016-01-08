package com.example.application;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.example.service.ECServiceManager;
import com.example.service.IECManager;
import com.example.util.Logger;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Environment;
import android.os.IBinder;

public class ECApplication extends Application {
	
	private static String cacheDir;//»º´æÂ·¾¶
	
	private IECManager ecManager;
	private List<Activity> records = new ArrayList<Activity>();
	
	//±ê¼Ç
	private static final String  TAG = "ECApplication";

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		bindService(new Intent(this, ECServiceManager.class), new ECServiceConnection(), Context.BIND_AUTO_CREATE);
		initCacheDirPath();
	}
	
	
	
	private class ECServiceConnection implements ServiceConnection{

		@Override
		public void onServiceConnected(ComponentName name, IBinder service){
			// TODO Auto-generated method stub
			Logger.d(TAG, "onServiceConnected");
			ecManager = (IECManager) service;
		}

		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private void initCacheDirPath()
	{
		File f; 
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
		{
			f = new File(Environment.getExternalStorageDirectory() + "/.lazyman/");
			if (!f.exists()) {
				f.mkdir();
			}
		}else{
			f= getApplicationContext().getCacheDir();
		}
		cacheDir = f.getAbsolutePath();
	}
	
	
	
	public static String getCacheDirPath()
	{
		return cacheDir;
	}
	
	public IECManager getEcManager()
	{
		return ecManager;
	}
	
	
	public void addActvity(Activity activity) {
		records.add(activity);
		Logger.d(TAG, "Current Acitvity Size :" + getCurrentActivitySize());
	}
	
	public void removeActvity(Activity activity) {
		records.remove(activity);
		Logger.d(TAG, "Current Acitvity Size :" + getCurrentActivitySize());
	}
	
	public void exit() {
		for (Activity activity : records) {
			activity.finish();
		}
 	}
	
	public int getCurrentActivitySize() {
		return records.size();
 	}
	

}
