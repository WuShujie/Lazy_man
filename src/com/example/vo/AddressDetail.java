package com.example.vo;

import android.os.Parcel;
import android.os.Parcelable;



public class AddressDetail implements Parcelable {

	/** ��ַ��ID */
	private int id;

	/** �ջ�����? */
	private String name;

	/** �ֻ����� */
	private String phonenumber;

	/** �̶��绰 */
	private String fixedtel;
	/** ʡID */
	private int provinceid;

	/** ��ID */
	private int cityid;

	/** ����ID */
	private int areaid;

	/** ������ַ */
	private String areadetail;

	public AddressDetail() {
	}

	public AddressDetail(int id, String name, String phonenumber, String fixedtel, int provinceid, int cityid,
			int areaid, String areadetail) {
		super();
		this.id = id;
		this.name = name;
		this.phonenumber = phonenumber;
		this.fixedtel = fixedtel;
		this.provinceid = provinceid;
		this.cityid = cityid;
		this.areaid = areaid;
		this.areadetail = areadetail;

	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getFixedtel() {
		return fixedtel;
	}

	public void setFixedtel(String fixedtel) {
		this.fixedtel = fixedtel;
	}

	public int getProvinceid() {
		return provinceid;
	}

	public void setProvinceid(int provinceid) {
		this.provinceid = provinceid;
	}

	public int getCityid() {
		return cityid;
	}

	public void setCityid(int cityid) {
		this.cityid = cityid;
	}

	public int getAreaid() {
		return areaid;
	}

	public void setAreaid(int areaid) {
		this.areaid = areaid;
	}

	public String getAreadetail() {
		return areadetail;
	}

	public void setAreadetail(String areadetail) {
		this.areadetail = areadetail;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddressDetail other = (AddressDetail) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AddressDetail [id=" + id + ", name=" + name + ", phonenumber=" + phonenumber + ", fixedtel=" + fixedtel
				+ ", provinceid=" + provinceid + ", cityid=" + cityid + ", areaid=" + areaid + ", areadetail="
				+ areadetail +  "]";
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(name);
		dest.writeString(phonenumber);
		dest.writeString(fixedtel);

		dest.writeInt(provinceid);
		dest.writeInt(cityid);
		dest.writeInt(areaid);

		dest.writeString(areadetail);
	
	}

	public AddressDetail(Parcel in) {
		id = in.readInt();
		name = in.readString();
		phonenumber = in.readString();
		fixedtel = in.readString();

		provinceid = in.readInt();
		cityid = in.readInt();
		areaid = in.readInt();

		areadetail = in.readString();

	}

	public static final Creator<AddressDetail> CREATOR = new Creator<AddressDetail>() {

		@Override
		public AddressDetail createFromParcel(Parcel source) {
			return new AddressDetail(source);
		}

		@Override
		public AddressDetail[] newArray(int size) {
			return new AddressDetail[size];
		}
	};

}
