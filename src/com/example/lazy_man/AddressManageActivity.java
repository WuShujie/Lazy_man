package com.example.lazy_man;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.adapter.AddressManageAdapter;
import com.example.adapter.AddressManageAdapter.OnItemButtonListener;
import com.example.parser.AddressManageParser;
import com.example.parser.SuccessParser;
import com.example.util.NetUtil;
import com.example.vo.AddressDetail;
import com.example.vo.RequestVo;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;

/**
 * ��ַ����
 * 
 * @author Dontouch
 * 
 */
public class AddressManageActivity extends BaseWapperActivity implements
		OnItemButtonListener, OnItemLongClickListener {

	private ListView addressItemlv;
	private AddressManageAdapter mAdapter;

	@Override
	public void onClick(View v) {

	}

	@Override
	protected void findViewById() {
		addressItemlv = (ListView) findViewById(R.id.address_manage_list);
		mAdapter = new AddressManageAdapter(this);
		mAdapter.setListener(this);
	}

	@Override
	protected void loadViewLayout() {
		setContentView(R.layout.address_manage_activity);
		setHeadRightText(R.string.address_manager_add);
		setHeadRightVisibility(View.VISIBLE);
		setTitle(R.string.address_manage);
	}

	@Override
	protected void onHeadRightButton(View v) {
		startActivityForResult(new Intent(this, AddAddressActivity.class), 200);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 200 && resultCode == 200) {
			// ���µ�ַ�б�
			ArrayList<AddressDetail> addressList = data
					.getParcelableArrayListExtra("addressList");
			mAdapter.clear();
			mAdapter.addAll(addressList);
		}
	}

	@Override
	protected void processLogic() {

		RequestVo reqVo = new RequestVo(R.string.url_addresslist, this, null,
				new AddressManageParser());
		getDataFromServer(reqVo, new DataCallback<List<AddressDetail>>() {

			@Override
			public void processData(List<AddressDetail> paramObject,
					boolean paramBoolean) {
				mAdapter.addAll(paramObject);
				addressItemlv.setAdapter(mAdapter);
			}
		});
	}

	@Override
	protected void setListener() {
		addressItemlv.setOnItemLongClickListener(this);
	}

	
	@Override
	public void onItemClick(View view, final int position) {
		switch (view.getId()) {
		case R.id.address_manage_update_btn:// �޸�
			Intent intent = new Intent(this, AddAddressActivity.class);
			intent.putExtra("address", mAdapter.getItem(position));
			startActivityForResult(intent, 200);
			break;
		case R.id.address_manage_delete_btn:// ɾ��
			final AddressDetail item = mAdapter.getItem(position);
			Builder builder = new Builder(this);
			builder.setTitle(R.string.toast).setMessage("ȷ��ɾ����?");
			builder.setPositiveButton(R.string.yes, new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					HashMap<String, String> requestDataMap = new HashMap<String, String>();
					requestDataMap.put("id", item.getId() + "");
					RequestVo vo = new RequestVo(R.string.url_addressdelete,
							context, requestDataMap, new SuccessParser());
					Boolean bool = (Boolean) NetUtil.post(vo);
					if (bool != null) {
						if (bool) {
							mAdapter.remove(mAdapter.getItem(position));
							Toast.makeText(AddressManageActivity.this,
									R.string.delete_success, Toast.LENGTH_LONG)
									.show();
						}
					}
				}
			});
			builder.setNegativeButton(R.string.no, null);
			builder.show();
			break;
		}
	}

	@Override
	public void finish() {
		setResult(200);
		super.finish();
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			final int position, long id) {
		final AddressDetail item = mAdapter.getItem(position);
		Builder builder = new Builder(this);
		builder.setTitle(R.string.toast).setMessage("ȷ������Ĭ�ϵ�ַ��?");
		builder.setPositiveButton(R.string.yes, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				HashMap<String, String> requestDataMap = new HashMap<String, String>();
				requestDataMap.put("id", item.getId() + "");
				RequestVo vo = new RequestVo(R.string.url_addressdefault,
						context, requestDataMap, new SuccessParser());
				Boolean bool = (Boolean) NetUtil.post(vo);
				if (bool != null) {
					if (bool) {
						Toast.makeText(AddressManageActivity.this,
								R.string.set_success, Toast.LENGTH_LONG).show();
					}
				}
			}
		});
		builder.setNegativeButton(R.string.no, null);
		builder.show();
		return true;
	}

}
