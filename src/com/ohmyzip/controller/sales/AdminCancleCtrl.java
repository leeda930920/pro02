package com.ohmyzip.controller.sales;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ohmyzip.model.SalesDAO;
import com.ohmyzip.vo.SalesVO;

@WebServlet("/AdminCancle.do")
public class AdminCancleCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String o_code = request.getParameter("o_code");
		
		SalesDAO dao = new SalesDAO();
		SalesVO sale = dao.getSales(o_code);
		String p_code = sale.getP_code();
		int amount = sale.getAmount();
		
		int cnt = dao.cancleSales(o_code, p_code, amount);
		
		if(cnt>=2){
			System.out.println("주문 취소 성공");
			response.sendRedirect("Survey.do");
		} else {
			System.out.println("주문 취소 실패");
			response.sendRedirect("SurveyLoad.do?o_code="+o_code);
		}
	}
}
