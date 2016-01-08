package com.example.vo;

public class OrderList {

	/** ������� */
	private String orderid;

	/** ������ʾ״̬ */
	private String status;

	/** ������� */
	private double price;

	/** �µ�ʱ�� */
	private String time;

	/** ������ʶ��1=>��ɾ�����޸� 2=>�����޸� 3=>����� */
	private int flag;

	public OrderList() {
	}

	public OrderList(String orderid, String status, double price, String time, int flag) {
		super();
		this.orderid = orderid;
		this.status = status;
		this.price = price;
		this.time = time;
		this.flag = flag;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

}
