package com.example.service;


import java.util.List;

import com.example.dao.ServiceDao;
import com.example.util.Logger;
import com.example.vo.ServiceHistory;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class ECServiceManager extends Service {

	private MyIECManager myIECManager;
	private static final String TAG ="ECSerciceManager";
	
	private ServiceDao serviceDao;
	
	
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		Logger.d(TAG, "ECServiceManager is start");
		
		myIECManager = new MyIECManager();
		serviceDao = new ServiceDao(this);
		
		
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return myIECManager;
	}
	
	private class MyIECManager extends Binder implements IECManager
	{

		@Override
		public void addServiceToHistory(ServiceHistory history) {
			// TODO Auto-generated method stub
			//∑Ω±„—È÷§
			Logger.d(TAG,"addServiceToHistory"+history.toString());
			
			if(serviceDao.findById(history.getId()))
				serviceDao.update(history);
			else
				serviceDao.add(history);
		}

		@Override
		public void clearServiceHistory() {
			// TODO Auto-generated method stub
			serviceDao.deleteAll();
		}

		@Override
		public List<ServiceHistory> getAllServiceHistory() {
			// TODO Auto-generated method stub
			return serviceDao.getAll();
		}
		
	}

}
