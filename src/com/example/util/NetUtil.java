package com.example.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;

import com.example.lazy_man.R;
import com.example.vo.RequestVo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;


public class NetUtil {
	
		private static BasicHeader[] headers = new BasicHeader[10];
		static {
			headers[0] = new BasicHeader("Appkey", "");
			headers[1] = new BasicHeader("Udid", "");
			headers[2] = new BasicHeader("Os", "");
			headers[3] = new BasicHeader("Osversion", "");
			headers[4] = new BasicHeader("Appversion", "");
			headers[5] = new BasicHeader("Sourceid", "");
			headers[6] = new BasicHeader("Ver", "");
			headers[7] = new BasicHeader("Userid", "");
			headers[8] = new BasicHeader("Usersession", "");
			headers[9] = new BasicHeader("Unique", "");
		}
		
		/*
		 * 
		 */
		public static Object post(RequestVo vo){
			DefaultHttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(vo.context.getString(R.string.app_host).concat(vo.context.getString(vo.requestUrl)));
			post.setHeaders(headers);
			Object obj = null;
			try {
				if(vo.requestDataMap!=null){
					HashMap<String,String> map = vo.requestDataMap;
					ArrayList<BasicNameValuePair> pairList = new ArrayList<BasicNameValuePair>();
					for(Map.Entry<String,String> entry:map.entrySet()){
						BasicNameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue());
						pairList.add(pair);
					}
					HttpEntity entity = new UrlEncodedFormEntity(pairList,"UTF-8");
					post.setEntity(entity);
				}
				HttpResponse response = client.execute(post);
				if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
					String result = EntityUtils.toString(response.getEntity(),"UTF-8");
					try {
						obj = vo.jsonParser.parseJSON(result);
					} catch (JSONException e) {
						Log.e(NetUtil.class.getSimpleName(), e.getLocalizedMessage(),e);
					}
					return obj;
				}
			} catch (ClientProtocolException e) {
				Log.e(NetUtil.class.getSimpleName(), e.getLocalizedMessage(),e);
			} catch (IOException e) {
				Log.e(NetUtil.class.getSimpleName(), e.getLocalizedMessage(),e);
			}
			return null;
		}
		
		/**
		 * 
		 * @param vo
		 * @return
		 */
		public static Object get(RequestVo vo){
			DefaultHttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet(vo.context.getString(R.string.app_host).concat(vo.context.getString(vo.requestUrl)));
			get.setHeaders(headers);
			Object obj = null;
			try {
				HttpResponse response = client.execute(get);
				if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
					String result = EntityUtils.toString(response.getEntity(),"UTF-8");
					Log.e(NetUtil.class.getSimpleName(), result);
					try {
						obj = vo.jsonParser.parseJSON(result);
					} catch (JSONException e) {
						Log.e(NetUtil.class.getSimpleName(), e.getLocalizedMessage(),e);
					}
					return obj;
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		/**
		 * 获得网络连接是否可用
		 * @param context
		 * @return
		 */
		public static boolean hasNetwork(Context context){
			ConnectivityManager con = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo workinfo = con.getActiveNetworkInfo();
			if(workinfo == null || !workinfo.isAvailable())
			{
				Toast.makeText(context, R.string.net_error, Toast.LENGTH_SHORT).show();
				return false;
			}
			return true;
		}
		
		public static enum Status {
			Login
		}
}
