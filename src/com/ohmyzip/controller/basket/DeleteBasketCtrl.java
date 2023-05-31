package com.ohmyzip.controller.basket;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ohmyzip.dto.Basket;
import com.ohmyzip.model.BasketDAO;

@WebServlet("/DeleteBasket.do")
public class DeleteBasketCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String b_no = request.getParameter("b_no");
		
		BasketDAO dao = new BasketDAO();
		Basket bas = dao.getBasketDetail(b_no);
		String id = bas.getId();
		int cnt = dao.deleteBasket(b_no);
		if(cnt==1){
			response.sendRedirect("MyBasket.do?id="+id);
		} else {
			response.sendRedirect("MyBasket.do?id="+id);
		}
	}
}
