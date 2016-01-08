package com.example.lazy_man;

import com.example.util.Constant;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.TextView;

/**
 * 关于软件的信息
 * @author Dontouch
 *
 */
public class AboutActivity extends BaseWapperActivity {

	private TextView textVersion;
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		textVersion = (TextView) findViewById(R.id.textVersion);
	}

	@Override
	protected void loadViewLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.about_activity);
		setTitle("关于");
		selectedBottomTab(Constant.MORE);
	
	}

	@Override
	protected void processLogic() {
		// TODO Auto-generated method stub
		textVersion.setText("版本号:" + getVersion());
	}

	private String getVersion() {
		// TODO Auto-generated method stub
		try{
			PackageManager manager = getPackageManager();
			PackageInfo info = manager.getPackageInfo(getPackageName(), 0);
			return info.versionName;
			
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		
	}

	

}
