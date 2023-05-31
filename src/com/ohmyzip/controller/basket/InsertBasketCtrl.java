package com.ohmyzip.controller.basket;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ohmyzip.dto.Product;
import com.ohmyzip.model.ProductDAO;

@WebServlet("/InsertBasket.do")
public class InsertBasketCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String p_code = request.getParameter("p_code");
		
		ProductDAO dao = new ProductDAO();
		Product pro = dao.getProduct(p_code);
		
		request.setAttribute("pro", pro);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/basket/insertBasket.jsp");
		view.forward(request, response);
	}
}
