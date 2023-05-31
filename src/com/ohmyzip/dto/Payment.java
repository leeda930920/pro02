package com.ohmyzip.dto;
import java.util.Date;
public class Payment {
	private	String	p_no;
	private	String	id;
	private	String	o_code;
	private	String	p_type;
	private	String	pt_no;
	private	int	p_price;
	private	String	p_date;
	public String getP_no() {
		return p_no;
	}
	public void setP_no(String p_no) {
		this.p_no = p_no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getO_code() {
		return o_code;
	}
	public void setO_code(String o_code) {
		this.o_code = o_code;
	}
	public String getP_type() {
		return p_type;
	}
	public void setP_type(String p_type) {
		this.p_type = p_type;
	}
	public String getPt_no() {
		return pt_no;
	}
	public void setPt_no(String pt_no) {
		this.pt_no = pt_no;
	}
	public int getP_price() {
		return p_price;
	}
	public void setP_price(int p_price) {
		this.p_price = p_price;
	}
	public String getP_date() {
		return p_date;
	}
	public void setP_date(String p_date) {
		this.p_date = p_date;
	}

}
