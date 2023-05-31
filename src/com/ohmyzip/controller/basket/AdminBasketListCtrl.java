package com.ohmyzip.controller.basket;

import java.io.IOException;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ohmyzip.model.BasketDAO;
import com.ohmyzip.vo.BasketVO;

@WebServlet("/AdminBasketList.do")
public class AdminBasketListCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		BasketDAO dao = new BasketDAO();
		
		ArrayList<BasketVO> basList = dao.getBasketList();
		request.setAttribute("basList", basList);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/admin/basketList.jsp");
		view.forward(request, response);
	}
}
