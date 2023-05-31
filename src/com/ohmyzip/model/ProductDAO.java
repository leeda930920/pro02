package com.ohmyzip.model;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.ohmyzip.dto.Product;
import com.ohmyzip.vo.CategoryVO;

public class ProductDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//상품 상세보기
	public Product getProduct(String p_code){
		Product pro = new Product();
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.PRODUCT_SELECT);
			pstmt.setString(1, p_code);
			rs = pstmt.executeQuery();
			if(rs.next()){
				pro.setP_code(rs.getString("p_code"));
				pro.setP_name(rs.getString("p_name"));
				pro.setP_size(rs.getString("p_size"));
				pro.setPrice(rs.getInt("price"));
				pro.setP_com(rs.getString("p_com"));
				pro.setAmount(rs.getInt("amount"));
				pro.setPicture(rs.getString("picture"));
				pro.setCate(rs.getString("cate"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		return pro;
	}	

	//전체 상품 목록 불러오기
	public ArrayList<Product> getProductList(){
		ArrayList<Product> proList = new ArrayList<Product>();
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.PRODUCT_SELECT_ALL);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Product pro = new Product();
				pro.setP_code(rs.getString("p_code"));
				pro.setP_name(rs.getString("p_name"));
				pro.setP_size(rs.getString("p_size"));
				pro.setPrice(rs.getInt("price"));
				pro.setP_com(rs.getString("p_com"));
				pro.setAmount(rs.getInt("amount"));
				pro.setPicture(rs.getString("picture"));
				pro.setCate(rs.getString("cate"));
				proList.add(pro);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		return proList;
	}	
	
	//카테고리별 제품목록 로딩
	public ArrayList<Product> getCateProductList(String cate){
		ArrayList<Product> proList = new ArrayList<Product>();
		//product 테이블에서 모든 레코드를 검색하여 반환된 ResultSet을 proList에 add를 한다.
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.PRODUCT_CATE_SELECT);
			pstmt.setString(1, cate);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Product pro = new Product();
				pro.setP_code(rs.getString("p_code"));
				pro.setP_name(rs.getString("p_name"));
				pro.setP_size(rs.getString("p_size"));
				pro.setPrice(rs.getInt("price"));
				pro.setP_com(rs.getString("p_com"));
				pro.setAmount(rs.getInt("amount"));
				pro.setPicture(rs.getString("picture"));
				pro.setCate(rs.getString("cate"));
				proList.add(pro);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		return proList;
	}
	
	//카테고리별 제품목록 로딩
		public ArrayList<Product> getAdminCateProductList(String cate){
			ArrayList<Product> proList = new ArrayList<Product>();
			//product 테이블에서 모든 레코드를 검색하여 반환된 ResultSet을 proList에 add를 한다.
			try {
				con = Oracle11.getConnection();
				pstmt = con.prepareStatement(Oracle11.PRODUCT_CATE_SELECT2);
				pstmt.setString(1, cate);
				rs = pstmt.executeQuery();
				while(rs.next()){
					Product pro = new Product();
					pro.setP_code(rs.getString("p_code"));
					pro.setP_name(rs.getString("p_name"));
					pro.setP_size(rs.getString("p_size"));
					pro.setPrice(rs.getInt("price"));
					pro.setP_com(rs.getString("p_com"));
					pro.setAmount(rs.getInt("amount"));
					pro.setPicture(rs.getString("picture"));
					pro.setCate(rs.getString("cate"));
					proList.add(pro);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Oracle11.close(rs, pstmt, con);
			}
			return proList;
		}
		
		//전체 상품 목록 불러오기
		public ArrayList<Product> getSoldoutProductList(){
			ArrayList<Product> proList = new ArrayList<Product>();
			try {
				con = Oracle11.getConnection();
				pstmt = con.prepareStatement(Oracle11.PRODUCT_SOLDOUT_SELECT);
				rs = pstmt.executeQuery();
				while(rs.next()){
					Product pro = new Product();
					pro.setP_code(rs.getString("p_code"));
					pro.setP_name(rs.getString("p_name"));
					pro.setP_size(rs.getString("p_size"));
					pro.setPrice(rs.getInt("price"));
					pro.setP_com(rs.getString("p_com"));
					pro.setAmount(rs.getInt("amount"));
					pro.setPicture(rs.getString("picture"));
					pro.setCate(rs.getString("cate"));
					proList.add(pro);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Oracle11.close(rs, pstmt, con);
			}
			return proList;
		}	
	
	//카테고리 로딩
	public HashMap<String, String> getCategory(String cate) {
		HashMap<String, String> cateMap = new HashMap<String, String>();
		String cateGroup = "";
		String cateName = "";
		if(cate.length()==2){
			cate = cate + "01";
		}
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.PRODUCT_CATENAME_SELECT);
			pstmt.setString(1, cate);
			rs = pstmt.executeQuery();
			if(rs.next()){
				cateGroup = "catename";
				cateName = rs.getString("catename");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		cateMap.put(cateGroup, cateName);
		return cateMap;
	}

	//대분류 코드 반환
	public ArrayList<CategoryVO> getFirstCategoryList(){
		ArrayList<CategoryVO> cateList = new ArrayList<CategoryVO>();
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.FIRST_CATEGORY_SELECT);
			rs = pstmt.executeQuery();
			while(rs.next()){
				CategoryVO cate = new CategoryVO();
				cate.setCt(rs.getString("ct"));
				cate.setCategroup(rs.getString("categroup"));
				cateList.add(cate);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		return cateList;
	}
	
	//중분류 코드 반환
	public ArrayList<CategoryVO> getSecondCategoryList(String ct){
		ArrayList<CategoryVO> cateList = new ArrayList<CategoryVO>();
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.SECOND_CATEGORY_SELECT);
			pstmt.setString(1, ct);
			rs = pstmt.executeQuery();
			while(rs.next()){
				CategoryVO cate = new CategoryVO();
				cate.setCate(rs.getString("cate"));
				cate.setCatename(rs.getString("catename"));
				cateList.add(cate);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		return cateList;
	}
	
	//상품 코드 발생기
	public String getProductCodeGenerator(String cate){
		String p_code = "";
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.PCODE_GENERATOR);
			pstmt.setString(1, cate);
			rs = pstmt.executeQuery();
			if(rs.next()){
				p_code = rs.getString("p_code").substring(4);
			} else {
				p_code = "0";
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		int tmp = 0;
		if(p_code==null){
			p_code = tmp + "0001";
		} else {
			tmp = Integer.parseInt(p_code) + 1;
			if(tmp>=1000){
				p_code = tmp + "";
			} else if(tmp>=100) {
				p_code = "0" + tmp;
			} else if(tmp>=10) {
				p_code = "00" + tmp;
			} else {
				p_code = "000" + tmp;
			}			
		}
		return p_code;
	}

	//상품 등록 처리
	public int insertProduct(Product pro) {
		int cnt = 0;
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.INSERT_PRODUCT);
			pstmt.setString(1, pro.getP_code());
			pstmt.setString(2, pro.getP_name());
			pstmt.setString(3, pro.getP_size());
			pstmt.setInt(4, pro.getPrice());
			pstmt.setString(5, pro.getP_com());
			pstmt.setInt(6, pro.getAmount());
			pstmt.setString(7, pro.getPicture());
			pstmt.setString(8, pro.getCate());
			cnt = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) { //오라클 JDBC 클래스가 없거나 경로가 다른 경우 발생
			e.printStackTrace();
		} catch (SQLException e){	//sql 구문이 틀린 경우 발생
			e.printStackTrace();			
		} catch (Exception e){	//알 수 없는 예외인 경우 발생
			e.printStackTrace();
		}
		Oracle11.close(pstmt, con);
		return cnt;
	}

	//입고 처리
	public int receiptProduct(String p_code, int amount, int price) {
		int cnt = 0;
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.RECEIPT_PRODUCT);
			pstmt.setInt(1, amount);
			pstmt.setInt(2, price);
			pstmt.setString(3, p_code);
			cnt = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) { //오라클 JDBC 클래스가 없거나 경로가 다른 경우 발생
			e.printStackTrace();
		} catch (SQLException e){	//sql 구문이 틀린 경우 발생
			e.printStackTrace();			
		} catch (Exception e){	//알 수 없는 예외인 경우 발생
			e.printStackTrace();
		}
		Oracle11.close(pstmt, con);
		return cnt;
	}

	public int updateProduct(Product pro) {
		int cnt =0 ;
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.UPDATE_PRODUCT2);
			pstmt.setString(1, pro.getP_name());
			pstmt.setString(2, pro.getP_size());
			pstmt.setInt(3, pro.getPrice());
			pstmt.setString(4, pro.getP_com());
			pstmt.setInt(5, pro.getAmount());
			pstmt.setString(6, pro.getPicture());
			pstmt.setString(11, pro.getCate());
			pstmt.setString(12, pro.getP_code());
			cnt = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) { //오라클 JDBC 클래스가 없거나 경로가 다른 경우 발생
			e.printStackTrace();
		} catch (SQLException e){	//sql 구문이 틀린 경우 발생
			e.printStackTrace();			
		} catch (Exception e){	//알 수 없는 예외인 경우 발생
			e.printStackTrace();
		}
		Oracle11.close(pstmt, con);
		return cnt;
	}

	public int deleteProduct(String p_code) {
		int cnt =0 ;
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.DELETE_PRODUCT);
			pstmt.setString(1, p_code);
			cnt = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) { //오라클 JDBC 클래스가 없거나 경로가 다른 경우 발생
			e.printStackTrace();
		} catch (SQLException e){	//sql 구문이 틀린 경우 발생
			e.printStackTrace();			
		} catch (Exception e){	//알 수 없는 예외인 경우 발생
			e.printStackTrace();
		}
		Oracle11.close(pstmt, con);
		return cnt;
	}
	
	public int salesProduct(String p_code, int amount){
		int cnt =0 ;
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.SALES_PRODUCT);
			pstmt.setInt(1, amount);
			pstmt.setString(2, p_code);
			cnt = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) { //오라클 JDBC 클래스가 없거나 경로가 다른 경우 발생
			e.printStackTrace();
		} catch (SQLException e){	//sql 구문이 틀린 경우 발생
			e.printStackTrace();			
		} catch (Exception e){	//알 수 없는 예외인 경우 발생
			e.printStackTrace();
		}
		Oracle11.close(pstmt, con);
		return cnt;
	}

}
