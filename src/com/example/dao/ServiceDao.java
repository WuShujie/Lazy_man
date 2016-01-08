package com.example.dao;

import java.util.ArrayList;
import java.util.List;

import com.example.vo.ServiceHistory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


/**
 * 家政服务的浏览记录  浏览的数据是写入在轻便级的数据中的
 * 
 * @author Dontouch
 *
 */
public class ServiceDao extends BaseDao {
	
	private static final String TABLE = "serviceHistory";

	public ServiceDao(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 将数据库中的数据 填充到history 的单体中
	 * @param cursor  数据库
	 * @param history  
	 */
	private  void fillServiceHistory(Cursor cursor , ServiceHistory history)
	{
		history.setId(cursor.getInt(cursor.getColumnIndex("id")));
		history.setName(cursor.getString(cursor.getColumnIndex("name")));
		history.setPic(cursor.getString(cursor.getColumnIndex("pic")));
		history.setMarketprice(cursor.getDouble(cursor.getColumnIndex("marketprice")));
		history.setPrice(cursor.getDouble(cursor.getColumnIndex("price")));
		history.setComment_count(cursor.getInt(cursor.getColumnIndex("comment_count")));
		history.setTime(cursor.getInt(cursor.getColumnIndex("time")));

	}
	
	/**
	 * 同上是相反的数据操作
	 * @param history
	 * @return
	 */
	private ContentValues fillContentValues(ServiceHistory history)
	{
		ContentValues values;
		values = new ContentValues();
		values.put("id", history.getId());
		values.put("name", history.getName());
		values.put("pic", history.getPic());
		values.put("marketprice", history.getMarketprice());
		values.put("price", history.getPrice());
		values.put("comment_count", history.getComment_count());
		values.put("time", history.getTime());
		return values;
		
	}
	
	/**
	 * 获得所有浏览过的服务
	 * @return
	 */
	public List<ServiceHistory> getAll(){
		return callBack(TYPE_READ, new DaoCallBack<List<ServiceHistory>>()
				{

					@Override
					public List<ServiceHistory> invoke(SQLiteDatabase conn) {
						// TODO Auto-generated method stub
						cursor = conn.query(TABLE, null, null, null, null, null, " time desc");
						
						ServiceHistory history;
						List<ServiceHistory> historys;
						
						historys =  new ArrayList<ServiceHistory>();
						
						while (cursor.moveToNext())
						{
							history = new ServiceHistory();
							fillServiceHistory(cursor , history);
							historys.add(history);
						}
						return null;
					}
			
				});
		
	}
	
	/**
	 * 删除相应的记录
	 * @param id
	 */
	public void delete(final int id)
	{
		callBack(TYPE_WRITE,new DaoCallBack<Void>(){

			@Override
			public Void invoke(SQLiteDatabase conn) {
				// TODO Auto-generated method stub
				conn.delete(TABLE, "id = ?", new String[] {Integer.toString(id) });
				return null;
			}
			
		});
	}
	
	/**
	 * 
	 * @param id  判断的编码为ID的浏览数据是否存在
	 * @return
	 */
	public Boolean findById(final int id)
	{
		
		return callBack(TYPE_READ , new DaoCallBack<Boolean>(){

			@Override
			public Boolean invoke(SQLiteDatabase conn) {
				// TODO Auto-generated method stub
				cursor = conn.query(TABLE, null, "id = ?", new String[] {Integer.toString(id)}, null, null, null);
				return cursor.moveToFirst();
			}
			
		});
		
	}
	
	/**
	 * 增加浏览的记录
	 * @param history
	 * @return
	 */
	public Long add(final ServiceHistory history)
	{
		return callBack(TYPE_WRITE ,new DaoCallBack<Long>(){

			@Override
			public Long invoke(SQLiteDatabase conn) {
				// TODO Auto-generated method stub
				return conn.insert(TABLE, null, fillContentValues(history));
			}
			
		});
		
	}
	
	/**
	 * 
	 * @param history 对存储的数据进行更新
	 * @return
	 */
	public Integer update(final ServiceHistory history)
	{
		return callBack(TYPE_WRITE , new DaoCallBack<Integer>(){

			@Override
			public Integer invoke(SQLiteDatabase conn) {
				// TODO Auto-generated method stub
				return conn.update(TABLE, fillContentValues(history), "id = ? ", new String[] {history.getId()+""} );
			}
			
		});
		
	}
	
	/**
	 * 删除所有的记录
	 * @return
	 */
	public Integer deleteAll()
	{
		return callBack(TYPE_WRITE , new DaoCallBack<Integer>(){

			@Override
			public Integer invoke(SQLiteDatabase conn) {
				// TODO Auto-generated method stub
				return conn.delete(TABLE, null, null);
			}
			
		});
		
	}
	

}
