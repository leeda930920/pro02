package com.ohmyzip.model;

import java.sql.Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ohmyzip.dto.Buy;
import com.ohmyzip.dto.Payment;
import com.ohmyzip.vo.SalesVO;

public class SalesDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	//판매+결제 등록 처리
		public int addSales(Buy buy, Payment pay, String b_no){
			int cnt = 0;
			try {
				con = Oracle11.getConnection();
				con.setAutoCommit(false);	//트랜잭션 처리시 오토커밋을 비활성화
				pstmt = con.prepareStatement(Oracle11.ADD_SALES);
				pstmt.setString(1, buy.getO_code());
				pstmt.setString(2, buy.getId());
				pstmt.setString(3, buy.getP_code());
				pstmt.setInt(4, buy.getAmount());
				pstmt.setInt(5, buy.getPrice());
				pstmt.setString(6, buy.getD_state());
				pstmt.setString(7, buy.getTel());
				pstmt.setString(8, buy.getD_name());
				pstmt.setString(9, buy.getAddr());
				pstmt.setString(10, buy.getD_code());
				cnt = pstmt.executeUpdate();

				pstmt = con.prepareStatement(Oracle11.ADD_PAYMENT);
				pstmt.setString(1, pay.getP_no());
				pstmt.setString(2, pay.getId());
				pstmt.setString(3, pay.getO_code());
				pstmt.setString(4, pay.getP_type());
				pstmt.setString(5, pay.getPt_no());
				pstmt.setInt(6, pay.getP_price());
				cnt = cnt + pstmt.executeUpdate();
				
				pstmt = con.prepareStatement(Oracle11.SALES_PRODUCT);
				pstmt.setInt(1, buy.getAmount());
				pstmt.setString(2, buy.getP_code());
				cnt = cnt + pstmt.executeUpdate();
				
				if(b_no!=null){
					pstmt = con.prepareStatement(Oracle11.DELETE_BASKET);
					pstmt.setString(1, b_no);
					cnt = cnt + pstmt.executeUpdate();
				}
				
				con.commit();	//수동 커밋
				con.setAutoCommit(true);	//오토커밋 활성화
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Oracle11.close(pstmt, con);
			}
			return cnt;
		}
		
		//주문(판매)코드 생성
		public String getOcodeGenerator(){
			String o_code = "";
			try {
				con = Oracle11.getConnection();
				pstmt = con.prepareStatement(Oracle11.OCODE_GENERATOR);
				rs = pstmt.executeQuery();
				if(rs.next()){
					o_code = rs.getString("o_code");
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Oracle11.close(rs, pstmt, con);
			}
			
			int tmp = Integer.parseInt(o_code) + 1;
			o_code = tmp + "";
			return o_code;
		}
		
		//결제 번호 생성
		public String getPnumGenerator(){
			String p_no = "";
			try {
				con = Oracle11.getConnection();
				pstmt = con.prepareStatement(Oracle11.PNUM_GENERATOR);
				rs = pstmt.executeQuery();
				if(rs.next()){
					p_no = rs.getString("p_no");
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Oracle11.close(rs, pstmt, con);
			}
			
			int tmp = Integer.parseInt(p_no) + 1;
			p_no = tmp + "";
			return p_no;
		}
		
		//관리자의 판매+결제 전체 목록 로딩
		public ArrayList<SalesVO> getSalesList(){
			ArrayList<SalesVO> salesList = new ArrayList<SalesVO>();
			try {
				con = Oracle11.getConnection();
				pstmt = con.prepareStatement(Oracle11.SALES_LIST);
				rs = pstmt.executeQuery();
				while(rs.next()){
					SalesVO sale = new SalesVO();
					sale.setO_code(rs.getString("o_code"));
					sale.setId(rs.getString("id"));
					sale.setP_code(rs.getString("p_code"));
					sale.setAmount(rs.getInt("amount"));
					sale.setPrice(rs.getInt("price"));
					sale.setO_date(rs.getString("o_date"));
					sale.setD_state(rs.getString("d_state"));
					sale.setTel(rs.getString("tel"));
					sale.setD_name(rs.getString("d_name"));
					sale.setAddr(rs.getString("addr"));
					sale.setD_code(rs.getString("d_code"));
					sale.setP_no(rs.getString("p_no"));
					sale.setP_type(rs.getString("p_type"));
					sale.setPt_no(rs.getString("pt_no"));
					salesList.add(sale);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Oracle11.close(rs, pstmt, con);
			}
			return salesList;
		}
		
		//관리자의 특정 판매 데이터 로딩 
		public SalesVO getSales(String o_code){
			SalesVO sale = new SalesVO();
			try {
				con = Oracle11.getConnection();
				pstmt = con.prepareStatement(Oracle11.SALES_INFO);
				pstmt.setString(1, o_code);
				rs = pstmt.executeQuery();
				if(rs.next()){
					sale.setO_code(rs.getString("o_code"));
					sale.setId(rs.getString("id"));
					sale.setP_code(rs.getString("p_code"));
					sale.setAmount(rs.getInt("amount"));
					sale.setPrice(rs.getInt("price"));
					sale.setO_date(rs.getString("o_date"));
					sale.setD_state(rs.getString("d_state"));
					sale.setTel(rs.getString("tel"));
					sale.setD_name(rs.getString("d_name"));
					sale.setAddr(rs.getString("addr"));
					sale.setD_code(rs.getString("d_code"));
					sale.setP_no(rs.getString("p_no"));
					sale.setP_type(rs.getString("p_type"));
					sale.setPt_no(rs.getString("pt_no"));
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Oracle11.close(rs, pstmt, con);
			}
			return sale;
		}
		
		//특정 사용자의 구매 목록 로딩
		public ArrayList<SalesVO> getByIdSalesList(String id){
			ArrayList<SalesVO> salesList = new ArrayList<SalesVO>();
			try {
				con = Oracle11.getConnection();
				pstmt = con.prepareStatement(Oracle11.BYID_SALES_LIST);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				while(rs.next()){
					SalesVO sale = new SalesVO();
					sale.setO_code(rs.getString("o_code"));
					sale.setId(rs.getString("id"));
					sale.setP_code(rs.getString("p_code"));
					sale.setAmount(rs.getInt("amount"));
					sale.setPrice(rs.getInt("price"));
					sale.setO_date(rs.getString("o_date"));
					sale.setD_state(rs.getString("d_state"));
					sale.setTel(rs.getString("tel"));
					sale.setD_name(rs.getString("d_name"));
					sale.setAddr(rs.getString("addr"));
					sale.setD_code(rs.getString("d_code"));
					sale.setP_no(rs.getString("p_no"));
					sale.setP_type(rs.getString("p_type"));
					sale.setPt_no(rs.getString("pt_no"));
					salesList.add(sale);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Oracle11.close(rs, pstmt, con);
			}
			return salesList;
		}
		
		//특정 사용자의 특정 판매 데이터 로딩
		public SalesVO getByIdSales(String id, String o_code){
			SalesVO sale = new SalesVO();
			try {
				con = Oracle11.getConnection();
				pstmt = con.prepareStatement(Oracle11.BYID_SALES_LIST);
				pstmt.setString(1, id);
				pstmt.setString(2, o_code);
				rs = pstmt.executeQuery();
				while(rs.next()){
					sale.setO_code(rs.getString("o_code"));
					sale.setId(rs.getString("id"));
					sale.setP_code(rs.getString("p_code"));
					sale.setAmount(rs.getInt("amount"));
					sale.setPrice(rs.getInt("price"));
					sale.setO_date(rs.getString("o_date"));
					sale.setD_state(rs.getString("d_state"));
					sale.setTel(rs.getString("tel"));
					sale.setD_name(rs.getString("d_name"));
					sale.setAddr(rs.getString("addr"));
					sale.setD_code(rs.getString("d_code"));
					sale.setP_no(rs.getString("p_no"));
					sale.setP_type(rs.getString("p_type"));
					sale.setPt_no(rs.getString("pt_no"));
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Oracle11.close(rs, pstmt, con);
			}
			return sale;
		}
		
		//판매 정보 목록만 로딩
		public ArrayList<Buy> getByIdBuyList(String id){
			ArrayList<Buy> buyList = new ArrayList<Buy>();
			try {
				con = Oracle11.getConnection();
				pstmt = con.prepareStatement(Oracle11.BYID_BUY_LIST);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				while(rs.next()){
					Buy buy = new Buy();
					buy.setO_code(rs.getString("o_code"));
					buy.setId(rs.getString("id"));
					buy.setP_code(rs.getString("p_code"));
					buy.setAmount(rs.getInt("amount"));
					buy.setPrice(rs.getInt("price"));
					buy.setO_date(rs.getString("o_date"));
					buy.setD_state(rs.getString("d_state"));
					buy.setTel(rs.getString("tel"));
					buy.setD_name(rs.getString("d_name"));
					buy.setAddr(rs.getString("addr"));
					buy.setD_code(rs.getString("d_code"));
					buyList.add(buy);
				}
			} catch (ClassNotFoundException e) {
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Oracle11.close(rs, pstmt, con);
			}
			return buyList;
		}
		
		//모든 결제 정보 목록만 로딩
		public ArrayList<Payment> getByPayList(){
			ArrayList<Payment> payList = new ArrayList<Payment>();
			try {
				con = Oracle11.getConnection();
				pstmt = con.prepareStatement(Oracle11.PAY_LIST);
				rs = pstmt.executeQuery();
				while(rs.next()){
					Payment pay = new Payment();
					pay.setP_no(rs.getString("p_no"));
					pay.setId(rs.getString("id"));
					pay.setO_code(rs.getString("p_code"));
					pay.setP_type(rs.getString("p_type"));
					pay.setPt_no(rs.getString("pt_no"));
					pay.setP_price(rs.getInt("p_price"));
					pay.setP_date(rs.getString("p_date"));
					payList.add(pay);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Oracle11.close(rs, pstmt, con);
			}
			return payList;
		}
		
		
		//특정 사용자의 특정 결제 정보
		public Payment getByIdPay(String o_code){
			Payment pay = new Payment();
			try {
				con = Oracle11.getConnection();
				pstmt = con.prepareStatement(Oracle11.BYOCODE_PAY);
				pstmt.setString(1, o_code);
				rs = pstmt.executeQuery();
				if(rs.next()){
					pay.setP_no(rs.getString("p_no"));
					pay.setId(rs.getString("id"));
					pay.setO_code(rs.getString("o_code"));
					pay.setP_type(rs.getString("p_type"));
					pay.setPt_no(rs.getString("pt_no"));
					pay.setP_price(rs.getInt("pprice"));
					pay.setP_date(rs.getString("pdate"));
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Oracle11.close(rs, pstmt, con);
			}
			return pay;
		}
		
		public int surveyUpdate(Buy buy){
			int cnt = 0;
			try {
				con = Oracle11.getConnection();
				pstmt = con.prepareStatement(Oracle11.UPDATE_SURVEY);
				pstmt.setString(1, buy.getD_name());
				pstmt.setString(2, buy.getD_code());
				pstmt.setString(3, buy.getD_state());
				pstmt.setString(4, buy.getO_code());
				cnt = pstmt.executeUpdate();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Oracle11.close(pstmt, con);
			}
			return cnt;
		}

		public int cancleSales(String o_code, String p_code, int amount) {
			int cnt = 0;
			try {
				con = Oracle11.getConnection();
				con.setAutoCommit(false);	//트랜잭션 처리시 오토커밋을 비활성화
				pstmt = con.prepareStatement(Oracle11.DELETE_BUY);
				pstmt.setString(1, o_code);
				cnt = pstmt.executeUpdate();

				pstmt = con.prepareStatement(Oracle11.DELETE_PAYMENT);
				pstmt.setString(1, o_code);
				cnt = cnt + pstmt.executeUpdate();
				
				pstmt = con.prepareStatement(Oracle11.RETURN_PRODUCT);
				pstmt.setInt(1, amount);
				pstmt.setString(2, p_code);
				cnt = cnt + pstmt.executeUpdate();
				
				con.commit();	//수동 커밋
				con.setAutoCommit(true);	//오토커밋 활성화
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Oracle11.close(pstmt, con);
			}
			return cnt;
		}

		public int returnSales(String o_code) {
			int cnt = 0;
			try {
				con = Oracle11.getConnection();
				pstmt = con.prepareStatement(Oracle11.RETURN_SALES);
				pstmt.setString(1, o_code);
				cnt = pstmt.executeUpdate();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Oracle11.close(pstmt, con);
			}
			return cnt;
		}

		public int okSales(String o_code) {
			int cnt = 0;
			try {
				con = Oracle11.getConnection();
				pstmt = con.prepareStatement(Oracle11.OK_SALES);
				pstmt.setString(1, o_code);
				cnt = pstmt.executeUpdate();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Oracle11.close(pstmt, con);
			}
			return cnt;
		}
	}
