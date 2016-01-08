package com.example.vo;

import java.util.List;

public class ServiceDetail {
	
	//ID 
	private int id;

	//�������� 
	private String name;

	// �г��� 
	private double marketprice;

	//��Ա�� 
	private double price;

	//��ʱ������
	private double limitPrice;

	// ʣ��ʱ�䣬��λΪ��
	private long leftTime;
	
	//���������� 
	private int comment_count;
	
	// ���� 
	private String score;

	//yes/no �Ƿ����
	private String available;
	
	//���������� 
	private int buyLimit;
	
	//��ش�����Ϣչʾ 
	private List<String> service_prom;
	
	//����˵�� 
	private String inventory_area;
	
	//ͼƬ 
	private List<String> pic;

	// ��ͼƬ 
	private List<String> bigPic;

	public ServiceDetail() {
	}
	
	public ServiceDetail(int id, String name, double marketprice, double price, double limitPrice, long leftTime,
			int comment_count, String score, String available, int buyLimit, List<String> service_prom,
			String inventory_area, List<String> pic, List<String> bigPic) {
		super();
		this.id = id;
		this.name = name;
		this.marketprice = marketprice;
		this.price = price;
		this.limitPrice = limitPrice;
		this.leftTime = leftTime;
		this.comment_count = comment_count;
		this.score = score;
		this.available = available;
		this.buyLimit = buyLimit;
		this.service_prom = service_prom;
		this.inventory_area = inventory_area;
		this.pic = pic;
		this.bigPic = bigPic;
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

	public double getMarketprice() {
		return marketprice;
	}

	public void setMarketprice(double marketprice) {
		this.marketprice = marketprice;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getLimitPrice() {
		return limitPrice;
	}

	public void setLimitPrice(double limitPrice) {
		this.limitPrice = limitPrice;
	}

	public long getLeftTime() {
		return leftTime;
	}

	public void setLeftTime(long leftTime) {
		this.leftTime = leftTime;
	}

	public int getComment_count() {
		return comment_count;
	}

	public void setComment_count(int comment_count) {
		this.comment_count = comment_count;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	public int getBuyLimit() {
		return buyLimit;
	}

	public void setBuyLimit(int buyLimit) {
		this.buyLimit = buyLimit;
	}

	public List<String> getService() {
		return service_prom;
	}

	public void setService_prom(List<String> service_prom) {
		this.service_prom = service_prom;
	}

	public String getInventory_area() {
		return inventory_area;
	}

	public void setInventory_area(String inventory_area) {
		this.inventory_area = inventory_area;
	}

	public List<String> getPic() {
		return pic;
	}

	public void setPic(List<String> pic) {
		this.pic = pic;
	}

	public List<String> getBigPic() {
		return bigPic;
	}

	public void setBigPic(List<String> bigPic) {
		this.bigPic = bigPic;
	}
	

}
