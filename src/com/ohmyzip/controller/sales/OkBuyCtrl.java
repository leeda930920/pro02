package com.ohmyzip.controller.sales;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ohmyzip.model.SalesDAO;

@WebServlet("/OkBuy.do")
public class OkBuyCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String o_code = request.getParameter("o_code");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("sid");
		
		SalesDAO dao = new SalesDAO();
		int cnt = dao.okSales(o_code);
		
		if(cnt>0){
			System.out.println("구매 완료 성공");
			response.sendRedirect("MySalesList.do?id="+id);
		} else {
			System.out.println("구매 완료 실패");
			response.sendRedirect("MySalesList.do?id="+id);
		}
	}
}
