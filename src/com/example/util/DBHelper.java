package com.example.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 本地数据库 与云端的数据库相互的结合
 * @author Dontouch
 *
 */
public class DBHelper {
	
	public static final String DB_NAME="LAZY_MAN";
	public static final int DB_VERSION = 1;
	private final SqlLiteHelper helper;
	private SQLiteDatabase db;
	
	
	private static class SqlLiteHelper extends SQLiteOpenHelper
	{
		

		public SqlLiteHelper(Context context) {
			super(context, DB_NAME, null, DB_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE T_CUST(ID INTEGER PRIMARY KEY,NAME TEXT NOT NULL,PWD TEXT NOT NULL) ");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL(" DROP TABLE IF EXISTS T_CUST");
		}
		
	}
	
	private DBHelper(Context context){
		helper = new SqlLiteHelper(context); 
		db = helper.getWritableDatabase();
	}
	
	
	public void insertCust(){
		ContentValues values = new ContentValues();
		values.put("ID", 1);
		values.put("NAME", "mathew");
		values.put("PWD", "666666");
		db.insert("T_CUST", null, values);
		db.execSQL("", new Object[]{});
	}

}
