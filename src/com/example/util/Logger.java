package com.example.util;

import android.util.Log;

/**
 * Ϊ�˷������  �Լ���д����־����İ취
 * @author Dontouch
 *
 */
public final class Logger {
	
	//��־������ʾ�������android.util.Log�ļ�������0ȫ����ʾ�����ô���7ȫ����ʾ
	public static final int LEVLE = 2;
	
	public static void v(String tag, String msg) {
		if (LEVLE <= Log.VERBOSE)
			Log.v(tag, msg);
	}

	public static void v(String tag, String msg, Throwable tr) {
		if (LEVLE <= Log.VERBOSE)
			Log.v(tag, msg, tr);
	}

	public static void d(String tag, String msg) {
		if (LEVLE <= Log.DEBUG)
			Log.d(tag, msg);
	}

	public static void d(String tag, String msg, Throwable tr) {
		if (LEVLE <= Log.DEBUG)
			Log.d(tag, msg, tr);
	}

	public static void i(String tag, String msg) {
		if (LEVLE <= Log.INFO)
			Logger.d(tag, msg);
	}

	public static void i(String tag, String msg, Throwable tr) {
		if (LEVLE <= Log.INFO)
			Logger.d(tag, msg, tr);
	}

	public static void w(String tag, String msg) {
		if (LEVLE <= Log.WARN)
			Log.w(tag, msg);
	}

	public static void w(String tag, String msg, Throwable tr) {
		if (LEVLE <= Log.WARN)
			Log.w(tag, msg, tr);
	}

	public static void w(String tag, Throwable tr) {
		if (LEVLE <= Log.WARN)
			Log.w(tag, tr.getMessage(), tr);
	}

	public static void e(String tag, String msg) {
		if (LEVLE <= Log.ERROR)
			Log.e(tag, msg);
	}

	public static void e(String tag, String msg, Throwable tr) {
		if (LEVLE <= Log.ERROR)
			Log.e(tag, msg, tr);
	}

	public static void e(String tag, Throwable tr) {
		if (LEVLE <= Log.ERROR)
			Log.e(tag, tr.getMessage(), tr);
	}

	public static void wtf(String tag, String msg) {
		if (LEVLE <= Log.ASSERT)
			Log.wtf(tag, msg);
	}

	public static void wtf(String tag, Throwable tr) {
		if (LEVLE <= Log.ASSERT)
			Log.wtf(tag, tr);
	}

	public static void wtf(String tag, String msg, Throwable tr) {
		if (LEVLE <= Log.ASSERT)
			Logger.wtf(tag, msg, tr);
	}
	

}
