package com.example.lazy_man;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.dao.AreaDao;
import com.example.parser.AddressManageParser;
import com.example.util.Logger;
import com.example.vo.AddressDetail;
import com.example.vo.Area;
import com.example.vo.RequestVo;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

/**
 * 地址的添加   对可以相应的修改
 * @author Dontouch
 *
 */
public class AddAddressActivity extends BaseWapperActivity implements OnItemSelectedListener{

	private static final String TAG = "AddAddressActivity";
	
	private List<Area> allProvince;
	private ArrayAdapter<Area> mProvinceAdapter;
	
	private View cityLy;
	private View areaLy;
	
	private Spinner mProvinceSpinner;
	private Spinner mCitySpinner;
	private Spinner mAreaSpinner;

	private AreaDao areaDao;
	
	private ArrayAdapter<Area> mCityeAdapter;
	private ArrayAdapter<Area> mAreaAdapter;

	private TextView mNameEdit;
	private TextView mMobileEdit;
	private TextView mTelEdit;
	private TextView mDetailEdit;

	// 是否是修改 
	private boolean isEdit;
	private boolean isCityFirst = true;
	private boolean isAreaFirst = true;

	//地址的详细
	private AddressDetail address;
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.save_address_button:// 确定
			if (TextUtils.isEmpty(mNameEdit.getText())) {
				Toast.makeText(this, "请输入名字", Toast.LENGTH_LONG).show();
				return;
			}
			if (TextUtils.isEmpty(mMobileEdit.getText())) {
				Toast.makeText(this, "请输入电话号码", Toast.LENGTH_LONG).show();
				return;
			}

			if (TextUtils.isEmpty(mDetailEdit.getText())) {
				Toast.makeText(this, "请输入详细地址", Toast.LENGTH_LONG).show();
				return;
			}
		
			/**
			 * 将数据存在在后台的数据
			 */
			HashMap<String ,String > requestDataMap = new HashMap<String, String>();
			requestDataMap.put("name", mNameEdit.getText().toString());
			requestDataMap.put("phonenumber", mMobileEdit.getText().toString());
			requestDataMap.put("fixedtel", mTelEdit.getText().toString());
			
			/**
			 * 详细地址要分开来的 不同的处理  分离对 改地区的 不同的编号
			 */
			
			StringBuilder builder = new StringBuilder();
			Area area = (Area) mProvinceSpinner.getSelectedItem();
			builder.append(area.getId());
			builder.append(",");
			area = (Area) mCitySpinner.getSelectedItem();
			builder.append(area.getId());
			builder.append(",");
			area = (Area) mAreaSpinner.getSelectedItem();
			builder.append(area.getId());
			
			requestDataMap.put("areaid", builder.toString());
			requestDataMap.put("areadetail", mDetailEdit.getText().toString());
			
			if(isEdit){
				requestDataMap.put("id", address.getId() + "");
			}
			
			RequestVo vo = new RequestVo(R.string.url_addresssave,this,requestDataMap,new AddressManageParser());
			getDataFromServer(vo,new DataCallback<ArrayList<AddressDetail>>(){

				@Override
				public void processData(ArrayList<AddressDetail> paramObject,
						boolean paramBoolean) {
					// TODO Auto-generated method stub
					Intent data = new Intent();

					Toast.makeText(AddAddressActivity.this, isEdit ? R.string.edit_success : R.string.add_succuess,
							Toast.LENGTH_LONG).show();
					data.putParcelableArrayListExtra("addressList", paramObject);
					setResult(200, data);
					finish();
					Logger.d(TAG,paramObject.toString());
				}
				
			});
			break;
		case R.id.cancel_address_button:// 取消
			finish();
			break;
			
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		
		Logger.d(TAG, position + " ");
 		Area item;
		List<Area> sub_area;
		switch (parent.getId()) {
		case R.id.address_add_spinner_province:
			if (isEdit && isCityFirst) {
				isCityFirst = false; 
				return;
			}
			item = (Area) mProvinceAdapter.getItem(position);
			sub_area = item.getSub_area();
			if (sub_area == null) {
				sub_area = areaDao.findBySuperCode(item.getId());
			}
			updateCity(sub_area);
			break;
		case R.id.address_add_spinner_city:
			if (isEdit && isAreaFirst) {
				isAreaFirst = false; 
				return;
			}
			
			item = (Area) mCityeAdapter.getItem(position);
			sub_area = item.getSub_area();
			if (sub_area == null) {
				sub_area = areaDao.findBySuperCode(item.getId());
			}
			updateArea(sub_area);
			break;
		}
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		
	
		
	}
	
	
	@Override
	protected void onResume() {
		super.onResume();
		Logger.d(TAG, "onResume()");
	}
	

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		mProvinceSpinner = (Spinner) findViewById(R.id.address_add_spinner_province);// 省
		mCitySpinner = (Spinner) findViewById(R.id.address_add_spinner_city);// 市
		mAreaSpinner = (Spinner) findViewById(R.id.address_add_spinner_area);// 区
		cityLy = findViewById(R.id.add_address_city_ly);
		areaLy = findViewById(R.id.add_address_area_ly);

		mNameEdit = (TextView) findViewById(R.id.add_address_name_edit);// 名称
		mMobileEdit = (TextView) findViewById(R.id.add_address_mobile_edit);// 手机
		mTelEdit = (TextView) findViewById(R.id.add_address_tel_edit);// 固定电话
		mDetailEdit = (TextView) findViewById(R.id.add_address_detail_edit);// 详细地址
	
		
		/**
		 * 两个按钮
		 */
		findViewById(R.id.save_address_button).setOnClickListener(this);
		findViewById(R.id.cancel_address_button).setOnClickListener(this);
	}

	@Override
	protected void loadViewLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.add_address_activity);
	}

	@Override
	protected void processLogic() {
		// TODO Auto-generated method stub
		
		//通过intent获取得到
		address = getIntent().getParcelableExtra("address");
		if (address != null) {
			isEdit = true;
			setTitle("地址修改");
			Logger.d("TAG", address.toString());
		} else {
			setTitle("地址添加");
		}

		areaDao = new AreaDao(this);
		allProvince = areaDao.getAllProvince();

		int provinceSelectId = -1;
		int citySelectId = -1;
		int areaSelectId = -1;
		if (isEdit) {
			// 修改回显数据
			provinceSelectId = address.getProvinceid();
			citySelectId = address.getCityid();
			areaSelectId = address.getAreaid();
			mNameEdit.setText(address.getName());
			mMobileEdit.setText(address.getPhonenumber());
			mTelEdit.setText(address.getFixedtel());
			mDetailEdit.setText(address.getAreadetail());
		}

		updateProvince(allProvince);
		int areaId;
		Area area;
		if (isEdit) {
			area = selectedSpinner(allProvince, provinceSelectId, mProvinceSpinner);
			areaId = address.getProvinceid();
		} else {
			area = allProvince.get(0);
			areaId = area.getId();
		}
		List<Area> citys = areaDao.findBySuperCode(areaId);
		updateCity(citys);
		area.setSub_area(citys);

		if (isEdit) {
			area = selectedSpinner(citys, citySelectId, mCitySpinner);
			areaId = area.getId();
		} else {
			area = citys.get(0);
		}

		List<Area> areas = areaDao.findBySuperCode(area.getId());
		updateArea(areas);
		if (isEdit)
			selectedSpinner(areas, areaSelectId, mAreaSpinner);
		if (areas != null && areas.size() > 0) {
			area.setSub_area(areas);
		}

	}

	private Area selectedSpinner(List<Area> areas, int select,
			Spinner spinner) {
		
		if (select != -1) {
			int i = 0;
			for (Area area : areas) {
				if (area.getId() == select) {
					spinner.setSelection(i);
					
					Logger.d(TAG, "select " + select);
					return area;
				}
				i++;
			}
		}
		return null;
	}

	private void updateArea(List<Area> areas) {
		if (areas != null && areas.size() > 0) {
			mAreaAdapter = new ArrayAdapter<Area>(context, android.R.layout.simple_spinner_item, areas);
			mAreaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			mAreaSpinner.setAdapter(mAreaAdapter);
			areaLy.setVisibility(View.VISIBLE);
		} else
			areaLy.setVisibility(View.GONE);
		
	}

	private void updateCity(List<Area> areas) {
		if (areas != null && areas.size() > 0) {
			mCityeAdapter = new ArrayAdapter<Area>(context, android.R.layout.simple_spinner_item, areas);
			mCityeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			mCitySpinner.setAdapter(mCityeAdapter);
			cityLy.setVisibility(View.VISIBLE);
		} else
			cityLy.setVisibility(View.GONE);
		
	}

	private void updateProvince(List<Area> areas) {
		
		mProvinceAdapter = new ArrayAdapter<Area>(context, android.R.layout.simple_spinner_item, areas);
		mProvinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mProvinceSpinner.setAdapter(mProvinceAdapter);
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		mProvinceSpinner.setOnItemSelectedListener(this);
		mCitySpinner.setOnItemSelectedListener(this);
		mAreaSpinner.setOnItemSelectedListener(this);
	}

}
