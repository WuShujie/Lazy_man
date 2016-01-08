package com.example.dao;

import com.example.util.DBUtil;
import com.example.util.Logger;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public abstract class BaseDao {
	
	
	
	protected SQLConnection sqlConnection;
	protected Cursor cursor;
	protected static final int TYPE_READ = 0; // 数据库只读类型
	protected static final int TYPE_WRITE = 1;// 数据库写类型
	private static final String TAG = "BaseDao";

	public BaseDao (Context context)
	{
		sqlConnection = new  SQLConnection(context);
	}
	
	
	/**
	 * 重要  就是回调函数
	 * @param type
	 * @param back
	 * @return
	 */
	protected<T> T callBack(int type , DaoCallBack<T> back)
	{
		
		T result  = null;
		
		SQLiteDatabase conn = null;
		try
		{
			switch (type)
			{
			case  TYPE_READ:
				conn = sqlConnection.getReadableDatabase();
				break;
			case  TYPE_WRITE:
				conn = sqlConnection.getWritableDatabase();
				break;
				  
			}
		}catch (Exception ex)
		{
			Logger.e(TAG,ex);
		}finally
		{
			DBUtil.Release(conn,cursor);
		}
		return result;
		
	}
	
	/**
	 * 定义的是回调的接口
	 * @author Dontouch
	 *
	 * @param <T>
	 */
	interface DaoCallBack<T>
	{
		T invoke(SQLiteDatabase conn);
	}
	
}
