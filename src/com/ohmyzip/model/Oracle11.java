package com.ohmyzip.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Oracle11 {
	static String driver = "oracle.jdbc.driver.OracleDriver";
	static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	static String user = "system";
	static String pass = "1234";
	
	//공지사항 관련 SQL
	final static String NOTICE_SELECT_ALL = "select * from notice order by idx desc";
	final static String NOTICE_SELECT_ONE = "select * from notice where idx=?";
	final static String NOTICE_READCOUNT_UPDATE = "update notice set readcnt=readcnt+1 where idx=?";
	final static String INSERT_NOTICE = "insert into notice values (noti_seq.nextval, ?, ?, ?, ?, default, default)";
	final static String UPDATE_NOTICE = "update notice set title=?, content=?, file1=?, resdate=sysdate where idx=?";
	final static String UPDATE_NOTICE2 = "update notice set title=?, content=?, resdate=sysdate where idx=?";
	final static String DELETE_NOTICE = "delete from notice where idx=?";
	
	//회원 관련 SQL
	final static String USER_SELECT_ALL = "select * from user1 order by regdate desc";
	final static String USER_LOGIN =  "select * from user1 where id=?";
	final static String USER_VISIT_COUNT =  "update user1 set visited=visited+1 where id=?";
	final static String INSET_USER = "insert into user1(id, pw, name, tel, addr, email) values (?,?,?,?,?,?)";
	final static String UPDATE_USER = "update user1 set pw=?, name=?, tel=?, addr=?, email=? where id=?";
	final static String UPDATE_USER2 = "update user1 set name=?, tel=?, addr=?, email=? where id=?";
	final static String DELETE_USER = "delete from user1 where id=?";
	final static String USER_SELECT_TEL = "select tel from user1 where id=?";
	final static String UPDATE_PW_RESET = "update user1 set pw=? where id=?";
	
	//상품 관련 SQL
	final static String PRODUCT_CATENAME_SELECT = "select * from category where cate=?";
	final static String PRODUCT_SELECT_ALL = "select * from product order by cate desc";
	final static String PRODUCT_SELECT = "select * from product where p_code=?";
	final static String PRODUCT_SOLDOUT_SELECT = "select * from product where amount<=0";
	final static String PRODUCT_CATE_SELECT = "select * from product where cate=?";
	final static String PRODUCT_CATE_SELECT2 = "select * from product where cate like ?||'%'";
	final static String PRODUCT_CATE_SELECT3 = "select * from product where cate like concat(?, '%')";
	final static String FIRST_CATEGORY_SELECT = "select distinct substr(cate,1,2) as ct, categroup from category group by substr(cate,1,2), categroup order by ct";
	final static String SECOND_CATEGORY_SELECT = "select cate, catename from category where cate like ?||'%' order by cate";
	final static String PCODE_GENERATOR = "select p_code from (select * from product where cate=? order by p_code desc) where rownum = 1";
	final static String INSERT_PRODUCT = "insert into product values(?,?,?,?,?,?,?,?)";
	final static String RECEIPT_PRODUCT = "update product set amount=amount+?, price=? where p_code=?";
	final static String UPDATE_PRODUCT = "update product set amount=amount+?, price=? where p_code=?";
	final static String UPDATE_PRODUCT2 = "update product set p_name=?, p_size=?, price=?, p_com=?, amount=?, picture=? cate=? where p_code=?";
	final static String SALES_PRODUCT = "update product set amount=amount-? where p_code=?";
	final static String DELETE_PRODUCT = "delete from prodcut where p_code=?";
	
	//장바구니 관련 SQL
	final static String BASKET_SELECT_ALL = "select * from basket order by b_no desc";
	final static String BASKET_SELECT_ALL2 = "select basket.b_no as b_no, basket.id as id, user1.name as name, basket.p_code as p_code, product.p_name as p_name, basket.amount as amount, product.price as price from basket, user1, product where basket.id=user1.id and basket.p_code=product.p_code";
	final static String BASKET_SELECT_BYID = "select * from basket where id=?";
	final static String BASKET_SELECT_BYID2 = "select basket.b_no as b_no, basket.id as id, user1.name as name, basket.p_code as p_code, product.p_name as p_name, basket.amount as amount, product.price as price from basket, user1, product where basket.id=user1.id and basket.p_code=product.p_code and basket.id=?";
	final static String BASKET_SELECT_BYPRODUCT = "select * from basket where p_code=?";
	final static String BASKET_SELECT_BYBNUM = "select * from basket where b_no=?";
	final static String INSERT_BASKET = "insert into basket values (?,?,?,?)";
	final static String DELETE_BASKET = "delete from basket where b_no=?";
	final static String BNUM_GENERATOR = "select b_no from (select b_no from basket order by b_no desc) where rownum = 1";

	final static String OCODE_GENERATOR = "select o_code from (select * from buy order by o_code desc) where rownum = 1";
	final static String PNUM_GENERATOR = "select p_no from (select * from payment order by p_no desc) where rownum = 1";
	final static String ADD_SALES = "insert into buy values (?,?,?,?,?,default,?,?,?,?,?)";
	final static String ADD_PAYMENT = "insert into payment values (?,?,?,?,?,?,default)";
	final static String BUY_TRANS_BASKET = "delete from basket where b_no=?";
	final static String BYID_BUY_LIST = "select * from buy where id=? order by o_code desc";
	final static String BYID_BUY = "select * from buy where id=? and o_code=?";
	final static String PAY_LIST = "select * from payment order by p_no desc";
	final static String BYOCODE_PAY = "select * from payment where o_code=? order by p_no desc";
	final static String SALES_LIST = "select buy.o_code as o_code, buy.id as id, buy.p_code as p_code, buy.amount as amount, buy.price as price, buy.o_date as o_date, buy.d_state as d_state, buy.tel as tel, buy.d_name as d_name, buy.addr as addr, buy.d_code as d_code,payment.p_no as p_no, payment.p_type as p_type, payment.pt_no as pt_no from buy, payment where payment.o_code=buy.o_code order by buy.o_code";
	final static String SALES_INFO = "select buy.o_code as o_code, buy.id as id, buy.p_code as p_code, buy.amount as amount, buy.price as price, buy.o_date as o_date, buy.d_state as d_state, buy.tel as tel, buy.d_name as d_name, buy.addr as addr, buy.d_code as d_code,payment.p_no as p_no, payment.p_type as p_type, payment.pt_no as pt_no from buy, payment where payment.o_code=buy.o_code and buy.o_code=? order by buy.o_code";
	final static String BYID_SALES_LIST = "select buy.o_code as o_code, buy.id as id, buy.p_code as p_code, buy.amount as amount, buy.price as price, buy.o_date as o_date, buy.d_state as d_state, buy.tel as tel, buy.d_name as d_name, buy.addr as addr, buy.d_code as d_code,payment.p_no as p_no, payment.p_type as p_type, payment.pt_no as pt_no from buy, payment where payment.o_code=buy.o_code and buy.id=? order by buy.o_code";
	final static String BYID_GET_SALE = "select buy.o_code as o_code, buy.id as id, buy.p_code as p_code, buy.amount as amount, buy.price as price, buy.o_date as o_date, buy.d_state as d_state, buy.tel as tel, buy.d_name as d_name, buy.addr as addr, buy.d_code as d_code, payment.p_no as p_no, payment.p_type as p_type, payment.pt_no as pt_no from buy, payment where payment.o_code=buy.o_code and id=? and buy.o_code=?";
	final static String UPDATE_SURVEY = "update buy set d_name=?, d_code=?, d_state=? where o_code=?";
	final static String DELETE_BUY = "delete from buy where o_code=?";
	final static String DELETE_PAYMENT = "delete from payment where o_code=?";
	final static String RETURN_PRODUCT = "update product set amount=amount+? where p_code=?";
	final static String RETURN_SALES = "update buy set d_state='반품요청' where o_code=?";
	final static String OK_SALES = "update buy set d_state='구매완료' where o_code=?";
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, user, pass);
		return con;
	}
	public static void close(PreparedStatement pstmt, Connection con){
		if(pstmt!=null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void close(ResultSet rs, PreparedStatement pstmt, Connection con){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(pstmt!=null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
