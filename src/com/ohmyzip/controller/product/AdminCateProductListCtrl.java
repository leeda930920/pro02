package com.ohmyzip.controller.product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ohmyzip.dto.Product;
import com.ohmyzip.model.ProductDAO;

@WebServlet("/AdminCateProductList.do")
public class AdminCateProductListCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cate = request.getParameter("cate");
		ProductDAO dao = new ProductDAO();
		ArrayList<Product> proList = new ArrayList<Product>();
		HashMap<String, String> cateMap = new HashMap<String, String>();
		if(cate==null){
			proList = dao.getProductList();
			cate = "0101";
			cateMap.put("catename", "전체");
		} else {
			proList = dao.getAdminCateProductList(cate);
			cateMap = dao.getCategory(cate);
		}
		
		request.setAttribute("proList", proList);
		request.setAttribute("cateMap", cateMap);
		
		//디스패치로 view를 생성하여 proList.jsp로 요청 받은 proList를 포워드
		RequestDispatcher view = request.getRequestDispatcher("/product/proList.jsp");
		view.forward(request, response);	
	}

}
