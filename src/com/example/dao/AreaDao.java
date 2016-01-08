package com.example.dao;

import java.util.ArrayList;
import java.util.List;

import com.example.vo.Area;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AreaDao extends BaseDao{

	public static String TABLE = "pub_cant";
	
	/**
	 * 获取所有的省份
	 * @return
	 */
	public List<Area> getAllProvince() {
		return callBack(TYPE_READ, new DaoCallBack<List<Area>>() {

			@Override
			public List<Area> invoke(SQLiteDatabase conn) {
				cursor = conn.query(TABLE, null, " super_code = ?", new String[] { "CN" }, null, null, null, null);
				Area area;
				List<Area> areas = new ArrayList<Area>();
				while (cursor.moveToNext()) {
					area = new Area();
					fillArea(cursor, area);
					areas.add(area);
				}
				return areas;
			}
		});
	}
	
	/**
	 * 
	 * @param superCode
	 * @return
	 */
	public List<Area> findBySuperCode(final int superCode) {
		return callBack(TYPE_READ, new DaoCallBack<List<Area>>() {

			@Override
			public List<Area> invoke(SQLiteDatabase conn) {
				cursor = conn.query(TABLE, null, " super_code = ?", new String[] { Integer.toString(superCode) }, null, null, null, null);
				Area area;
				List<Area> areas = new ArrayList<Area>();
				while (cursor.moveToNext()) {
					area = new Area();
					fillArea(cursor, area);
					areas.add(area);
				}
				return areas;
			}
		});
	}
	
	/**
	 * 
	 * @param cantCode
	 * @return
	 */
	public Area findByCantCode(final int cantCode)  {
		return callBack(TYPE_READ, new DaoCallBack<Area>() {

			@Override
			public Area invoke(SQLiteDatabase conn) {
				cursor = conn.query(TABLE, null, " cant_code = ?", new String[] { Integer.toString(cantCode) }, null, null, null, null);
				Area area = new Area();
				if (cursor.moveToFirst()) {
					fillArea(cursor, area);
				}
				return area;
			}
		});
	}
	
	/**
	 * 
	 * @param cursor
	 * @param area
	 */
	private void fillArea(Cursor cursor, Area area) {
		area.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("cant_code"))));
		area.setValue(cursor.getString(cursor.getColumnIndex("cant_name")));
	}
	
	/**
	 * 
	 * @param context
	 */
	public AreaDao(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

}
