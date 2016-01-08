package com.example.dao;

import java.util.ArrayList;
import java.util.List;

import com.example.vo.ServiceHistory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


/**
 * ��������������¼  �����������д������㼶�������е�
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
	 * �����ݿ��е����� ��䵽history �ĵ�����
	 * @param cursor  ���ݿ�
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
	 * ͬ�����෴�����ݲ���
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
	 * �������������ķ���
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
	 * ɾ����Ӧ�ļ�¼
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
	 * @param id  �жϵı���ΪID����������Ƿ����
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
	 * ��������ļ�¼
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
	 * @param history �Դ洢�����ݽ��и���
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
	 * ɾ�����еļ�¼
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
