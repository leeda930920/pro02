package com.ohmyzip.controller.product;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ohmyzip.model.ProductDAO;


@WebServlet("/ReceiptProductPro.do")
public class ReceiptProductProCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String p_code = request.getParameter("p_code");
		int amount = Integer.parseInt(request.getParameter("amount"));
		int price = Integer.parseInt(request.getParameter("price"));
		
		ProductDAO dao = new ProductDAO();
		int cnt = dao.receiptProduct(p_code, amount, price);
		
		if(cnt==0){ //입고 처리 실패
			String msg = "상품을 입고하지 못했습니다.";
			request.setAttribute("msg", msg);
			
			//디스패치로 view를 생성하여 noticeList.jsp로 요청 받은 notiList를 포워드
			RequestDispatcher view = request.getRequestDispatcher("ReceiptProduct.do?p_code="+p_code);
			view.forward(request, response);
		} else { //상품등록 성공시 목록으로 가기
			response.sendRedirect("ProductList.do");
		}
	}

}
