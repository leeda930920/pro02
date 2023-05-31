package com.ohmyzip.controller.sales;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ohmyzip.dto.Buy;
import com.ohmyzip.dto.Payment;
import com.ohmyzip.model.SalesDAO;

@WebServlet("/AddPayment.do")
public class AddPaymentCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int amount = Integer.parseInt(request.getParameter("amount"));
		String id = request.getParameter("id");
		String p_code = request.getParameter("p_code");
		String b_no = request.getParameter("b_no");
		String cate = request.getParameter("cate");
		
		Payment pay = new Payment();
		Buy buy = new Buy();
		SalesDAO dao = new SalesDAO();
		
		buy.setO_code(dao.getOcodeGenerator());
		buy.setId(id);
		buy.setP_code(p_code);
		buy.setAmount(amount);
		buy.setPrice(Integer.parseInt(request.getParameter("payamount")));
		buy.setTel(request.getParameter("tel"));
		buy.setAddr(request.getParameter("address1")+" "+request.getParameter("address2"));
		buy.setD_state("배송전");
		buy.setD_name("");
		buy.setD_code("");

		pay.setP_no(dao.getPnumGenerator());
		pay.setId(request.getParameter("id"));
		pay.setO_code(buy.getP_code());
		pay.setP_type(request.getParameter("p_type"));
		pay.setPt_no(request.getParameter("pt_no"));
		pay.setP_price(Integer.parseInt(request.getParameter("payamount")));
		
		int cnt = dao.addSales(buy, pay, b_no);
		if(cnt>=3){
			System.out.println("트랜잭션 처리 성공");
			response.sendRedirect("ProductList.do?cate="+cate);
		} else {
			System.out.println("트랜잭션 처리 실패");
			response.sendRedirect("AddSales.do?b_no="+b_no+"&p_code="+p_code+"&amount="+amount+"&id="+id);
		}
	}
}
