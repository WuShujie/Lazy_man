package com.example.lazy_man;

import java.io.File;

import com.example.application.ECApplication;
import com.example.engine.DownLoadTask;
import com.example.engine.DownLoadTask.DownlaodListener;
import com.example.parser.BaseParser;
import com.example.parser.VersionParser;
import com.example.util.Logger;
import com.example.util.NetUtil;
import com.example.util.ThreadPoolManager;
import com.example.vo.RequestVo;
import com.example.vo.Version;
import android.app.AlertDialog.Builder;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 欢迎界面 
 * @author Dontouch
 *
 */
public class WelcomeActivity extends Activity implements Runnable, DownlaodListener {

	//标志
	private static final String TAG = "WelcomeActivity";
	// 提示用户更新 
	private static final int SHOW_UPDATE_DIALOG = 11;
	//下载失败 
	private static final int DOWN_ERROR = 12;
	//从服务器获取的版本信息 
	private Version version;
	//是否设置进度条最大值 
	private boolean flag = true;
	// 进度条 
	private ProgressDialog mProgressDialog;
	// 进度条当前的值 
	private int progressValue;
	//apk 文件 
	private File file;
	// 下载任务 
	private DownLoadTask downLoadTask;

	
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case DOWN_ERROR:
				mProgressDialog.dismiss();
				Toast.makeText(WelcomeActivity.this, R.string.down_error, Toast.LENGTH_SHORT).show();
				gotoHome();
				break;
			case SHOW_UPDATE_DIALOG:
				
				Logger.d(TAG, "更新版本提示");
				new Builder(WelcomeActivity.this).setTitle("升级提醒").setMessage("亲，有新的版本赶快升级!").setCancelable(false)
						.setPositiveButton("是", new OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int which) {
								//下载最新的apk
								downApk();
							}
						}).setNegativeButton("否", new OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int which) {
								Logger.d(TAG, "不更新直接进入主界面");
								gotoHome();
							}
						}).show();
				break;

			default:
				break;
			}
		};
	};

	//客户端的版本
	private String clientVersion;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome_activity);
		try {
			//获取现在客户端的版本
			clientVersion = getClientVersion();
		} catch (NameNotFoundException e) {
			Logger.e(TAG, e);
		}
		//在欢迎界面中显示 版本的信息
		((TextView) findViewById(R.id.welcome_version)).setText(clientVersion);
		
		//加入到线程池中 
		ThreadPoolManager.getInstance().addTask(this);
	}

	/**
	 * 从服务器下载新的Apk
	 */
	private void downApk() {
		
		initProgressDialog();
		file = new File(ECApplication.getCacheDirPath(), "lazyman.apk");
		downLoadTask = new DownLoadTask(version.getUrl(), file.getAbsolutePath(), 5);
		downLoadTask.setListener(this);
		ThreadPoolManager.getInstance().addTask(downLoadTask);

	}

	/**
	 * 安装Apk
	 */
	private void installApk() {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
		startActivity(intent);
		finish();
	}

	@Override
	public void update(int total, int len, int threadid) {
		if (flag) {
			mProgressDialog.setMax(total);
			flag = false;
		}
		progressValue += len;
		mProgressDialog.setProgress(progressValue);
	}

	@Override
	public void downLoadFinish(int totalSucess) {
		mProgressDialog.dismiss();
		if (totalSucess == 5)
			installApk();
		else {
			Message.obtain(handler, DOWN_ERROR).sendToTarget();
		}
	}

	@Override
	public void downLoadError(int type) {
		// Message.obtain(handler, DOWN_ERROR).sendToTarget();
	}

	/**
	 * 初始化进度条
	 */
	private void initProgressDialog() {
		mProgressDialog = new ProgressDialog(this);// 进度条初始化
		mProgressDialog.setCancelable(false);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		mProgressDialog.setMessage(getString(R.string.downning));
		mProgressDialog.show();
	}

	/**
	 * 进入主页
	 */
	private void gotoHome() {
		Intent intent = new Intent(this, HomeActivity.class);
		startActivity(intent);
		finish();
	}

	@Override
	public void run() {
		try {
			if (NetUtil.hasNetwork(this)) {
				BaseParser<Version> jsonParser = new VersionParser();
				RequestVo vo = new RequestVo(R.string.url_version, this, null, jsonParser);
				version = (Version) NetUtil.get(vo);
				if (version != null) {
					String v = version.getVersion();

					Logger.d(TAG, "获取当前服务器版本号为 ：" + v);
					if (clientVersion.equals(v)) {
						gotoHome();
					} else {
						Message.obtain(handler, SHOW_UPDATE_DIALOG).sendToTarget();
					}
				} else {
					gotoHome();
				}
			} else {
				gotoHome();
			}
		} catch (Exception e) {
			Logger.e(TAG, e);
			gotoHome();
		}
	}

	/**
	 * 获取当前应用的版本号
	 * 通过packageManager获取的客户端的版本
	 * @return
	 * @throws NameNotFoundException
	 */
	private String getClientVersion() throws NameNotFoundException {
		PackageManager packageManager = getPackageManager();
		PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
		return packageInfo.versionName;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (downLoadTask != null)
			downLoadTask.cancel();
		downLoadTask = null;
		if (mProgressDialog != null)
			mProgressDialog.dismiss();
		mProgressDialog = null;
		file = null;
	}
	

}
