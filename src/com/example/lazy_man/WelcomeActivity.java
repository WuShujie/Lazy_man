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
 * ��ӭ���� 
 * @author Dontouch
 *
 */
public class WelcomeActivity extends Activity implements Runnable, DownlaodListener {

	//��־
	private static final String TAG = "WelcomeActivity";
	// ��ʾ�û����� 
	private static final int SHOW_UPDATE_DIALOG = 11;
	//����ʧ�� 
	private static final int DOWN_ERROR = 12;
	//�ӷ�������ȡ�İ汾��Ϣ 
	private Version version;
	//�Ƿ����ý��������ֵ 
	private boolean flag = true;
	// ������ 
	private ProgressDialog mProgressDialog;
	// ��������ǰ��ֵ 
	private int progressValue;
	//apk �ļ� 
	private File file;
	// �������� 
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
				
				Logger.d(TAG, "���°汾��ʾ");
				new Builder(WelcomeActivity.this).setTitle("��������").setMessage("�ף����µİ汾�Ͽ�����!").setCancelable(false)
						.setPositiveButton("��", new OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int which) {
								//�������µ�apk
								downApk();
							}
						}).setNegativeButton("��", new OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int which) {
								Logger.d(TAG, "������ֱ�ӽ���������");
								gotoHome();
							}
						}).show();
				break;

			default:
				break;
			}
		};
	};

	//�ͻ��˵İ汾
	private String clientVersion;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome_activity);
		try {
			//��ȡ���ڿͻ��˵İ汾
			clientVersion = getClientVersion();
		} catch (NameNotFoundException e) {
			Logger.e(TAG, e);
		}
		//�ڻ�ӭ��������ʾ �汾����Ϣ
		((TextView) findViewById(R.id.welcome_version)).setText(clientVersion);
		
		//���뵽�̳߳��� 
		ThreadPoolManager.getInstance().addTask(this);
	}

	/**
	 * �ӷ����������µ�Apk
	 */
	private void downApk() {
		
		initProgressDialog();
		file = new File(ECApplication.getCacheDirPath(), "lazyman.apk");
		downLoadTask = new DownLoadTask(version.getUrl(), file.getAbsolutePath(), 5);
		downLoadTask.setListener(this);
		ThreadPoolManager.getInstance().addTask(downLoadTask);

	}

	/**
	 * ��װApk
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
	 * ��ʼ��������
	 */
	private void initProgressDialog() {
		mProgressDialog = new ProgressDialog(this);// ��������ʼ��
		mProgressDialog.setCancelable(false);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		mProgressDialog.setMessage(getString(R.string.downning));
		mProgressDialog.show();
	}

	/**
	 * ������ҳ
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

					Logger.d(TAG, "��ȡ��ǰ�������汾��Ϊ ��" + v);
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
	 * ��ȡ��ǰӦ�õİ汾��
	 * ͨ��packageManager��ȡ�Ŀͻ��˵İ汾
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
