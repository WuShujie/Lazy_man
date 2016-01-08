package com.example.dao;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/***
 * 有错误  存在逻辑的错误
 * @author Dontouch
 *
 */
public class ServiceDetailDao extends BaseDao{

	public ServiceDetailDao(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	

	/**
	 * 获取服务的数量
	 * @param serviceId
	 * @return
	 */
	public String findServiceCount(final String serviceId) {
		return callBack(TYPE_READ, new DaoCallBack<String>() {

			@Override
			public String invoke(SQLiteDatabase conn) {
				String count = null;
				cursor = conn.rawQuery("select count from person where id=?", new String[] { serviceId });

				if (cursor.moveToFirst()) {
					count = cursor.getString(0);
				}
				return count;
			}
		});
	}

	/**
	 * 需要修改  增加服务的次数  不对的 逻辑出现了错误
	 * @param serviceId
	 * @param count
	 */
	public void addServiceCount(final String serviceId, final String count) {
		callBack(TYPE_WRITE, new DaoCallBack<Void>() {

			@Override
			public Void invoke(SQLiteDatabase conn) {
				if (conn.isOpen()) {
					conn.execSQL("insert into serviceNum (id,count) values (?,?)", new Object[] { serviceId, count });

				}
				return null;
			}	
		});
	}

	/***+
	 * 同上 存在的错误
	 * @param serviceId
	 */
	public void deleteServiceCount(final String serviceId) {
		callBack(TYPE_WRITE, new DaoCallBack<Void>() {

			@Override
			public Void invoke(SQLiteDatabase conn) {
				if (conn.isOpen()) {
					conn.execSQL("delete from serviceNum where id=?", new Object[] { serviceId });
				}
				return null;
			}
		});
	}

}
