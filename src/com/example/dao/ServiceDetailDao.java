package com.example.dao;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/***
 * �д���  �����߼��Ĵ���
 * @author Dontouch
 *
 */
public class ServiceDetailDao extends BaseDao{

	public ServiceDetailDao(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	

	/**
	 * ��ȡ���������
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
	 * ��Ҫ�޸�  ���ӷ���Ĵ���  ���Ե� �߼������˴���
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
	 * ͬ�� ���ڵĴ���
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
