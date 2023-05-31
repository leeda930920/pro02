package com.ohmyzip.dto;
public class Product {
	private	String	p_code;
	private	String	p_name;
	private	String	p_size;
	private	int	price;
	private	String	p_com;
	private	int	amount;
	private	String picture;
	private String Cate;
	public String getP_code() {
		return p_code;
	}
	public void setP_code(String p_code) {
		this.p_code = p_code;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public String getP_size() {
		return p_size;
	}
	public void setP_size(String p_size) {
		this.p_size = p_size;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getP_com() {
		return p_com;
	}
	public void setP_com(String p_com) {
		this.p_com = p_com;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getCate() {
		return Cate;
	}
	public void setCate(String cate) {
		Cate = cate;
	}

}