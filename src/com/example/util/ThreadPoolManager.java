package com.example.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 
 * @author Dontouch  Ïß³Ì³Ø
 *
 */
public class ThreadPoolManager {
	
	private ExecutorService service;
	
	private ThreadPoolManager()
	{
		int num  = Runtime.getRuntime().availableProcessors();
		service = Executors.newFixedThreadPool(num *2);
	}
	
	private static final ThreadPoolManager manager  = new ThreadPoolManager();
	
	public static ThreadPoolManager getInstance()
	{
		return manager;
		
	}
	
	public void addTask(Runnable runnable)
	{
		service.execute(runnable);
	}
	

}
